/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter.analexx;
import Interpreter.analex.interfaces.ITokenEventListener;
import Interpreter.analexx.Analex;
import Interpreter.analex.models.TokenCommand;
import Interpreter.events.TokenEvent;
import Interpreter.analex.utils.Token;

/**
 *
 * @author Rafa
 */
public class Interpreter implements Runnable{
    private ITokenEventListener listener;
    private Analex analex;
    
    private String command;
    private String sender;
    
    public Interpreter(String command, String sender){
        this.command=command;
        this.sender=sender;
    }
    
    public ITokenEventListener getListener(){
        return listener;
    }
    
    public void setListener(ITokenEventListener listener){
        this.listener= listener;
    }
    
    public String getCommand(){
        return command;
    }
    
    public void setCommand(String command){
        this.command=command;
    }
    
    public String getSender(){
        return sender;
    }
    
    public void setSender(String sender){
        this.sender= sender;
    }
    
    private void filterEvent(TokenCommand token_command){
        TokenEvent token_event = new TokenEvent(this, sender);
        token_event.setAction(token_command.getAction());

        int count_params = token_command.countParams();
        for (int i = 0; i < count_params; i++) {
            int pos = token_command.getParams(i);
            token_event.addParams(analex.getParam(pos));
        }

        switch (token_command.getName()) {
            case Token.USUARIO:
                listener.usuario(token_event);
                break;
            case Token.ALUMNO:
                listener.alumno(token_event);
                break;
            case Token.TUTOR:
                listener.tutor(token_event);
                break;
                
            case Token.HORARIO:
                listener.horario(token_event);
            break;
            
            case Token.TUTOR_HORARIO:   
                listener.tutor_horario(token_event);
            break;
            
            case Token.INSCRIPCION:   
                listener.inscripcion(token_event);
            break;
            
            case Token.SERVICIO:
                listener.servicio(token_event);
            break;
            
            case Token.INFORMECLASE:
                listener.informeclase(token_event);
            break;
            
            case Token.ASISTENCIA:
                listener.asistencia(token_event);
                break;
                
            case Token.LICENCIA:
                listener.licencia(token_event);
                break;
                
            case Token.REPROGRAMACION:
                listener.reprogramacion(token_event);
                break;
                
            case Token.VENTA:
                listener.venta(token_event);
                break;
                
            case Token.PAGO:
                listener.pago(token_event);
                break;
                
            case Token.REPORTE:
                listener.reporte(token_event);
                break;
                
            default:
            System.err.println("Caso de uso no reconocido: " + token_command.getName());
            break;
        }
    }
    
    private void tokenError(Token token, String error){
        TokenEvent token_event = new TokenEvent(this, sender);
        token_event.setAction(token.getAttribute());
        token_event.addParams(command);
        token_event.addParams(error);
        listener.error(token_event);
    }
    
    
    public void run() {
        analex = new Analex(command);
        TokenCommand token_command = new TokenCommand();
        Token token;

        while((token = analex.Preanalisis()).getName() != Token.END && token.getName() != Token.ERROR){
            if(token.getName() == Token.CU){
                token_command.setName(token.getAttribute());// id del CU
            } else if(token.getName() == Token.ACTION){
                token_command.setAction(token.getAttribute());// id de la accion
            } else if(token.getName() == Token.PARAMS){
                token_command.addParams(token.getAttribute());// la posicion del parametro en el tsp
            }
            analex.next();
        }

        if(token.getName() == Token.END){
            filterEvent(token_command);// se analizo el comando con exito
        } else if(token.getName() == Token.ERROR){
            tokenError(token, analex.lexeme()); // se produjo un error en el analisis
        }
    }
    
}
