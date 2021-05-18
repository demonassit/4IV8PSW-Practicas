<%-- 
    Document   : registroEmpleados
    Created on : 14/05/2021, 06:19:23 PM
    Author     : demon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de Empleado Exitoso</h1>
        <%
            HttpSession sesionCliente = request.getSession();
            
            //si es de una bd el metodo de verificarusuario(String user, Strig pass)
            //haces una instancias del usuario
                                                  //e.usuario  
            sesionCliente.setAttribute("Usuario", "Mario");
            %>
        <br>
        Bienvenido: <% out.println(sesionCliente.getAttribute("Usuario"));  %>
        <br>
        <a href="index.html" >Regresar al Menu Principal</a>
    </body>
</html>
