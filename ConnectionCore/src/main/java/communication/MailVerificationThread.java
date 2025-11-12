/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import Bussines.BAlumno;
import Bussines.BAsistencia;
import Bussines.BHorario;
import Bussines.BInforme_clase;
import Bussines.BInscripcion;
import Bussines.BLicencia;
import Bussines.BPago;
import Bussines.BReprogramacion;
import Bussines.BServicio;
import Bussines.BTutor;
import Bussines.BTutorHorario;
import Bussines.BVenta;
import Interfaces.IEmailEventListener;
import Interpreter.analex.utils.Token;
import Interpreter.analex.interfaces.ITokenEventListener;
import Interpreter.analexx.Interpreter;
import Interpreter.events.TokenEvent;
import Bussines.Bususario;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.sasl.AuthenticationException;
import utils.Command;
import utils.Email;
import utils.Extractor;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.FlagTerm;
import jakarta.mail.search.SearchTerm;
import java.sql.SQLException;
import java.util.Properties;
import utils.HtmlTableGenerator;

/**
 *
 * @author Rafa
 */
public class MailVerificationThread implements Runnable{
    private final static int PORT_POP =110;
    private final static String HOST= "mail.tecnoweb.org.bo";
    private final static String USER= "grupo16sa";
    private final static String PASSWORD= "grup016grup016*"; //mcewvaahflvqhafw
    
    private IEmailEventListener emailEventListener;
    private Bususario busuario;
    private BTutor btutor;
    private BAlumno balumno;
    private BHorario bhorario;
    private BTutorHorario btutorhorario;
    private BInscripcion binscripcion;
    private BServicio bservicio;
    private BInforme_clase binformeClase;
    private BAsistencia basistencia;
    private BLicencia blicencia;
    private BReprogramacion breprogramacion;
    private BVenta bventa;
    private BPago bpago;
    
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;
    

    public void setEmailEventListener(IEmailEventListener emailEventListener) {
        this.emailEventListener = emailEventListener;
    }

    public IEmailEventListener getEmailEventListener() {
        return emailEventListener;
    }
    
    public MailVerificationThread(){
        socket = null;
        input = null;
        output = null;
        busuario = new Bususario();
        btutor= new BTutor();
        balumno = new BAlumno();
        bhorario = new BHorario();      
        btutorhorario = new BTutorHorario();
        binscripcion = new BInscripcion();
        binformeClase= new BInforme_clase();
        basistencia = new BAsistencia();
        blicencia = new BLicencia();
        breprogramacion = new BReprogramacion();
        bventa = new BVenta();
        bpago = new BPago();
      
    }

