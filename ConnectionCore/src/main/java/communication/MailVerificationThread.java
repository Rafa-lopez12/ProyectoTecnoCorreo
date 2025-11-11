/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import Bussines.BAlumno;
import Bussines.BHorario;
import Bussines.BInscripcion;
import Bussines.BServicio;
import Bussines.BTutor;
import Bussines.BTutorHorario;
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
