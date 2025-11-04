package Bussines;
import Data.DAlumno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BAlumno {
    private DAlumno dAlumno;
    
    public BAlumno(){
        dAlumno = new DAlumno();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        // parametros: [0]nombre, [1]apellido, [2]email, [3]telefono, [4]fecha_nacimiento, [5]direccion, [6]estado, [7]grado_escolar, [8]fecha_ingreso
        dAlumno.guardar(
            parametros.get(0), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            parametros.get(4), 
            parametros.get(5), 
            parametros.get(6), 
            parametros.get(7),
            parametros.get(8)
        );
        dAlumno.disconnect();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]nombre, [2]apellido, [3]email, [4]telefono, [5]fecha_nacimiento, [6]direccion, [7]estado, [8]grado_escolar, [9]fecha_ingreso
        dAlumno.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            parametros.get(4), 
            parametros.get(5), 
            parametros.get(6), 
            parametros.get(7),
            parametros.get(8),
            parametros.get(9)
        );
        dAlumno.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dAlumno.eliminar(Integer.parseInt(parametros.get(0)));
        dAlumno.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> alumnos = (ArrayList<String[]>) dAlumno.listar();
        dAlumno.disconnect();
        return alumnos;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] alumno = dAlumno.ver(id);
        dAlumno.disconnect();
        return alumno;
    }
    
    public ArrayList<String[]> listarPorGrado(String gradoEscolar) throws SQLException{
        ArrayList<String[]> alumnos = (ArrayList<String[]>) dAlumno.listarPorGrado(gradoEscolar);
        dAlumno.disconnect();
        return alumnos;
    }
}