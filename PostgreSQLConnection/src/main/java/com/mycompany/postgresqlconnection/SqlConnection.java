/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.postgresqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Rafa
 */
public class SqlConnection {
    private static final String DRIVER ="jdbc:postgresql://";
    
    private Connection connection;
    private String user;
    private String password;
    private String host;
    private String port;
    private String driver;
    private String name;
    private String url;

    public SqlConnection(String user, String password, String host, String port, String name) {
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        this.name=name;
        
        this.driver= DRIVER + host +":" + port + "/" + name;  //127.0.0.1:5432/tecno_bd
    }
    
    public Connection connect() {
        try{
            connection=DriverManager.getConnection(driver,user,password);
        } catch(SQLException ex){
            System.err.println("Class SqlConnection java dice: " + "Ocurrio un error al momento de hacer una conexion connect()");
        }
        return connection;
    }
    
    public void closeConnection(){
        try{
            connection.close();
        }catch(SQLException ex){
            System.err.println("Class SqlConnection java dice: " + "Ocurrio un error al momento de cerrar la conexion closeconnect()");
        }
    }

    
}
