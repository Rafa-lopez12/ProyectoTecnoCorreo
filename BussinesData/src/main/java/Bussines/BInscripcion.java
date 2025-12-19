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
        // parametros: [0]servicio_id, [1]alumno_id, [2]tutor_id, [3]horarios (JSON), 
        //             [4]fecha_inscripcion, [5]estado, [6]observaciones
        
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
            parametros.get(3),   // horarios (JSON string)
            parametros.get(4),   // fecha_inscripcion
            parametros.get(5),   // estado
            parametros.get(6)    // observaciones
        );
        dInscripcion.disconnect();
        return inscripcionId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]horarios (JSON), [2]estado, [3]observaciones
        dInscripcion.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1),   // horarios (JSON string)
            parametros.get(2),   // estado
            parametros.get(3)    // observaciones
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
        parametros.add(null); // horarios
        parametros.add("retirado");
        parametros.add("Retiro: " + motivoRetiro);
        
        modificar(parametros);
    }
    
    public void finalizarInscripcion(int inscripcionId) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(inscripcionId));
        parametros.add(null); // horarios
        parametros.add("finalizado");
        parametros.add("Curso finalizado exitosamente");
        
        modificar(parametros);
    }
    
    // Método auxiliar para crear JSON de horarios
    public String crearHorariosJSON(List<String[]> horarios) {
        // Ejemplo: [{"dia": "Lunes", "hora_inicio": "08:00", "hora_fin": "10:00"}, ...]
        StringBuilder json = new StringBuilder("[");
        for(int i = 0; i < horarios.size(); i++) {
            String[] horario = horarios.get(i);
            json.append("{")
                .append("\"dia\":\"").append(horario[0]).append("\",")
                .append("\"hora_inicio\":\"").append(horario[1]).append("\",")
                .append("\"hora_fin\":\"").append(horario[2]).append("\"")
                .append("}");
            if(i < horarios.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}