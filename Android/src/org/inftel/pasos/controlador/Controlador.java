package org.inftel.pasos.controlador;

import org.inftel.pasos.modelo.Modelo;
import org.inftel.pasos.vos.Preferencias;

public class Controlador {

	private Modelo modelo;
	
	public Controlador(Modelo modelo){
		this.modelo = modelo;
	}
	
	public Preferencias getPreferencias(){
		return this.modelo.getPreferencias();
	}
	
}
