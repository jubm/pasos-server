/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var marker;

var map;

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
    
    $("#iframe").hide();
    //$("#mapa").hide();
    
    /*
     * EVENTO BOTÓN FINALIZACIÓN ATENCIÓN ALARMA
     */
    $("#atendido").click(function(){            
        $.post('comet?action=atendido',function(data){});
        $("#alarma").hide();
    });
    //Load map
    initialize();    
     
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

function alarma(){   
    alert("alarmaaaaa");
    $("#iframe").show();
    //$("#iframe").empty().append(info);
    parent.mostrarMarker(LT,LN);  
}
function mostrarMarker(LT,LN){
        
        myLatlng = new google.maps.LatLng(LT ,LN);
        marker1 = new google.maps.Marker({
        position: myLatlng, 
              map: map
        });
        marker = marker1;
}
function borradoMarker(){    
    markersArrayProtegida.setMap(null);         
    
}

//Crear mapa
function initialize() {
    directionsDisplay = new google.maps.DirectionsRenderer();  
    var myLatlng1 = new google.maps.LatLng(36.717696,-4.463711);   
    var myOptions = {
        zoom: 15,
        center: myLatlng1,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("mapa"), myOptions);
    directionsDisplay.setMap(map);
}