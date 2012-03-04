package org.inftel.pasos.controlador;

import org.inftel.pasos.modelo.Modelo;

public class Controlador implements IControlador{

	private Modelo modelo;
	
	public Controlador(Modelo modelo){
		this.modelo = modelo;
	}

	public void setNotifVibrador(Boolean b) {
		this.modelo.setNotifVibracion(b);
	}

	public void setNotifVoz(Boolean b) {
		this.modelo.setNotifVoz(b);
	}

	public void setTema(String t) {
		this.modelo.setTema(t);
	}

	public void setTamTexto(int t) {
		// TODO Auto-generated method stub
		
	}

	public void aumentarTexto() {
		// TODO Auto-generated method stub
		
	}

	public void disminuirTexto() {
		// TODO Auto-generated method stub
		
	}

	public Boolean getNotifVibracion() {
		return this.modelo.getNotifVibracion();
	}

	public Boolean getNotifVoz() {
		return this.modelo.getNotifVoz();
	}

	public String getTema() {
		return this.modelo.getTema();
	}

	public int getTamTexto() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
