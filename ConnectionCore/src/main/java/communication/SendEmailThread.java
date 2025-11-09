package communication;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class SendEmailThread implements Runnable {
    private static final String HOST = "smtp.gmail.com";
    private static final String USER = "rafa.123asdlopez@gmail.com";
    private static final String PASSWORD = "mcewvaahflvqhafw"; // Tu contraseña de aplicación
    
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
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER, PASSWORD);
                }
            });
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            
            // Enviar como HTML
            message.setContent(htmlBody, "text/html; charset=utf-8");
            
            Transport.send(message);
            System.out.println("✓ Correo enviado exitosamente a: " + to);
            
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