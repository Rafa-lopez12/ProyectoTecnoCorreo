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
public class DReprogramacion {
    private SqlConnection connection;
    
    public DReprogramacion(){
        connection = new SqlConnection("grupo16sa", "grup016grup016*", "mail.tecnoweb.org.bo", "5432", "db_grupo16sa");
    }
    
    public int guardar(int licenciaId, String fechaOriginal, String fechaNueva, String estado, String observaciones) throws SQLException{
        String query = "INSERT INTO reprogramacion(licencia_id, fecha_original, fecha_nueva, estado, observaciones)" 
                     + " VALUES(?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, licenciaId);
        ps.setDate(2, Date.valueOf(fechaOriginal));
        ps.setDate(3, Date.valueOf(fechaNueva));
        ps.setString(4, estado != null ? estado : "programada");
        ps.setString(5, observaciones);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DReprogramacion.java dice: Ocurrio un error al insertar reprogramacion guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String fechaNueva, String estado, String observaciones) throws SQLException{
        String query = "UPDATE reprogramacion SET fecha_nueva=?, estado=?, observaciones=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setDate(1, Date.valueOf(fechaNueva));
        ps.setString(2, estado);
        ps.setString(3, observaciones);
        ps.setInt(4, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DReprogramacion.java dice: Ocurrio un error al modificar reprogramacion modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM reprogramacion WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DReprogramacion.java dice: Ocurrio un error al eliminar reprogramacion eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> reprogramaciones = new ArrayList<>();
        String query = "SELECT r.*, " +
                      "l.tutor_id, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido, " +
                      "l.fecha_licencia, l.motivo " +
                      "FROM reprogramacion r " +
                      "JOIN licencia l ON r.licencia_id = l.id " +
                      "JOIN tutor t ON l.tutor_id = t.id " +
                      "ORDER BY r.fecha_nueva DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            reprogramaciones.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("licencia_id")),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_licencia"),
                set.getString("fecha_original"),
                set.getString("fecha_nueva"),
                set.getString("estado"),
                set.getString("observaciones")
            });
        }
        return reprogramaciones;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] reprogramacion = null;
        String query = "SELECT r.*, " +
                      "l.tutor_id, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido, " +
                      "l.fecha_licencia, l.motivo " +
                      "FROM reprogramacion r " +
                      "JOIN licencia l ON r.licencia_id = l.id " +
                      "JOIN tutor t ON l.tutor_id = t.id " +
                      "WHERE r.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            reprogramacion = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("licencia_id")),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido"),
                set.getString("fecha_licencia"),
                set.getString("fecha_original"),
                set.getString("fecha_nueva"),
                set.getString("estado"),
                set.getString("observaciones")
            };
        }
        
        return reprogramacion;
    }
    

    

    
    public void marcarRealizada(int id) throws SQLException{
        String query = "UPDATE reprogramacion SET estado='realizada' WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DReprogramacion.java dice: Ocurrio un error al marcar reprogramacion como realizada");
            throw new SQLException();
        }
    }
    
    public void cancelar(int id) throws SQLException{
        String query = "UPDATE reprogramacion SET estado='cancelada' WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DReprogramacion.java dice: Ocurrio un error al cancelar reprogramacion");
            throw new SQLException();
        }
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}