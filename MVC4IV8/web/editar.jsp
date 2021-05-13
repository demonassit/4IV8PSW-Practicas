<%-- 
    Document   : editar
    Created on : 13/05/2021, 05:55:31 PM
    Author     : demon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizacion de Datos del Empleado</title>
    </head>
    <body>
        <h1>Tabla del Empleado para Actualizar</h1>
        <br>
        <form method="post" name="actualizarEmpleado" action="actualizarempleado" >
            <table border="2">
                <%
                    int id = Integer.parseInt(request.getParameter("id"));
                    
                    Empleado e = AccionesEmpleado.BuscarEmpleadoById(id);
                    
                    %>
                <tr>
                    <td>ID</td>
                    <td> <input type="hidden" name="id2" value="<%=e.getId()%>" > </td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre2" value="<%=e.getNombre()%>" ></td>
                </tr> 
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password2" value="<%=e.getPassword()%>"></td>
                </tr> 
                <tr>
                    <td>Emial:</td>
                    <td><input type="email" name="emial2" value="<%=e.getEmail()%>" ></td>
                </tr> 
                <tr>
                    <td>Pais:</td>
                    <td><select name="pais2" > 
                                    <option>Mexico</option>
                                    <option>Al que nadie quiere</option>
                                    <option>Por ahi</option>
                                    <option>En algun lugar</option>
                                </select></td>
                </tr> 
                <tr>
                            <td colspan="2" > <input type="submit" 
                                                     value="Actualizar Empleado" > </td>        
                        </tr>
            </table>
        </form>
        <a href="index.html" >Regresar al Menu Principal</a> 
        <br>
        <a href="ConsultarEmpleados.jsp" >Consultar todos los empleados</a>     
    </body>
</html>
