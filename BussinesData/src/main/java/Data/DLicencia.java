package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 * CAMBIO IMPORTANTE: Ahora licencia se relaciona con asistencia_id
 * Ya no tiene tutor_id ni fecha_licencia directamente
 */
public class DLicencia {
    private SqlConnection connection;
    
    public DLicencia(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int asistenciaId, String motivo, String estado) throws SQLException{
        String query = "INSERT INTO licencia(asistencia_id, motivo, estado, fecha_solicitud)" 
                     + " VALUES(?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, asistenciaId);
        ps.setString(2, motivo);
        ps.setString(3, estado != null ? estado : "pendiente");
        ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DLicencia.java dice: Ocurrio un error al insertar licencia guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String motivo, String estado) throws SQLException{
        String query = "UPDATE licencia SET motivo=?, estado=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, motivo);
        ps.setString(2, estado);
        ps.setInt(3, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DLicencia.java dice: Ocurrio un error al modificar licencia modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM licencia WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DLicencia.java dice: Ocurrio un error al eliminar licencia eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> licencias = new ArrayList<>();
        String query = "SELECT l.*, " +
                      "a.fecha as fecha_asistencia, a.inscripcion_id, " +
                      "i.tutor_id, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre " +
                      "FROM licencia l " +
                      "JOIN asistencia a ON l.asistencia_id = a.id " +
                      "JOIN inscripcion i ON a.inscripcion_id = i.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "ORDER BY l.fecha_solicitud DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            licencias.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("asistencia_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("tutor_nombre"),
                set.getString("alumno_nombre"),
                set.getString("fecha_asistencia"),
                set.getString("motivo"),
                set.getString("estado"),
                set.getTimestamp("fecha_solicitud").toString()
            });
        }
        return licencias;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] licencia = null;
        String query = "SELECT l.*, " +
                      "a.fecha as fecha_asistencia, a.inscripcion_id, " +
                      "i.tutor_id, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre " +
                      "FROM licencia l " +
                      "JOIN asistencia a ON l.asistencia_id = a.id " +
                      "JOIN inscripcion i ON a.inscripcion_id = i.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "WHERE l.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            licencia = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("asistencia_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("tutor_nombre"),
                set.getString("alumno_nombre"),
                set.getString("fecha_asistencia"),
                set.getString("motivo"),
                set.getString("estado"),
                set.getTimestamp("fecha_solicitud").toString()
            };
        }
        
        return licencia;
    }
    
    // Nuevo método: listar licencias por tutor
    public List<String[]> listarPorTutor(int tutorId) throws SQLException{
        List<String[]> licencias = new ArrayList<>();
        String query = "SELECT l.*, a.fecha as fecha_asistencia " +
                      "FROM licencia l " +
                      "JOIN asistencia a ON l.asistencia_id = a.id " +
                      "JOIN inscripcion i ON a.inscripcion_id = i.id " +
                      "WHERE i.tutor_id=? " +
                      "ORDER BY l.fecha_solicitud DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, tutorId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            licencias.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("asistencia_id")),
                set.getString("fecha_asistencia"),
                set.getString("motivo"),
                set.getString("estado"),
                set.getTimestamp("fecha_solicitud").toString()
            });
        }
        return licencias;
    }
    
    // Verificar si ya existe una licencia para una asistencia
    public boolean existeLicencia(int asistenciaId) throws SQLException{
        String query = "SELECT COUNT(*) as total FROM licencia WHERE asistencia_id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, asistenciaId);
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