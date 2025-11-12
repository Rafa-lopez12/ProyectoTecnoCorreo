package Bussines;
import Data.DReprogramacion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BReprogramacion {
    private DReprogramacion dReprogramacion;
    
    public BReprogramacion(){
        dReprogramacion = new DReprogramacion();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]licencia_id, [1]fecha_original, [2]fecha_nueva, [3]estado, [4]observaciones
        int reprogramacionId = dReprogramacion.guardar(
            Integer.parseInt(parametros.get(0)),
            parametros.get(1),
            parametros.get(2),
            parametros.get(3),
            parametros.get(4)
        );
        dReprogramacion.disconnect();
        return reprogramacionId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]fecha_nueva, [2]estado, [3]observaciones
        dReprogramacion.modificar(
            Integer.parseInt(parametros.get(0)),
            parametros.get(1),
            parametros.get(2),
            parametros.get(3)
        );
        dReprogramacion.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dReprogramacion.eliminar(Integer.parseInt(parametros.get(0)));
        dReprogramacion.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> reprogramaciones = (ArrayList<String[]>) dReprogramacion.listar();
        dReprogramacion.disconnect();
        return reprogramaciones;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] reprogramacion = dReprogramacion.ver(id);
        dReprogramacion.disconnect();
        return reprogramacion;
    }
    
    

    public void programarClase(int licenciaId, String fechaOriginal, String fechaNueva, String observaciones) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(licenciaId));
        parametros.add(fechaOriginal);
        parametros.add(fechaNueva);
        parametros.add("programada");
        parametros.add(observaciones);
        guardar(parametros);
    }
    
    public void marcarRealizada(int reprogramacionId) throws SQLException{
        dReprogramacion.marcarRealizada(reprogramacionId);
        dReprogramacion.disconnect();
    }
    
    public void cancelar(int reprogramacionId) throws SQLException{
        dReprogramacion.cancelar(reprogramacionId);
        dReprogramacion.disconnect();
    }
    

    
    // Método para reprogramar una clase con validación
    public void reprogramarClase(int reprogramacionId, String nuevaFecha, String motivo) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(reprogramacionId));
        parametros.add(nuevaFecha);
        parametros.add("programada");
        parametros.add("Reprogramada: " + motivo);
        modificar(parametros);
    }
}