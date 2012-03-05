package org.inftel.pasos.activities;

import java.util.Observable;
import java.util.Observer;

import org.inftel.pasos.R;
import org.inftel.pasos.controlador.Controlador;
import org.inftel.pasos.modelo.Modelo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ZoomControls;

public class PreferenciasActivity extends Activity implements Observer {

	private static final String TAG = PreferenciasActivity.class
			.getSimpleName();
	private Modelo modelo;
	private Controlador controlador;

	// Elementos UI
	private TextView notif_titulo;
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
	private ZoomControls zoom;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferencias);

		Log.d(TAG, "ON START!");

		initModeloControlador();
		initViews();

		actualizarOpciones();
		actualizarTamTextos();
	}

	/**
	 * Actualiza el tama–o de los textos
	 */
	private void actualizarTamTextos(){
		float tam = controlador.getTamTexto();
		notif_titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, tam);
		vib_titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, tam);
		voz_titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, tam);
		tema_titulo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, tam);
		tema1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, tam-5);
		tema2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, tam-5);
		tema3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, tam-5);
	}
	
	/**
	 * Inicializa las variables que representan a los elementos de la UI
	 */
	private void initViews() {
		
		notif_titulo = (TextView) findViewById(R.id.pref_not_titulo);
		vib_titulo = (TextView) findViewById(R.id.pref_not_vib_titulo);
		vib_check = (CheckBox) findViewById(R.id.pref_not_vib_checkbox);
		voz_titulo = (TextView) findViewById(R.id.pref_not_voz_titulo);
		voz_check = (CheckBox) findViewById(R.id.pref_not_voz_checkbox);
		tema_titulo = (TextView) findViewById(R.id.pref_tema_titulo);
		tema1 = (RadioButton) findViewById(R.id.pref_tema_1);
		tema2 = (RadioButton) findViewById(R.id.pref_tema_2);
		tema3 = (RadioButton) findViewById(R.id.pref_tema_3);
		temaGroup = (RadioGroup) findViewById(R.id.pref_tema_radiogroup);
		zoom = (ZoomControls) findViewById(R.id.pref_zoom);

		// EVENTOS ZOOM
		zoom.setOnZoomInClickListener(new OnClickListener() {

			public void onClick(View v) {
				controlador.aumentarTexto();
			}
		});
		zoom.setOnZoomOutClickListener(new OnClickListener() {

			public void onClick(View v) {
				controlador.disminuirTexto();
			}
		});

		
		// EVENTO GROUP
		temaGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				Log.d(TAG, "SELECCIONADO -> " + checkedId);
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
		actualizarTamTextos();
	}

}