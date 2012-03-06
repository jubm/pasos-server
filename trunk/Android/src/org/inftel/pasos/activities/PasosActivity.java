package org.inftel.pasos.activities;

import org.inftel.pasos.R;
import org.inftel.pasos.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class PasosActivity extends Activity{

	private static final String TAG = PasosActivity.class.getSimpleName();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageButton button = (ImageButton)findViewById(R.id.imageButton1);
		button.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				sendFrame();
				return true;
			}
		});
	}

	// MENU
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_configuracion:

			Intent intent = new Intent(this, PreferenciasActivity.class);
			startActivity(intent);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendFrame(){
		/*Location location= Utils.currentLocation(context);
		Double LT = location.getLatitude();
		Double LN = location.getLongitude();
		String RD = Utils.getIMEI(context);
		String trama = "*$AU11&LD20120306&LH0103157&LN204283491&LT136431697&RD358987010052665";
		Connection.sendMessage("","");
		Toast.makeText(context, "Mensaje Enviado", Toast.LENGTH_LONG).show();
		Log.d(getClass().getSimpleName(), "Enviando señal de alarma");*/
		Location location= Utils.currentLocation(this.getBaseContext());
		Double LT = location.getLatitude();
		Double LN = location.getLongitude();
		Log.d(TAG,"Latitud:"+LT);
		Log.d(TAG,"Longitud:"+LN);
	}
}