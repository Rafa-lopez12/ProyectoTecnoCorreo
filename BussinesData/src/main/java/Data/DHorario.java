package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class DHorario {
    private SqlConnection connection;
    
    public DHorario(){
        connection = new SqlConnection("grupo16sa", "grup016grup016*", "mail.tecnoweb.org.bo", "5432", "db_grupo16sa");
    }
    
    public int guardar(String diaSemana, String horaInicio, String horaFin, String estado) throws SQLException{
        String query = "INSERT INTO horario(dia_semana, hora_inicio, hora_fin, estado)" 
                     + " VALUES(?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setString(1, diaSemana);
        ps.setTime(2, Time.valueOf(horaInicio));
        ps.setTime(3, Time.valueOf(horaFin));
        ps.setString(4, estado != null ? estado : "activo");

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DHorario.java dice: Ocurrio un error al insertar un horario guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String diaSemana, String horaInicio, String horaFin, String estado) throws SQLException{
        String query = "UPDATE horario SET dia_semana=?, hora_inicio=?, hora_fin=?, estado=?" 
                     + " WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setString(1, diaSemana);
        ps.setTime(2, Time.valueOf(horaInicio));
        ps.setTime(3, Time.valueOf(horaFin));
        ps.setString(4, estado);
        ps.setInt(5, id);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DHorario.java dice: Ocurrio un error al modificar un horario modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM horario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate() == 0){
            System.err.println("Class DHorario.java dice: Ocurrio un error al eliminar un horario eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> horarios = new ArrayList<>();
        String query = "SELECT * FROM horario ORDER BY dia_semana, hora_inicio";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            horarios.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("dia_semana"),
                set.getTime("hora_inicio").toString(),
                set.getTime("hora_fin").toString(),
                set.getString("estado")
            });
        }
        return horarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] horario = null;
        String query = "SELECT * FROM horario WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            horario = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("dia_semana"),
                set.getTime("hora_inicio").toString(),
                set.getTime("hora_fin").toString(),
                set.getString("estado")
            };
        }
        
        return horario;
    }
    
    public List<String[]> listarPorDia(String diaSemana) throws SQLException{
        List<String[]> horarios = new ArrayList<>();
        String query = "SELECT * FROM horario WHERE dia_semana=? ORDER BY hora_inicio";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, diaSemana);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            horarios.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("dia_semana"),
                set.getTime("hora_inicio").toString(),
                set.getTime("hora_fin").toString(),
                set.getString("estado")
            });
        }
        return horarios;
    }
    
    public List<String[]> listarActivos() throws SQLException{
        List<String[]> horarios = new ArrayList<>();
        String query = "SELECT * FROM horario WHERE estado='activo' ORDER BY dia_semana, hora_inicio";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            horarios.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("dia_semana"),
                set.getTime("hora_inicio").toString(),
                set.getTime("hora_fin").toString(),
                set.getString("estado")
            });
        }
        return horarios;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
    
    public ArrayList<String[]> listarDisponibles() throws SQLException {
        ArrayList<String[]> horariosDisponibles = new ArrayList<>();

        String query = "SELECT DISTINCT h.id, h.dia_semana, h.hora_inicio, h.hora_fin, " +
                       "CONCAT(u.nombre, ' ', u.apellido) as tutor_nombre " +
                       "FROM horario h " +
                       "JOIN tutor_horario th ON h.id = th.horario_id " +
                       "JOIN tutor t ON th.tutor_id = t.id " +
                       "JOIN usuario u ON t.user_id = u.id " +
                       "WHERE h.estado = 'activo' " +
                       "AND NOT EXISTS ( " +
                       "  SELECT 1 FROM inscripcion i " +
                       "  WHERE i.tutor_id = t.id " +
                       "  AND i.estado = 'activo' " +
                       "  AND i.horarios::jsonb @> jsonb_build_array(jsonb_build_object(" +
                       "    'dia', h.dia_semana, " +
                       "    'hora_inicio', TO_CHAR(h.hora_inicio, 'HH24:MI'), " +
                       "    'hora_fin', TO_CHAR(h.hora_fin, 'HH24:MI')" +
                       "  )) " +
                       ") " +
                       "ORDER BY h.dia_semana, h.hora_inicio";

        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            String[] horario = new String[5];
            horario[0] = String.valueOf(rs.getInt("id"));
            horario[1] = rs.getString("dia_semana");
            horario[2] = rs.getString("hora_inicio");
            horario[3] = rs.getString("hora_fin");
            horario[4] = rs.getString("tutor_nombre");
            horariosDisponibles.add(horario);
        }

        rs.close();
        ps.close();

        return horariosDisponibles;
    }
    
}