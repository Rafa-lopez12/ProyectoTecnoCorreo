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
    
    public int guardar(int alumnoId, int tutorId, String fechaInscripcion, String estado, String observaciones) throws SQLException{
        String query = "INSERT INTO inscripcion(alumno_id, tutor_id, fecha_inscripcion, estado, observaciones)" 
                     + " VALUES(?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, alumnoId);
        ps.setInt(2, tutorId);
        ps.setDate(3, fechaInscripcion != null ? Date.valueOf(fechaInscripcion) : new Date(System.currentTimeMillis()));
        ps.setString(4, estado != null ? estado : "activo");
        ps.setString(5, observaciones);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DInscripcion.java dice: Ocurrio un error al insertar una inscripcion guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String estado, String observaciones) throws SQLException{
        String query = "UPDATE inscripcion SET estado=?, observaciones=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, estado);
        ps.setString(2, observaciones);
        ps.setInt(3, id);
        
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
        String query = "SELECT " +
                      "a.nombre as alumno_nombre, a.apellido as alumno_apellido, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM inscripcion i " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "ORDER BY i.fecha_inscripcion DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            inscripciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
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
                      "a.nombre as alumno_nombre, a.apellido as alumno_apellido, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM inscripcion i " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "WHERE i.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            inscripcion = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("alumno_id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_inscripcion"),
                set.getString("estado"),
                set.getString("observaciones")
            };
        }
        
        return inscripcion;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}