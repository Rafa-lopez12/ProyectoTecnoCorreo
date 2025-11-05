package Bussines;
import Data.DHorario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BHorario {
    private DHorario dHorario;
    
    public BHorario(){
        dHorario = new DHorario();
    }
    
    public int guardar(List<String> parametros) throws SQLException{
        // parametros: [0]dia_semana, [1]hora_inicio, [2]hora_fin, [3]estado
        int horarioId = dHorario.guardar(
            parametros.get(0), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3)
        );
        dHorario.disconnect();
        return horarioId;
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]dia_semana, [2]hora_inicio, [3]hora_fin, [4]estado
        dHorario.modificar(
            Integer.parseInt(parametros.get(0)), 
            parametros.get(1), 
            parametros.get(2), 
            parametros.get(3),
            parametros.get(4)
        );
        dHorario.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        dHorario.eliminar(Integer.parseInt(parametros.get(0)));
        dHorario.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> horarios = (ArrayList<String[]>) dHorario.listar();
        dHorario.disconnect();
        return horarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] horario = dHorario.ver(id);
        dHorario.disconnect();
        return horario;
    }
    
    public ArrayList<String[]> listarPorDia(String diaSemana) throws SQLException{
        ArrayList<String[]> horarios = (ArrayList<String[]>) dHorario.listarPorDia(diaSemana);
        dHorario.disconnect();
        return horarios;
    }
    
    public ArrayList<String[]> listarActivos() throws SQLException{
        ArrayList<String[]> horarios = (ArrayList<String[]>) dHorario.listarActivos();
        dHorario.disconnect();
        return horarios;
    }
}