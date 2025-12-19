package Data;

import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class DPago {
    private SqlConnection connection;
    
    public DPago() {
        connection = new SqlConnection("grupo16sa", "grup016grup016*", "mail.tecnoweb.org.bo", "5432", "db_grupo16sa");
    }
    
    public int guardar(int ventaId, double monto, String metodoPago, String observaciones) throws SQLException {
        String query = "INSERT INTO pago(venta_id, monto, fecha_pago, metodo_pago, observaciones) " 
                     + "VALUES(?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, ventaId);
        ps.setDouble(2, monto);
        ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        ps.setString(4, metodoPago);
        ps.setString(5, observaciones);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            System.err.println("Class DPago.java dice: Ocurrió un error al insertar pago guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, double monto, String metodoPago, String observaciones) throws SQLException {
        String query = "UPDATE pago SET monto=?, metodo_pago=?, observaciones=? WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        
        ps.setDouble(1, monto);
        ps.setString(2, metodoPago);
        ps.setString(3, observaciones);
        ps.setInt(4, id);
        
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPago.java dice: Ocurrió un error al modificar pago modificar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException {
        List<String[]> pagos = new ArrayList<>();
        String query = "SELECT p.*, " +
                      "v.alumno_id, " +
                      "a.nombre || ' ' || a.apellido AS alumno_nombre, " +
                      "v.mes_correspondiente " +
                      "FROM pago p " +
                      "JOIN venta v ON p.venta_id = v.id " +
                      "JOIN alumno a ON v.alumno_id = a.id " +
                      "ORDER BY p.fecha_pago DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while (set.next()) {
            pagos.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("venta_id")),
                set.getString("alumno_nombre"),
                set.getString("mes_correspondiente"),
                String.valueOf(set.getDouble("monto")),
                set.getTimestamp("fecha_pago").toString(),
                set.getString("metodo_pago"),
                set.getString("observaciones") != null ? set.getString("observaciones") : ""
            });
        }
        return pagos;
    }
    
    public String[] ver(int id) throws SQLException {
        String[] pago = null;
        String query = "SELECT p.*, " +
                      "v.alumno_id, " +
                      "a.nombre || ' ' || a.apellido AS alumno_nombre, " +
                      "v.mes_correspondiente " +
                      "FROM pago p " +
                      "JOIN venta v ON p.venta_id = v.id " +
                      "JOIN alumno a ON v.alumno_id = a.id " +
                      "WHERE p.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            pago = new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("venta_id")),
                set.getString("alumno_nombre"),
                set.getString("mes_correspondiente"),
                String.valueOf(set.getDouble("monto")),
                set.getTimestamp("fecha_pago").toString(),
                set.getString("metodo_pago"),
                set.getString("observaciones") != null ? set.getString("observaciones") : ""
            };
        }
        
        return pago;
    }
    
    public List<String[]> listarPorVenta(int ventaId) throws SQLException {
        List<String[]> pagos = new ArrayList<>();
        String query = "SELECT * FROM pago WHERE venta_id=? ORDER BY fecha_pago DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, ventaId);
        ResultSet set = ps.executeQuery();
        
        while (set.next()) {
            pagos.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getDouble("monto")),
                set.getTimestamp("fecha_pago").toString(),
                set.getString("metodo_pago"),
                set.getString("observaciones") != null ? set.getString("observaciones") : ""
            });
        }
        return pagos;
    }
    
    public List<String[]> listarPorAlumno(int alumnoId) throws SQLException {
        List<String[]> pagos = new ArrayList<>();
        String query = "SELECT p.*, v.mes_correspondiente " +
                      "FROM pago p " +
                      "JOIN venta v ON p.venta_id = v.id " +
                      "WHERE v.alumno_id=? " +
                      "ORDER BY p.fecha_pago DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, alumnoId);
        ResultSet set = ps.executeQuery();
        
        while (set.next()) {
            pagos.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("venta_id")),
                set.getString("mes_correspondiente"),
                String.valueOf(set.getDouble("monto")),
                set.getTimestamp("fecha_pago").toString(),
                set.getString("metodo_pago")
            });
        }
        return pagos;
    }
    
    public double obtenerTotalPagadoPorVenta(int ventaId) throws SQLException {
        double total = 0;
        String query = "SELECT COALESCE(SUM(monto), 0) AS total FROM pago WHERE venta_id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, ventaId);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            total = set.getDouble("total");
        }
        
        return total;
    }
    
    public void disconnect() {
        if (connection != null) {
            connection.closeConnection();
        }
    }
}
