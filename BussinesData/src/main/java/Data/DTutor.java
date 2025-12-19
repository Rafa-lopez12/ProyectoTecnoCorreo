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
public class DTutor {
    private SqlConnection connection;
    
    public DTutor(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public void guardar(int userId, String rol, String grado, String email, String password) throws SQLException{
        String query = "INSERT INTO tutor(user_id, rol, grado, email, password)" 
                     + " VALUES(?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, userId);
        ps.setString(2, rol);
        ps.setString(3, grado);
        ps.setString(4, email);
        ps.setString(5, password); // Debería estar hasheado

        if(ps.executeUpdate() == 0){
            System.err.println("Class DTutor.java dice: Ocurrio un error al insertar un tutor guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String rol, String grado, String email, String password) throws SQLException{
        String query;
        PreparedStatement ps;
        
        // Si no se proporciona password, no lo actualizamos
        if(password == null || password.isEmpty()){
            query = "UPDATE tutor SET rol=?, grado=?, email=? WHERE id=?";
            ps = connection.connect().prepareStatement(query);
            ps.setString(1, rol);
            ps.setString(2, grado);
            ps.setString(3, email);
            ps.setInt(4, id);
        } else {
            query = "UPDATE tutor SET rol=?, grado=?, email=?, password=? WHERE id=?";
            ps = connection.connect().prepareStatement(query);
            ps.setString(1, rol);
            ps.setString(2, grado);
            ps.setString(3, email);
            ps.setString(4, password); // Debería estar hasheado
            ps.setInt(5, id);
        }
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DTutor.java dice: Ocurrio un error al modificar un tutor modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM tutor WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DTutor.java dice: Ocurrio un error al eliminar un tutor eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> tutores = new ArrayList<>();
        String query = "SELECT t.*, u.nombre, u.apellido, u.telefono, u.fecha_nacimiento, u.direccion, u.estado " +
                      "FROM tutor t " +
                      "INNER JOIN usuario u ON t.user_id = u.id " +
                      "ORDER BY t.id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            tutores.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("user_id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("rol"),
                set.getString("grado"),
                set.getString("email")
            });
        }
        return tutores;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] tutor = null;
        String query = "SELECT t.*, u.nombre, u.apellido, u.telefono, u.fecha_nacimiento, u.direccion, u.estado " +
                      "FROM tutor t " +
                      "INNER JOIN usuario u ON t.user_id = u.id " +
                      "WHERE t.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            tutor = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("user_id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("rol"),
                set.getString("grado"),
                set.getString("email")
            };
        }
        
        return tutor;
    }
    
    public boolean validarCredenciales(String email, String password) throws SQLException{
        String query = "SELECT password FROM tutor WHERE email=?";
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