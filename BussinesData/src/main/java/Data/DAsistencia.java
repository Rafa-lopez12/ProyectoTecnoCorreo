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
public class DAsistencia {
    private SqlConnection connection;
    
    public DAsistencia(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int inscripcionId, String fecha, String estado, String observaciones) throws SQLException{
        String query = "INSERT INTO asistencia(inscripcion_id, fecha, estado, observaciones)" 
                     + " VALUES(?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, inscripcionId);
        ps.setDate(2, Date.valueOf(fecha));
        ps.setString(3, estado);
        ps.setString(4, observaciones);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DAsistencia.java dice: Ocurrio un error al insertar asistencia guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String estado, String observaciones) throws SQLException{
        String query = "UPDATE asistencia SET estado=?, observaciones=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, estado);
        ps.setString(2, observaciones);
        ps.setInt(3, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DAsistencia.java dice: Ocurrio un error al modificar asistencia modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM asistencia WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DAsistencia.java dice: Ocurrio un error al eliminar asistencia eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> asistencias = new ArrayList<>();
        String query = "SELECT a.*, " +
                      "i.id as inscripcion_id, " +
                      "al.nombre as alumno_nombre, al.apellido as alumno_apellido, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido, " +
                      "s.nombre as servicio_nombre " +
                      "FROM asistencia a " +
                      "JOIN inscripcion i ON a.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN servicio s ON i.servicio_id = s.id " +
                      "ORDER BY a.fecha DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            asistencias.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("servicio_nombre"),
                set.getString("fecha"),
                set.getString("estado"),
                set.getString("observaciones")
            });
        }
        return asistencias;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] asistencia = null;
        String query = "SELECT a.*, " +
                      "i.id as inscripcion_id, " +
                      "al.nombre as alumno_nombre, al.apellido as alumno_apellido, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido, " +
                      "s.nombre as servicio_nombre " +
                      "FROM asistencia a " +
                      "JOIN inscripcion i ON a.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "JOIN servicio s ON i.id_servicio = s.id " +
                      "WHERE a.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            asistencia = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("alumno_nombre") + " " + set.getString("alumno_apellido"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("servicio_nombre"),
                set.getString("fecha"),
                set.getString("estado"),
                set.getString("observaciones")
            };
        }
        
        return asistencia;
    }
    

    
    public boolean existeAsistencia(int inscripcionId, String fecha) throws SQLException{
        String query = "SELECT COUNT(*) as total FROM asistencia WHERE inscripcion_id=? AND fecha=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, inscripcionId);
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