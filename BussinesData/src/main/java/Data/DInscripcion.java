package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PGobject;

/**
 * @author Rafa
 */
public class DInscripcion {
    private SqlConnection connection;
    
    public DInscripcion(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int servicioId, int alumnoId, int tutorId, String horarios, String fechaInscripcion, String estado, String observaciones) throws SQLException{
        String query = "INSERT INTO inscripcion(id_servicio, alumno_id, tutor_id, horarios, fecha_inscripcion, estado, observaciones)" 
                     + " VALUES(?,?,?,?::json,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, servicioId);
        ps.setInt(2, alumnoId);
        ps.setInt(3, tutorId);
        
        // Manejar el JSON para horarios
        if(horarios != null && !horarios.isEmpty()) {
            ps.setString(4, horarios);
        } else {
            ps.setNull(4, java.sql.Types.OTHER);
        }
        
        ps.setDate(5, fechaInscripcion != null ? Date.valueOf(fechaInscripcion) : new Date(System.currentTimeMillis()));
        ps.setString(6, estado != null ? estado : "activo");
        ps.setString(7, observaciones);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DInscripcion.java dice: Ocurrio un error al insertar una inscripcion guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String horarios, String estado, String observaciones) throws SQLException{
        String query = "UPDATE inscripcion SET horarios=?::json, estado=?, observaciones=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        // Manejar el JSON para horarios
        if(horarios != null && !horarios.isEmpty()) {
            ps.setString(1, horarios);
        } else {
            ps.setNull(1, java.sql.Types.OTHER);
        }
        
        ps.setString(2, estado);
        ps.setString(3, observaciones);
        ps.setInt(4, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DInscripcion.java dice: Ocurrio un error al modificar una inscripcion modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM inscripcion WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DInscripcion.java dice: Ocurrio un error al eliminar una inscripcion eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> inscripciones = new ArrayList<>();
        String query = "SELECT i.*, " +
                      "s.nombre as servicio_nombre, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "a.codigo as alumno_codigo, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.id_servicio = s.id " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN usuario u_al ON a.user_id = u_al.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "ORDER BY i.fecha_inscripcion DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("id_servicio")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("servicio_nombre"),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("tutor_nombre"),
                set.getString("horarios") != null ? set.getString("horarios") : "[]",
                set.getString("fecha_inscripcion"),
                set.getString("estado"),
                set.getString("observaciones")
            });
        }
        return inscripciones;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] inscripcion = null;
        String query = "SELECT i.*, " +
                      "s.nombre as servicio_nombre, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "a.codigo as alumno_codigo, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.id_servicio = s.id " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN usuario u_al ON a.user_id = u_al.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "WHERE i.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            inscripcion = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("id_servicio")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("servicio_nombre"),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("tutor_nombre"),
                set.getString("horarios") != null ? set.getString("horarios") : "[]",
                set.getString("fecha_inscripcion"),
                set.getString("estado"),
                set.getString("observaciones")
            };
        }
        
        return inscripcion;
    }
    
    public List<String[]> listarPorAlumno(int alumnoId) throws SQLException{
        List<String[]> inscripciones = new ArrayList<>();
        String query = "SELECT i.*, " +
                      "s.nombre as servicio_nombre, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.id_servicio = s.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "WHERE i.alumno_id=? " +
                      "ORDER BY i.fecha_inscripcion DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, alumnoId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("id_servicio")),
                set.getString("servicio_nombre"),
                set.getString("tutor_nombre"),
                set.getString("horarios") != null ? set.getString("horarios") : "[]",
                set.getString("fecha_inscripcion"),
                set.getString("estado"),
                set.getString("observaciones")
            });
        }
        return inscripciones;
    }
    
    public List<String[]> listarPorTutor(int tutorId) throws SQLException{
        List<String[]> inscripciones = new ArrayList<>();
        String query = "SELECT i.*, " +
                      "s.nombre as servicio_nombre, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "a.codigo as alumno_codigo, " +
                      "a.grado_escolar " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.id_servicio = s.id " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN usuario u_al ON a.user_id = u_al.id " +
                      "WHERE i.tutor_id=? " +
                      "ORDER BY u_al.apellido";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, tutorId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("id_servicio")),
                set.getString("servicio_nombre"),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("grado_escolar"),
                set.getString("horarios") != null ? set.getString("horarios") : "[]",
                set.getString("fecha_inscripcion"),
                set.getString("estado"),
                set.getString("observaciones")
            });
        }
        return inscripciones;
    }
    
    public List<String[]> listarPorServicio(int servicioId) throws SQLException{
        List<String[]> inscripciones = new ArrayList<>();
        String query = "SELECT i.*, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "a.codigo as alumno_codigo, " +
                      "u_tu.nombre || ' ' || u_tu.apellido as tutor_nombre " +
                      "FROM inscripcion i " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN usuario u_al ON a.user_id = u_al.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN usuario u_tu ON t.user_id = u_tu.id " +
                      "WHERE i.id_servicio=? " +
                      "ORDER BY i.fecha_inscripcion DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, servicioId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("tutor_nombre"),
                set.getString("fecha_inscripcion"),
                set.getString("estado")
            });
        }
        return inscripciones;
    }
    
    public boolean existeInscripcion(int alumnoId, int tutorId, int servicioId) throws SQLException{
        String query = "SELECT COUNT(*) as total FROM inscripcion WHERE alumno_id=? AND tutor_id=? AND id_servicio=? AND estado='activo'";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, alumnoId);
        ps.setInt(2, tutorId);
        ps.setInt(3, servicioId);
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