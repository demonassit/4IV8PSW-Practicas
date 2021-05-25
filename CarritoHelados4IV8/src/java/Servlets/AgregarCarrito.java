/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modelo.DCompra;
import Modelo.MProducto;
import Modelo.MUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author demon
 */
public class AgregarCarrito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            HttpSession sesionuok = request.getSession();
            
            //generar el vector del detalle del carrito para saber que esta agregando
            
            Vector<DCompra> vectorDetalle = null;
            
            //saber como esta el stock al momento de agregarlo, para que despues lo reste
            
            Vector<MProducto> stockProducto = null;
            
            //si se creo la sesion con el atributo del detalle de venta
            
            if(sesionuok.getAttribute("detalleVenta") != null){
                //si existe una sesion con ese atributo y hay que obtener los datos
                //de detalle de la compra que esta realizando el usuario
                vectorDetalle = (Vector<DCompra>)sesionuok.getAttribute("detalleVenta");
                stockProducto = (Vector<MProducto>)sesionuok.getAttribute("stockProducto");
            }else{
                //no existe esa sesion y se crean por primera vez los carritos
                vectorDetalle = new Vector<DCompra>();
                stockProducto = new Vector<MProducto>();
            }
            
            //obtener que producto se esta seleccionado
            
            int codigo, cantidad;
            
            codigo = Integer.parseInt(request.getParameter("txtCodigo").trim());
            cantidad = Integer.parseInt(request.getParameter("txtCantidad").trim());
            
            MProducto producto = new MProducto();
            
            producto = producto.buscarProducto(codigo);
            
            //vamos a hacer la operacion del calculo del subtotal y total
            
            double subtotal = cantidad * producto.getPrecio_producto();
            
            MUsuario usuario = new MUsuario();
            
            //creo el detalle de la compra
            DCompra detallecompra = new DCompra();
            
            detallecompra.setId_dcompra(vectorDetalle.size()+1);
            detallecompra.setId_producto(codigo);
            detallecompra.setCantidad_compra(cantidad);
            detallecompra.setSubtotal_compra(subtotal);
            detallecompra.setId_ecompra(usuario.getId_usuario());
            
            //agrego el detalle de la venta al vector detalle
            vectorDetalle.add(detallecompra);
            
            sesionuok.setAttribute("detalleVenta", vectorDetalle);
            
            //agregar el stock actualizado al producto
            
            producto.setStock_producto(producto.getStock_producto()-cantidad);
            
            stockProducto.add(producto);
            
            sesionuok.setAttribute("stockProducto", stockProducto);
            
            response.sendRedirect("CarritoHelados.jsp");
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
