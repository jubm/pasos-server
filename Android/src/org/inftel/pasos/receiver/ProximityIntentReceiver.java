package org.inftel.pasos.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.graphics.Color;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

public class ProximityIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        
        String key = LocationManager.KEY_PROXIMITY_ENTERING;

        Boolean entering = intent.getBooleanExtra(key, false);
        
        if (entering) {
            Log.d(getClass().getSimpleName(), "Entrando en area restringida!!!!");
            Toast toast = Toast.makeText(context,"Entrando en area restringida!!!!" , Toast.LENGTH_LONG);
            toast.show();  
        }
        else {
            Log.d(getClass().getSimpleName(), "Saliendo del area!!!");
            Toast toast = Toast.makeText(context,"Saliendo del area!!!" , Toast.LENGTH_LONG);
            toast.show(); 
        }
        
    }
    private String getAlarmCenterIP(Context context){
    	 String alarmCenterIp;
         SharedPreferences prefs = context.getSharedPreferences("ConfigurationSendMessage", Context.MODE_PRIVATE);    
         alarmCenterIp = prefs.getString("IP","default_ip"); 
         return alarmCenterIp;
    }

}
