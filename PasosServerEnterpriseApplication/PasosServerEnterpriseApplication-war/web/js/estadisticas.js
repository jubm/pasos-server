$(document).ready(function(){
    $("#buscarEstadisticas").click(function(){
        //alert("click");
        anio=$("#listaAnios").val();
        //alert("años:"+anio);
        $.post("http://localhost:8080/PasosServerEnterpriseApplication-war/EstadisticasServlet?anio="+anio,function(data){
            $("#grafico").empty().append(data);
        });
    }) 
});




