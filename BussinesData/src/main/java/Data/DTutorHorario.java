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
public class DTutorHorario {
    private SqlConnection connection;
    
    public DTutorHorario(){
        connection = new SqlConnection("grupo16sa", "grup016grup016*", "mail.tecnoweb.org.bo", "5432", "db_grupo16sa");
    }
    
    public void guardar(int tutorId, int horarioId, String fechaAsignacion) throws SQLException{
        String query = "INSERT INTO tutor_horario(tutor_id, horario_id, fecha_asignacion)" 
                     + " VALUES(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, tutorId);
        ps.setInt(2, horarioId);
        ps.setDate(3, fechaAsignacion != null ? Date.valueOf(fechaAsignacion) : new Date(System.currentTimeMillis()));

        if(ps.executeUpdate() == 0){
            System.err.println("Class DTutorHorario.java dice: Ocurrio un error al insertar tutor_horario guardar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int tutorId, int horarioId) throws SQLException{
        String query = "DELETE FROM tutor_horario WHERE tutor_id=? AND horario_id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, tutorId);
        ps.setInt(2, horarioId);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DTutorHorario.java dice: Ocurrio un error al eliminar tutor_horario eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> asignaciones = new ArrayList<>();
        String query = "SELECT t.id, " +
                       "t.nombre || ' ' || t.apellido as tutor_nombre, " +
                       "h.dia_semana, h.hora_inicio, h.hora_fin, th.fecha_asignacion " +
                       "FROM tutor_horario th " +
                       "INNER JOIN tutor t ON th.tutor_id = t.id " +
                       "INNER JOIN horario h ON th.horario_id = h.id " +
                       "ORDER BY t.apellido, h.dia_semana, h.hora_inicio";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();

        while(set.next()){
            asignaciones.add(new String[] {
                String.valueOf(set.getInt("id")),           // ID de asignación
                set.getString("tutor_nombre"),               // Nombre completo
                set.getString("dia_semana"),                 // Día
                set.getString("hora_inicio"),                // Hora inicio
                set.getString("hora_fin"),                   // Hora fin
                set.getString("fecha_asignacion")            // Fecha asignación
            });
        }
        return asignaciones;
    }
    
    public List<String[]> listarPorTutor(int tutorId) throws SQLException{
        List<String[]> horarios = new ArrayList<>();
        String query = "SELECT th.*, h.dia_semana, h.hora_inicio, h.hora_fin, h.estado " +
                      "FROM tutor_horario th " +
                      "JOIN horario h ON th.horario_id = h.id " +
                      "WHERE th.tutor_id=? " +
                      "ORDER BY h.dia_semana, h.hora_inicio";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, tutorId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            horarios.add(new String[] {
                String.valueOf(set.getInt("tutor_id")),
                String.valueOf(set.getInt("horario_id")),
                set.getString("dia_semana"),
                set.getTime("hora_inicio").toString(),
                set.getTime("hora_fin").toString(),
                set.getString("estado"),
                set.getString("fecha_asignacion")
            });
        }
        return horarios;
    }
    
    public List<String[]> listarPorHorario(int horarioId) throws SQLException{
        List<String[]> tutores = new ArrayList<>();
        String query = "SELECT th.*, t.nombre, t.apellido, t.email, t.telefono " +
                      "FROM tutor_horario th " +
                      "JOIN tutor t ON th.tutor_id = t.id " +
                      "WHERE th.horario_id=? " +
                      "ORDER BY t.apellido";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, horarioId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            tutores.add(new String[] {
                String.valueOf(set.getInt("tutor_id")),
                String.valueOf(set.getInt("horario_id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("email"),
                set.getString("telefono"),
                set.getString("fecha_asignacion")
            });
        }
        return tutores;
    }
    
    public boolean existeAsignacion(int tutorId, int horarioId) throws SQLException{
        String query = "SELECT COUNT(*) as total FROM tutor_horario WHERE tutor_id=? AND horario_id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, tutorId);
        ps.setInt(2, horarioId);
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