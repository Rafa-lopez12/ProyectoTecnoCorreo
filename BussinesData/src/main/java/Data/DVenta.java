package Data;
import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;

/**
 * @author Rafa
 * CAMBIO IMPORTANTE: Ahora venta se relaciona con inscripcion_id en lugar de alumno_id
 * Además, se agregó el campo cuotas
 */
public class DVenta {
    private SqlConnection connection;
    
    public DVenta(){
        connection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
    }
    
    public int guardar(int inscripcionId, Integer propietarioId, String tipoVenta, Integer cuotas,
                       double montoTotal, double montoPagado, double saldoPendiente, 
                       String mesCorrespondiente, String fechaVenta, String fechaVencimiento, 
                       String estado) throws SQLException{
        
        Connection conn = connection.connect();
        
        // 1. Insertar la venta
        String query = "INSERT INTO venta(inscripcion_id, propietario_id, tipo_venta, cuotas, monto_total, " +
                      "monto_pagado, saldo_pendiente, mes_correspondiente, fecha_venta, fecha_vencimiento, estado)" 
                     + " VALUES(?,?,?,?,?,?,?,?,?,?,?) RETURNING id";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, inscripcionId);
        if(propietarioId != null) {
            ps.setInt(2, propietarioId);
        } else {
            ps.setNull(2, java.sql.Types.INTEGER);
        }
        ps.setString(3, tipoVenta);
        if(cuotas != null) {
            ps.setInt(4, cuotas);
        } else {
            ps.setNull(4, java.sql.Types.INTEGER);
        }
        ps.setDouble(5, montoTotal);
        ps.setDouble(6, montoPagado);
        ps.setDouble(7, saldoPendiente);
        ps.setString(8, mesCorrespondiente);
        ps.setDate(9, fechaVenta != null ? Date.valueOf(fechaVenta) : new Date(System.currentTimeMillis()));
        ps.setDate(10, fechaVencimiento != null ? Date.valueOf(fechaVencimiento) : null);
        ps.setString(11, estado != null ? estado : "pendiente");
        
        ResultSet rs = ps.executeQuery();
        int ventaId = 0;
        
        if(rs.next()){
            ventaId = rs.getInt("id");
        } else {
            System.err.println("Class DVenta.java dice: Ocurrio un error al insertar venta guardar()");
            throw new SQLException();
        }
        
        rs.close();
        ps.close();
        
        // 2. Si es crédito, generar las cuotas automáticamente
        if("credito".equalsIgnoreCase(tipoVenta) && cuotas != null && cuotas > 0) {
            generarCuotas(conn, ventaId, cuotas, montoTotal, fechaVenta);
            System.out.println("✓ Venta #" + ventaId + " creada con " + cuotas + " cuotas generadas automáticamente");
        } else {
            System.out.println("✓ Venta #" + ventaId + " creada (contado)");
        }
        
