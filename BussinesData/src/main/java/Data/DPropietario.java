package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class DPropietario {
    private SqlConnection connection;
    
    public DPropietario(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public void guardar(int userId, String rol, String email, String password) throws SQLException{
        String query = "INSERT INTO propietario(user_id, rol, email, password)" 
                     + " VALUES(?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, userId);
        ps.setString(2, rol);
        ps.setString(3, email);
        ps.setString(4, password); // Debería estar hasheado

        if(ps.executeUpdate() == 0){
            System.err.println("Class DPropietario.java dice: Ocurrio un error al insertar un propietario guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String rol, String email, String password) throws SQLException{
        String query;
        PreparedStatement ps;
        
        // Si no se proporciona password, no lo actualizamos
        if(password == null || password.isEmpty()){
            query = "UPDATE propietario SET rol=?, email=? WHERE id=?";
            ps = connection.connect().prepareStatement(query);
            ps.setString(1, rol);
            ps.setString(2, email);
            ps.setInt(3, id);
        } else {
            query = "UPDATE propietario SET rol=?, email=?, password=? WHERE id=?";
            ps = connection.connect().prepareStatement(query);
            ps.setString(1, rol);
            ps.setString(2, email);
            ps.setString(3, password); // Debería estar hasheado
            ps.setInt(4, id);
        }
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DPropietario.java dice: Ocurrio un error al modificar un propietario modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM propietario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DPropietario.java dice: Ocurrio un error al eliminar un propietario eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> propietarios = new ArrayList<>();
        String query = "SELECT p.*, u.nombre, u.apellido, u.telefono, u.fecha_nacimiento, u.direccion, u.estado " +
                      "FROM propietario p " +
                      "INNER JOIN usuario u ON p.user_id = u.id " +
                      "ORDER BY p.id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            propietarios.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("user_id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("rol"),
                set.getString("email")
            });
        }
        return propietarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] propietario = null;
        String query = "SELECT p.*, u.nombre, u.apellido, u.telefono, u.fecha_nacimiento, u.direccion, u.estado " +
                      "FROM propietario p " +
                      "INNER JOIN usuario u ON p.user_id = u.id " +
                      "WHERE p.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            propietario = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("user_id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("rol"),
                set.getString("email")
            };
        }
        
        return propietario;
    }
    
    public boolean validarCredenciales(String email, String password) throws SQLException{
        String query = "SELECT password FROM propietario WHERE email=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, email);
        ResultSet set = ps.executeQuery();
        
        if(set.next()){
            String storedPassword = set.getString("password");
            // Aquí deberías comparar con hash
            return storedPassword.equals(password);
        }
        
        return false;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}