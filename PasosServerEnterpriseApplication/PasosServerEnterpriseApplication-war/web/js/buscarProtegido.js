/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
   $("#buscar").click(function(){
       nombre=$("#nombre").val();
       apellidos=$("#apellidos").val();
        $.post("http://localhost:8080/PasosServerEnterpriseApplication-war/SearchServlet?nombre="+nombre+"&apellidos="+apellidos,function(data){
            $("#datos").empty().append(data);
        });
    }) 
});


