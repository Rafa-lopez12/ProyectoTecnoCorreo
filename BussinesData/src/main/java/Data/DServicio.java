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
public class DServicio {
    private SqlConnection connection;
    
    public DServicio(){
        connection = new SqlConnection("grupo16sa", "grup016grup016*", "mail.tecnoweb.org.bo", "5432", "db_grupo16sa");
    }
    
    // Listar todos los servicios
    public List<String[]> listar() throws SQLException{
        List<String[]> servicios = new ArrayList<>();
        String query = "SELECT * FROM servicio ORDER BY nombre";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            servicios.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("descripcion"),
                String.valueOf(set.getBoolean("requiere_direccion")),
                String.valueOf(set.getBoolean("requiere_foto")),
                String.valueOf(set.getBoolean("estado"))
            });
        }
        return servicios;
    }
    
    // Ver un servicio específico
    public String[] ver(int id) throws SQLException{
        String[] servicio = null;
        String query = "SELECT * FROM servicio WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            servicio = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("descripcion"),
                String.valueOf(set.getBoolean("requiere_direccion")),
                String.valueOf(set.getBoolean("requiere_foto")),
                String.valueOf(set.getBoolean("estado"))
            };
        }
        
        return servicio;
    }
    
    
    // Cambiar estado (activar/desactivar)
    public void cambiarEstado(int id, boolean estado) throws SQLException{
        String query = "UPDATE servicio SET estado=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setBoolean(1, estado);
        ps.setInt(2, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DServicio.java dice: Ocurrio un error al cambiar estado cambiarEstado()");
            throw new SQLException();
        }
    }
    
    // Listar alumnos inscritos en un servicio
    public List<String[]> listarAlumnosPorServicio(int servicioId) throws SQLException{
        List<String[]> alumnos = new ArrayList<>();
        String query = "SELECT a.id, a.nombre, a.apellido, a.email, a.telefono, " +
                      "a.grado_escolar, i.fecha_inscripcion, i.estado as estado_inscripcion, " +
                      "t.nombre as tutor_nombre, t.apellido as tutor_apellido " +
                      "FROM inscripcion i " +
                      "JOIN alumno a ON i.alumno_id = a.id " +
                      "JOIN tutor t ON i.tutor_id = t.id " +
                      "WHERE i.id_servicio = ? " +
                      "ORDER BY i.fecha_inscripcion DESC";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, servicioId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            alumnos.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("email"),
                set.getString("telefono"),
                set.getString("grado_escolar"),
                set.getString("fecha_inscripcion"),
                set.getString("estado_inscripcion"),
                set.getString("tutor_nombre") + " " + set.getString("tutor_apellido")
            });
        }
        return alumnos;
    }
    
    // Obtener estadísticas de un servicio
    public String[] obtenerEstadisticas(int servicioId) throws SQLException{
        String[] stats = new String[5];
        String query = "SELECT " +
                      "COUNT(*) as total_inscritos, " +
                      "COUNT(CASE WHEN i.estado = 'activo' THEN 1 END) as activos, " +
                      "COUNT(CASE WHEN i.estado = 'retirado' THEN 1 END) as retirados, " +
                      "COUNT(CASE WHEN i.estado = 'finalizado' THEN 1 END) as finalizados, " +
                      "s.nombre as servicio_nombre " +
                      "FROM servicio s " +
                      "LEFT JOIN inscripcion i ON s.id = i.id_servicio " +
                      "WHERE s.id = ? " +
                      "GROUP BY s.id, s.nombre";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, servicioId);
        ResultSet set = ps.executeQuery();
        
        if(set.next()){
            stats[0] = set.getString("servicio_nombre");
            stats[1] = String.valueOf(set.getInt("total_inscritos"));
            stats[2] = String.valueOf(set.getInt("activos"));
            stats[3] = String.valueOf(set.getInt("retirados"));
            stats[4] = String.valueOf(set.getInt("finalizados"));
        }
        
        return stats;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}