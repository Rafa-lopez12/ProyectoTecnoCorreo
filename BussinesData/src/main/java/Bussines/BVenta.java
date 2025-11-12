package Bussines;
import Data.DVenta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BVenta {
    private DVenta dVenta;
    
    public BVenta(){
        dVenta = new DVenta();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]alumno_id, [1]propietario_id, [2]tipo_venta, [3]monto_total, 
        //             [4]monto_pagado, [5]mes_correspondiente, [6]fecha_venta, [7]fecha_vencimiento
        
        double montoTotal = Double.parseDouble(parametros.get(3));
        double montoPagado = Double.parseDouble(parametros.get(4));
        double saldoPendiente = montoTotal - montoPagado;
        
        Integer propietarioId = null;
        if(parametros.get(1) != null && !parametros.get(1).isEmpty() && !parametros.get(1).equals("null")) {
            propietarioId = Integer.parseInt(parametros.get(1));
        }
        
        String estado = (saldoPendiente <= 0) ? "pagado" : "pendiente";
        
        int ventaId = dVenta.guardar(
            Integer.parseInt(parametros.get(0)),  // alumno_id
            propietarioId,                         // propietario_id
            parametros.get(2),                     // tipo_venta
            montoTotal,                            // monto_total
            montoPagado,                           // monto_pagado
            saldoPendiente,                        // saldo_pendiente
            parametros.get(5),                     // mes_correspondiente
            parametros.get(6),                     // fecha_venta
            parametros.get(7),                     // fecha_vencimiento
            estado
        );
        dVenta.disconnect();
        return ventaId;
    }
    
    
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> ventas = (ArrayList<String[]>) dVenta.listar();
        dVenta.disconnect();
        return ventas;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] venta = dVenta.ver(id);
        dVenta.disconnect();
        return venta;
    }
    
    public ArrayList<String[]> listarPorAlumno(int alumnoId) throws SQLException{
        ArrayList<String[]> ventas = (ArrayList<String[]>) dVenta.listarPorAlumno(alumnoId);
        dVenta.disconnect();
        return ventas;
    }
    
    public ArrayList<String[]> listarPorEstado(String estado) throws SQLException{
        ArrayList<String[]> ventas = (ArrayList<String[]>) dVenta.listarPorEstado(estado);
        dVenta.disconnect();
        return ventas;
    }
    
    public ArrayList<String[]> listarPorMes(String mesCorrespondiente) throws SQLException{
        ArrayList<String[]> ventas = (ArrayList<String[]>) dVenta.listarPorMes(mesCorrespondiente);
        dVenta.disconnect();
        return ventas;
    }
    
    public ArrayList<String[]> listarVencidas() throws SQLException{
        ArrayList<String[]> ventas = (ArrayList<String[]>) dVenta.listarVencidas();
        dVenta.disconnect();
        return ventas;
    }
    
    // Métodos específicos para gestión de ventas
    
    public void registrarVentaContado(int alumnoId, Integer propietarioId, double monto, String mesCorrespondiente) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(alumnoId));
        parametros.add(propietarioId != null ? String.valueOf(propietarioId) : "null");
        parametros.add("contado");
        parametros.add(String.valueOf(monto));
        parametros.add(String.valueOf(monto)); // monto_pagado = monto_total
        parametros.add(mesCorrespondiente);
        parametros.add(null); // fecha_venta (se usa CURRENT_DATE)
        parametros.add(null); // fecha_vencimiento (no aplica para contado)
        guardar(parametros);
    }
    
    public void registrarVentaCredito(int alumnoId, Integer propietarioId, double monto, 
                                      double montoPagado, String mesCorrespondiente, 
                                      String fechaVencimiento) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(alumnoId));
        parametros.add(propietarioId != null ? String.valueOf(propietarioId) : "null");
        parametros.add("credito");
        parametros.add(String.valueOf(monto));
        parametros.add(String.valueOf(montoPagado));
        parametros.add(mesCorrespondiente);
        parametros.add(null); // fecha_venta (se usa CURRENT_DATE)
        parametros.add(fechaVencimiento);
        guardar(parametros);
    }
    
    public void marcarComoPagado(int ventaId) throws SQLException{
        dVenta.marcarComoPagado(ventaId);
        dVenta.disconnect();
    }
    
    public void marcarComoVencido(int ventaId) throws SQLException{
        dVenta.marcarComoVencido(ventaId);
        dVenta.disconnect();
    }
    
    public String[] obtenerEstadisticas() throws SQLException{
        String[] stats = dVenta.obtenerEstadisticas();
        dVenta.disconnect();
        return stats;
    }
    
    // Métodos de utilidad
    
    public ArrayList<String[]> listarPendientes() throws SQLException{
        return listarPorEstado("pendiente");
    }
    
    public ArrayList<String[]> listarPagadas() throws SQLException{
        return listarPorEstado("pagado");
    }
    
    public ArrayList<String[]> listarVencidas2() throws SQLException{
        return listarPorEstado("vencido");
    }
    
    // Actualizar estado de ventas vencidas automáticamente
    public void actualizarVentasVencidas() throws SQLException{
        ArrayList<String[]> vencidas = listarVencidas();
        for(String[] venta : vencidas) {
            int ventaId = Integer.parseInt(venta[0]);
            marcarComoVencido(ventaId);
        }
        System.out.println("Se actualizaron " + vencidas.size() + " ventas vencidas");
    }
}