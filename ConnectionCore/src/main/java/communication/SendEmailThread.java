package communication;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendEmailThread implements Runnable {
    
    private final static String PROTOCOL = "smtp";
    private final String mail = "grupo16sa@tecnoweb.org.bo";
    private final String username = "grupo16sa";
    private final String password = "grup016grup016*";
    
    private String to;
    private String subject;
    private String htmlBody;
    
    public SendEmailThread(String to, String subject, String htmlBody) {
        this.to = to;
        this.subject = subject;
        this.htmlBody = htmlBody;
    }
    
    @Override
    public void run() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", PROTOCOL);
        
        props.setProperty("mail.smtp.auth", "false");
        props.setProperty("mail.smtp.tls.enable", "true");
        props.setProperty("mail.smtp.host", "mail.tecnoweb.org.bo");
        props.setProperty("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            
            // Para HTML
            message.setContent(htmlBody, "text/html; charset=utf-8");
            // O para texto plano: message.setText(htmlBody);

            Transport.send(message);

            System.out.println("✓ Correo enviado correctamente a: " + to);

        } catch (MessagingException e) {
            System.err.println("✗ Error al enviar correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método estático para envío rápido
    public static void sendEmail(String to, String subject, String htmlBody) {
        Thread thread = new Thread(new SendEmailThread(to, subject, htmlBody));
        thread.start();
    }
}