/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bussines;

import Data.DProducto;
import Data.DUsuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rafa
 */
public class Bproducto {
    private DProducto dProducto;
    private DUsuario dUsuario;
    
    public Bproducto(){
        dProducto= new DProducto();
        dUsuario= new DUsuario();
    }
    
    public void guardar(List<String> parametros, String correo) throws SQLException{
        int usuarioId = dUsuario.getIdByEmail(correo);
        if (usuarioId != -1) {
            dProducto.guardar(parametros.get(0), Integer.parseInt(parametros.get(1)), usuarioId);
            dProducto.disconnect();
            
        }
        dUsuario.disconnect();
    }
}
