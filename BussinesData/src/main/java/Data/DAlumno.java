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
    
    // Ahora requiere user_id y código
    public void guardar(int userId, String ci, String codigo, String gradoEscolar, String fechaIngreso) throws SQLException{
        String query = "INSERT INTO alumno(user_id, ci, codigo, grado_escolar, fecha_ingreso)" 
                     + " VALUES(?,?,?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, userId);
        ps.setString(2, ci);           // ← NUEVO
        ps.setString(3, codigo);
        ps.setString(4, gradoEscolar);
        ps.setDate(5, Date.valueOf(fechaIngreso));
        if(ps.executeUpdate() == 0){
            System.err.println("Class DAlumno.java dice: Ocurrio un error al insertar un alumno guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String codigo, String gradoEscolar, String fechaIngreso) throws SQLException{
        String query = "UPDATE alumno SET codigo=?, grado_escolar=?, fecha_ingreso=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, codigo);
        ps.setString(2, gradoEscolar);
        ps.setDate(3, Date.valueOf(fechaIngreso));
        ps.setInt(4, id);
        
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
    
    // Listar con JOIN a usuario para obtener datos completos
    public List<String[]> listar() throws SQLException{
        List<String[]> alumnos = new ArrayList<>();
        String query = "SELECT a.*, u.nombre, u.apellido, u.telefono, u.fecha_nacimiento, u.direccion, u.estado " +
                      "FROM alumno a " +
                      "INNER JOIN usuario u ON a.user_id = u.id " +
                      "ORDER BY a.id";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            alumnos.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("user_id")),
                set.getString("codigo"),
                set.getString("nombre"),
                set.getString("apellido"),
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
        String query = "SELECT a.*, u.nombre, u.apellido, u.telefono, u.email, u.fecha_nacimiento, u.direccion, u.estado " +
                      "FROM alumno a " +
                      "INNER JOIN usuario u ON a.user_id = u.id " +
                      "WHERE a.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            alumno = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("user_id")),
                set.getString("codigo"),
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
        String query = "SELECT a.*, u.nombre, u.apellido, u.telefono, u.fecha_nacimiento, u.direccion, u.estado " +
                      "FROM alumno a " +
                      "INNER JOIN usuario u ON a.user_id = u.id " +
                      "WHERE a.grado_escolar=? " +
                      "ORDER BY u.apellido";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, gradoEscolar);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            alumnos.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("user_id")),
                set.getString("codigo"),
                set.getString("nombre"),
                set.getString("apellido"),
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
    
    // Verificar si existe un código
    public boolean existeCodigo(String codigo) throws SQLException{
        String query = "SELECT COUNT(*) as total FROM alumno WHERE codigo=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, codigo);
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