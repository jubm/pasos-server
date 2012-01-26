/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#atendido").click(function(){            
            $.post('chat?action=atendido',function(data){});
        });
});


function alarma(){
    alert('ALARMAAAAA');
//    document.getElementById('alarma').innerHTML='alarmaaaaaaaa'
//    parent.document.getElementById('alarma').innerHTML='alarmaaaaaaaa';
}

