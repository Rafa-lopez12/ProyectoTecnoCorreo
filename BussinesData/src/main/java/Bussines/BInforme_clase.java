package Bussines;

import Data.DInformeclase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * @author Rafa
 * CAMBIO IMPORTANTE: Ahora trabaja con asistencia_id en lugar de inscripcion_id
 */
public class BInforme_clase {
    private DInformeclase dInformeClase;
    
    public BInforme_clase(){
        dInformeClase = new DInformeclase();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]asistencia_id (CAMBIO: era inscripcion_id), [1]fecha, [2]temas_vistos, [3]tareas_asignadas,
        //            [4]nivel_comprension, [5]participacion, [6]cumplimiento_tareas, [7]calificacion,
        //            [8]resumen, [9]logros, [10]dificultades, [11]recomendaciones, 
        //            [12]observaciones, [13]estado
        
        int asistenciaId = Integer.parseInt(parametros.get(0));
        String fecha = parametros.get(1);
        
        // Verificar si ya existe un informe para esta asistencia en esta fecha
        if(dInformeClase.existeInforme(asistenciaId, fecha)){
            dInformeClase.disconnect();
            throw new SQLException("Ya existe un informe para esta asistencia en la fecha " + fecha);
        }
        
        BigDecimal calificacion = null;
        if(parametros.get(7) != null && !parametros.get(7).isEmpty() && !parametros.get(7).equals("null")){
            calificacion = new BigDecimal(parametros.get(7));
        }
        
        int informeId = dInformeClase.guardar(
            asistenciaId,           // asistencia_id (CAMBIO)
            fecha,
            parametros.get(2),
            parametros.get(3),
            parametros.get(4),
            parametros.get(5),
            parametros.get(6),
            calificacion,
            parametros.get(8),
            parametros.get(9),
            parametros.get(10),
            parametros.get(11),
            parametros.get(12),
            parametros.get(13)
        );
        dInformeClase.disconnect();
        return informeId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]temas_vistos, [2]tareas_asignadas, [3]nivel_comprension,
        //            [4]participacion, [5]cumplimiento_tareas, [6]calificacion, [7]resumen,
        //            [8]logros, [9]dificultades, [10]recomendaciones, [11]observaciones, [12]estado
        
        BigDecimal calificacion = null;
        if(parametros.get(6) != null && !parametros.get(6).isEmpty() && !parametros.get(6).equals("null")){
            calificacion = new BigDecimal(parametros.get(6));
        }
        
        dInformeClase.modificar(
            Integer.parseInt(parametros.get(0)),
            parametros.get(1),
            parametros.get(2),
            parametros.get(3),
            parametros.get(4),
            parametros.get(5),
            calificacion,
            parametros.get(7),
            parametros.get(8),
            parametros.get(9),
            parametros.get(10),
            parametros.get(11),
            parametros.get(12)
        );
        dInformeClase.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dInformeClase.eliminar(Integer.parseInt(parametros.get(0)));
        dInformeClase.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> informes = (ArrayList<String[]>) dInformeClase.listar();
        dInformeClase.disconnect();
        return informes;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] informe = dInformeClase.ver(id);
        dInformeClase.disconnect();
        return informe;
    }
    
    // Nuevo método: listar informes por asistencia
    public ArrayList<String[]> listarPorAsistencia(int asistenciaId) throws SQLException{
        ArrayList<String[]> informes = (ArrayList<String[]>) dInformeClase.listarPorAsistencia(asistenciaId);
        dInformeClase.disconnect();
        return informes;
    }
}