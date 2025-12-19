// DReporte.java
package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Rafa
 */
public class DReporte {
    private SqlConnection connection;
    
    public DReporte(){
        connection = new SqlConnection("grupo16sa", "grup016grup016*", "mail.tecnoweb.org.bo", "5432", "db_grupo16sa");
    }
    
    // 1. Reporte de Asistencias vs Faltas
    public Map<String, Integer> obtenerEstadisticasAsistencia() throws SQLException {
        Map<String, Integer> estadisticas = new HashMap<>();
        String query = "SELECT estado, COUNT(*) as total FROM asistencia GROUP BY estado";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            estadisticas.put(rs.getString("estado"), rs.getInt("total"));
        }
        
        return estadisticas;
    }
    
    // 2. Reporte de Inscripciones por Mes
    public Map<String, Integer> obtenerInscripcionesPorMes() throws SQLException {
        Map<String, Integer> inscripciones = new HashMap<>();
        String query = "SELECT TO_CHAR(fecha_inscripcion, 'YYYY-MM') as mes, COUNT(*) as total " +
                      "FROM inscripcion " +
                      "GROUP BY TO_CHAR(fecha_inscripcion, 'YYYY-MM') " +
                      "ORDER BY mes DESC " +
                      "LIMIT 12";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            inscripciones.put(rs.getString("mes"), rs.getInt("total"));
        }
        
        return inscripciones;
    }
    
    // 3. Reporte de Alumnos por Servicio
    public Map<String, Integer> obtenerAlumnosPorServicio() throws SQLException {
        Map<String, Integer> alumnos = new HashMap<>();
        String query = "SELECT s.nombre, COUNT(DISTINCT i.alumno_id) as total " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.servicio_id = s.id " +
                      "WHERE i.estado = 'activo' " +
                      "GROUP BY s.nombre";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            alumnos.put(rs.getString("nombre"), rs.getInt("total"));
        }
        
        return alumnos;
    }
    
    // 4. Reporte de Ventas por Estado
    public Map<String, Double> obtenerVentasPorEstado() throws SQLException {
        Map<String, Double> ventas = new HashMap<>();
        String query = "SELECT estado, SUM(monto_total) as total " +
                      "FROM venta " +
                      "GROUP BY estado";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            ventas.put(rs.getString("estado"), rs.getDouble("total"));
        }
        
        return ventas;
    }
  
    
    // 6. Reporte de Licencias por Estado
    public Map<String, Integer> obtenerLicenciasPorEstado() throws SQLException {
        Map<String, Integer> licencias = new HashMap<>();
        String query = "SELECT estado, COUNT(*) as total " +
                      "FROM licencia " +
                      "GROUP BY estado";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            licencias.put(rs.getString("estado"), rs.getInt("total"));
        }
        
        return licencias;
    }
    
    
    public Map<String, Double> obtenerPagosPorMes() throws SQLException {
        Map<String, Double> pagos = new LinkedHashMap<>();
        String query = "SELECT TO_CHAR(fecha_pago, 'YYYY-MM') as mes, SUM(monto) as total " +
                      "FROM pago " +
                      "GROUP BY TO_CHAR(fecha_pago, 'YYYY-MM') " +
                      "ORDER BY mes DESC " +
                      "LIMIT 12";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            pagos.put(rs.getString("mes"), rs.getDouble("total"));
        }
        
        return pagos;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
    
}