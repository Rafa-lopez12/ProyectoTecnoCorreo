package Bussines;
import Data.DTutorHorario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BTutorHorario {
    private DTutorHorario dTutorHorario;
    
    public BTutorHorario(){
        dTutorHorario = new DTutorHorario();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        // parametros: [0]tutor_id, [1]horario_id, [2]fecha_asignacion
        
        // Verificar si ya existe la asignación
        if(dTutorHorario.existeAsignacion(
                Integer.parseInt(parametros.get(0)), 
                Integer.parseInt(parametros.get(1)))){
            dTutorHorario.disconnect();
            throw new SQLException("El tutor ya está asignado a este horario");
        }
        
        dTutorHorario.guardar(
            Integer.parseInt(parametros.get(0)), 
            Integer.parseInt(parametros.get(1)), 
            parametros.get(2)
        );
        dTutorHorario.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]tutor_id, [1]horario_id
        dTutorHorario.eliminar(
            Integer.parseInt(parametros.get(0)), 
            Integer.parseInt(parametros.get(1))
        );
        dTutorHorario.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> tutorHorarios = (ArrayList<String[]>) dTutorHorario.listar();
        dTutorHorario.disconnect();
        return tutorHorarios;
    }
    
    public ArrayList<String[]> listarPorTutor(int tutorId) throws SQLException{
        ArrayList<String[]> horarios = (ArrayList<String[]>) dTutorHorario.listarPorTutor(tutorId);
        dTutorHorario.disconnect();
        return horarios;
    }
    
    public ArrayList<String[]> listarPorHorario(int horarioId) throws SQLException{
        ArrayList<String[]> tutores = (ArrayList<String[]>) dTutorHorario.listarPorHorario(horarioId);
        dTutorHorario.disconnect();
        return tutores;
    }
    
    public boolean existeAsignacion(int tutorId, int horarioId) throws SQLException{
        boolean existe = dTutorHorario.existeAsignacion(tutorId, horarioId);
        dTutorHorario.disconnect();
        return existe;
    }
}