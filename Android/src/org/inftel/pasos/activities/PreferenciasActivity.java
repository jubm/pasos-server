package org.inftel.pasos.activities;

import java.util.Observable;
import java.util.Observer;

import org.inftel.pasos.R;
import org.inftel.pasos.controlador.Controlador;
import org.inftel.pasos.modelo.Modelo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class PreferenciasActivity extends Activity implements Observer {

	private static final String TAG = PreferenciasActivity.class
			.getSimpleName();
	private Modelo modelo;
	private Controlador controlador;

	// Elementos UI
	private TextView vib_titulo;
	private TextView vib_desc;
	private CheckBox vib_check;
	private TextView voz_titulo;
	private TextView voz_desc;
	private CheckBox voz_check;
	private TextView tema_titulo;
	private TextView tema_desc;
	private RadioButton tema1;
	private RadioButton tema2;
	private RadioButton tema3;
	private RadioGroup temaGroup;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferencias);

		Log.d(TAG, "ON START!");

		initModeloControlador();
		initViews();

		actualizarOpciones();
	}

	/**
	 * Inicializa las variables que representan a los elementos de la UI
	 */
	private void initViews() {
		vib_titulo = (TextView) findViewById(R.id.pref_not_vib_titulo);
		vib_desc = (TextView) findViewById(R.id.pref_not_vib_desc);
		vib_check = (CheckBox) findViewById(R.id.pref_not_vib_checkbox);
		voz_titulo = (TextView) findViewById(R.id.pref_not_voz_titulo);
		voz_desc = (TextView) findViewById(R.id.pref_not_voz_desc);
		voz_check = (CheckBox) findViewById(R.id.pref_not_voz_checkbox);
		tema_titulo = (TextView) findViewById(R.id.pref_tema_titulo);
		tema_desc = (TextView) findViewById(R.id.pref_tema_desc);
		tema1 = (RadioButton) findViewById(R.id.pref_tema_1);
		tema2 = (RadioButton) findViewById(R.id.pref_tema_2);
		tema3 = (RadioButton) findViewById(R.id.pref_tema_3);
		temaGroup = (RadioGroup) findViewById(R.id.pref_tema_radiogroup);
		
		temaGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				Log.d(TAG,"SELECCIONADO -> "+checkedId);
			}
		});
		
	}

	/**
	 * Inicializa el modelo y establece el controlador
	 */
	private void initModeloControlador() {

		modelo = new Modelo(this.getBaseContext());
		modelo.addObserver(this);
		this.controlador = new Controlador(modelo);
		Log.d(TAG, "Modelo y controlador establecidos");
	}

	/**
	 * Repinta el estado de los elementos seleccionables de la UI
	 */
	private void actualizarOpciones() {

		vib_check.setChecked(controlador.getNotifVibracion());
		voz_check.setChecked(controlador.getNotifVoz());
		Log.d(TAG, "VIBRA ->" + controlador.getNotifVibracion());
		Log.d(TAG, "VOZ -> " + controlador.getNotifVoz());

		String t = controlador.getTema();
		if (t.equals(getString(R.id.pref_tema_1))) {
			tema1.setChecked(true);
			tema2.setChecked(false);
			tema3.setChecked(false);
		} else if (t.equals(getString(R.id.pref_tema_2))) {
			tema1.setChecked(false);
			tema2.setChecked(true);
			tema3.setChecked(false);
		} else if (t.equals(getString(R.id.pref_tema_3))) {
			tema1.setChecked(false);
			tema2.setChecked(false);
			tema3.setChecked(true);
		}
	}

	public void onClickVibracion(View v) {
		CheckBox cb = (CheckBox) v;
		controlador.setNotifVibrador(cb.isChecked());
	}

	public void onClickVoz(View v) {
		CheckBox cb = (CheckBox) v;
		controlador.setNotifVoz(cb.isChecked());
	}

	/**
	 * Notificaci—n de actualizaci—n en modelo
	 */
	public void update(Observable observable, Object data) {

		Log.d(TAG, "Update");

	}
}