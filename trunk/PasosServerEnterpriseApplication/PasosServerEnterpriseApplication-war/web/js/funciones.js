/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
   
    /*
    * EVENTOS MENU
    */

    $("#menuBuscar").click(function(){
        $("#content").load("http://localhost:8080/PasosServerEnterpriseApplication-war/buscarProtegido.jsp");
    });
    
    $("#menuCrear").click(function(){
        $("#content").load("http://localhost:8080/PasosServerEnterpriseApplication-war/crearUsuarios.jsp");
    });
    
    $("#menuEstadisticas").click(function(){
        $("#content").empty().append("<p>Estadisticas</p>"); 
    });
    
    //$("#iframe").hide();
    $("#alarma").hide();
    
    /*
     * EVENTO BOTÓN FINALIZACIÓN ATENCIÓN ALARMA
     */
    $("#atendido").click(function(){            
        $.post('comet?action=atendido',function(data){});
        $("#alarma").hide();
    });
     
});


/*
 * DECLARACIÓN DE FUNCIONES
 */

function buscarUsuario(){
    
    var nombre=$("#nombre").val();
    var apellidos=$("#apellidos").val();
    var url = "SearchServlet?nombre="+nombre+"&apellidos="+apellidos;
    
    $.ajax({
        url: url,
        beforeSend: function(){
            $("#datos").empty().append("<img src='imagenes/loading.gif'>");
        },
        success: function(data){
            $("#datos").empty().append(data);
        }
    });    
}


function crearUsuario(){
     

     
        nombreP = $("#nombreP").val();
        apellidosP = $("#apellidosP").val();
        fechanacP = $("#fechanacP").val();
        telefonoP = $("#telefonoP").val();
        imagenP = $("#imagenP").val();
        nombreC1 = $("#nombreC1").val();
        movilC1 = $("#movilC1").val();
        emailC1 = $("#emailC1").val();
        nombreC2 = $("#nombreC2").val();
        movilC2 = $("#movilC2").val();
        emailC2 = $("#emailC2").val();
        nombreA = $("#nombreA").val();
        apellidosA = $("#apellidosA").val();
        dispositivoA = $("#dispositivoA").val();
        distanciaA = $("#distanciaA").val();
        imagenA = $("#imagenA").val();
        
        url="CreateUserServlet?nombreP="+nombreP+"&apellidosP="+apellidosP+"&fechanacP="+fechanacP+"&telefonoP="+telefonoP+
            "&imagenP="+imagenP+"&nombreC1="+nombreC1+"&movilC1="+movilC1+"&emailC1="+emailC1+"&nombreC2="+nombreC2+"&movilC2="+movilC2+"&emailC2="+emailC2+
            "&nombreA="+nombreA+"&apellidosA="+apellidosA+"&dispositivoA="+dispositivoA+"&distanciaA="+distanciaA+"&imagenA="+imagenA;
        
        $.ajax({
                    
            type: 'POST',
            url: url,
            beforeSend: function(){
              alert(url);  
            },                
            success: function(respuesta) {
                $("#content").empty().append(respuesta);
            },
            error: function() {
                alert("Los datos no han podido ser almacenados correctamente");
            }
        });      
                    
}
              
//Añadido para que no se recargue la página
function startUpload(){
    document.getElementById('f1_upload_process').style.visibility = 'visible';
    return true;
}

function stopUpload(success){
    var result = '';
    if (success == 1){
        document.getElementById('result').innerHTML =
        '<span class="msg">The file was uploaded successfully!<\/span><br/><br/>';
    }
    else {
        document.getElementById('result').innerHTML = 
        '<span class="emsg">There was an error during file upload!<\/span><br/><br/>';
    }
    document.getElementById('f1_upload_process').style.visibility = 'hidden';
    return true;   
}

function alarma(protegido,maltratador,lt,ln,tipo){
    alert("alertaaaa");
    $("#alarma").empty();
    $("#protegido").append("Protegido/a: "+protegido);
    if(tipo=="AU11"){
        $("#maltratador").empty();
    }else{
        $("#maltratador").append("Maltratador/a: "+maltratador);
    }
    
    $("#alarma").show();
}


function alarm(){
    alert("alarmaaaaa");
}