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
public class DCompra {
    
    /*
    id_dcompra, id_producto, cantidad_compra
    subtotal_compra, id_ecompra
    */
    
    private int id_dcompra, id_producto, cantidad_compra, id_ecompra;
    private double subtotal_compra;
    
    public DCompra(){
    
    }

    public int getId_dcompra() {
        return id_dcompra;
    }

    public void setId_dcompra(int id_dcompra) {
        this.id_dcompra = id_dcompra;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad_compra() {
        return cantidad_compra;
    }

    public void setCantidad_compra(int cantidad_compra) {
        this.cantidad_compra = cantidad_compra;
    }

    public int getId_ecompra() {
        return id_ecompra;
    }

    public void setId_ecompra(int id_ecompra) {
        this.id_ecompra = id_ecompra;
    }

    public double getSubtotal_compra() {
        return subtotal_compra;
    }

    public void setSubtotal_compra(double subtotal_compra) {
        this.subtotal_compra = subtotal_compra;
    }
    
    
    
}
