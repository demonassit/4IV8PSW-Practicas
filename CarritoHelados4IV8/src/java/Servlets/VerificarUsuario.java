/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Modelo.MUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author demon
 */
public class VerificarUsuario extends HttpServlet {

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
            
            //obtener los parametros
            
            String user, pass;
            
            user = request.getParameter("txtUsuario");
            pass = request.getParameter("txtPassword");
            
            //genero mi objeto del usuario
            
            MUsuario u = new MUsuario();
            
            //verifico el tipo de usuario
            
            u = u.verificarUsuario(user, pass);
            
            //verifico la sesion
            
            if(u!=null){
                /*
                El usuario existe en la base de datos y la clave es correcta
                */
                HttpSession sesionu = request.getSession(true);
                //ahora saber el tipo de usario a traves de sus atributos
                sesionu.setAttribute("usuario", u);
                
                
                HttpSession sesionuok = request.getSession();
                sesionuok.setAttribute("usuario", user);
                
                if(u.getPrivilegio_usuario() == 0){
                    //va a ser el cliente
                    response.sendRedirect("MostrarHelados.jsp");
                }else{
                    //este es el admin
                    response.sendRedirect("MostrarAdministrador.jsp");
                }
            }else{
                //el usuario no esta registrado o no existe
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
