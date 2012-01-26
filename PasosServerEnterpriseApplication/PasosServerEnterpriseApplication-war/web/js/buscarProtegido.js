/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("#boton_alarmas").hide();
    
    $("#buscar").click(function(){
        nombre=$("#nombre").val();
        apellidos=$("#apellidos").val();
        $.post("http://localhost:8080/PasosServerEnterpriseApplication-war/SearchServlet?nombre="+nombre+"&apellidos="+apellidos,function(data){
            $("#datos").empty().append(data);
        });
        $("#boton_alarmas").show();
    }) 
    
    $("#boton_alarmas").click(function(){
        $.post("http://localhost:8080/PasosServerEnterpriseApplication-war/TablaAlarmasServlet",function(data){
            $("#cont_alarmas").empty().append(data);
        });
    })
    
});


