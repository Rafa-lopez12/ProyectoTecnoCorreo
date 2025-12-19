package Bussines;
import Data.DAlumno;
import Data.DUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafa
 */
public class BAlumno {
    private DAlumno dAlumno;
    private DUsuario dUsuario;
    
    public BAlumno(){
        dAlumno = new DAlumno();
        dUsuario = new DUsuario();
    }
    
    public void guardar(List<String> parametros) throws SQLException{
        // parametros: [0]nombre, [1]apellido, [2]email, [3]telefono, [4]fecha_nacimiento, 
        //             [5]direccion, [6]estado, [7]codigo, [8]grado_escolar, [9]fecha_ingreso
        
        // Primero crear el usuario
        int userId = dUsuario.guardar(
            parametros.get(0),  // nombre
            parametros.get(1),  // apellido
            parametros.get(2),  // email
            parametros.get(3),  // telefono
            parametros.get(4),  // fecha_nacimiento
            parametros.get(5),  // direccion
            parametros.get(6)   // estado
        );
        dUsuario.disconnect();
        
        // Verificar que el código no exista
        if(dAlumno.existeCodigo(parametros.get(7))){
            dAlumno.disconnect();
            throw new SQLException("El código de alumno ya existe: " + parametros.get(7));
        }
        
        // Luego crear el alumno con el user_id
        dAlumno.guardar(
            userId,              // user_id
            parametros.get(7),   // codigo
            parametros.get(8),   // grado_escolar
            parametros.get(9)    // fecha_ingreso
        );
        dAlumno.disconnect();
    }
    
    public void modificar(List<String> parametros) throws SQLException{
        // parametros: [0]id, [1]nombre, [2]apellido, [3]email, [4]telefono, [5]fecha_nacimiento, 
        //             [6]direccion, [7]estado, [8]codigo, [9]grado_escolar, [10]fecha_ingreso
        
        // Primero obtener el alumno para saber su user_id
        String[] alumno = dAlumno.ver(Integer.parseInt(parametros.get(0)));
        int userId = Integer.parseInt(alumno[1]); // user_id está en posición 1
        
        // Modificar el usuario
        dUsuario.modificar(
            userId,              // id del usuario
            parametros.get(1),   // nombre
            parametros.get(2),   // apellido
            parametros.get(3),   // email
            parametros.get(4),   // telefono
            parametros.get(5),   // fecha_nacimiento
            parametros.get(6),   // direccion
            parametros.get(7)    // estado
        );
        dUsuario.disconnect();
        
        // Modificar el alumno
        dAlumno.modificar(
            Integer.parseInt(parametros.get(0)), // id del alumno
            parametros.get(8),   // codigo
            parametros.get(9),   // grado_escolar
            parametros.get(10)   // fecha_ingreso
        );
        dAlumno.disconnect();
    }
    
    public void eliminar(List<String> parametros) throws SQLException{
        // parametros: [0]id
        // Al eliminar el alumno, el usuario también se eliminará por CASCADE
        dAlumno.eliminar(Integer.parseInt(parametros.get(0)));
        dAlumno.disconnect();
    }
    
    public ArrayList<String[]> listar() throws SQLException{
        ArrayList<String[]> alumnos = (ArrayList<String[]>) dAlumno.listar();
        dAlumno.disconnect();
        return alumnos;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] alumno = dAlumno.ver(id);
        dAlumno.disconnect();
        return alumno;
    }
    
    public ArrayList<String[]> listarPorGrado(String gradoEscolar) throws SQLException{
        ArrayList<String[]> alumnos = (ArrayList<String[]>) dAlumno.listarPorGrado(gradoEscolar);
        dAlumno.disconnect();
        return alumnos;
    }
    
    public boolean existeCodigo(String codigo) throws SQLException{
        boolean existe = dAlumno.existeCodigo(codigo);
        dAlumno.disconnect();
        return existe;
    }
}