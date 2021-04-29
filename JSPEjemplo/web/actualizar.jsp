<%-- 
    Document   : actualizar
    Created on : 29/04/2021, 06:37:12 PM
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
                try{
                        set = con.createStatement();
                        //necesito los parametros del formulario
                        String q;
                        int id;
                        String nombre, ciudad, tel;
                
                        nombre = request.getParameter("nombre2");
                        ciudad = request.getParameter("ciudad2");
                        tel = request.getParameter("tel2");
                        
                        id = Integer.parseInt(request.getParameter("id2"));
                        
                        q = "update registrousuario set "
                                + "nom_usu = '"+nombre+"', ciu_usu = '"+ciudad+"', tel_usu = '"+tel+"' "
                                + "where id_usu = "+id+"";
                        
                        int actualizar = set.executeUpdate(q);
                        
                        %>
                        <h1>Datos Actualizados con Exito</h1>
                        <%
                            set.close();
                    
                }catch(SQLException ed){
                        System.out.println("Error al actualizar el registro en la tabla");
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
