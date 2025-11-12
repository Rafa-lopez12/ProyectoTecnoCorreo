package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class DLicencia {
    private SqlConnection connection;
    
    public DLicencia(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int tutorId, String fechaLicencia, String motivo, String estado) throws SQLException{
        String query = "INSERT INTO licencia(tutor_id, fecha_licencia, motivo, estado, fecha_solicitud)" 
                     + " VALUES(?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, tutorId);
        ps.setDate(2, Date.valueOf(fechaLicencia));
        ps.setString(3, motivo);
        ps.setString(4, estado != null ? estado : "pendiente");
        ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DLicencia.java dice: Ocurrio un error al insertar licencia guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String fechaLicencia, String motivo, String estado) throws SQLException{
        String query = "UPDATE licencia SET fecha_licencia=?, motivo=?, estado=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setDate(1, Date.valueOf(fechaLicencia));
        ps.setString(2, motivo);
        ps.setString(3, estado);
        ps.setInt(4, id);
        
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
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM licencia l " +
                      "JOIN tutor t ON l.tutor_id = t.id " +
                      "ORDER BY l.fecha_solicitud DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            licencias.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_licencia"),
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
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM licencia l " +
                      "JOIN tutor t ON l.tutor_id = t.id " +
                      "WHERE l.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            licencia = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("tutor_id")),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_licencia"),
                set.getString("motivo"),
                set.getString("estado"),
                set.getTimestamp("fecha_solicitud").toString()
            };
        }
        
        return licencia;
    }
    

    

    
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}