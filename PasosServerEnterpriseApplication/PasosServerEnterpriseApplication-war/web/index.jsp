<%-- 
    Document   : index
    Created on : 25-ene-2012, 11:55:55
    Author     : Juan Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/css" src="css/estilo.css"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/funciones.js"></script>
        <title>paSOS - login</title>
    </head>
    <body>
        <center>
        <h1>Login</h1>
        <form ACTION="LoginServlet" METHOD="POST">
         <table BORDER=0 WIDTH="50%" >       
        <tr> 
            <td> <b>Usuario </b> </td>
            <td> <input TYPE="TEXT" NAME="username" SIZE="20" MAXLENGTH="20"> </td> </tr>
        <tr>
            <td> <b> Contrase&ntilde;a:</b> </td> 
            <td> <input TYPE="PASSWORD" NAME="password"> </td>
        </tr> 
        </table> 
        <input type="hidden" name="action" value="login"/>    
        <input TYPE="SUBMIT" NAME="Entrar" VALUE="Entrar"> </form>
        </center>
    </body>
</html>