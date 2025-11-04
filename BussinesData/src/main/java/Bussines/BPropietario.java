package Bussines;
import Data.DPropietario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BPropietario {
    private DPropietario dPropietario;
    
    public BPropietario(){
        dPropietario = new DPropietario();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        // parametros: [0]nombre, [1]apellido, [2]email, [3]password, [4]telefono, [5]fecha_nacimiento, [6]direccion, [7]estado
        
        // Aquí deberías hashear la contraseña antes de guardarla
        String hashedPassword = hashPassword(parametros.get(3));
        
        dPropietario.guardar(
            parametros.get(0), 
            parametros.get(1), 
            parametros.get(2), 
            hashedPassword,
            parametros.get(4), 
            parametros.get(5), 
            parametros.get(6), 
            parametros.get(7)
        );
        dPropietario.disconnect();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]nombre, [2]apellido, [3]email, [4]password, [5]telefono, [6]fecha_nacimiento, [7]direccion, [8]estado
        
        String password = parametros.get(4);
        // Si se proporciona password, hashearlo
        if(password != null && !password.isEmpty()){
            password = hashPassword(password);
        }
        
        dPropietario.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            password,
            parametros.get(5), 
            parametros.get(6), 
            parametros.get(7),
            parametros.get(8)
        );
        dPropietario.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
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