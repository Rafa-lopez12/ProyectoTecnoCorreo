package Bussines;
import Data.DServicio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BServicio {
    private DServicio dServicio;
    
    public BServicio(){
        dServicio = new DServicio();
    }
    
    // Listar todos los servicios
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> servicios = (ArrayList<String[]>) dServicio.listar();
        dServicio.disconnect();
        return servicios;
    }
    
    
    // Ver un servicio específico
    public String[] ver(int id) throws SQLException{
        String[] servicio = dServicio.ver(id);
        dServicio.disconnect();
        return servicio;
    }
    
    // Activar un servicio
    public void activar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dServicio.cambiarEstado(Integer.parseInt(parametros.get(0)), true);
        dServicio.disconnect();
    }
    
    // Desactivar un servicio
    public void desactivar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dServicio.cambiarEstado(Integer.parseInt(parametros.get(0)), false);
        dServicio.disconnect();
    }
    
    // ====== NUEVAS FUNCIONALIDADES ======
    
    // Listar alumnos inscritos en un servicio específico
    public ArrayList<String[]> listarAlumnosPorServicio(int servicioId) throws SQLException{
        ArrayList<String[]> alumnos = (ArrayList<String[]>) dServicio.listarAlumnosPorServicio(servicioId);
        dServicio.disconnect();
        return alumnos;
    }
    
    // Obtener estadísticas de un servicio
    public String[] obtenerEstadisticas(int servicioId) throws SQLException{
        String[] stats = dServicio.obtenerEstadisticas(servicioId);
        dServicio.disconnect();
        return stats;
    }
}