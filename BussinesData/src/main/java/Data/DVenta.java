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
public class DVenta {
    private SqlConnection connection;
    
    public DVenta(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int alumnoId, Integer propietarioId, String tipoVenta, double montoTotal, 
                       double montoPagado, double saldoPendiente, String mesCorrespondiente, 
                       String fechaVenta, String fechaVencimiento, String estado) throws SQLException{
        String query = "INSERT INTO venta(alumno_id, propietario_id, tipo_venta, monto_total, monto_pagado, " +
                      "saldo_pendiente, mes_correspondiente, fecha_venta, fecha_vencimiento, estado)" 
                     + " VALUES(?,?,?,?,?,?,?,?,?,?) RETURNING id";
        PreparedStatement ps = connection.connect().prepareStatement(query);

        ps.setInt(1, alumnoId);
        if(propietarioId != null) {
            ps.setInt(2, propietarioId);
        } else {
            ps.setNull(2, java.sql.Types.INTEGER);
        }
        ps.setString(3, tipoVenta);
        ps.setDouble(4, montoTotal);
        ps.setDouble(5, montoPagado);
        ps.setDouble(6, saldoPendiente);
        ps.setString(7, mesCorrespondiente);
        ps.setDate(8, fechaVenta != null ? Date.valueOf(fechaVenta) : new Date(System.currentTimeMillis()));
        ps.setDate(9, fechaVencimiento != null ? Date.valueOf(fechaVencimiento) : null);
        ps.setString(10, estado != null ? estado : "pendiente");

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("id");
        } else {
            System.err.println("Class DVenta.java dice: Ocurrio un error al insertar venta guardar()");
            throw new SQLException();
        }
    }
    
    
    public List<String[]> listar() throws SQLException{
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT v.*, " +
                      "a.nombre || ' ' || a.apellido as alumno_nombre, " +
                      "COALESCE(p.nombre || ' ' || p.apellido, 'Sin registrar') as propietario_nombre " +
                      "FROM venta v " +
                      "JOIN alumno a ON v.alumno_id = a.id " +
                      "LEFT JOIN propietario p ON v.propietario_id = p.id " +
                      "ORDER BY v.fecha_venta DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("alumno_id")),
                set.getString("alumno_nombre"),
                set.getString("propietario_nombre"),
                set.getString("tipo_venta"),
                String.valueOf(set.getDouble("monto_total")),
                String.valueOf(set.getDouble("monto_pagado")),
                String.valueOf(set.getDouble("saldo_pendiente")),
                set.getString("mes_correspondiente"),
                set.getString("fecha_venta"),
                set.getString("fecha_vencimiento") != null ? set.getString("fecha_vencimiento") : "N/A",
                set.getString("estado")
            });
        }
        return ventas;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] venta = null;
        String query = "SELECT v.*, " +
                      "a.nombre || ' ' || a.apellido as alumno_nombre, " +
                      "COALESCE(p.nombre || ' ' || p.apellido, 'Sin registrar') as propietario_nombre " +
                      "FROM venta v " +
                      "JOIN alumno a ON v.alumno_id = a.id " +
                      "LEFT JOIN propietario p ON v.propietario_id = p.id " +
                      "WHERE v.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            venta = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("alumno_id")),
                set.getString("alumno_nombre"),
                set.getString("propietario_nombre"),
                set.getString("tipo_venta"),
                String.valueOf(set.getDouble("monto_total")),
                String.valueOf(set.getDouble("monto_pagado")),
                String.valueOf(set.getDouble("saldo_pendiente")),
                set.getString("mes_correspondiente"),
                set.getString("fecha_venta"),
                set.getString("fecha_vencimiento") != null ? set.getString("fecha_vencimiento") : "N/A",
                set.getString("estado")
            };
        }
        
        return venta;
    }
    
    public List<String[]> listarPorAlumno(int alumnoId) throws SQLException{
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT v.*, " +
                      "COALESCE(p.nombre || ' ' || p.apellido, 'Sin registrar') as propietario_nombre " +
                      "FROM venta v " +
                      "LEFT JOIN propietario p ON v.propietario_id = p.id " +
                      "WHERE v.alumno_id=? " +
                      "ORDER BY v.fecha_venta DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, alumnoId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("propietario_nombre"),
                set.getString("tipo_venta"),
                String.valueOf(set.getDouble("monto_total")),
                String.valueOf(set.getDouble("monto_pagado")),
                String.valueOf(set.getDouble("saldo_pendiente")),
                set.getString("mes_correspondiente"),
                set.getString("fecha_venta"),
                set.getString("estado")
            });
        }
        return ventas;
    }
    
    public List<String[]> listarPorEstado(String estado) throws SQLException{
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT v.*, " +
                      "a.nombre || ' ' || a.apellido as alumno_nombre " +
                      "FROM venta v " +
                      "JOIN alumno a ON v.alumno_id = a.id " +
                      "WHERE v.estado=? " +
                      "ORDER BY v.fecha_venta DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, estado);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("alumno_nombre"),
                set.getString("tipo_venta"),
                String.valueOf(set.getDouble("monto_total")),
                String.valueOf(set.getDouble("monto_pagado")),
                String.valueOf(set.getDouble("saldo_pendiente")),
                set.getString("mes_correspondiente"),
                set.getString("fecha_venta"),
                set.getString("estado")
            });
        }
        return ventas;
    }
    
    public List<String[]> listarPorMes(String mesCorrespondiente) throws SQLException{
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT v.*, " +
                      "a.nombre || ' ' || a.apellido as alumno_nombre " +
                      "FROM venta v " +
                      "JOIN alumno a ON v.alumno_id = a.id " +
                      "WHERE v.mes_correspondiente=? " +
                      "ORDER BY a.apellido";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, mesCorrespondiente);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("alumno_nombre"),
                set.getString("tipo_venta"),
                String.valueOf(set.getDouble("monto_total")),
                String.valueOf(set.getDouble("monto_pagado")),
                String.valueOf(set.getDouble("saldo_pendiente")),
                set.getString("estado")
            });
        }
        return ventas;
    }
    
    public List<String[]> listarVencidas() throws SQLException{
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT v.*, " +
                      "a.nombre || ' ' || a.apellido as alumno_nombre, " +
                      "a.telefono as alumno_telefono " +
                      "FROM venta v " +
                      "JOIN alumno a ON v.alumno_id = a.id " +
                      "WHERE v.tipo_venta='credito' " +
                      "AND v.fecha_vencimiento < CURRENT_DATE " +
                      "AND v.estado != 'pagado' " +
                      "ORDER BY v.fecha_vencimiento";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("alumno_nombre"),
                set.getString("alumno_telefono"),
                String.valueOf(set.getDouble("saldo_pendiente")),
                set.getString("mes_correspondiente"),
                set.getString("fecha_vencimiento"),
                set.getString("estado")
            });
        }
        return ventas;
    }
    
    public void actualizarMontosPago(int ventaId, double montoPagado, double saldoPendiente) throws SQLException{
        String query = "UPDATE venta SET monto_pagado=?, saldo_pendiente=?, " +
                      "estado=CASE WHEN ? <= 0 THEN 'pagado' ELSE estado END " +
                      "WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setDouble(1, montoPagado);
        ps.setDouble(2, saldoPendiente);
        ps.setDouble(3, saldoPendiente);
        ps.setInt(4, ventaId);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DVenta.java dice: Ocurrio un error al actualizar montos");
            throw new SQLException();
        }
    }
    
    public void marcarComoPagado(int ventaId) throws SQLException{
        String query = "UPDATE venta SET estado='pagado', saldo_pendiente=0 WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, ventaId);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DVenta.java dice: Ocurrio un error al marcar como pagado");
            throw new SQLException();
        }
    }
    
    public void marcarComoVencido(int ventaId) throws SQLException{
        String query = "UPDATE venta SET estado='vencido' WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, ventaId);
        
        if(ps.executeUpdate() == 0){
            System.err.println("Class DVenta.java dice: Ocurrio un error al marcar como vencido");
            throw new SQLException();
        }
    }
    
    // Obtener estadísticas de ventas
    public String[] obtenerEstadisticas() throws SQLException{
        String[] stats = new String[6];
        String query = "SELECT " +
                      "COUNT(*) as total_ventas, " +
                      "SUM(monto_total) as total_vendido, " +
                      "SUM(monto_pagado) as total_pagado, " +
                      "SUM(saldo_pendiente) as total_pendiente, " +
                      "COUNT(CASE WHEN estado='pendiente' THEN 1 END) as ventas_pendientes, " +
                      "COUNT(CASE WHEN estado='pagado' THEN 1 END) as ventas_pagadas " +
                      "FROM venta";
        
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        if(set.next()){
            stats[0] = String.valueOf(set.getInt("total_ventas"));
            stats[1] = String.valueOf(set.getDouble("total_vendido"));
            stats[2] = String.valueOf(set.getDouble("total_pagado"));
            stats[3] = String.valueOf(set.getDouble("total_pendiente"));
            stats[4] = String.valueOf(set.getInt("ventas_pendientes"));
            stats[5] = String.valueOf(set.getInt("ventas_pagadas"));
        }
        
        return stats;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}