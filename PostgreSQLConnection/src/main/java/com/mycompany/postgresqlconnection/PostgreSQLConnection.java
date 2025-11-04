/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.postgresqlconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafa
 */
public class PostgreSQLConnection {

    public static void main(String[] args) {
       try{
            SqlConnection sqlConnection = new SqlConnection("postgres", "leyendas13", "127.0.0.1", "5432", "prueba_tecno");
            String query= "Select * from usuarios where id = 1";    
            PreparedStatement ps= sqlConnection.connect().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("resultado: "+ rs.next());
       }catch(SQLException ex){
           Logger.getLogger(PostgreSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
