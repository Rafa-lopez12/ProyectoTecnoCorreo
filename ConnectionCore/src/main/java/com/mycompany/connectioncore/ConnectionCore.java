/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.connectioncore;

import Interfaces.IEmailEventListener;
import communication.MailVerificationThread;
import java.util.List;
import utils.Email;

/**
 *
 * @author Rafa
 */
public class ConnectionCore {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PROCESAMIENTO DE COMANDOS POR CORREO ===");
        System.out.println("Esperando comandos por correo...\n");
        
        MailVerificationThread mail = new MailVerificationThread();
        
        // Listener opcional para notificaciones adicionales
        mail.setEmailEventListener(new IEmailEventListener() {
            @Override
            public void onReceiveEmailEvent(List<Email> emails) {
                System.out.println("\n=== CORREOS PROCESADOS: " + emails.size() + " ===");
                for (Email email : emails) {
                    System.out.println("  - De: " + email.getFrom());
                    System.out.println("    Asunto: " + email.getSubject());
                }
                System.out.println();
            }
        });
        
        Thread thread = new Thread(mail);
        thread.setName("Mail verification thread");
        thread.start();
        
        System.out.println("Sistema iniciado. Verificando correos cada 5 segundos...");
    }
}
