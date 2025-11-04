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
public class DTutor {
    private SqlConnection connection;
    
    public DTutor(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public void guardar(String nombre, String apellido, String email, String telefono, String fechaNacimiento, String direccion, String estado) throws SQLException{
        String query = "INSERT INTO tutor(nombre, apellido, email, telefono, fecha_nacimiento, direccion, estado)" 
                     + " VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, email);
        ps.setString(4, telefono);
        ps.setDate(5, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
        ps.setString(6, direccion);
        ps.setString(7, estado != null ? estado : "activo");

        if(ps.executeUpdate() == 0){
            System.err.println("Class DTutor.java dice: Ocurrio un error al insertar un tutor guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String nombre, String apellido, String email, String telefono, String fechaNacimiento, String direccion, String estado) throws SQLException{
        String query = "UPDATE tutor SET nombre=?, apellido=?, email=?, telefono=?, fecha_nacimiento=?, direccion=?, estado=?" 
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
        String query = "SELECT * FROM tutor ORDER BY id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            tutores.add(new String[] {
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
        return tutores;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] tutor = null;
        String query = "SELECT * FROM tutor WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            tutor = new String[]{
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
        
        return tutor;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}