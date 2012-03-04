package org.inftel.pasos.controlador;

import org.inftel.pasos.modelo.Modelo;
import org.inftel.pasos.vos.Preferencias;

public class Controlador implements IControlador{

	private Modelo modelo;
	
	public Controlador(Modelo modelo){
		this.modelo = modelo;
	}
	
	public void setPreferencias(Preferencias preferencias) {
		modelo.setPreferencias(preferencias);
	}

}
