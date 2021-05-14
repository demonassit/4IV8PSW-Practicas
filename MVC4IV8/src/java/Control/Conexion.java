/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author demon
 */
package Control;

import java.sql.*;

public class Conexion {
    
    public static Connection getConnection(){
    
        String url, userName, password;
        
        url = "jdbc:mysql://localhost/empleados";
        userName = "root";
        password = "n0m3l0";
        
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Se conecto con la BD");
        
        }catch(Exception e){
            System.out.println("No se conecto con la BD");
            System.out.println(e.getMessage());
        
        }
        return con;
    }
    
}
