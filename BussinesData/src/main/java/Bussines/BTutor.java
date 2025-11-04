package Bussines;
import Data.DTutor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BTutor {
    private DTutor dTutor;
    
    public BTutor(){
        dTutor = new DTutor();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        // parametros: [0]nombre, [1]apellido, [2]email, [3]telefono, [4]fecha_nacimiento, [5]direccion, [6]estado
        dTutor.guardar(
            parametros.get(0), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            parametros.get(4), 
            parametros.get(5), 
            parametros.get(6)
        );
        dTutor.disconnect();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]nombre, [2]apellido, [3]email, [4]telefono, [5]fecha_nacimiento, [6]direccion, [7]estado
        dTutor.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            parametros.get(4), 
            parametros.get(5), 
            parametros.get(6), 
            parametros.get(7)
        );
        dTutor.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
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
}