    @Override
    public void run() {
        while (true) {
            Store store = null;
            Folder inbox = null;
            try {
                Properties properties = new Properties();
                properties.put("mail.store.protocol", "pop3");
                properties.put("mail.pop3.host", HOST);
                properties.put("mail.pop3.port", "110");
                properties.put("mail.pop3.ssl.enable", "false");
                properties.put("mail.pop3.auth", "true");
                properties.put("mail.pop3.connectiontimeout", "5000");
                properties.put("mail.pop3.timeout", "5000");

                Session session = Session.getInstance(properties, null);
                store = session.getStore("pop3");
                store.connect(HOST, USER, PASSWORD);

                System.out.println("***************Conexion establecida**********************");

                inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_WRITE);

                // POP3: Obtener TODOS los mensajes (no hay búsqueda por flags)
                Message[] messages = inbox.getMessages();

                List<Email> emails = new ArrayList<>();
                int count = messages.length;

                if (count > 0) {
                    System.out.println("Correos encontrados: " + count);

                    for (Message msg : messages) {
                        Email email = new Email();

                        if (msg.getFrom() != null && msg.getFrom().length > 0) {
                            email.setFrom(msg.getFrom()[0].toString());
                        }

                        email.setSubject(msg.getSubject());

                        // PROCESAR EL COMANDO DEL CORREO
                        processEmailCommand(email);

                        emails.add(email);

                        // IMPORTANTE: Marcar para ELIMINAR después de procesar
                        msg.setFlag(Flags.Flag.DELETED, true);
                        System.out.println("✓ Correo procesado y marcado para eliminar");
                    }

                    System.out.println("Total procesados: " + emails.size());

                    if (emailEventListener != null) {
                        emailEventListener.onReceiveEmailEvent(emails);
                    }
                } else {
                    System.out.println("No hay correos nuevos...");
                }

                // Cerrar con 'true' para aplicar los cambios (eliminar mensajes)
                inbox.close(true);  // ← MUY IMPORTANTE: true para confirmar eliminaciones
                store.close();

                System.out.println("***************Conexion Terminada**********************");

                Thread.sleep(10000);  // Aumentado a 10 segundos

            } catch (Exception ex) {
                System.err.println("Error en el ciclo de verificación:");
                Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ie) {
                    Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ie);
                }
            } finally {
                try {
                    if (inbox != null && inbox.isOpen()) {
                        inbox.close(true);  // true para confirmar eliminaciones
                    }
                    if (store != null && store.isConnected()) {
                        store.close();
                    }
                } catch (MessagingException e) {
                    Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }


        private void processEmailCommand(Email email) {
        String comando = email.getSubject(); // El comando viene en el asunto
        String sender = email.getFrom();
        
        System.out.println("Procesando comando: " + comando);
        System.out.println("De: " + sender);
        
        Interpreter interpreter = new Interpreter(comando, sender);
        interpreter.setListener(new ITokenEventListener() {
            
        @Override
        public void usuario(TokenEvent event) {
            try {
                System.out.println("=== COMANDO USUARIO ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    busuario.guardar(event.getParams());
                    System.out.println("✓ Usuario agregado exitosamente");

                } else if (event.getAction() == Token.MODIFY) {
                    busuario.modificar(event.getParams());
                    System.out.println("✓ Usuario modificado");

                } else if (event.getAction() == Token.DELETE) {
                    busuario.eliminar(event.getParams());
                    System.out.println("✓ Usuario eliminado");

                } else if (event.getAction() == Token.GET) {
                    // LISTAR USUARIOS
                    ArrayList<String[]> usuarios = busuario.listar();
                    String htmlTable = HtmlTableGenerator.generateUsuarioTable(usuarios);

                    // Enviar correo con la tabla
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Usuarios",
                        htmlTable
                    );
                    System.out.println("✓ Lista de usuarios enviada por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
            }
        }

        @Override
        public void alumno(TokenEvent event) {
            try {
                System.out.println("=== COMANDO ALUMNO ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    balumno.guardar(event.getParams());
                    System.out.println("✓ Alumno agregado exitosamente");

                } else if (event.getAction() == Token.MODIFY) {
                    balumno.modificar(event.getParams());
                    System.out.println("✓ Alumno modificado");

                } else if (event.getAction() == Token.DELETE) {
                    balumno.eliminar(event.getParams());
                    System.out.println("✓ Alumno eliminado");

                } else if (event.getAction() == Token.GET) {
                    // LISTAR ALUMNOS
                    ArrayList<String[]> alumnos = balumno.listar();
                    String htmlTable = HtmlTableGenerator.generateAlumnoTable(alumnos);

                    // Enviar correo con la tabla
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Alumnos",
                        htmlTable
                    );
                    System.out.println("✓ Lista de alumnos enviada por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
            }
        }

        @Override
        public void tutor(TokenEvent event) {
            try {
                System.out.println("=== COMANDO TUTOR ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    btutor.guardar(event.getParams());
                    System.out.println("✓ Tutor agregado exitosamente");

                } else if (event.getAction() == Token.MODIFY) {
                    btutor.modificar(event.getParams());
                    System.out.println("✓ Tutor modificado");

                } else if (event.getAction() == Token.DELETE) {
                    btutor.eliminar(event.getParams());
                    System.out.println("✓ Tutor eliminado");

                } else if (event.getAction() == Token.GET) {
                    // LISTAR TUTORES
                    ArrayList<String[]> tutores = btutor.listar();
                    String htmlTable = HtmlTableGenerator.generateTutorTable(tutores);

                    // Enviar correo con la tabla
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Tutores",
                        htmlTable
                    );
                    System.out.println("✓ Lista de tutores enviada por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
            }
        }
        
        @Override
        public void horario(TokenEvent event) {
            try {
                System.out.println("=== COMANDO HORARIO ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    bhorario.guardar(event.getParams());
                    System.out.println("✓ Horario agregado exitosamente");

                } else if (event.getAction() == Token.MODIFY) {
                    bhorario.modificar(event.getParams());
                    System.out.println("✓ Horario modificado");

                } else if (event.getAction() == Token.DELETE) {
                    bhorario.eliminar(event.getParams());
                    System.out.println("✓ Horario eliminado");

                } else if (event.getAction() == Token.GET) {
                    // LISTAR HORARIOS
                    ArrayList<String[]> horarios = bhorario.listar();
                    String htmlTable = HtmlTableGenerator.generateHorarioTable(horarios);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Horarios",
                        htmlTable
                    );
                    System.out.println("✓ Lista de horarios enviada por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        
        @Override
        public void inscripcion(TokenEvent event) {
                    try {
                            System.out.println("=== COMANDO INSCRIPCION ===");
                            System.out.println(event);

                            if (event.getAction() == Token.ADD) {
                                int inscripcionId = binscripcion.guardar(event.getParams());
                                System.out.println("✓ Inscripción creada exitosamente. ID: " + inscripcionId);

                            } else if (event.getAction() == Token.MODIFY) {
                                // Modificar inscripción
                                // Params: id, estado, observaciones
                                binscripcion.modificar(event.getParams());
                                System.out.println("✓ Inscripción modificada");

                            } else if (event.getAction() == Token.DELETE) {
                                // Eliminar inscripción
                                binscripcion.eliminar(event.getParams());
                                System.out.println("✓ Inscripción eliminada");

                            } else if (event.getAction() == Token.GET) {
                                // Listar inscripciones
                                ArrayList<String[]> inscripciones = binscripcion.listar();
                                String htmlTable = HtmlTableGenerator.generateInscripcionTable(inscripciones);

                                SendEmailThread.sendEmail(
                                    event.getSender(),
                                    "Lista de Inscripciones",
                                    htmlTable
                                );
                                System.out.println("✓ Lista de inscripciones enviada por correo");
                            } else if (event.getAction() == Token.LIST_IALUMNO) {
 
                                int alumnoId = Integer.parseInt(event.getParams().get(0));
                                ArrayList<String[]> inscripciones = binscripcion.listarPorAlumno(alumnoId);

                                // Obtener nombre del alumno
                                String[] alumno = balumno.ver(alumnoId);
                                String nombreAlumno = alumno != null ? alumno[1] + " " + alumno[2] : "Desconocido";

                                String htmlTable = HtmlTableGenerator.generateInscripcionesPorAlumnoTable(inscripciones, nombreAlumno);

                                SendEmailThread.sendEmail(
                                    event.getSender(),
                                    "Inscripciones de " + nombreAlumno,
                                    htmlTable
                                );
                                System.out.println("✓ Inscripciones del alumno enviadas por correo");

                            } else if (event.getAction() == Token.LIST_ITUTOR) {

                                int tutorId = Integer.parseInt(event.getParams().get(0));
                                ArrayList<String[]> inscripciones = binscripcion.listarPorTutor(tutorId);

                      
                                String[] tutor = btutor.ver(tutorId);
                                String nombreTutor = tutor != null ? tutor[1] + " " + tutor[2] : "Desconocido";

                                String htmlTable = HtmlTableGenerator.generateInscripcionesPorTutorTable(inscripciones, nombreTutor);

                                SendEmailThread.sendEmail(
                                    event.getSender(),
                                    "Alumnos de " + nombreTutor,
                                    htmlTable
                                );
                                System.out.println("✓ Inscripciones del tutor enviadas por correo");

                            } else if (event.getAction() == Token.LIST_ISERVICIO) {
 
                                int servicioId = Integer.parseInt(event.getParams().get(0));
                                ArrayList<String[]> inscripciones = binscripcion.listarPorServicio(servicioId);

                                // Obtener nombre del servicio
                                String[] servicio = bservicio.ver(servicioId);
                                String nombreServicio = servicio != null ? servicio[1] : "Desconocido";

                                String htmlTable = HtmlTableGenerator.generateInscripcionesPorServicioTable(inscripciones, nombreServicio);

                                SendEmailThread.sendEmail(
                                    event.getSender(),
                                    "Inscripciones - " + nombreServicio,
                                    htmlTable
                                );
                                System.out.println("✓ Inscripciones del servicio enviadas por correo");
                            } else {
                                System.err.println("✗ Acción no válida");
                            }
                        } catch (SQLException e) {
                            System.err.println("✗ Error: " + e.getMessage());
                            e.printStackTrace();
                        }
        }
        
        @Override
        public void tutor_horario(TokenEvent event) {
               try {
                    System.out.println("=== COMANDO TUTOR-HORARIO ===");
                    System.out.println(event);

                    if (event.getAction() == Token.ADD) {
                        // Asignar horario a tutor
                        btutorhorario.guardar(event.getParams());
                        System.out.println("✓ Horario asignado al tutor exitosamente");

                    } else if (event.getAction() == Token.DELETE) {
                        // Desasignar horario de tutor
                        btutorhorario.eliminar(event.getParams());
                        System.out.println("✓ Horario desasignado del tutor exitosamente");

                    } else if (event.getAction() == Token.GET) {
                        // Listar todas las asignaciones
                        ArrayList<String[]> asignaciones = btutorhorario.listar();
                        System.out.println(asignaciones);
                        String htmlTable = HtmlTableGenerator.generateTutorHorarioTable(asignaciones);

                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Asignaciones Tutor-Horario",
                            htmlTable
                        );
                        System.out.println("✓ Lista de asignaciones enviada por correo");

                    } else {
                        System.err.println("✗ Acción no válida");
                    }
                } catch (SQLException e) {
                    System.err.println("✗ Error: " + e.getMessage());
                    e.printStackTrace();
                }
        }
        
            @Override
            public void servicio(TokenEvent event) {
                try {
                    System.out.println("=== COMANDO SERVICIO ===");
                    System.out.println(event);

                    if (event.getAction() == Token.GET) {
                        // Listar todos los servicios
                        ArrayList<String[]> servicios = bservicio.listar();
                        String htmlTable = HtmlTableGenerator.generateServicioTable(servicios);

                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Lista de Servicios",
                            htmlTable
                        );
                        System.out.println("✓ Lista de servicios enviada por correo");

                    } else if (event.getAction() == Token.ACTIVATE) {

                        bservicio.activar(event.getParams());
                        
                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Servicio Activado",
                            "<html><body><h2>✓ Servicio activado exitosamente</h2>" +
                            "<p>ID del servicio: " + event.getParams().get(0) + "</p></body></html>"
                        );
                        System.out.println("✓ Servicio activado exitosamente");

                    } else if (event.getAction() == Token.DEACTIVATE) {
 
                        bservicio.desactivar(event.getParams());
                        
                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Servicio Desactivado",
                            "<html><body><h2>✓ Servicio desactivado exitosamente</h2>" +
                            "<p>ID del servicio: " + event.getParams().get(0) + "</p></body></html>"
                        );
                        System.out.println("✓ Servicio desactivado exitosamente");
                    /*
                    } else if (event.getAction() == Token.LIST_BY) {
                        // Listar alumnos por servicio
                        // Params: [0]id_servicio
                        int servicioId = Integer.parseInt(event.getParams().get(0));
                        
                        // Obtener nombre del servicio
                        String[] servicio = bservicio.ver(servicioId);
                        String nombreServicio = servicio != null ? servicio[1] : "Desconocido";
                        
                        // Obtener alumnos
                        ArrayList<String[]> alumnos = bservicio.listarAlumnosPorServicio(servicioId);
                        String htmlTable = HtmlTableGenerator.generateAlumnosPorServicioTable(alumnos, nombreServicio);

                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Alumnos Inscritos en " + nombreServicio,
                            htmlTable
                        );
                        System.out.println("✓ Lista de alumnos por servicio enviada por correo");
                        
                    } else if (event.getAction() == Token.STATS) {
                        // Ver estadísticas de servicio
                        // Params: [0]id_servicio
                        int servicioId = Integer.parseInt(event.getParams().get(0));
                        String[] stats = bservicio.obtenerEstadisticas(servicioId);
                        
                        String htmlStats = HtmlTableGenerator.generateEstadisticasServicio(stats);

                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Estadísticas del Servicio",
                            htmlStats
                        );
                        System.out.println("✓ Estadísticas del servicio enviadas por correo");
 */
                    } else {
                        System.err.println("✗ Acción no válida");
                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Error en Comando",
                            "<html><body><h2>✗ Error</h2>" +
                            "<p>Acción no válida para servicio</p>" +
                            "<p>Acciones disponibles: get, activar, desactivar, listarpor, stats</p></body></html>"
                        );
                    }
                } catch (SQLException e) {
                    System.err.println("✗ Error: " + e.getMessage());
                    e.printStackTrace();
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Error al Procesar Servicio",
                        "<html><body><h2>✗ Error en Base de Datos</h2>" +
                        "<p>" + e.getMessage() + "</p></body></html>"
                    );
                } catch (Exception e) {
                    System.err.println("✗ Error inesperado: " + e.getMessage());
                    e.printStackTrace();
                }
            }
       @Override
        public void informeclase(TokenEvent event) {
                try {
                    System.out.println("=== COMANDO INFORME_CLASE ===");
                    System.out.println(event);
                    if (event.getAction() == Token.ADD) {
                        binformeClase.guardar(event.getParams());
                        System.out.println("✓ Informe de clase agregado exitosamente");
                    } else if (event.getAction() == Token.MODIFY) {
                        binformeClase.modificar(event.getParams());
                        System.out.println("✓ Informe de clase modificado");
                    } else if (event.getAction() == Token.DELETE) {
                        binformeClase.eliminar(event.getParams());
                        System.out.println("✓ Informe de clase eliminado");
                    } else if (event.getAction() == Token.GET) {
                        // LISTAR INFORMES DE CLASE
                        ArrayList<String[]> informes = binformeClase.listar();
                        String htmlTable = HtmlTableGenerator.generateInformeClaseTable(informes);
                        SendEmailThread.sendEmail(
                            event.getSender(),
                            "Lista de Informes de Clase",
                            htmlTable
                        );
                        System.out.println("✓ Lista de informes enviada por correo");
                    } else {
                        System.err.println("✗ Acción no válida");
                    }
                } catch (SQLException e) {
                    System.err.println("✗ Error: " + e.getMessage());
                    e.printStackTrace();
                }
        }
        
        @Override
        public void asistencia(TokenEvent event) {
            try {
                System.out.println("=== COMANDO ASISTENCIA ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    int asistenciaId = basistencia.guardar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Asistencia Registrada",
                        "<html><body><h2>✓ Asistencia registrada exitosamente</h2>" +
                        "<p>ID de asistencia: " + asistenciaId + "</p></body></html>"
                    );
                    System.out.println("✓ Asistencia agregada exitosamente");

                } else if (event.getAction() == Token.MODIFY) {
                    basistencia.modificar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Asistencia Modificada",
                        "<html><body><h2>✓ Asistencia modificada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Asistencia modificada");

                } else if (event.getAction() == Token.DELETE) {
                    basistencia.eliminar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Asistencia Eliminada",
                        "<html><body><h2>✓ Asistencia eliminada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Asistencia eliminada");

                } else if (event.getAction() == Token.GET) {
                    ArrayList<String[]> asistencias = basistencia.listar();
                    String htmlTable = HtmlTableGenerator.generateAsistenciaTable(asistencias);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Asistencias",
                        htmlTable
                    );
                    System.out.println("✓ Lista de asistencias enviada por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
                e.printStackTrace();
                
                SendEmailThread.sendEmail(
                    event.getSender(),
                    "Error al Procesar Asistencia",
                    "<html><body><h2>✗ Error en Base de Datos</h2>" +
                    "<p>" + e.getMessage() + "</p></body></html>"
                );
            }
        }

        @Override
        public void licencia(TokenEvent event) {
            try {
                System.out.println("=== COMANDO LICENCIA ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    int licenciaId = blicencia.guardar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Licencia Solicitada",
                        "<html><body><h2>✓ Licencia solicitada exitosamente</h2>" +
                        "<p>ID de licencia: " + licenciaId + "</p>" +
                        "<p>Estado: Pendiente de aprobación</p></body></html>"
                    );
                    System.out.println("✓ Licencia agregada exitosamente");

                } else if (event.getAction() == Token.MODIFY) {
                    blicencia.modificar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Licencia Modificada",
                        "<html><body><h2>✓ Licencia modificada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Licencia modificada");

                } else if (event.getAction() == Token.DELETE) {
                    blicencia.eliminar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Licencia Eliminada",
                        "<html><body><h2>✓ Licencia eliminada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Licencia eliminada");

                } else if (event.getAction() == Token.GET) {
                    ArrayList<String[]> licencias = blicencia.listar();
                    String htmlTable = HtmlTableGenerator.generateLicenciaTable(licencias);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Licencias",
                        htmlTable
                    );
                    System.out.println("✓ Lista de licencias enviada por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
                e.printStackTrace();
                
                SendEmailThread.sendEmail(
                    event.getSender(),
                    "Error al Procesar Licencia",
                    "<html><body><h2>✗ Error en Base de Datos</h2>" +
                    "<p>" + e.getMessage() + "</p></body></html>"
                );
            }
        }

        @Override
        public void reprogramacion(TokenEvent event) {
            try {
                System.out.println("=== COMANDO REPROGRAMACION ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    int reprogramacionId = breprogramacion.guardar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Reprogramación Creada",
                        "<html><body><h2>✓ Reprogramación creada exitosamente</h2>" +
                        "<p>ID de reprogramación: " + reprogramacionId + "</p>" +
                        "<p>Estado: Programada</p></body></html>"
                    );
                    System.out.println("✓ Reprogramación agregada exitosamente");

                } else if (event.getAction() == Token.MODIFY) {
                    breprogramacion.modificar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Reprogramación Modificada",
                        "<html><body><h2>✓ Reprogramación modificada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Reprogramación modificada");

                } else if (event.getAction() == Token.DELETE) {
                    breprogramacion.eliminar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Reprogramación Eliminada",
                        "<html><body><h2>✓ Reprogramación eliminada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Reprogramación eliminada");

                } else if (event.getAction() == Token.GET) {
                    ArrayList<String[]> reprogramaciones = breprogramacion.listar();
                    String htmlTable = HtmlTableGenerator.generateReprogramacionTable(reprogramaciones);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Reprogramaciones",
                        htmlTable
                    );
                    System.out.println("✓ Lista de reprogramaciones enviada por correo");

                } else if (event.getAction() == Token.MARCAR_REALIZADA) {
                    int reprogramacionId = Integer.parseInt(event.getParams().get(0));
                    breprogramacion.marcarRealizada(reprogramacionId);
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Reprogramación Realizada",
                        "<html><body><h2>✓ Reprogramación marcada como realizada</h2>" +
                        "<p>ID de reprogramación: " + reprogramacionId + "</p></body></html>"
                    );
                    System.out.println("✓ Reprogramación marcada como realizada");


                } else {
                    System.err.println("✗ Acción no válida");
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
                e.printStackTrace();
                
                SendEmailThread.sendEmail(
                    event.getSender(),
                    "Error al Procesar Reprogramación",
                    "<html><body><h2>✗ Error en Base de Datos</h2>" +
                    "<p>" + e.getMessage() + "</p></body></html>"
                );
            }
        }
        @Override
       public void venta(TokenEvent event) {
                try {
                System.out.println("=== COMANDO VENTA ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    int ventaId = bventa.guardar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Venta Registrada",
                        "<html><body><h2>✓ Venta registrada exitosamente</h2>" +
                        "<p>ID de venta: " + ventaId + "</p>" +
                        "<p>La venta ha sido creada correctamente.</p></body></html>"
                    );
                    System.out.println("✓ Venta agregada exitosamente. ID: " + ventaId);

                } else if (event.getAction() == Token.MODIFY) {
                    bventa.modificar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Venta Modificada",
                        "<html><body><h2>✓ Venta modificada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Venta modificada");

                } else if (event.getAction() == Token.DELETE) {
                    bventa.eliminar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Venta Eliminada",
                        "<html><body><h2>✓ Venta eliminada exitosamente</h2></body></html>"
                    );
                    System.out.println("✓ Venta eliminada");

                } else if (event.getAction() == Token.GET) {
                    ArrayList<String[]> ventas = bventa.listar();
                    String htmlTable = HtmlTableGenerator.generateVentaTable(ventas);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Ventas",
                        htmlTable
                    );
                    System.out.println("✓ Lista de ventas enviada por correo");

                } else if (event.getAction() == Token.LIST_POR_ALUMNO_VENTA) {
                    int alumnoId = Integer.parseInt(event.getParams().get(0));
                    ArrayList<String[]> ventas = bventa.listarPorAlumno(alumnoId);
                    
                    // Obtener nombre del alumno
                    String[] alumno = balumno.ver(alumnoId);
                    String nombreAlumno = alumno != null ? alumno[1] + " " + alumno[2] : "Desconocido";
                    
                    String htmlTable = HtmlTableGenerator.generateVentasPorAlumnoTable(ventas, nombreAlumno);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Ventas de " + nombreAlumno,
                        htmlTable
                    );
                    System.out.println("✓ Ventas del alumno enviadas por correo");

                } else if (event.getAction() == Token.LIST_POR_MES) {
                    String mes = event.getParams().get(0);
                    ArrayList<String[]> ventas = bventa.listarPorMes(mes);
                    
                    String htmlTable = HtmlTableGenerator.generateVentasPorMesTable(ventas, mes);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Ventas de " + mes,
                        htmlTable
                    );
                    System.out.println("✓ Ventas del mes enviadas por correo");

                } else if (event.getAction() == Token.LIST_VENCIDAS) {
                    ArrayList<String[]> ventas = bventa.listarVencidas();
                    String htmlTable = HtmlTableGenerator.generateVentasVencidasTable(ventas);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Ventas Vencidas",
                        htmlTable
                    );
                    System.out.println("✓ Ventas vencidas enviadas por correo");

                } else if (event.getAction() == Token.LIST_PENDIENTES_VENTA) {
                    ArrayList<String[]> ventas = bventa.listarPendientes();
                    String htmlTable = HtmlTableGenerator.generateVentasPendientesTable(ventas);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Ventas Pendientes",
                        htmlTable
                    );
                    System.out.println("✓ Ventas pendientes enviadas por correo");

                } else if (event.getAction() == Token.LIST_PAGADAS) {
                    ArrayList<String[]> ventas = bventa.listarPagadas();
                    String htmlTable = HtmlTableGenerator.generateVentasPagadasTable(ventas);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Ventas Pagadas",
                        htmlTable
                    );
                    System.out.println("✓ Ventas pagadas enviadas por correo");

                } else if (event.getAction() == Token.MARCAR_PAGADO) {
                    int ventaId = Integer.parseInt(event.getParams().get(0));
                    bventa.marcarComoPagado(ventaId);
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Venta Marcada como Pagada",
                        "<html><body><h2>✓ Venta marcada como pagada</h2>" +
                        "<p>ID de venta: " + ventaId + "</p>" +
                        "<p>El saldo pendiente se ha establecido en 0.</p></body></html>"
                    );
                    System.out.println("✓ Venta marcada como pagada");

                } else if (event.getAction() == Token.MARCAR_VENCIDO) {
                    int ventaId = Integer.parseInt(event.getParams().get(0));
                    bventa.marcarComoVencido(ventaId);
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Venta Marcada como Vencida",
                        "<html><body><h2>⚠ Venta marcada como vencida</h2>" +
                        "<p>ID de venta: " + ventaId + "</p></body></html>"
                    );
                    System.out.println("✓ Venta marcada como vencida");

                } else if (event.getAction() == Token.ESTADISTICAS) {
                    String[] stats = bventa.obtenerEstadisticas();
                    String htmlStats = HtmlTableGenerator.generateEstadisticasVentas(stats);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Estadísticas de Ventas",
                        htmlStats
                    );
                    System.out.println("✓ Estadísticas de ventas enviadas por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Error en Comando",
                        "<html><body><h2>✗ Error</h2>" +
                        "<p>Acción no válida para venta</p></body></html>"
                    );
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
                e.printStackTrace();
                
                SendEmailThread.sendEmail(
                    event.getSender(),
                    "Error al Procesar Venta",
                    "<html><body><h2>✗ Error en Base de Datos</h2>" +
                    "<p>" + e.getMessage() + "</p></body></html>"
                );
            } catch (Exception e) {
                System.err.println("✗ Error inesperado: " + e.getMessage());
                e.printStackTrace();
                
                SendEmailThread.sendEmail(
                    event.getSender(),
                    "Error al Procesar Venta",
                    "<html><body><h2>✗ Error</h2>" +
                    "<p>" + e.getMessage() + "</p></body></html>"
                );
            }
        }

        @Override
        public void pago(TokenEvent event) {
                try {
                System.out.println("=== COMANDO PAGO ===");
                System.out.println(event);

                if (event.getAction() == Token.ADD) {
                    int pagoId = bpago.guardar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Pago Registrado",
                        "<html><body><h2>✓ Pago registrado exitosamente</h2>" +
                        "<p>ID de pago: " + pagoId + "</p>" +
                        "<p>La venta asociada se ha actualizado automáticamente.</p></body></html>"
                    );
                    System.out.println("✓ Pago agregado exitosamente. ID: " + pagoId);

                } else if (event.getAction() == Token.MODIFY) {
                    bpago.modificar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Pago Modificado",
                        "<html><body><h2>✓ Pago modificado exitosamente</h2>" +
                        "<p>La venta asociada se ha recalculado automáticamente.</p></body></html>"
                    );
                    System.out.println("✓ Pago modificado");

                } else if (event.getAction() == Token.DELETE) {
                    bpago.eliminar(event.getParams());
                    
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Pago Eliminado",
                        "<html><body><h2>✓ Pago eliminado exitosamente</h2>" +
                        "<p>La venta asociada se ha recalculado automáticamente.</p></body></html>"
                    );
                    System.out.println("✓ Pago eliminado");

                } else if (event.getAction() == Token.GET) {
                    ArrayList<String[]> pagos = bpago.listar();
                    String htmlTable = HtmlTableGenerator.generatePagoTable(pagos);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Lista de Pagos",
                        htmlTable
                    );
                    System.out.println("✓ Lista de pagos enviada por correo");

                } else if (event.getAction() == Token.LIST_POR_VENTA) {
                    int ventaId = Integer.parseInt(event.getParams().get(0));
                    ArrayList<String[]> pagos = bpago.listarPorVenta(ventaId);
                    
                    String htmlTable = HtmlTableGenerator.generatePagosPorVentaTable(pagos, ventaId);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Pagos de Venta #" + ventaId,
                        htmlTable
                    );
                    System.out.println("✓ Pagos de la venta enviados por correo");

                } else if (event.getAction() == Token.LIST_POR_ALUMNO_VENTA) {
                    // Reutilizamos la misma acción para listar pagos por alumno
                    int alumnoId = Integer.parseInt(event.getParams().get(0));
                    ArrayList<String[]> pagos = bpago.listarPorAlumno(alumnoId);
                    
                    // Obtener nombre del alumno
                    String[] alumno = balumno.ver(alumnoId);
                    String nombreAlumno = alumno != null ? alumno[1] + " " + alumno[2] : "Desconocido";
                    
                    String htmlTable = HtmlTableGenerator.generatePagosPorAlumnoTable(pagos, nombreAlumno);

                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Pagos de " + nombreAlumno,
                        htmlTable
                    );
                    System.out.println("✓ Pagos del alumno enviados por correo");

                } else {
                    System.err.println("✗ Acción no válida");
                    SendEmailThread.sendEmail(
                        event.getSender(),
                        "Error en Comando",
                        "<html><body><h2>✗ Error</h2>" +
                        "<p>Acción no válida para pago</p></body></html>"
                    );
                }
            } catch (SQLException e) {
                System.err.println("✗ Error: " + e.getMessage());
                e.printStackTrace();
                
                SendEmailThread.sendEmail(
                    event.getSender(),
                    "Error al Procesar Pago",
                    "<html><body><h2>✗ Error en Base de Datos</h2>" +
                    "<p>" + e.getMessage() + "</p></body></html>"
                );
            } catch (Exception e) {
                System.err.println("✗ Error inesperado: " + e.getMessage());
                e.printStackTrace();
                
                SendEmailThread.sendEmail(
                    event.getSender(),
                    "Error al Procesar Pago",
                    "<html><body><h2>✗ Error</h2>" +
                    "<p>" + e.getMessage() + "</p></body></html>"
                );
            }
        }


       @Override
       public void error(TokenEvent event) {
       System.err.println("✗ ERROR AL PROCESAR COMANDO:");
       System.err.println(event);
       System.err.println("Formato esperado: usuario <accion> <param1, param2, ...>");
       
        }

            

            

            



                });

               
                interpreter.run();
           }
    
    
    private void authUser(String email, String password) throws IOException{
        if (socket != null && input != null && output != null  ) {
            input.readLine();
            output.writeBytes(Command.user(email));
            input.readLine();
            output.writeBytes(Command.pass(password));
            String message = input.readLine();
            if (message.contains("-ERR")) {
                throw new AuthenticationException();
            }
        }
    }
    
    
    private void deleteEmails(int emails) throws IOException{
        for (int i = 1; i <= emails; i++) {
            output.writeBytes(Command.dele(i));
            
        }
    }
    
    
    private int getEmailCount(String line){
        String[] data = line.split(" ");
        return Integer.parseInt(data[1]);
        
    }
    
    
    private List<Email> getEmails(int count) throws IOException{
        List<Email> emails = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            output.writeBytes(Command.retr(i));
            String text = readMultiline();
            emails.add(Extractor.getEmail(text));
            
        }
        return emails;
    }
    
    
    private String readMultiline() throws IOException{
        String lines= "";
        while(true){
            String line = input.readLine();
            if (line == null) {
                throw new IOException("Server no responde (ocurrio un error al abrir el correo");
            }
            
            if (line.equals(" ")) {
                break;
            }
            
            lines = lines + "\n" + line;
        }
        
        return lines;
    }
}
