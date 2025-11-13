// BReporte.java
package Bussines;
import Data.DReporte;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Rafa
 */
public class BReporte {
    private DReporte dReporte;
    
    public BReporte(){
        dReporte = new DReporte();
    }
    
    public Map<String, Integer> obtenerEstadisticasAsistencia() throws SQLException {
        Map<String, Integer> datos = dReporte.obtenerEstadisticasAsistencia();
        dReporte.disconnect();
        return datos;
    }
    
    public Map<String, Integer> obtenerInscripcionesPorMes() throws SQLException {
        Map<String, Integer> datos = dReporte.obtenerInscripcionesPorMes();
        dReporte.disconnect();
        return datos;
    }
    
    public Map<String, Integer> obtenerAlumnosPorServicio() throws SQLException {
        Map<String, Integer> datos = dReporte.obtenerAlumnosPorServicio();
        dReporte.disconnect();
        return datos;
    }
    
    public Map<String, Double> obtenerVentasPorEstado() throws SQLException {
        Map<String, Double> datos = dReporte.obtenerVentasPorEstado();
        dReporte.disconnect();
        return datos;
    }
    

    
    public Map<String, Integer> obtenerLicenciasPorEstado() throws SQLException {
        Map<String, Integer> datos = dReporte.obtenerLicenciasPorEstado();
        dReporte.disconnect();
        return datos;
    }
    public Map<String, Double> obtenerPagosPorMes() throws SQLException {
        Map<String, Double> datos = dReporte.obtenerPagosPorMes();
        dReporte.disconnect();
        return datos;
    }
    

}