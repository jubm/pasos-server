<%-- 
    Document   : main
    Created on : 26-ene-2012, 0:12:20
    Author     : albertomateos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>paSOS</title>
        <script type="text/css" src="css/estilo.css"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/funciones.js"></script>
    </head>
    <body>
        <div id="user">Bienvenido: <%= request.getAttribute("p")%>

        </div>
        <div id="menu">
            <a href="#" id="menuBuscar">Buscar usuario</a>
            <a href="#" id="menuCrear">Crear usuario</a>
            <a href="#" id="menuEstadisticas">Estadisticas</a>
        </div>

        <div id="content"></div>
        <div id="alarma">
            <div id="mapa"></div>
            <p id="protegido"></p>
            <p id="maltratador"></p>
            <input type="button" id="atendido" name="atendido" title="Atendido"></input>
        </div>
    <frameset>

        <iframe src ="comet?action=suscribe" id="iframe" name="iframeChat" width="100%" scrolling="auto">
        </iframe> 

    </frameset>

</body>
</html>
