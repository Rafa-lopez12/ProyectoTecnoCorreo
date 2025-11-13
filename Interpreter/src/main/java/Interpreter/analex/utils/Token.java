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
    public static final int SERVICIO = 106;
    public static final int INFORMECLASE = 107;
    public static final int ASISTENCIA = 108;
    public static final int LICENCIA = 109;
    public static final int REPROGRAMACION = 110;
    public static final int VENTA = 111;
    public static final int PAGO = 112;
    public static final int REPORTE = 113;
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
    public static final int ACTIVATE = 207;
    public static final int DEACTIVATE = 208;
    public static final int LIST_IALUMNO = 209;
    public static final int LIST_ITUTOR = 210;
    public static final int LIST_ISERVICIO = 211;
    public static final int STATS = 212;
    public static final int MARCAR_REALIZADA = 213;
    
    public static final int LIST_POR_ALUMNO_VENTA = 224;
    public static final int LIST_POR_MES = 225;
    public static final int LIST_VENCIDAS = 226;
    public static final int LIST_PENDIENTES_VENTA = 227;
    public static final int LIST_PAGADAS = 228;
    public static final int MARCAR_PAGADO = 229;
    public static final int MARCAR_VENCIDO = 230;
    public static final int ESTADISTICAS = 231;
    public static final int LIST_POR_VENTA = 232;
    public static final int REGISTRAR_PAGO = 233;
    public static final int REPORTE_ASISTENCIA = 234;
    public static final int REPORTE_INSCRIPCIONES = 235;
    public static final int REPORTE_SERVICIOS = 236;
    public static final int REPORTE_VENTAS = 237;
    public static final int REPORTE_LICENCIAS = 239;
    public static final int REPORTE_PAGOS = 240;
    
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
    public static final String LEXEME_SERVICIO = "servicio";
    public static final String LEXEME_INFORMECLASE = "informeclase";
    public static final String LEXEME_ASISTENCIA = "asistencia";
    public static final String LEXEME_LICENCIA = "licencia";
    public static final String LEXEME_REPROGRAMACION = "reprogramacion";
    public static final String LEXEME_VENTA = "venta";
    public static final String LEXEME_PAGO = "pago";
    public static final String LEXEME_REPORTE = "reporte";
    
    
    public static final String LEXEME_ADD = "add";
    public static final String LEXEME_DELETE = "delete";
    public static final String LEXEME_MODIFY = "modify";
    public static final String LEXEME_GET = "get";
    public static final String LEXEME_VERIFY = "verify";
    public static final String LEXEME_CANCEL = "cancel";
    public static final String LEXEME_REPORT = "report";
    public static final String LEXEME_ACTIVATE = "activar";
    public static final String LEXEME_DEACTIVATE = "desactivar";
    public static final String LEXEME_LIST_BY_TUTOR = "listarportutor";
    public static final String LEXEME_LIST_BY_ALUMNO = "listarporalumno";
    public static final String LEXEME_LIST_BY_SERVICIO = "listarporservicio";
    public static final String LEXEME_STATS = "stats";
    public static final String LEXEME_MARCAR_REALIZADA = "marcarrealizada";
    public static final String LEXEME_LIST_POR_ALUMNO_VENTA = "listarporalumnoventa";
    public static final String LEXEME_LIST_POR_MES = "listarpormes";
    public static final String LEXEME_LIST_VENCIDAS = "listarvencidas";
    public static final String LEXEME_LIST_PENDIENTES_VENTA = "listarpendientesventa";
    public static final String LEXEME_LIST_PAGADAS = "listarpagadas";
    public static final String LEXEME_MARCAR_PAGADO = "marcarpagado";
    public static final String LEXEME_MARCAR_VENCIDO = "marcarvencido";
    public static final String LEXEME_ESTADISTICAS = "estadisticas";
    public static final String LEXEME_LIST_POR_VENTA = "listarporventa";
    public static final String LEXEME_REGISTRAR_PAGO = "registrarpago";
    public static final String LEXEME_REPORTE_ASISTENCIA = "asistencias";
    public static final String LEXEME_REPORTE_INSCRIPCIONES = "inscripciones";
    public static final String LEXEME_REPORTE_SERVICIOS = "servicios";
    public static final String LEXEME_REPORTE_VENTAS = "ventas";
    public static final String LEXEME_REPORTE_LICENCIAS = "licencias";
    public static final String LEXEME_REPORTE_PAGOS = "pagos";
    
   
    

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
            
        case SERVICIO:
            return LEXEME_SERVICIO;
            
        case INFORMECLASE:
            return LEXEME_INFORMECLASE;   
            
        case ASISTENCIA:
            return LEXEME_ASISTENCIA;
            
        case LICENCIA:
            return LEXEME_LICENCIA;
            
        case REPROGRAMACION:
            return LEXEME_REPROGRAMACION;
            
        case VENTA:
            return LEXEME_VENTA;
            
        case PAGO:
            return LEXEME_PAGO;
        case REPORTE:
            return LEXEME_REPORTE;
            
            
            
         case ADD:
                return LEXEME_ADD;
         case DELETE:
                return LEXEME_DELETE;
         case MODIFY:
                return LEXEME_MODIFY;
         case GET:
                return LEXEME_GET;
         case VERIFY:
                return LEXEME_VERIFY;
         case CANCEL:
                return LEXEME_CANCEL;
         case REPORT:
                return LEXEME_REPORT;
         case ACTIVATE:
                return LEXEME_ACTIVATE;
         case DEACTIVATE:
                return LEXEME_DEACTIVATE;
                
         case LIST_POR_ALUMNO_VENTA:
            return LEXEME_LIST_POR_ALUMNO_VENTA;
        case LIST_POR_MES:
            return LEXEME_LIST_POR_MES;
        case LIST_VENCIDAS:
            return LEXEME_LIST_VENCIDAS;
        case LIST_PENDIENTES_VENTA:
            return LEXEME_LIST_PENDIENTES_VENTA;
        case LIST_PAGADAS:
            return LEXEME_LIST_PAGADAS;
        case MARCAR_PAGADO:
            return LEXEME_MARCAR_PAGADO;
        case MARCAR_VENCIDO:
            return LEXEME_MARCAR_VENCIDO;
        case ESTADISTICAS:
            return LEXEME_ESTADISTICAS;
        case LIST_POR_VENTA:
            return LEXEME_LIST_POR_VENTA;
        case REGISTRAR_PAGO:
            return LEXEME_REGISTRAR_PAGO;
        case REPORTE_ASISTENCIA:
            return LEXEME_REPORTE_ASISTENCIA;
        case REPORTE_INSCRIPCIONES:
            return LEXEME_REPORTE_INSCRIPCIONES;
        case REPORTE_SERVICIOS:
            return LEXEME_REPORTE_SERVICIOS;
        case REPORTE_VENTAS:
            return LEXEME_REPORTE_VENTAS;
        case REPORTE_LICENCIAS:
            return LEXEME_REPORTE_LICENCIAS;
        case REPORTE_PAGOS:
            return LEXEME_REPORTE_PAGOS;   


            
                
            // ACCIONES DE LISTADO
            case LIST_IALUMNO:
                return LEXEME_LIST_BY_ALUMNO;
            case LIST_ITUTOR:
                return LEXEME_LIST_BY_TUTOR;
            case LIST_ISERVICIO:
                return LEXEME_LIST_BY_SERVICIO;
  
                
            case MARCAR_REALIZADA:
                return LEXEME_MARCAR_REALIZADA;

                
           
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
            
         case LEXEME_SERVICIO:
            return SERVICIO;
            
         case LEXEME_INFORMECLASE:
            return INFORMECLASE;    
            
         case LEXEME_ASISTENCIA:
            return ASISTENCIA;
            
        case LEXEME_LICENCIA:
            return LICENCIA;
            
        case LEXEME_REPROGRAMACION:
            return REPROGRAMACION;
            
        case LEXEME_VENTA:
            return VENTA;
            
        case LEXEME_PAGO:
            return PAGO;
            
        case LEXEME_REPORTE:
            return REPORTE;
            
            
            
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
            
        case LEXEME_ACTIVATE:
            return ACTIVATE;
        case LEXEME_DEACTIVATE:
            return DEACTIVATE;
        case LEXEME_LIST_BY_ALUMNO:
            return LIST_IALUMNO;
        case LEXEME_LIST_BY_TUTOR:
            return LIST_ITUTOR;
        case LEXEME_LIST_BY_SERVICIO:
            return LIST_ISERVICIO;
         
         case LEXEME_MARCAR_REALIZADA:
            return MARCAR_REALIZADA;

        case LEXEME_STATS:
            return STATS;
            
            
        case LEXEME_LIST_POR_ALUMNO_VENTA:
            return LIST_POR_ALUMNO_VENTA;
        case LEXEME_LIST_POR_MES:
            return LIST_POR_MES;
        case LEXEME_LIST_VENCIDAS:
            return LIST_VENCIDAS;
        case LEXEME_LIST_PENDIENTES_VENTA:
            return LIST_PENDIENTES_VENTA;
        case LEXEME_LIST_PAGADAS:
            return LIST_PAGADAS;
        case LEXEME_MARCAR_PAGADO:
            return MARCAR_PAGADO;
        case LEXEME_MARCAR_VENCIDO:
            return MARCAR_VENCIDO;
        case LEXEME_ESTADISTICAS:
            return ESTADISTICAS;
        case LEXEME_LIST_POR_VENTA:
            return LIST_POR_VENTA;
        case LEXEME_REGISTRAR_PAGO:
            return REGISTRAR_PAGO;
            
        case LEXEME_REPORTE_ASISTENCIA:
            return REPORTE_ASISTENCIA;
        case LEXEME_REPORTE_INSCRIPCIONES:
            return REPORTE_INSCRIPCIONES;
        case LEXEME_REPORTE_SERVICIOS:
            return REPORTE_SERVICIOS;
        case LEXEME_REPORTE_VENTAS:
            return REPORTE_VENTAS;
        case LEXEME_REPORTE_LICENCIAS:
            return REPORTE_LICENCIAS;
        case LEXEME_REPORTE_PAGOS:
            return REPORTE_PAGOS;
            
        case LEXEME_ERROR_COMMAND:
            return ERROR_COMMAND;

        case LEXEME_ERROR_CHARACTER:
            return ERROR_CHARACTER;
        default:
            return -1;

        }
    }
    
    
}
