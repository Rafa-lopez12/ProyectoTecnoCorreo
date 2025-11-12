package Bussines;
import Data.DLicencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BLicencia {
    private DLicencia dLicencia;
    
    public BLicencia(){
        dLicencia = new DLicencia();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]tutor_id, [1]fecha_licencia, [2]motivo, [3]estado
        int licenciaId = dLicencia.guardar(
            Integer.parseInt(parametros.get(0)),
            parametros.get(1),
            parametros.get(2),
            parametros.get(3)
        );
        dLicencia.disconnect();
        return licenciaId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]fecha_licencia, [2]motivo, [3]estado
        dLicencia.modificar(
            Integer.parseInt(parametros.get(0)),
            parametros.get(1),
            parametros.get(2),
            parametros.get(3)
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
    public void solicitarLicencia(int tutorId, String fechaLicencia, String motivo) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(tutorId));
        parametros.add(fechaLicencia);
        parametros.add(motivo);
        parametros.add("pendiente");
        guardar(parametros);
    }
    
    

}