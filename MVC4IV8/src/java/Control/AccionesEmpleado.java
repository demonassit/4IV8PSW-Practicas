/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author demon
 */

import Modelo.Empleado;
import java.sql.*;
import java.util.*;

public class AccionesEmpleado {
    
    public static int registrarEmpleado(Empleado e){
        int estatus = 0;
        try{
            Connection con = Conexion.getConnection();
            String q = "insert into empleados(nom_emp, pass_emp, email_emp, pais_emp) "
                    + "values(?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPais());
            
            estatus = ps.executeUpdate();
            System.out.println("Registro de empleado exitoso");
            con.close();
        }catch(Exception ed){
            System.out.println("Error al registar al empleado");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
        
    }
    
    public static int actualizarEmpleado(Empleado e){
        int estatus = 0;
        try{
            Connection con = Conexion.getConnection();
            String q = "update empleados set nom_emp = ?, pass_emp = ?,"
                    + "email_emp = ?, pais_emp = ?"
                    + " where id_emp = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPais());
            ps.setInt(5, e.getId());
            
            estatus = ps.executeUpdate();
            System.out.println("Actualizacion del empleado exitosa");
            con.close();
        }catch(Exception ed){
            System.out.println("Error al actualizar al empleado");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
        
    }
    
    public static int borrarEmpleado(int id){
        int estatus = 0;
        try{
            Connection con = Conexion.getConnection();
            String q = "delete from empleados where id_emp = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
            estatus = ps.executeUpdate();
            System.out.println("Eliminacion del empleado exitoso");
            con.close();
        }catch(Exception ed){
            System.out.println("Error al borrar al empleado");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
        
    }
    
    public static Empleado buscarEmpleadoById(int id){
        Empleado e = new Empleado();
        try{
            Connection con = Conexion.getConnection();
            String q = "select * from empleados where id_emp = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e.setId(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setPais(rs.getString(5));
            }
            System.out.println("Se encontro al empleado");
            con.close();
        }catch(Exception ed){
            System.out.println("Error al buscar al empleado");
            System.out.println(ed.getMessage());
        
        }
        return e;
        
    }
    
    public static List<Empleado> getAllEmpleados(){
        List<Empleado> lista = new ArrayList<Empleado>();
        
        try{
            Connection con = Conexion.getConnection();
            String q = "select * from empleados";
            
            PreparedStatement ps = con.prepareStatement(q);

            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Empleado e = new Empleado();
                e.setId(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setPais(rs.getString(5));
                lista.add(e);
            }
            System.out.println("Se encontro a los empleado");
            con.close();
        }catch(Exception ed){
            System.out.println("Error al buscar a los empleado");
            System.out.println(ed.getMessage());
        
        }
        return lista;
        
    }
    
    
}
