package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class DInscripcion {
    private SqlConnection connection;
    
    public DInscripcion(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int servicioId, int alumnoId, int tutorId, String fechaInscripcion, String direccion, String fotoUrl, String estado, String observaciones) throws SQLException{
        String query = "INSERT INTO inscripcion(servicio_id, alumno_id, tutor_id, fecha_inscripcion, direccion, foto_url, estado, observaciones)" 
                     + " VALUES(?,?,?,?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, servicioId);
        ps.setInt(2, alumnoId);
        ps.setInt(3, tutorId);
        ps.setDate(4, fechaInscripcion != null ? Date.valueOf(fechaInscripcion) : new Date(System.currentTimeMillis()));
        ps.setString(5, direccion);
        ps.setString(6, fotoUrl);
        ps.setString(7, estado != null ? estado : "activo");
        ps.setString(8, observaciones);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DInscripcion.java dice: Ocurrio un error al insertar una inscripcion guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String direccion, String fotoUrl, String estado, String observaciones) throws SQLException{
        String query = "UPDATE inscripcion SET direccion=?, foto_url=?, estado=?, observaciones=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, direccion);
        ps.setString(2, fotoUrl);
        ps.setString(3, estado);
        ps.setString(4, observaciones);
        ps.setInt(5, id);
        
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
                      "a.nombre as alumno_nombre, a.apellido as alumno_apellido, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.servicio_id = s.id " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "ORDER BY i.fecha_inscripcion DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("servicio_id")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("servicio_nombre"),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_inscripcion"),
                set.getString("direccion"),
                set.getString("foto_url"),
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
                      "a.nombre as alumno_nombre, a.apellido as alumno_apellido, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.servicio_id = s.id " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "WHERE i.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            inscripcion = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("servicio_id")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("servicio_nombre"),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_inscripcion"),
                set.getString("direccion"),
                set.getString("foto_url"),
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
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.servicio_id = s.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "WHERE i.alumno_id=? " +
                      "ORDER BY i.fecha_inscripcion DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, alumnoId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("servicio_id")),
                set.getString("servicio_nombre"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
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
                      "a.nombre as alumno_nombre, a.apellido as alumno_apellido, " +
                      "a.grado_escolar " +
                      "FROM inscripcion i " +
                      "JOIN servicio s ON i.servicio_id = s.id " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "WHERE i.tutor_id=? " +
                      "ORDER BY a.apellido";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, tutorId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("servicio_id")),
                set.getString("servicio_nombre"),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("grado_escolar"),
                set.getString("fecha_inscripcion"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("observaciones")
            });
        }
        return inscripciones;
    }
    
    public List<String[]> listarPorServicio(int servicioId) throws SQLException{
        List<String[]> inscripciones = new ArrayList<>();
        String query = "SELECT i.*, " +
                      "a.nombre as alumno_nombre, a.apellido as alumno_apellido, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM inscripcion i " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "WHERE i.servicio_id=? " +
                      "ORDER BY i.fecha_inscripcion DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, servicioId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_inscripcion"),
                set.getString("estado")
            });
        }
        return inscripciones;
    }
    
    public boolean existeInscripcion(int alumnoId, int tutorId, int servicioId) throws SQLException{
        String query = "SELECT COUNT(*) as total FROM inscripcion WHERE alumno_id=? AND tutor_id=? AND servicio_id=? AND estado='activo'";
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