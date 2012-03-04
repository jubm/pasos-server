package org.inftel.pasos.controlador;


public interface IControlador {
	
	void setNotifVibrador(Boolean b);
	void setNotifVoz(Boolean b);
	void setTema(String t);
	void setTamTexto(int t);
	void aumentarTexto();
	void disminuirTexto();
	Boolean getNotifVibracion();
	Boolean getNotifVoz();
	String getTema();
	int getTamTexto();
}
