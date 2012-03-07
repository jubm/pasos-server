package org.inftel.pasos.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Utils {
	
	public static void sendMessage(String message){
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://10.0.2.2:8080/PasosServerEnterpriseApplication-war/FrameHandlerServlet");
        try {
            
        	List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("trama", message));
            UrlEncodedFormEntity sendentity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
            httppost.setEntity(sendentity);

            // Execute HTTP Post Request
            httpclient.execute(httppost);
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
	}
	
	public static Location currentLocation(Context context){
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	        if (location==null) {
	        	Log.d("Localizacion","No hay localizacion conocida.Abortando...");
	            return null;
	        }
	     return location;
		
		
	}
	
	public static String getIMEI(Context context){   	
    	TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    	String IMEI = tm.getDeviceId();
    	return IMEI;
    }
	
	public static String getDateHour(){
		Date d=new Date();
		String dia=String.valueOf(d.getDate());
		String mes=String.valueOf(d.getMonth());
		String anio=String.valueOf(d.getYear());
		if (d.getMonth() < 10)  
	        mes = '0' + mes;  
	  
	    if (d.getDate() < 10)  
	        dia = '0' + dia;
	    
	    String segundos = String.valueOf(d.getSeconds());  
	    String minutos  = String.valueOf(d.getMinutes()); 
	    String horas = String.valueOf(d.getHours());
	    horas = (d.getHours()>10) ? horas:"0"+horas;
	    minutos= (d.getMinutes()>10) ? minutos:"0"+minutos;
	    segundos = (d.getSeconds()>10) ? segundos:"0"+segundos;
	    return "LD"+anio+mes+dia+"&LH"+horas+minutos+segundos;
	}
}
