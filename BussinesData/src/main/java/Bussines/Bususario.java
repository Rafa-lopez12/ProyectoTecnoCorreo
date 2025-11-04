package Bussines;
import Data.DUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class Bususario {
    private DUsuario dUsuario;
    
    public Bususario(){
        dUsuario = new DUsuario();
    }
    
    // Retorna el ID del usuario creado
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]nombre, [1]apellido, [2]email, [3]telefono, [4]fecha_nacimiento, [5]direccion, [6]estado
        int userId = dUsuario.guardar(
            parametros.get(0), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            parametros.get(4), 
            parametros.get(5), 
            parametros.get(6)
        );
        dUsuario.disconnect();
        return userId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]nombre, [2]apellido, [3]email, [4]telefono, [5]fecha_nacimiento, [6]direccion, [7]estado
        dUsuario.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            parametros.get(4), 
            parametros.get(5), 
            parametros.get(6), 
            parametros.get(7)
        );
        dUsuario.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dUsuario.eliminar(Integer.parseInt(parametros.get(0)));
        dUsuario.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> usuarios = (ArrayList<String[]>) dUsuario.listar();
        dUsuario.disconnect();
        return usuarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] usuario = dUsuario.ver(id);
        dUsuario.disconnect();
        return usuario;
    }
    
    public int getIdByEmail(String email) throws SQLException{
        int id = dUsuario.getIdByEmail(email);
        dUsuario.disconnect();
        return id;
    }
}