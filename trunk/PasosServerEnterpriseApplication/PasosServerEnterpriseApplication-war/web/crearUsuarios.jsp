<script type="text/javascript">
    $("#enviar").click(function(){
                    
        //enviarDatos()
        crearUsuario();
                    
    });
                 
    $("#tipouser").change(function(){
        var selector = $("#tipouser").val();
        if(selector==1){
            $("#protegida").show();
            $("#agresor").hide();
        }else{
            $("#protegida").hide();
            $("#agresor").show();
                        
        }
    });
</script>


<form name="alta" enctype="multipart/form-data" target="upload_target" onsubmit="startUpload();">


    <div id="protegida">
        <fieldset>
            <legend align="right">Datos personales</legend>

            Nombre <br/>
            <input id="nombreP" type="text" name="nombreP" value=""/>
            <br />
            Apellidos <br />
            <input id="apellidosP" type="text" name="apellidosP" value=""/>
            <br />
            Fecha de nacimiento <br />
            <input id="fechanacP" type="text" name="fechanacP" value="" />
            <br />
            Numero de telefono <br />
            <input id="telefonoP" type="text" name="telefonoP"  value="" />
            <br />

        </fieldset>
        <fieldset>
            <legend align="right">Fotograf�a de la v�ctima</legend>
            Enlace imagen <br />
            <input type="file" name="file" id="imagenP">
        </fieldset>

        <fieldset>
            <legend align="right">Contacto 1</legend>
            Nombre <br/>
            <input id="nombreC1" type="text" name="nombreC1" value="" />
            <br/>
            Tel�fono <br/>
            <input id="movilC1" type="text" name="movilC1" value="" />
            <br/>
            Correo electr�nico <br/>
            <input id="emailC1" type="text" name="emailC1" value="" />
            <br/>         
        </fieldset>
        <fieldset>
            <legend align="right">Contacto 2</legend>
            Nombre <br/>
            <input id="nombreC2" type="text" name="nombrecs" value="" />
            <br/>
            Tel�fono <br/>
            <input id="movilC2" type="text" name="movils" value="" />
            <br/>
            Correo electr�nico <br/>
            <input id="emailC2" type="text" name="emails" value="" />
            <br/>         
        </fieldset>


    </div>      

    <div id="agresor">      
        <fieldset>
            <legend align="right">Datos Agresor (Opcional)</legend>
            Nombre <br/>
            <input id="nombreA" type="text" name="nombrea" value=""/>
            <br />
            Apellidos <br />
            <input id="apellidosA" type="text" name="apellidosa" value=""/>
            <br />
            N�mero dispositivo <br />
            <input id="dispositivoA" type="text" name="dispositivo" value="" />
            <br />
            Distancia alejamiento v�ctima <br />
            <input id="distanciaA" type="text" name="distancia"  value="" />
            <br />

        </fieldset>

        <fieldset>
            <legend align="right">Fotograf�a del agresor</legend>
            Enlace imagen <br />
            <input type="file" name="file2" id="imagenA">
        </fieldset>


    </div>
    <input type="submit" name="enviar" id="enviar" value="Enviar"/>
</form>

</div>
<iframe id="upload_target" name="upload_target" src="#" style="width:0;height:0;border:0px solid #fff;"></iframe>
