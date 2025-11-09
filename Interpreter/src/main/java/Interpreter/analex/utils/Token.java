/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter.analex.utils;

/**
 *
 * @author Rafa
 */
public class Token {
    private int name;// si es CU, ACTION o ERROR
    private int attribute; // que tipo ya sea CU, ACTION o ERROR

    //constantes numericas para manejar el analex
    public static final int CU = 0;
    public static final int ACTION = 1;
    public static final int PARAMS = 2;
    public static final int END = 3;
    public static final int ERROR = 4;

    public static final int USUARIO = 100;
    public static final int ALUMNO = 101;
    public static final int TUTOR = 102;
    public static final int HORARIO = 103;
    public static final int TUTOR_HORARIO = 104;
    public static final int INSCRIPCION = 105;
//    public static final int CLIENT = 101;
//    public static final int DPTO = 102;
 //   public static final int SOCIAL = 103;
 //   public static final int SCHEDULE = 104;
  //  public static final int NOTIFY = 105;
  //  public static final int VISIT = 106;
  //  public static final int SUPPORT = 107;
  //  public static final int RESERVE = 108;
  //  public static final int APARTMENT = 109;
  //  public static final int VETERINARIO = 110;
  //  public static final int MASCOTA = 111;
    
    
    public static final int ADD = 200;
    public static final int DELETE = 201;
    public static final int MODIFY = 202;
    public static final int GET = 203;
    public static final int VERIFY = 204;
    public static final int CANCEL = 205;
    public static final int REPORT = 206;
    
    
    public static final int ERROR_COMMAND = 300;
    public static final int ERROR_CHARACTER = 301;

    //constantes literales para realizar un efecto de impre
    public static final String LEXEME_CU = "caso de uso";
    public static final String LEXEME_ACTION = "action";
    public static final String LEXEME_PARAMS = "params";
    public static final String LEXEME_END = "end";
    public static final String LEXEME_ERROR = "error";
    
    public static final String LEXEME_USUARIO = "usuario";
    public static final String LEXEME_ALUMNO = "alumno";
    public static final String LEXEME_TUTOR = "tutor";
    public static final String LEXEME_HORARIO = "horario";
    public static final String LEXEME_TUTORHORARIO = "tutorhorario";
    public static final String LEXEME_INSCRIPCION = "inscripcion";
    
    
    public static final String LEXEME_ADD = "add";
    public static final String LEXEME_DELETE = "delete";
    public static final String LEXEME_MODIFY = "modify";
    public static final String LEXEME_GET = "get";
    public static final String LEXEME_VERIFY = "verify";
    public static final String LEXEME_CANCEL = "cancel";
    public static final String LEXEME_REPORT = "report";

    public static final String LEXEME_ERROR_COMMAND = "UNKNOWN COMMAND";
    public static final String LEXEME_ERROR_CHARACTER = "UNKNOWN CHARACTER";
    
    
    public Token(){
        
    }
    
    public Token(String token){
        int id = findByLexeme(token);
        if(id !=-1){
            if(100 <= id && id < 200){
                this.name = CU;
                this.attribute = id;
            } else if(200 <= id && id < 300){
                this.name = ACTION;
                this.attribute = id;
            }
        } else {
            this.name = ERROR;
            this.attribute = ERROR_COMMAND;
            System.err.println("Class Token.Constructor dice: \n "
                + " El lexema enviado al constructor no es reconocido como un token \n"
                + "Lexema: " + token);
        }
    }
    
    public Token(int name){
        this.name= name;
    }
    
    public Token(int name, int attribute){
        this.name=name;
        this.attribute=attribute;
    }

    public int getName() {
        return name;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString(){
        if(0 <= name  && name <=1){
            return "< " + getStringToken(name) + " , " + getStringToken(attribute) + ">";
        }else if(name == 2){
            return "< " + getStringToken(name) + " , " + attribute + ">";
        }else if(3 == name){
            return "< " + getStringToken(name) + " , " + "_______ >";
        } else if(name == 4){
            return "< " + getStringToken(name) + " , " + getStringToken(attribute) + ">";
        }
        return "< TOKEN , DESCONOCIDO>";
    }
    
    public String getStringToken(int token){
        switch (token) {
        case CU:
            return LEXEME_CU;
        case ACTION:
            return LEXEME_ACTION;
        case PARAMS:
            return LEXEME_PARAMS;
        case END:
            return LEXEME_END;
        case ERROR:
            return LEXEME_ERROR;


        case USUARIO:
            return LEXEME_USUARIO;
        
        case ALUMNO:
            return LEXEME_ALUMNO;
            
        case TUTOR:
            return LEXEME_TUTOR;
        
        case HORARIO:
            return LEXEME_HORARIO;
            
        case TUTOR_HORARIO:
            return LEXEME_TUTORHORARIO;
            
        case INSCRIPCION:
            return LEXEME_INSCRIPCION;
            
            
            
            
        case ERROR_COMMAND:
            return LEXEME_ERROR_COMMAND;
        case ERROR_CHARACTER:
            return LEXEME_ERROR_CHARACTER;
        default:
            return "N: " + token;
           
        }
    }
    
    
    public int findByLexeme(String lexeme){
        switch (lexeme) {
        case LEXEME_CU:
            return CU;
        case LEXEME_ACTION:
            return ACTION;
        case LEXEME_PARAMS:
            return PARAMS;
        case LEXEME_END:
            return END;
        case LEXEME_ERROR:
            return ERROR;


        case LEXEME_USUARIO:
            return USUARIO;
            
        case LEXEME_ALUMNO:
            return ALUMNO;
            
        case LEXEME_TUTOR:
            return TUTOR;
            
        case LEXEME_HORARIO:
            return HORARIO;
           
         case LEXEME_TUTORHORARIO:
            return TUTOR_HORARIO;
            
         case LEXEME_INSCRIPCION:
            return INSCRIPCION;  
            
            
        case LEXEME_ADD:
            return ADD;
        case LEXEME_DELETE:
            return DELETE;
        case LEXEME_MODIFY:
            return MODIFY;
        case LEXEME_GET:
            return GET;
        case LEXEME_VERIFY:
            return VERIFY;
        case LEXEME_CANCEL:
            return CANCEL;
        case LEXEME_REPORT:
            return REPORT;
        case LEXEME_ERROR_COMMAND:
            return ERROR_COMMAND;

        case LEXEME_ERROR_CHARACTER:
            return ERROR_CHARACTER;
        default:
            return -1;

        }
    }
    
    
}
