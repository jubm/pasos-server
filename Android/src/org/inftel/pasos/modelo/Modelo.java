package org.inftel.pasos.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.inftel.pasos.vos.Preferencias;

public class Modelo extends Observable implements Serializable{

	private static final long serialVersionUID = 1L;

	private Preferencias preferencias;

	public Modelo() {
	}

	public Preferencias getPreferencias() {
		return preferencias;
	}
	
	public void setPreferencias(Preferencias preferencias){
		this.preferencias = preferencias;
		setChanged();
		notifyObservers(preferencias);		
	}
	
}
