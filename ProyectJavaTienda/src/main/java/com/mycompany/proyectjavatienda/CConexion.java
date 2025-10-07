/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectjavatienda;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CConexion {
    Connection conectar=null;
    String usuario="db_abebfd_gamestore_admin";
    String contrasenia="wi30053101";
    String bd="db_abebfd_gamestore";
    String ip="sql8020.site4now.net";
    String puerto="1433";
   
    public Connection establecerConexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            String cadena ="jdbc:sqlserver://"+ip+":"+puerto+";"+"databaseName="+bd+";"+
            "encryp=true;trustServerCertificate=true";
            
         conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
         JOptionPane.showMessageDialog(null,"Se conecto correctamente a la base de datos");
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null,"No se conecto correctamente a la base de datos");
        }
        return conectar;
    }
}
