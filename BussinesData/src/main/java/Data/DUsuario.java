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
public class DUsuario {
    private SqlConnection connection;
    
    public DUsuario(){
        connection = new SqlConnection("grupo16sa", "grup016grup016*", "mail.tecnoweb.org.bo", "5432", "db_grupo16sa");
    }
    
    public int guardar(String nombre, String apellido, String telefono, String fechaNacimiento, String direccion, String estado) throws SQLException{
        String query = "INSERT INTO usuario(nombre, apellido, telefono, fecha_nacimiento, direccion, estado)" 
                     + " VALUES(?,?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, telefono);
        ps.setDate(4, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
        ps.setString(5, direccion);
        ps.setString(6, estado != null ? estado : "activo");

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DUsuario.java dice: Ocurrio un error al insertar un usuario guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String nombre, String apellido, String email, String telefono, String fechaNacimiento, String direccion, String estado) throws SQLException{
        String query = "UPDATE usuario SET nombre=?, apellido=?, email=?, telefono=?, fecha_nacimiento=?, direccion=?, estado=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, email);
        ps.setString(4, telefono);
        ps.setDate(5, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
        ps.setString(6, direccion);
        ps.setString(7, estado);
        ps.setInt(8, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DUsuario.java dice: Ocurrio un error al modificar un usuario modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM usuario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DUsuario.java dice: Ocurrio un error al eliminar un usuario eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario ORDER BY id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            usuarios.add(new String[] {
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
        return usuarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] usuario = null;
        String query = "SELECT * FROM usuario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            usuario = new String[]{
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
        
        return usuario;
    }
    
    public int getIdByEmail(String email) throws SQLException{
        int id = -1;
        String query = "SELECT id FROM usuario WHERE email=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, email);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            id = set.getInt("id");
        }
        
        return id;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}