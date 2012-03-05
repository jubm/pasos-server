package org.inftel.pasos.modelo;

import java.io.Serializable;
import java.util.Observable;

import org.inftel.pasos.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Modelo extends Observable implements Serializable{

	private static final long serialVersionUID = 1L;

	private Boolean notifVibracion;
	private Boolean notifVoz;
	private String tema;
	private float tamTexto;
	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;
	
	public Modelo(Context ctx) {
		prefs = ctx.getSharedPreferences("prefs",
				Activity.MODE_PRIVATE);
		editor = prefs.edit();

		notifVibracion = prefs.getBoolean("vib", true);
		notifVoz = prefs.getBoolean("voz", true);
		tema = prefs.getString("tema", ctx.getString(R.string.tema1));
		tamTexto = prefs.getFloat("tam", 20);
	}

	
	
	public Boolean getNotifVibracion() {
		return notifVibracion;
	}



	public float getTamTexto() {
		return tamTexto;
	}



	public void setTamTexto(float tamTexto) {
		this.tamTexto = tamTexto;
		setChanged();
		notifyObservers(this);	
	}



	public void setNotifVibracion(Boolean notifVibracion) {
		this.notifVibracion = notifVibracion;
		editor.putBoolean("vib", notifVibracion);
		editor.commit();
		setChanged();
		notifyObservers(this);	
	}



	public Boolean getNotifVoz() {
		return notifVoz;
	}



	public void setNotifVoz(Boolean notifVoz) {
		this.notifVoz = notifVoz;
		editor.putBoolean("voz", notifVoz);
		editor.commit();
		setChanged();
		notifyObservers(this);	
	}



	public String getTema() {
		return tema;
	}



	public void setTema(String tema) {
		this.tema = tema;
		setChanged();
		notifyObservers(this);	
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
