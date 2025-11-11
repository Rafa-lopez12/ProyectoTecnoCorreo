/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interpreter;
import Bussines.BAlumno;
import Bussines.BTutor;
import Interpreter.analexx.Interpreter;
import Interpreter.analex.interfaces.ITokenEventListener;
import Interpreter.events.TokenEvent;
import Interpreter.analex.utils.Token;
import Bussines.Bususario;
import java.sql.SQLException;

/**
 *
 * @author Rafa
 */
public class Interpreterr {

    public static void main(String[] args) {
        String comando= "usuario add <luis, lopez, 8939918, masculino, rafo.lgf07020155@gmail.com, 2003-04-08, 76069986>";
        String correo= "rafa.123asdlopez@gmail.com";
        
        
        Bususario busuario = new Bususario();
        BAlumno balumno = new BAlumno();
        BTutor btutor= new BTutor();
        Interpreter interpreter = new Interpreter(comando, correo);
        interpreter.setListener(new ITokenEventListener(){
            
            @Override
            public void usuario(TokenEvent event) {
                try {
                      if (event.getAction() == Token.ADD) {
                           busuario.guardar(event.getParams());
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
                System.out.println("DESCONOCIDO");
                System.out.println(event);
                //enviar notificacion
            }

            @Override
            public void horario(TokenEvent tokenEvent) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void tutor_horario(TokenEvent tokenEvent) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void inscripcion(TokenEvent tokenEvent) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void servicio(TokenEvent tokenEvent) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        });
        
        Thread thread = new Thread(interpreter);
        thread.setName("Interpreter thread");
        thread.start();
        
        
    }
}
