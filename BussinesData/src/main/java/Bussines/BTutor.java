package Bussines;
import Data.DTutor;
import Data.DUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BTutor {
    private DTutor dTutor;
    private DUsuario dUsuario;
    
    public BTutor(){
        dTutor = new DTutor();
        dUsuario = new DUsuario();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        // parametros: [0]nombre, [1]apellido, [2]telefono, [3]fecha_nacimiento, [4]direccion, 
        //             [5]estado, [6]rol, [7]grado, [8]email, [9]password
        
        // Primero crear el usuario (sin email - el email va en tutor)
        int userId = dUsuario.guardar(
            parametros.get(0),   // nombre
            parametros.get(1),   // apellido
            null,                // email (null porque va en tutor)
            parametros.get(2),   // telefono
            parametros.get(3),   // fecha_nacimiento
            parametros.get(4),   // direccion
            parametros.get(5)    // estado
        );
        dUsuario.disconnect();
        
        // Hashear password
        String hashedPassword = hashPassword(parametros.get(9));
        
        // Luego crear el tutor con el user_id
        dTutor.guardar(
            userId,              // user_id
            parametros.get(6),   // rol
            parametros.get(7),   // grado
            parametros.get(8),   // email
            hashedPassword       // password
        );
        dTutor.disconnect();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]nombre, [2]apellido, [3]telefono, [4]fecha_nacimiento, 
        //             [5]direccion, [6]estado, [7]rol, [8]grado, [9]email, [10]password
        
        // Primero obtener el tutor para saber su user_id
        String[] tutor = dTutor.ver(Integer.parseInt(parametros.get(0)));
        int userId = Integer.parseInt(tutor[1]); // user_id está en posición 1
        
        // Modificar el usuario
        dUsuario.modificar(
            userId,              // id del usuario
            parametros.get(1),   // nombre
            parametros.get(2),   // apellido
            null,                // email (null porque está en tutor)
            parametros.get(3),   // telefono
            parametros.get(4),   // fecha_nacimiento
            parametros.get(5),   // direccion
            parametros.get(6)    // estado
        );
        dUsuario.disconnect();
        
        // Hashear password si se proporciona
        String password = parametros.get(10);
        if(password != null && !password.isEmpty()){
            password = hashPassword(password);
        }
        
        // Modificar el tutor
        dTutor.modificar(
            Integer.parseInt(parametros.get(0)), // id del tutor
            parametros.get(7),   // rol
            parametros.get(8),   // grado
            parametros.get(9),   // email
            password             // password (puede ser null)
        );
        dTutor.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        // Al eliminar el tutor, el usuario también se eliminará por CASCADE
        dTutor.eliminar(Integer.parseInt(parametros.get(0)));
        dTutor.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> tutores = (ArrayList<String[]>) dTutor.listar();
        dTutor.disconnect();
        return tutores;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] tutor = dTutor.ver(id);
        dTutor.disconnect();
        return tutor;
    }
    
    public boolean validarLogin(String email, String password) throws SQLException{
        String hashedPassword = hashPassword(password);
        boolean valido = dTutor.validarCredenciales(email, hashedPassword);
        dTutor.disconnect();
        return valido;
    }
    
    // Método simple de hash - RECOMENDADO: usar BCrypt o similar en producción
    private String hashPassword(String password){
        // Por ahora retorna el password tal cual
        // TODO: Implementar BCrypt o SHA-256
        return password;
    }
}