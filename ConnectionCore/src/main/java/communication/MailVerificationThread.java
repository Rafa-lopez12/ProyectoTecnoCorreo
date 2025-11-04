/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import Bussines.BAlumno;
import Bussines.BTutor;
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

/**
 *
 * @author Rafa
 */
public class MailVerificationThread implements Runnable{
    private final static int PORT_POP =993;
    private final static String HOST= "imap.gmail.com";
    private final static String USER= "rafa.123asdlopez@gmail.com";
    private final static String PASSWORD= "mcewvaahflvqhafw";
    
    private IEmailEventListener emailEventListener;
    private Bususario busuario;
    private BTutor btutor;
    private BAlumno balumno;
    
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
    }

    @Override
    public void run() {
        while (true) {
            Store store = null;
            Folder inbox = null;
            try {
                Properties properties = new Properties();
                properties.put("mail.store.protocol", "imaps");
                properties.put("mail.imap.host", HOST);
                properties.put("mail.imap.port", "993");
                properties.put("mail.imap.ssl.enable", "true");
                properties.put("mail.imap.ssl.trust", HOST);
                properties.put("mail.imap.auth", "true");
                
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER, PASSWORD);
                    }
                });
                
                store = session.getStore("imaps");
                store.connect(HOST, USER, PASSWORD);
                
                System.out.println("***************Conexion establecida**********************");
                
                inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_WRITE);
                
                // BUSCAR SOLO CORREOS NO LEÍDOS
                SearchTerm searchTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
                Message[] messages = inbox.search(searchTerm);
                
                List<Email> emails = new ArrayList<>();
                int count = messages.length;
                
                if (count > 0) {
                    for (Message msg : messages) {
                        Email email = new Email();
                        
                        if (msg.getFrom() != null && msg.getFrom().length > 0) {
                            email.setFrom(msg.getFrom()[0].toString());
                        }
                        
                        email.setSubject(msg.getSubject());
                        
                        // PROCESAR EL COMANDO DEL CORREO
                        processEmailCommand(email);
                        
                        emails.add(email);
                        
                        // Marcar como leído
                        msg.setFlag(Flags.Flag.SEEN, true);
                        // Si quieres eliminar, descomenta:
                        // msg.setFlag(Flags.Flag.DELETED, true);
                    }
                    
                    System.out.println(emails);
                }
                
                inbox.close(true);
                store.close();
                
                System.out.println("***************Conexion Terminada**********************");
                
                if (count > 0 && emailEventListener != null) {
                    emailEventListener.onReceiveEmailEvent(emails);
                }
                
                Thread.sleep(5000);
                
            } catch (Exception ex) {
                Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ie);
                }
            } finally {
                try {
                    if (inbox != null && inbox.isOpen()) {
                        inbox.close(false);
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
                    System.out.println("=== COMANDO RECIBIDO POR CORREO ===");
                    System.out.println(event);
                    
                    if (event.getAction() == Token.ADD) {
                        busuario.guardar(event.getParams());
                        System.out.println("✓ Usuario agregado exitosamente");
                        // Aquí puedes enviar un correo de confirmación
                        
                    } else if (event.getAction() == Token.MODIFY) {
                        System.out.println("✓ Usuario modificado");
                        // busuario.modificar(event.getParams());
                        
                    } else if (event.getAction() == Token.DELETE) {
                        System.out.println("✓ Usuario eliminado");
                        // busuario.eliminar(event.getParams());
                        
                    } else {
                        System.err.println("✗ Acción no válida para el caso de uso");
                    }
                    
                } catch (SQLException e) {
                    System.err.println("✗ Error SQL: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            @Override
            public void alumno(TokenEvent event) {
                try {
                      if (event.getAction() == Token.ADD) {
                           balumno.guardar(event.getParams());
                           System.out.println("OK AL AGREGAR");
                        }else if (event.getAction() == Token.MODIFY) {

                        }else if (event.getAction() == Token.DELETE) {

                        }else{
                            System.out.println("La accion no es validad para el caso de uso");
                        }
                } 
              
                catch (SQLException e) {
               
                    System.out.println("Mensaje: " +e.getSQLState());
                }
            }
            
            @Override
            public void tutor(TokenEvent event) {
                try {
                      if (event.getAction() == Token.ADD) {
                           btutor.guardar(event.getParams());
                           System.out.println("OK AL AGREGAR");
                        }else if (event.getAction() == Token.MODIFY) {

                        }else if (event.getAction() == Token.DELETE) {

                        }else{
                            System.out.println("La accion no es validad para el caso de uso");
                        }
                } 
              
                catch (SQLException e) {
               
                    System.out.println("Mensaje: " +e.getSQLState());
                }
            }

            @Override
            public void error(TokenEvent event) {
                System.err.println("✗ ERROR AL PROCESAR COMANDO:");
                System.err.println(event);
                System.err.println("Formato esperado: usuario <accion> <param1, param2, ...>");
                // Aquí puedes enviar un correo con el error al remitente
            }
        });
        
        // Ejecutar el intérprete en el mismo hilo (o crear uno nuevo si prefieres)
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
