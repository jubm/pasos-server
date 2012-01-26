<%-- 
    Document   : crearUsuarios
    Created on : 26-ene-2012, 11:35:26
    Author     : Gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js" language="javascript"></script>
        <script type="text/javascript" src="crearusuarios.js"> </script>
    </head>
    <body>
        <div id="menu">
        </div>
        <div id="content" style=" width: 50%">
            
                <form name="alta">
                
                    
                        
                            <select name="tipouser" id="tipouser">
                                <option value="1">Protegida</option>
                                <option value="2">Agresor/Sospechoso</option>
                                
                            </select>
                            <div id="protegida">
                <fieldset>
                    <legend align="right">Datos personales</legend>
                    
                    Nombre <br/>
                    <input id="nombre" type="text" name="nombre" value=""/>
                    <br />
                    Apellidos <br />
                    <input id="apellidos" type="text" name="apellidos" value=""/>
                    <br />
                    Fecha de nacimiento <br />
                    <input id="fechanac" type="text" name="fechanac" value="" />
                    <br />
                    Numero de telefono <br />
                    <input id="telefono" type="text" name="telefono"  value="" />
                    <br />
                    
                </fieldset>
                <fieldset>
                    <legend align="right">Fotografía de la víctima</legend>
                    Enlace imagen <br />
                    <input type="file" name="file" id="file">
                </fieldset>
               
                <fieldset>
                    <legend align="right">Contacto 1</legend>
                    Nombre <br/>
                    <input id="nombrec" type="text" name="nombrec" value="" />
                    <br/>
                    Teléfono <br/>
                    <input id="movil" type="text" name="movil" value="" />
                    <br/>
                    Correo electrónico <br/>
                    <input id="email" type="text" name="email" value="" />
                    <br/>         
                </fieldset>
                  <fieldset>
                    <legend align="right">Contacto 2</legend>
                    Nombre <br/>
                    <input id="nombrecs" type="text" name="nombrecs" value="" />
                    <br/>
                    Teléfono <br/>
                    <input id="movils" type="text" name="movils" value="" />
                    <br/>
                    Correo electrónico <br/>
                    <input id="emails" type="text" name="emails" value="" />
                    <br/>         
                </fieldset>
                 
                    
                </div>      
                
                <div id="agresor" style="display:none">      
                  <fieldset>
                    <legend align="right">Datos Agresor</legend>
                    Nombre <br/>
                    <input id="nombrea" type="text" name="nombrea" value=""/>
                    <br />
                    Apellidos <br />
                    <input id="apellidosa" type="text" name="apellidosa" value=""/>
                    <br />
                    Número dispositivo <br />
                    <input id="dispositivo" type="text" name="dispositivo" value="" />
                    <br />
                    Distancia alejamiento víctima <br />
                    <input id="distancia" type="text" name="distancia"  value="" />
                    <br />
                    
                </fieldset>
            
                <fieldset>
                    <legend align="right">Fotografía del agresor</legend>
                    Enlace imagen <br />
                    <input type="file" name="file2" id="file2">
                </fieldset>
                
                        
                </div>
                    <input type="submit" name="enviar" id="enviar" value="Enviar"/>
                </form>
                
        </div> 
    </body>
</html>
