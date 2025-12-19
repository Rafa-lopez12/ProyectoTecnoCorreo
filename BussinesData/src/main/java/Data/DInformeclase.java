package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * @author Rafa
 * CAMBIO IMPORTANTE: Ahora informe_clase se relaciona con asistencia_id en lugar de inscripcion_id
 */
public class DInformeclase {
    private SqlConnection connection;
    
    public DInformeclase(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int asistenciaId, String fecha, String temasVistos, String tareasAsignadas, 
                      String nivelComprension, String participacion, String cumplimientoTareas, 
                      BigDecimal calificacion, String resumen, String logros, String dificultades, 
                      String recomendaciones, String observaciones, String estado) throws SQLException{
        
        String query = "INSERT INTO informe_clase(asistencia_id, fecha, temas_vistos, tareas_asignadas, " +
                      "nivel_comprension, participacion, cumplimiento_tareas, calificacion, resumen, " +
                      "logros, dificultades, recomendaciones, observaciones, estado)" 
                     + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, asistenciaId);
        ps.setDate(2, Date.valueOf(fecha));
        ps.setString(3, temasVistos);
        ps.setString(4, tareasAsignadas);
        ps.setString(5, nivelComprension);
        ps.setString(6, participacion);
        ps.setString(7, cumplimientoTareas);
        ps.setBigDecimal(8, calificacion);
        ps.setString(9, resumen);
        ps.setString(10, logros);
        ps.setString(11, dificultades);
        ps.setString(12, recomendaciones);
        ps.setString(13, observaciones);
        ps.setString(14, estado != null ? estado : "realizada");

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DInformeClase.java dice: Ocurrio un error al insertar un informe guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String temasVistos, String tareasAsignadas, String nivelComprension, 
                         String participacion, String cumplimientoTareas, BigDecimal calificacion, 
                         String resumen, String logros, String dificultades, String recomendaciones, 
                         String observaciones, String estado) throws SQLException{
        
        String query = "UPDATE informe_clase SET temas_vistos=?, tareas_asignadas=?, nivel_comprension=?, " +
                      "participacion=?, cumplimiento_tareas=?, calificacion=?, resumen=?, logros=?, " +
                      "dificultades=?, recomendaciones=?, observaciones=?, estado=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, temasVistos);
        ps.setString(2, tareasAsignadas);
        ps.setString(3, nivelComprension);
        ps.setString(4, participacion);
        ps.setString(5, cumplimientoTareas);
        ps.setBigDecimal(6, calificacion);
        ps.setString(7, resumen);
        ps.setString(8, logros);
        ps.setString(9, dificultades);
        ps.setString(10, recomendaciones);
        ps.setString(11, observaciones);
        ps.setString(12, estado);
        ps.setInt(13, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DInformeClase.java dice: Ocurrio un error al modificar un informe modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM informe_clase WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DInformeClase.java dice: Ocurrio un error al eliminar un informe eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> informes = new ArrayList<>();
        String query = "SELECT ic.*, " +
                      "a.inscripcion_id, " +
                      "i.alumno_id, i.tutor_id, i.id_servicio, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre, " +
                      "s.nombre as servicio_nombre " +
                      "FROM informe_clase ic " +
                      "JOIN asistencia a ON ic.asistencia_id = a.id " +
                      "JOIN inscripcion i ON a.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "JOIN servicio s ON i.id_servicio = s.id " +
                      "ORDER BY ic.fecha DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            informes.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("asistencia_id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("alumno_nombre"),
                set.getString("tutor_nombre"),
                set.getString("servicio_nombre"),
                set.getString("fecha"),
                set.getString("temas_vistos"),
                set.getString("nivel_comprension"),
                set.getString("participacion"),
                set.getBigDecimal("calificacion") != null ? set.getBigDecimal("calificacion").toString() : "N/A",
                set.getString("estado")
            });
        }
        return informes;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] informe = null;
        String query = "SELECT ic.*, " +
                      "a.inscripcion_id, " +
                      "i.alumno_id, i.tutor_id, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre, " +
                      "s.nombre as servicio_nombre " +
                      "FROM informe_clase ic " +
                      "JOIN asistencia a ON ic.asistencia_id = a.id " +
                      "JOIN inscripcion i ON a.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "JOIN servicio s ON i.id_servicio = s.id " +
                      "WHERE ic.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            informe = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("asistencia_id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("alumno_nombre"),
                set.getString("tutor_nombre"),
                set.getString("servicio_nombre"),
                set.getString("fecha"),
                set.getString("temas_vistos"),
                set.getString("tareas_asignadas"),
                set.getString("nivel_comprension"),
                set.getString("participacion"),
                set.getString("cumplimiento_tareas"),
                set.getBigDecimal("calificacion") != null ? set.getBigDecimal("calificacion").toString() : "N/A",
                set.getString("resumen"),
                set.getString("logros"),
                set.getString("dificultades"),
                set.getString("recomendaciones"),
                set.getString("observaciones"),
                set.getString("estado")
            };
        }
        
        return informe;
    }
    
    // Nuevo método: listar informes por asistencia
    public List<String[]> listarPorAsistencia(int asistenciaId) throws SQLException{
        List<String[]> informes = new ArrayList<>();
        String query = "SELECT * FROM informe_clase WHERE asistencia_id=? ORDER BY fecha DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, asistenciaId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            informes.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("fecha"),
                set.getString("temas_vistos"),
                set.getString("nivel_comprension"),
                set.getString("participacion"),
                set.getBigDecimal("calificacion") != null ? set.getBigDecimal("calificacion").toString() : "N/A",
                set.getString("estado")
            });
        }
        return informes;
    }
    
    // Verificar si ya existe un informe para una asistencia en una fecha
    public boolean existeInforme(int asistenciaId, String fecha) throws SQLException{
        String query = "SELECT COUNT(*) as total FROM informe_clase WHERE asistencia_id=? AND fecha=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, asistenciaId);
        ps.setDate(2, Date.valueOf(fecha));
        ResultSet set = ps.executeQuery();
        
        if(set.next()){
            return set.getInt("total") > 0;
        }
        return false;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}