        return ventaId;
    }
    
        private void generarCuotas(Connection conn, int ventaId, int numCuotas, double montoTotal, String fechaVenta) throws SQLException {
            double montoPorCuota = Math.round((montoTotal / numCuotas) * 100.0) / 100.0; // Redondeo a 2 decimales

            LocalDate fechaBase = fechaVenta != null ? LocalDate.parse(fechaVenta) : LocalDate.now();

            String sqlPago = "INSERT INTO pago (venta_id, monto, metodo_pago, fecha_pago, observaciones, estado, fecha_vencimiento) " +
                            "VALUES (?, ?, 'pendiente', NULL, ?, 'pendiente', ?)";

            PreparedStatement psPago = conn.prepareStatement(sqlPago);

            for (int i = 1; i <= numCuotas; i++) {
                // Calcular fecha de vencimiento: +1 mes, +2 meses, etc.
                LocalDate fechaVenc = fechaBase.plusMonths(i);

                psPago.setInt(1, ventaId);
                psPago.setDouble(2, montoPorCuota);
                psPago.setString(3, "Cuota " + i + " de " + numCuotas);
                psPago.setDate(4, Date.valueOf(fechaVenc));

                psPago.executeUpdate();
            }

            psPago.close();
        }
    
    
    public List<String[]> listar() throws SQLException{
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT v.*, " +
                      "i.alumno_id, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "al.codigo as alumno_codigo, " +
                      "COALESCE(u_pr.nombre || ' ' || u_pr.apellido, 'Sin registrar') as propietario_nombre " +
                      "FROM venta v " +
                      "JOIN inscripcion i ON v.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "LEFT JOIN propietario p ON v.propietario_id = p.id " +
                      "LEFT JOIN usuario u_pr ON p.user_id = u_pr.id " +
                      "ORDER BY v.fecha_venta DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                String.valueOf(set.getInt("alumno_id")),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("propietario_nombre"),
                set.getString("tipo_venta"),
                set.getInt("cuotas") > 0 ? String.valueOf(set.getInt("cuotas")) : "N/A",
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
                      "i.alumno_id, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "al.codigo as alumno_codigo, " +
                      "COALESCE(u_pr.nombre || ' ' || u_pr.apellido, 'Sin registrar') as propietario_nombre " +
                      "FROM venta v " +
                      "JOIN inscripcion i ON v.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "LEFT JOIN propietario p ON v.propietario_id = p.id " +
                      "LEFT JOIN usuario u_pr ON p.user_id = u_pr.id " +
                      "WHERE v.id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        
        if (set.next()) {
            venta = new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                String.valueOf(set.getInt("alumno_id")),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("propietario_nombre"),
                set.getString("tipo_venta"),
                set.getInt("cuotas") > 0 ? String.valueOf(set.getInt("cuotas")) : "N/A",
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
                      "COALESCE(u_pr.nombre || ' ' || u_pr.apellido, 'Sin registrar') as propietario_nombre " +
                      "FROM venta v " +
                      "JOIN inscripcion i ON v.inscripcion_id = i.id " +
                      "LEFT JOIN propietario p ON v.propietario_id = p.id " +
                      "LEFT JOIN usuario u_pr ON p.user_id = u_pr.id " +
                      "WHERE i.alumno_id=? " +
                      "ORDER BY v.fecha_venta DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, alumnoId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("propietario_nombre"),
                set.getString("tipo_venta"),
                set.getInt("cuotas") > 0 ? String.valueOf(set.getInt("cuotas")) : "N/A",
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
    
    public List<String[]> listarPorInscripcion(int inscripcionId) throws SQLException{
        List<String[]> ventas = new ArrayList<>();
        String query = "SELECT v.*, " +
                      "COALESCE(u_pr.nombre || ' ' || u_pr.apellido, 'Sin registrar') as propietario_nombre " +
                      "FROM venta v " +
                      "LEFT JOIN propietario p ON v.propietario_id = p.id " +
                      "LEFT JOIN usuario u_pr ON p.user_id = u_pr.id " +
                      "WHERE v.inscripcion_id=? " +
                      "ORDER BY v.fecha_venta DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, inscripcionId);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("propietario_nombre"),
                set.getString("tipo_venta"),
                set.getInt("cuotas") > 0 ? String.valueOf(set.getInt("cuotas")) : "N/A",
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
                      "i.alumno_id, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "al.codigo as alumno_codigo " +
                      "FROM venta v " +
                      "JOIN inscripcion i ON v.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "WHERE v.estado=? " +
                      "ORDER BY v.fecha_venta DESC";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, estado);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("tipo_venta"),
                set.getInt("cuotas") > 0 ? String.valueOf(set.getInt("cuotas")) : "N/A",
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
                      "i.alumno_id, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "al.codigo as alumno_codigo " +
                      "FROM venta v " +
                      "JOIN inscripcion i ON v.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "WHERE v.mes_correspondiente=? " +
                      "ORDER BY u_al.apellido";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, mesCorrespondiente);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
                set.getString("tipo_venta"),
                set.getInt("cuotas") > 0 ? String.valueOf(set.getInt("cuotas")) : "N/A",
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
                      "i.alumno_id, " +
                      "u_al.nombre || ' ' || u_al.apellido as alumno_nombre, " +
                      "u_al.telefono as alumno_telefono, " +
                      "al.codigo as alumno_codigo " +
                      "FROM venta v " +
                      "JOIN inscripcion i ON v.inscripcion_id = i.id " +
                      "JOIN alumno al ON i.alumno_id = al.id " +
                      "JOIN usuario u_al ON al.user_id = u_al.id " +
                      "WHERE v.tipo_venta='credito' " +
                      "AND v.fecha_vencimiento < CURRENT_DATE " +
                      "AND v.estado != 'pagado' " +
                      "ORDER BY v.fecha_vencimiento";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        
        while(set.next()){
            ventas.add(new String[] {
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("inscripcion_id")),
                set.getString("alumno_nombre"),
                set.getString("alumno_codigo"),
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