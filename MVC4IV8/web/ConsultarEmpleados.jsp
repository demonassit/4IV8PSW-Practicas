<%-- 
    Document   : ConsultarEmpleados
    Created on : 13/05/2021, 05:47:11 PM
    Author     : demon
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla de Empleados</title>
    </head>
    <body>
        <h1>Tabla General de Empleados</h1>
        <table border="2" >
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Pais</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    
                    List<Empleado> lista = AccionesEmpleado.getAllEmpleados();
                    
                    for(Empleado e : lista){
                    %>
                    <tr>
                        <td> <%=e.getId() %> </td>
                        <td> <%=e.getNombre() %> </td>
                        <td> <%=e.getPassword()%> </td>
                        <td> <%=e.getEmail()%> </td>
                        <td> <%=e.getPais()%> </td>
                        <td> <a href="editar.jsp?id=<%=e.getId()%>" >Editar</a> </td>
                        <td> <a href="borrar?id=<%=e.getId()%>" >Borrar</a> </td>
                    </tr>    
                    <%
                    }
                    %>
                
            </tbody>
            
        </table>
        <a href="index.html" >Regresar al Menu Principal</a> 
        <br>
        
    </body>
</html>
