/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bussinesdata;

import Bussines.Bproducto;
import Bussines.Bususario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafa
 */
public class BussinesData {

    public static void main(String[] args) {
        usuario();
        
    }
    
    public static void producto(){
        Bproducto bproducto= new Bproducto();
        List<String> producto= new ArrayList<String>();
        producto.add("Producto1");
        producto.add("100");
        
        try {
            bproducto.guardar(producto, "raf.asd09.lopez@gmail.com");
        } catch (SQLException ex) {
            Logger.getLogger(BussinesData.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    public static void usuario(){
        Bususario bususario= new Bususario();
        List<String> usuario= new ArrayList<String>();
        usuario.add("Rafael");
        usuario.add("Lopez");
        usuario.add("1234567");
        usuario.add("Masculino");
        usuario.add("raf.asd09.lopez@gmail.com");
        usuario.add("2003-04-08");
        usuario.add("76069986");
        
        try {
            bususario.guardar(usuario);
        } catch (SQLException ex) {
           Logger.getLogger(BussinesData.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
}