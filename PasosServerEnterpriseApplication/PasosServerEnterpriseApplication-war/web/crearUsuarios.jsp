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


    <select name="tipouser" id="tipouser">
        <option value="1">Protegida</option>
        <option value="2">Agresor/Sospechoso</option>

    </select>

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
            IMEI <br />
            <input id="imeiP" type="text" name="imeiP"  value="" />
            <br />

        </fieldset>

        <fieldset>
            <legend align="right">Contacto 1</legend>
            Nombre <br/>
            <input id="nombreC1" type="text" name="nombreC1" value="" />
            <br/>
            Teléfono <br/>
            <input id="movilC1" type="text" name="movilC1" value="" />
            <br/>
            Correo electrónico <br/>
            <input id="emailC1" type="text" name="emailC1" value="" />
            <br/>         
        </fieldset>
        <fieldset>
            <legend align="right">Contacto 2</legend>
            Nombre <br/>
            <input id="nombreC2" type="text" name="nombrecs" value="" />
            <br/>
            Teléfono <br/>
            <input id="movilC2" type="text" name="movils" value="" />
            <br/>
            Correo electrónico <br/>
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
            Número dispositivo <br />
            <input id="dispositivoA" type="text" name="dispositivo" value="" />
            <br />
            Distancia alejamiento víctima <br />
            <input id="distanciaA" type="text" name="distancia"  value="" />
            <br />
            IMEI <br />
            <input id="imeiA" type="text" name="imeiA"  value="" />
            <br />
        </fieldset>


    </div>
    <input type="submit" name="enviar" id="enviar" value="Enviar"/>

</div>
