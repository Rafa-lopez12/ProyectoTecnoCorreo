package Bussines;
import Data.DPropietario;
import Data.DUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BPropietario {
    private DPropietario dPropietario;
    private DUsuario dUsuario;
    
    public BPropietario(){
        dPropietario = new DPropietario();
        dUsuario = new DUsuario();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        // parametros: [0]nombre, [1]apellido, [2]telefono, [3]fecha_nacimiento, [4]direccion, 
        //             [5]estado, [6]rol, [7]email, [8]password
        
        // Primero crear el usuario (sin email - el email va en propietario)
        int userId = dUsuario.guardar(
            parametros.get(0),   // nombre
            parametros.get(1),   // apellido
            parametros.get(2),   // telefono
            parametros.get(3),   // fecha_nacimiento
            parametros.get(4),   // direccion
            parametros.get(5)    // estado
        );
        dUsuario.disconnect();
        
        // Hashear password
        String hashedPassword = hashPassword(parametros.get(8));
        
        // Luego crear el propietario con el user_id
        dPropietario.guardar(
            userId,              // user_id
            parametros.get(6),   // rol
            parametros.get(7),   // email
            hashedPassword       // password
        );
        dPropietario.disconnect();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]nombre, [2]apellido, [3]telefono, [4]fecha_nacimiento, 
        //             [5]direccion, [6]estado, [7]rol, [8]email, [9]password
        
        // Primero obtener el propietario para saber su user_id
        String[] propietario = dPropietario.ver(Integer.parseInt(parametros.get(0)));
        int userId = Integer.parseInt(propietario[1]); // user_id está en posición 1
        
        // Modificar el usuario
        dUsuario.modificar(
            userId,              // id del usuario
            parametros.get(1),   // nombre
            parametros.get(2),   // apellido
            null,                // email (null porque está en propietario)
            parametros.get(3),   // telefono
            parametros.get(4),   // fecha_nacimiento
            parametros.get(5),   // direccion
            parametros.get(6)    // estado
        );
        dUsuario.disconnect();
        
        // Hashear password si se proporciona
        String password = parametros.get(9);
        if(password != null && !password.isEmpty()){
            password = hashPassword(password);
        }
        
        // Modificar el propietario
        dPropietario.modificar(
            Integer.parseInt(parametros.get(0)), // id del propietario
            parametros.get(7),   // rol
            parametros.get(8),   // email
            password             // password (puede ser null)
        );
        dPropietario.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        // Al eliminar el propietario, el usuario también se eliminará por CASCADE
        dPropietario.eliminar(Integer.parseInt(parametros.get(0)));
        dPropietario.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> propietarios = (ArrayList<String[]>) dPropietario.listar();
        dPropietario.disconnect();
        return propietarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] propietario = dPropietario.ver(id);
        dPropietario.disconnect();
        return propietario;
    }
    
    public boolean validarLogin(String email, String password) throws SQLException{
        String hashedPassword = hashPassword(password);
        boolean valido = dPropietario.validarCredenciales(email, hashedPassword);
        dPropietario.disconnect();
        return valido;
    }
    
    // Método simple de hash - RECOMENDADO: usar BCrypt o similar en producción
    private String hashPassword(String password){
        // Por ahora retorna el password tal cual
        // TODO: Implementar BCrypt o SHA-256
        return password;
    }
}