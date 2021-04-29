<%-- 
    Document   : borrar
    Created on : 29/04/2021, 06:10:35 PM
    Author     : demon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.sql.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrar Registro</title>
    </head>
    <body>
        
        <%
            //aqui ya puedo incorporar codigo java
            Connection con = null;
            Statement set = null;
            ResultSet rs = null;

            String url, userName, password, driver;

            url = "jdbc:mysql://localhost/registro";
            userName = "root";
            password = "n0m3l0";

            driver = "com.mysql.jdbc.Driver";
            
            try{
                Class.forName(driver);
                con = DriverManager.getConnection(url, userName, password);
                try{
                        set = con.createStatement();
                        //necesito los parametros del formulario
                        String q;
                        int id;
                        
                        id = Integer.parseInt(request.getParameter("id"));
                        
                        q = "delete from registrousuario where id_usu="+id;
                        
                        int eliminar = set.executeUpdate(q);
                        
                        %>
                        <h1>Registro Eliminado con Exito</h1>
                        <%
                            set.close();
                    
                }catch(SQLException ed){
                        System.out.println("Error al eliminar el registro en la tabla");
                        System.out.println(ed.getMessage());
                        %>
                        <h1>Solo juguito contigo ¬¬ T_T</h1>
                        <%
                        
                
                }
                con.close();
            
            }catch(Exception e){
                System.out.println("Error al conectar con la bd");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                %>
                <br>
                <h1>Sitio en Construccion</h1>        
                        <%
            }
            
            %>
        
        
        <a href="index.html" >Regresar a la Pagina Principal</a>   
    </body>
</html>
