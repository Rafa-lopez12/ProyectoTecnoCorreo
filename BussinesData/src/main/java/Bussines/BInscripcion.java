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
        // parametros: [0]servicio_id, [1]alumno_id, [2]tutor_id, [3]fecha_inscripcion, [4]direccion, [5]foto_url, [6]estado, [7]observaciones
        
        int servicioId = Integer.parseInt(parametros.get(0));
        int alumnoId = Integer.parseInt(parametros.get(1));
        int tutorId = Integer.parseInt(parametros.get(2));
        
        // Verificar si el alumno ya está inscrito con este tutor en este servicio
        if(dInscripcion.existeInscripcion(alumnoId, tutorId, servicioId)){
            dInscripcion.disconnect();
            throw new SQLException("El alumno ya está inscrito con este tutor en este servicio");
        }
        
        int inscripcionId = dInscripcion.guardar(
            servicioId,
            alumnoId,
            tutorId,
            parametros.get(3), 
            parametros.get(4), 
            parametros.get(5),
            parametros.get(6), 
            parametros.get(7)
        );
        dInscripcion.disconnect();
        return inscripcionId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]direccion, [2]foto_url, [3]estado, [4]observaciones
        dInscripcion.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1), 
            parametros.get(2),
            parametros.get(3),
            parametros.get(4)
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
    
    public ArrayList<String[]> listarPorAlumno(int alumnoId) throws SQLException{
        ArrayList<String[]> inscripciones = (ArrayList<String[]>) dInscripcion.listarPorAlumno(alumnoId);
        dInscripcion.disconnect();
        return inscripciones;
    }
    
    public ArrayList<String[]> listarPorTutor(int tutorId) throws SQLException{
        ArrayList<String[]> inscripciones = (ArrayList<String[]>) dInscripcion.listarPorTutor(tutorId);
        dInscripcion.disconnect();
        return inscripciones;
    }
    
    public ArrayList<String[]> listarPorServicio(int servicioId) throws SQLException{
        ArrayList<String[]> inscripciones = (ArrayList<String[]>) dInscripcion.listarPorServicio(servicioId);
        dInscripcion.disconnect();
        return inscripciones;
    }
    
    public void retirarAlumno(int inscripcionId, String motivoRetiro) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(inscripcionId));
        parametros.add(null); // direccion
        parametros.add(null); // foto_url
        parametros.add("retirado");
        parametros.add("Retiro: " + motivoRetiro);
        
        modificar(parametros);
    }
    
    public void finalizarInscripcion(int inscripcionId) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(inscripcionId));
        parametros.add(null); // direccion
        parametros.add(null); // foto_url
        parametros.add("finalizado");
        parametros.add("Curso finalizado exitosamente");
        
        modificar(parametros);
    }
}