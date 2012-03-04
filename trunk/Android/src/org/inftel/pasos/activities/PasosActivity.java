package org.inftel.pasos.activities;

import org.inftel.pasos.R;
import org.inftel.pasos.modelo.Modelo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PasosActivity extends Activity{
	
	
    private static final String TAG = PasosActivity.class.getSimpleName();
    private Modelo modelo;	
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init);
        
        // Se obtienen el surfer y la localización a partir del Intent
        Modelo modelo = new Modelo();
        
        /// FALTA GENERAR EL MODELO!!!

        
        // Una vez generado el modelo se pasa a la activity principal
        Intent i = new Intent(this,AlarmaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("modelo", modelo);
        i.putExtras(bundle);
        startActivity(i);
        
        
        Log.d(TAG,"Hola");
    }
}