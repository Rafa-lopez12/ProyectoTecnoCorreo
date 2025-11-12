package Bussines;
import Data.DPago;
import Data.DVenta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BPago {
    private DPago dPago;
    private DVenta dVenta;
    
    public BPago(){
        dPago = new DPago();
        dVenta = new DVenta();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]venta_id, [1]monto, [2]metodo_pago, [3]observaciones, [4]registrado_por
        
        int ventaId = Integer.parseInt(parametros.get(0));
        double montoPago = Double.parseDouble(parametros.get(1));
        
        Integer registradoPor = null;
        if(parametros.get(4) != null && !parametros.get(4).isEmpty() && !parametros.get(4).equals("null")) {
            registradoPor = Integer.parseInt(parametros.get(4));
        }
        
        // Registrar el pago
        int pagoId = dPago.guardar(
            ventaId,
            montoPago,
            parametros.get(2),
            parametros.get(3),
            registradoPor
        );
        
        // Actualizar los montos en la venta
        actualizarVentaDespuesDePago(ventaId);
        
        dPago.disconnect();
        return pagoId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]monto, [2]metodo_pago, [3]observaciones
        
        // Primero obtenemos el pago actual para saber a qué venta pertenece
        String[] pagoActual = dPago.ver(Integer.parseInt(parametros.get(0)));
        int ventaId = Integer.parseInt(pagoActual[1]);
        
        dPago.modificar(
            Integer.parseInt(parametros.get(0)),
            Double.parseDouble(parametros.get(1)),
            parametros.get(2),
            parametros.get(3)
        );
        
        // Actualizar los montos en la venta
        actualizarVentaDespuesDePago(ventaId);
        
        dPago.disconnect();
    }
    

    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> pagos = (ArrayList<String[]>) dPago.listar();
        dPago.disconnect();
        return pagos;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] pago = dPago.ver(id);
        dPago.disconnect();
        return pago;
    }
    
    public ArrayList<String[]> listarPorVenta(int ventaId) throws SQLException{
        ArrayList<String[]> pagos = (ArrayList<String[]>) dPago.listarPorVenta(ventaId);
        dPago.disconnect();
        return pagos;
    }
    
    public ArrayList<String[]> listarPorAlumno(int alumnoId) throws SQLException{
        ArrayList<String[]> pagos = (ArrayList<String[]>) dPago.listarPorAlumno(alumnoId);
        dPago.disconnect();
        return pagos;
    }
    
    // Método para registrar un pago y actualizar automáticamente la venta
    public void registrarPago(int ventaId, double monto, String metodoPago, 
                             String observaciones, Integer registradoPor) throws SQLException{
        List<String> parametros = new ArrayList<>();
        parametros.add(String.valueOf(ventaId));
        parametros.add(String.valueOf(monto));
        parametros.add(metodoPago);
        parametros.add(observaciones);
        parametros.add(registradoPor != null ? String.valueOf(registradoPor) : "null");
        guardar(parametros);
    }
    
    // Método auxiliar para actualizar los montos de la venta después de un pago
    private void actualizarVentaDespuesDePago(int ventaId) throws SQLException{
        // Obtener el total pagado de todos los pagos de esta venta
        double totalPagado = dPago.obtenerTotalPagadoPorVenta(ventaId);
        
        // Obtener la venta para conocer el monto total
        String[] venta = dVenta.ver(ventaId);
        double montoTotal = Double.parseDouble(venta[5]);
        
        // Calcular el saldo pendiente
        double saldoPendiente = montoTotal - totalPagado;
        
        // Actualizar la venta con los nuevos montos
        dVenta.actualizarMontosPago(ventaId, totalPagado, saldoPendiente);
        
        dVenta.disconnect();
    }
    
    // Método para registrar pago parcial
    public void registrarPagoParcial(int ventaId, double monto, String metodoPago, Integer registradoPor) throws SQLException{
        registrarPago(ventaId, monto, metodoPago, "Pago parcial", registradoPor);
    }
    
    // Método para registrar pago completo
    public void registrarPagoCompleto(int ventaId, double monto, String metodoPago, Integer registradoPor) throws SQLException{
        registrarPago(ventaId, monto, metodoPago, "Pago completo", registradoPor);
    }
    
    // Obtener total pagado por una venta
    public double obtenerTotalPagadoPorVenta(int ventaId) throws SQLException{
        double total = dPago.obtenerTotalPagadoPorVenta(ventaId);
        dPago.disconnect();
        return total;
    }
}