/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import com.mycompany.postgresqlconnection.SqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafa
 */
public class DProducto {
    private SqlConnection connection;
    
    public DProducto() {
        connection= new SqlConnection("grupo16sa", "grup016grup016*", "www.tecnoweb.org.bo", "5432", "db_grupo16sa");
        
    
    }
    
    
    public void guardar(String nombre, int stock, int usuarioId) throws SQLException{
        String query =  "INSERT INTO productos(nombre,stock, usuario_id)" + " values(?,?,?)";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setInt(2, stock);
        ps.setInt(3, usuarioId);
        
        if(ps.executeUpdate()==0){
            System.err.println("Class DProducto.java dice: " + "Ocurrio un error al insertar un producto guardar()");
            throw new SQLException();
        }
    }
    
    public void modificar(int id, String nombre, String apellido, String ci, String genero, String correo, String fechaNacimiento, String telefono) throws SQLException{
        String query =  "UPDATE usuarios Set nombre=?, apellido=?, ci=?, genero=?,  correo=?, fecha_nacimiento=?, telefono=?" 
                + "WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, ci);
        ps.setString(4, genero);
        ps.setString(5, correo);
        ps.setString(6, fechaNacimiento);
        ps.setString(7, telefono);
        ps.setInt(8, id);
        if(ps.executeUpdate()==0){
            System.err.println("Class DUsuario.java dice: " + "Ocurrio un error al modificar un usuario modificar()");
            throw new SQLException();
        }
    }
    
    public void eliminar(int id) throws SQLException{
        String query= "DELETE FROM usuarios WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);

        if(ps.executeUpdate()==0){
            System.err.println("Class DUsuario.java dice: " + "Ocurrio un error al eliminar un usuario eliminar()");
            throw new SQLException();
        }
    }
    
    public List<String[]> listar() throws SQLException{
        List<String[]> usuarios= new ArrayList<>();
        String query= "SELECT * FROM usuarios";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ResultSet set= ps.executeQuery();
        while(set.next()){
            usuarios.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("ci"),
                set.getString("genero"),
                set.getString("correo"),
                set.getString("fecha_nacimiento"),
                set.getString("telefono")
            });
        }
        return  usuarios;
    }
    
    public String[] ver(int id) throws SQLException{
        String[] usuario=null;
        String query= "SELECT * FROM usuarios WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set= ps.executeQuery();
        if (set.next()) {
            usuario = new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("nombre"),
                set.getString("apellido"),
                set.getString("ci"),
                set.getString("genero"),
                set.getString("correo"),
                set.getString("fecha_nacimiento"),
                set.getString("telefono")
            };
        }
        
        return usuario;
    }
    
    public int getIdByCorreo(String correo) throws SQLException{
        int id= -1;
        String query= "SELECT * FROM usuarios WHERE id=?";
        PreparedStatement ps = connection.connect().prepareStatement(query);
        ps.setString(1, correo);
        ResultSet set= ps.executeQuery();
        if (set.next()) {
            id=set.getInt("id");
        }
        
        return id;
    }
    
    public void disconnect(){
        if(connection != null){
            connection.closeConnection();
        }
    }
}
