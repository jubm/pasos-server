package org.inftel.pasos.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.inftel.pasos.vos.Preferencias;

public class Modelo implements Serializable {

	private static final long serialVersionUID = 1L;
	private final List<Listener> listeners = new ArrayList<Listener>();

	private Preferencias preferencias;

	public Modelo() {
	}

	public synchronized Preferencias getPreferencias() {
		return preferencias;
	}
	
	public final void addListener(Listener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public final void removeListener(Listener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}
	
	
	public final void updatePreferencias() { 
		Preferencias newPreferencias = new Preferencias();
		//OBTENCIÓN DE LAS PREFERENCIAS
		
		
		synchronized (this) {
			preferencias = newPreferencias;
		}

		synchronized (listeners) {
			for (Listener listener : listeners) {
				listener.onModelStateUpdated(this);
			}
		}
	}
	
	
	
	// INTERFAZ PARA NOTIFICACIÓN DE CAMBIOS EN EL MODELO
	public interface Listener {
		void onModelStateUpdated(Modelo modelo);
	}

}
