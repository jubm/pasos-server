package org.inftel.pasos.vos;

public class Preferencias {
	
	String ipServidor;
	String IMEI;
	double centroZonaExcluida;
	int radioZonaExcluida;
	
	public Preferencias(){
		
	}

	public String getIpServidor() {
		return ipServidor;
	}

	public void setIpServidor(String ipServidor) {
		this.ipServidor = ipServidor;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public double getCentroZonaExcluida() {
		return centroZonaExcluida;
	}

	public void setCentroZonaExcluida(double centroZonaExcluida) {
		this.centroZonaExcluida = centroZonaExcluida;
	}

	public int getRadioZonaExcluida() {
		return radioZonaExcluida;
	}

	public void setRadioZonaExcluida(int radioZonaExcluida) {
		this.radioZonaExcluida = radioZonaExcluida;
	}
	
	

}
