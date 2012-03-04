package org.inftel.pasos.activities;

import java.util.Observable;
import java.util.Observer;

import org.inftel.pasos.R;
import org.inftel.pasos.controlador.Controlador;
import org.inftel.pasos.modelo.Modelo;
import org.inftel.pasos.vos.Preferencias;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PreferenciasActivity extends Activity implements Observer{
	
	
    private static final String TAG = PreferenciasActivity.class.getSimpleName();
    private Modelo modelo;
    private Controlador controlador;
	
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias);
        
        // Se obtienen el modelo a partir del Intent
        modelo = (Modelo) getIntent().getExtras().getSerializable("modelo");
        modelo.addObserver(this);
        this.controlador = new Controlador(modelo);
        Log.d(TAG,"Modelo y controlador establecidos");
               

    }

    
 	public void update(Observable observable, Object data) {
		
 		Log.d(TAG,"Update");
 		
	}
}