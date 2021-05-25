<%-- 
    Document   : MostrarHelados
    Created on : 25/05/2021, 04:16:50 PM
    Author     : demon
--%>

<%@page import="Modelo.DCompra"%>
<%@page import="Modelo.MProducto"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" %>

<%
    String usuario = "";
    HttpSession sesionuok = request.getSession();
    if(sesionuok.getAttribute("usuario")==null){
    
    %>
    
    <jsp:forward page="index.html">
        <jsp:param name="error" value="Es obligatorio Identificarse" />
        
    </jsp:forward>
    
    <%
    
    }else{
        usuario = (String)sesionuok.getAttribute("usuario");
    }
    %>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Helados Ben and Jerrys</title>
    </head>
    <body>
        
        <br>
        
        <h1>boton de Cerrar sesión</h1>
        <%
            
          
            
            //todos los productos
            Vector<MProducto> vecproducto = new MProducto().listaProductos();
            
            %>
        
        
        <h1>Una tabla donde aparezcan todos los productos </h1>
        
        <%
            //recorrer el vector del producto
            for(MProducto producto : vecproducto){
            //voy imprimiendo producto por producto
            }
            %>
    </body>
</html>
