package Bussines;
import Data.DLicencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 * CAMBIO IMPORTANTE: Ahora trabaja con asistencia_id en lugar de tutor_id y fecha_licencia
 */
public class BLicencia {
    private DLicencia dLicencia;
    
    public BLicencia(){
        dLicencia = new DLicencia();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]asistencia_id (CAMBIO: era tutor_id y fecha_licencia), 
        //             [1]motivo, [2]estado
        
        int asistenciaId = Integer.parseInt(parametros.get(0));
        
        // Verificar si ya existe una licencia para esta asistencia
        if(dLicencia.existeLicencia(asistenciaId)){
            dLicencia.disconnect();
            throw new SQLException("Ya existe una licencia para esta asistencia");
        }
        
        int licenciaId = dLicencia.guardar(
            asistenciaId,        // asistencia_id (CAMBIO)
            parametros.get(1),   // motivo
            parametros.get(2)    // estado
        );
        dLicencia.disconnect();
        return licenciaId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]motivo, [2]estado
        dLicencia.modificar(
            Integer.parseInt(parametros.get(0)),
            parametros.get(1),
            parametros.get(2)
        );
        dLicencia.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dLicencia.eliminar(Integer.parseInt(parametros.get(0)));
        dLicencia.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> licencias = (ArrayList<String[]>) dLicencia.listar();
        dLicencia.disconnect();
        return licencias;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] licencia = dLicencia.ver(id);
        dLicencia.disconnect();
        return licencia;
    }
    
    // Métodos específicos para gestión de licencias
    
    /**
     * Solicitar una licencia para una asistencia específica
     * NOTA: Ya no se pasa tutor_id y fecha, ahora se pasa la asistencia_id
     */
    public void solicitarLicencia(int asistenciaId, String motivo) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(asistenciaId));
        parametros.add(motivo);
        parametros.add("pendiente");
        guardar(parametros);
    }
    
    /**
     * Aprobar una licencia
     */
    public void aprobarLicencia(int licenciaId) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(licenciaId));
        
        // Obtener la licencia actual
        String[] licencia = ver(licenciaId);
        String motivo = licencia[6]; // motivo está en posición 6
        
        parametros.add(motivo);
        parametros.add("aprobada");
        modificar(parametros);
    }
    
    /**
     * Rechazar una licencia
     */
    public void rechazarLicencia(int licenciaId, String motivoRechazo) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(licenciaId));
        
        // Obtener la licencia actual
        String[] licencia = ver(licenciaId);
        String motivo = licencia[6] + " - RECHAZADA: " + motivoRechazo;
        
        parametros.add(motivo);
        parametros.add("rechazada");
        modificar(parametros);
    }
    
    /**
     * Listar licencias por tutor
     */
    public ArrayList<String[]> listarPorTutor(int tutorId) throws SQLException{
        ArrayList<String[]> licencias = (ArrayList<String[]>) dLicencia.listarPorTutor(tutorId);
        dLicencia.disconnect();
        return licencias;
    }
}