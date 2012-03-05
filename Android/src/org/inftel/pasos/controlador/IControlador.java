package org.inftel.pasos.controlador;


public interface IControlador {
	
	void setNotifVibrador(Boolean b);
	void setNotifVoz(Boolean b);
	void setTema(String t);
	void setTamTexto(float t);
	void aumentarTexto();
	void disminuirTexto();
	Boolean getNotifVibracion();
	Boolean getNotifVoz();
	String getTema();
	float getTamTexto();
}
