/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modelo.DCompra;
import Modelo.MCompra;
import Modelo.MProducto;
import Modelo.MUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class FinalizarCompra extends HttpServlet {

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
            Date dia = new Date();
            
            Vector<DCompra> vectorDetalle = (Vector<DCompra>)sesionuok.getAttribute("detalleVenta");
            
            //saber como esta el stock al momento de agregarlo, para que despues lo reste
            
            Vector<MProducto> stockProducto = (Vector<MProducto>)sesionuok.getAttribute("stockProducto");
            
            MProducto producto = new MProducto();
            double totalpagar = 0;
            
            //es recorrer todo el vector de los productos para saber cuanto voy a pagar
            
            for(DCompra dc : vectorDetalle){
                totalpagar += dc.getSubtotal_compra();
            }
            
            MUsuario usuario = new MUsuario();
            MCompra compra = new MCompra();
            DCompra dcompra = new DCompra();
            
            dcompra.setId_ecompra(usuario.getId_usuario());
            compra.setFecha_compra(dia.toString());
            compra.setTotal_compra(totalpagar);
            
            boolean registrarCompra = compra.registrarCompra(compra, vectorDetalle);//1
            // 0 1 
            boolean actualizarStock = producto.actualizarStock(stockProducto);//1
            //       1                0
            if(registrarCompra != actualizarStock){
                response.sendRedirect("MensajeCompra.jsp");
            }else{
                response.sendRedirect("error.jsp");
            }
            
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
