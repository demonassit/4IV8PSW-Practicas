/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author demon
 */
import Control.Conexion;
import java.sql.*;
import java.util.Vector;

public class MCompra {
    
    /*
    id_compra, fecha_compra, total_compra,
    id_dcompra, id_forma
    */
    
    private int id_compra, id_dcompra, id_forma;
    private String fecha_compra;
    private double total_compra;
    
    public MCompra(){
    
    }
    
    private int ultimoCodigoInsetado(Connection con){
        int codigo = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String q = "select max(id_compra) as Codigo from MCompra";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                codigo = rs.getInt("Codigo");
                break;
            }
        
        }catch(SQLException sq){
            System.out.println("Error al consultar la compra");
            System.out.println(sq.getMessage());
    
        }finally{
            try{
                rs.close();
                ps.close();
                
                
            }catch(Exception e){
                System.out.println("Error al encontrar la clase");
                System.out.println(e.getMessage());
            }
        }
        return codigo;
        
    }
    
    /*
    necesito un metood que se encargue de registrar la compra, y justo
    cuando se obtenga la ultima compra realizada se registre al mismo tiempo
    el detalle de la compra que son los productos seleccionados del carrito
    */
    
    public boolean registrarCompra(MCompra c, Vector<DCompra> ldv){
        boolean registro = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into MCompra("
                    + "fecha_compra, total_compra, id_dcompra, id_forma) "
                    + "values(?,?,?,?)";
            
            ps = con.prepareStatement(q);
            ps.setString(1, c.getFecha_compra());
            ps.setDouble(2, c.getTotal_compra());
            ps.setInt(3, c.getId_dcompra());
            ps.setInt(4, c.getId_forma());
            
            if(ps.executeUpdate() == 1){
                int codigo = this.ultimoCodigoInsetado(con);
                registro = this.registrarDetalleCompra(codigo, ldv, con);
            }else{
                registro = false;
            }
        
        }catch(SQLException sq){
            System.out.println("Error al registrar la compra");
            System.out.println(sq.getMessage());
            registro = false;
    
        }finally{
            try{
                
                ps.close();
                con.close();
                
                
            }catch(Exception e){
                System.out.println("Error al encontrar la clase");
                System.out.println(e.getMessage());
            }
        }
        return registro;
    }
    
    private boolean registrarDetalleCompra(int codigo, Vector<DCompra> ldv, Connection con) {
        boolean registro = false;
        PreparedStatement ps = null;
        try{
            
            for(DCompra dc : ldv){
                String q = "insert into DCompra values(?,?,?,?,?)";
                ps = con.prepareStatement(q);
                ps.setInt(1, codigo);
                ps.setInt(2, dc.getId_producto());
                ps.setInt(3, dc.getCantidad_compra());
                ps.setDouble(4, dc.getSubtotal_compra());
                ps.setInt(5, dc.getId_ecompra());
                
                if(ps.executeUpdate() == 1){
                    registro = true;
                }else{
                    registro = false;
                    break;
                }
            }
        
        }catch(SQLException sq){
            System.out.println("Error al registrar el dcompra");
            System.out.println(sq.getMessage());
            registro = false;
    
        }finally{
            try{
                
                ps.close();
                
                
                
            }catch(Exception e){
                System.out.println("Error al encontrar la clase");
                System.out.println(e.getMessage());
            }
        }
        return registro;
    }        

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_dcompra() {
        return id_dcompra;
    }

    public void setId_dcompra(int id_dcompra) {
        this.id_dcompra = id_dcompra;
    }

    public int getId_forma() {
        return id_forma;
    }

    public void setId_forma(int id_forma) {
        this.id_forma = id_forma;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(double total_compra) {
        this.total_compra = total_compra;
    }

 
    
    
}
