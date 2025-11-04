package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 * @author Rafa
 */
public class DPropietario {
    private SqlConnection connection;
    
    public DPropietario(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public void guardar(String nombre, String apellido, String email, String password, String telefono, String fechaNacimiento, String direccion, String estado) throws SQLException{
        String query = "INSERT INTO propietario(nombre, apellido, email, password, telefono, fecha_nacimiento, direccion, estado)" 
                     + " VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, email);
        ps.setString(4, password); // Aquí deberías hashear la contraseña
        ps.setString(5, telefono);
        ps.setDate(6, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
        ps.setString(7, direccion);
        ps.setString(8, estado != null ? estado : "activo");

        if(ps.executeUpdate() == 0){
            System.err.println("Class DPropietario.java dice: Ocurrio un error al insertar un propietario guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String nombre, String apellido, String email, String password, String telefono, String fechaNacimiento, String direccion, String estado) throws SQLException{
        String query;
        PreparedStatement ps;
        
        // Si no se proporciona password, no lo actualizamos
        if(password == null || password.isEmpty()){
            query = "UPDATE propietario SET nombre=?, apellido=?, email=?, telefono=?, fecha_nacimiento=?, direccion=?, estado=?" 
                  + " WHERE id=?";
            ps = connection.connect().prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, telefono);
            ps.setDate(5, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
            ps.setString(6, direccion);
            ps.setString(7, estado);
            ps.setInt(8, id);
        } else {
            query = "UPDATE propietario SET nombre=?, apellido=?, email=?, password=?, telefono=?, fecha_nacimiento=?, direccion=?, estado=?" 
                  + " WHERE id=?";
            ps = connection.connect().prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setString(4, password); // Aquí deberías hashear la contraseña
            ps.setString(5, telefono);
            ps.setDate(6, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
            ps.setString(7, direccion);
            ps.setString(8, estado);
            ps.setInt(9, id);
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
        String query = "SELECT id, nombre, apellido, email, telefono, fecha_nacimiento, direccion, estado FROM propietario ORDER BY id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            propietarios.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("email"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado")
            });
        }
        return propietarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] propietario = null;
        String query = "SELECT id, nombre, apellido, email, telefono, fecha_nacimiento, direccion, estado FROM propietario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            propietario = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("email"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado")
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