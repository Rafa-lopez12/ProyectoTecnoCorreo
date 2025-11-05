package Bussines;
import Data.DInscripcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BInscripcion {
    private DInscripcion dInscripcion;
    
    public BInscripcion(){
        dInscripcion = new DInscripcion();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
   
        
        int alumnoId = Integer.parseInt(parametros.get(0));
        int tutorId = Integer.parseInt(parametros.get(1));
        
        int inscripcionId = dInscripcion.guardar(
            alumnoId,
            tutorId,
            parametros.get(2), 
            parametros.get(3), 
            parametros.get(4)
        );
        dInscripcion.disconnect();
        return inscripcionId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]estado, [2]observaciones
        dInscripcion.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1), 
            parametros.get(2)
        );
        dInscripcion.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dInscripcion.eliminar(Integer.parseInt(parametros.get(0)));
        dInscripcion.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> inscripciones = (ArrayList<String[]>) dInscripcion.listar();
        dInscripcion.disconnect();
        return inscripciones;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] inscripcion = dInscripcion.ver(id);
        dInscripcion.disconnect();
        return inscripcion;
    }
    

    
    public void retirarAlumno(int inscripcionId, String motivoRetiro) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(inscripcionId));
        parametros.add("retirado");
        parametros.add("Retiro: " + motivoRetiro);
        
        modificar(parametros);
    }
    
    public void finalizarInscripcion(int inscripcionId) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(inscripcionId));
        parametros.add("finalizado");
        parametros.add("Curso finalizado exitosamente");
        
        modificar(parametros);
    }
}