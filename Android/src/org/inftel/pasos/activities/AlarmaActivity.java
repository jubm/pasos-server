package org.inftel.pasos.activities;

import org.inftel.pasos.R;
import org.inftel.pasos.controlador.Controlador;
import org.inftel.pasos.modelo.Modelo;
import org.inftel.pasos.modelo.Modelo.Listener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AlarmaActivity extends Activity implements Listener{
	
	
    private static final String TAG = AlarmaActivity.class.getSimpleName();
    private Modelo modelo;
    private Controlador controlador;
	
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Se obtienen el modelo a partir del Intent
        modelo = (Modelo) getIntent().getExtras().getSerializable("modelo");

        // Se establece el modelo
        Log.d(TAG,"Se va a establecer el modelo");
        setModelo(modelo);
    }

    
    public void setModelo(Modelo modelo) {

        if (modelo == null) {
            throw new NullPointerException("Modelo");
        }

        Modelo oldModel = this.modelo;
        if (oldModel != null) {
            oldModel.removeListener(this);
        }
        this.modelo = modelo;
        this.modelo.addListener(this);
        this.controlador = new Controlador(this.modelo);

    }
    
    
	public void onModelStateUpdated(Modelo modelo) {
		// TODO Auto-generated method stub
		
	}
}