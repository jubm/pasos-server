package org.inftel.pasos.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Utils {
	
	public static void sendMessage(String message){
		HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.1.133:8080/PasosServerEnterpriseApplication-war/FrameHandlerServlet");
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
	
	public static String currentLocation(Context context){
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	        if (location==null) {
	        	Log.d("Localizacion","No hay localizacion conocida.Abortando...");
	            return "";
	        }else{
	        	String LT = Location.convert(location.getLatitude(),Location.FORMAT_SECONDS);
	        	String LN = Location.convert(location.getLongitude(),Location.FORMAT_SECONDS);
	    		Log.d("PasosActivity","CONVERSION lat: "+LT); 
	    		Log.d("PasosActivity","CONVERSION long: "+LN);
	    		
	    		String lat = composeLatitude(LT);
	    		Log.d("PasosActivity","TRAMA lat: "+lat); 
	    		String lon = composeLongitude(LN);
	    		Log.d("PasosActivity","TRAMA lon: "+lon); 
	    		return "&LN"+lon+"&LT"+lat;
	        }
	}
	
	public static String composeLatitude(String lt){
		String result="";
		String[] campos = lt.split(":");
		int grados = Integer.parseInt(campos[0]);  
		int minutos = Integer.parseInt(campos[1]);

		double segundos = Double.parseDouble(campos[2].replace(',', '.'));
		
		// GRADOS
		if(grados>0){
			result += "1";
		}else{
			result += "2";
		}
		grados = Math.abs(grados);
		
		if(grados <10){
			result += "0"+grados;
		}else{
			result += grados;
		}
		
		// MINUTOS
		result += minutos;
		
		// SEGUNDOS
		double diezMilesimasMinuto = segundos/0.006;
		String aux = String.valueOf(diezMilesimasMinuto);
		if(aux.length()>=4){
			result += aux.substring(0,4);
		}else{
			result += aux;
			for(int i=0;i<4-aux.length();i++){
				result += "0";
			}
		}
		return result;
	}
	
	public static String composeLongitude(String ln){
		String result="";
		String[] campos = ln.split(":");
		int grados = Integer.parseInt(campos[0]);  
		int minutos = Integer.parseInt(campos[1]);
		double segundos = Double.parseDouble(campos[2].replace(',', '.'));
		
		// GRADOS
		if(grados>0){
			result += "1";
		}else{
			result += "2";
		}
		grados = Math.abs(grados);
		
		if(grados <10){
			result += "00"+grados;
		}else if(grados <100){
			result += "0"+grados;
		}else{
			result += grados;
		}
		
		// MINUTOS
		result += minutos;
		
		// SEGUNDOS
		double diezMilesimasMinuto = segundos/0.006;
		String aux = String.valueOf(diezMilesimasMinuto);
		if(aux.length()>=4){
			result += aux.substring(0,4);
		}else{
			result += aux;
			for(int i=0;i<4-aux.length();i++){
				result += "0";
			}
		}
		return result;
	}
	
	public static String getIMEI(Context context){   	
    	TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    	String IMEI = tm.getDeviceId();
    	return "&RD"+IMEI;
    }
	
	public static String getDateHour(){	
		
		Calendar c = Calendar.getInstance();
		String anio = String.valueOf(c.get(Calendar.YEAR));
		String mes = String.valueOf(c.get(Calendar.MONTH));
		String dia = String.valueOf(c.get(Calendar.DATE));
		
		Log.d("PasosActivity", "A„O -> "+anio);
		Log.d("PasosActivity", "MES -> "+mes);
		Log.d("PasosActivity", "DIA -> "+dia);

		if (c.get(Calendar.MONTH) < 10)  
	        mes = '0' + mes;  
	  
	    if (c.get(Calendar.DATE) < 10)  
	        dia = '0' + dia;
	    
	    String segundos = String.valueOf(c.get(Calendar.SECOND));  
	    String minutos  = String.valueOf(c.get(Calendar.MINUTE)); 
	    String horas = String.valueOf(c.get(Calendar.HOUR));
	    horas = (c.get(Calendar.HOUR)>10) ? horas:"0"+horas;
	    minutos= (c.get(Calendar.MINUTE)>10) ? minutos:"0"+minutos;
	    segundos = (c.get(Calendar.SECOND)>10) ? segundos:"0"+segundos;
	    return "&LD"+anio+mes+dia+"&LH"+horas+minutos+segundos;
	}
}
