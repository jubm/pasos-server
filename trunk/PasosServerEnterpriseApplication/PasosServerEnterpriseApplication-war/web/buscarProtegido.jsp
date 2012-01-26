<%-- 
    Document   : buscarProtegido
    Created on : 25-ene-2012, 15:31:52
    Author     : Juan Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/buscarProtegido.js"></script>
    </head>
    <body>
        <div id="menu">
        </div>
        <div id="content">
            <div id="busqueda" style="background-color:#FFD700; height:200px; width:200px; float:left;">
                <!--<form name="form_busqueda" action="searchServlet">-->
                    Nombre usuario: <input type="text" id="nombre" name="nombre" value="" />
                    Apellidos usuario: <input type="text" id="apellidos" name="apellidos" value="" />
                    <input type="submit" id="buscar" value="Buscar" name="buscar" />
                    <button type="button" id="boton_alarmas">Mostrar estadísticias</button> 
                <!--</form>-->
            </div>
            <div id="datos" style="background-color:#EEEEEE; height:200px; width:400px; float:left;">
                <!--<img src="" id="foto"/>
                <p id="nombre"><b>Nombre:      </b></p>
                <p id="apellidos"><b>Apellidos:       </b></p>
                <p id="fecha_nacimiento"><b>Fecha de nacimiento:       </b></p>
                <p id="telefono"><b>Telefono:       </b></p>-->
            </div> 
            <div id="cont_alarmas">
                
            </div>
        </div>
    </body>
</html>
