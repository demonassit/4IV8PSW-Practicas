<%-- 
    Document   : guardar
    Created on : 29/04/2021, 05:25:54 PM
    Author     : demon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.sql.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            
            //diferentes vistas para los errores
            //error exclusivo de sql
            try{
                set = con.createStatement();
                //necesito los parametros del formulario
                String nombre, ciudad, tel, q;
                
                nombre = request.getParameter("nombre");
                ciudad = request.getParameter("ciudad");
                tel = request.getParameter("tel");
                
                q = "insert into registrousuario(nom_usu, ciu_usu, tel_usu) "
                        + "values ('"+nombre+"', '"+ciudad+"', '"+tel+"')";
                
                int registro = set.executeUpdate(q);
                %>
                <h1>Registro Exitoso</h1>
                <%
                set.close();
            
            }catch(SQLException ed){
                System.out.println("Error al registrar en la tabla");
                System.out.println(ed.getMessage());
                
                %>
                <h1>Registro No Exitoso, error en la lectura de la tabla</h1>
                <%
            
            }
            con.close();
        
        }catch(Exception e){
            System.out.println("Error al conectar bd");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            %>
            <h1>Sitio en Construccion</h1>
            <%
        
        }
            
            %>
        
        
        
        
        
        <a href="index.html" >Regresar a la Pagina Principal</a>
    </body>
</html>
