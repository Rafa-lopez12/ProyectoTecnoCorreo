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
public class DAlumno {
    private SqlConnection connection;
    
    public DAlumno(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public void guardar(String nombre, String apellido, String email, String telefono, String fechaNacimiento, String direccion, String estado, String gradoEscolar, String fechaIngreso) throws SQLException{
        String query = "INSERT INTO alumno(nombre, apellido, email, telefono, fecha_nacimiento, direccion, estado, grado_escolar, fecha_ingreso)" 
                     + " VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, email);
        ps.setString(4, telefono);
        ps.setDate(5, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
        ps.setString(6, direccion);
        ps.setString(7, estado != null ? estado : "activo");
        ps.setString(8, gradoEscolar);
        ps.setDate(9, Date.valueOf(fechaIngreso));

        if(ps.executeUpdate() == 0){
            System.err.println("Class DAlumno.java dice: Ocurrio un error al insertar un alumno guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String nombre, String apellido, String email, String telefono, String fechaNacimiento, String direccion, String estado, String gradoEscolar, String fechaIngreso) throws SQLException{
        String query = "UPDATE alumno SET nombre=?, apellido=?, email=?, telefono=?, fecha_nacimiento=?, direccion=?, estado=?, grado_escolar=?, fecha_ingreso=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, email);
        ps.setString(4, telefono);
        ps.setDate(5, fechaNacimiento != null ? Date.valueOf(fechaNacimiento) : null);
        ps.setString(6, direccion);
        ps.setString(7, estado);
        ps.setString(8, gradoEscolar);
        ps.setDate(9, Date.valueOf(fechaIngreso));
        ps.setInt(10, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DAlumno.java dice: Ocurrio un error al modificar un alumno modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM alumno WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DAlumno.java dice: Ocurrio un error al eliminar un alumno eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumno ORDER BY id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            alumnos.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("email"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("grado_escolar"),
                set.getString("fecha_ingreso")
            });
        }
        return alumnos;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] alumno = null;
        String query = "SELECT * FROM alumno WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            alumno = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("email"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("grado_escolar"),
                set.getString("fecha_ingreso")
            };
        }
        
        return alumno;
    }
    
    public List<String[]> listarPorGrado(String gradoEscolar) throws SQLException{
        List<String[]> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumno WHERE grado_escolar=? ORDER BY id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, gradoEscolar);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            alumnos.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("email"),
                set.getString("telefono"),
                set.getString("fecha_nacimiento"),
                set.getString("direccion"),
                set.getString("estado"),
                set.getString("grado_escolar"),
                set.getString("fecha_ingreso")
            });
        }
        return alumnos;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}