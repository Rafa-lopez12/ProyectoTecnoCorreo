package Bussines;
import Data.DAsistencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BAsistencia {
    private DAsistencia dAsistencia;
    
    public BAsistencia(){
        dAsistencia = new DAsistencia();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]inscripcion_id, [1]fecha, [2]estado, [3]observaciones
        
        int inscripcionId = Integer.parseInt(parametros.get(0));
        String fecha = parametros.get(1);
        
        // Verificar si ya existe asistencia para esa inscripción en esa fecha
        if(dAsistencia.existeAsistencia(inscripcionId, fecha)){
            dAsistencia.disconnect();
            throw new SQLException("Ya existe un registro de asistencia para esta inscripción en la fecha " + fecha);
        }
        
        int asistenciaId = dAsistencia.guardar(
            inscripcionId,
            fecha,
            parametros.get(2),
            parametros.get(3)
        );
        dAsistencia.disconnect();
        return asistenciaId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]estado, [2]observaciones
        dAsistencia.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1),
            parametros.get(2)
        );
        dAsistencia.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dAsistencia.eliminar(Integer.parseInt(parametros.get(0)));
        dAsistencia.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> asistencias = (ArrayList<String[]>) dAsistencia.listar();
        dAsistencia.disconnect();
        return asistencias;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] asistencia = dAsistencia.ver(id);
        dAsistencia.disconnect();
        return asistencia;
    }
    
    
    public void marcarPresente(int inscripcionId, String fecha) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(inscripcionId));
        parametros.add(fecha);
        parametros.add("presente");
        parametros.add("Asistencia registrada");
        guardar(parametros);
    }
    
    public void marcarAusente(int inscripcionId, String fecha, String motivo) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(inscripcionId));
        parametros.add(fecha);
        parametros.add("ausente");
        parametros.add(motivo);
        guardar(parametros);
    }
    

}