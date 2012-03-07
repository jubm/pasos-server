package org.inftel.pasos.activities;

import org.inftel.pasos.R;
import org.inftel.pasos.utils.Utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;

public class PasosActivity extends Activity{

	private static final String TAG = PasosActivity.class.getSimpleName();
	private int level;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageButton button = (ImageButton)findViewById(R.id.imageButton1);
		 this.registerReceiver(this.infoReceiver,   
			        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		button.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				sendFrame();
				return true;
			}
		});
	}
private BroadcastReceiver infoReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context arg0, Intent intent) {
			 level = intent.getIntExtra("level", 0);  
		     //contentTxt.setText(String.valueOf(level) + "%");
			
		}
	};

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
		
		String trama = "*$AU11&LD20120306&LH0103157&LN204283491&LT136431697&RD358987010052665";
		Connection.sendMessage("","");
		Toast.makeText(context, "Mensaje Enviado", Toast.LENGTH_LONG).show();
		Log.d(getClass().getSimpleName(), "Enviando señal de alarma");*/
		
		
		
		/*Location location= Utils.currentLocation(this.getBaseContext());
		Double LT = location.getLatitude();
		Double LN = location.getLongitude();
		String RD = Utils.getIMEI(this.getBaseContext());*/
		String fechaHora =Utils.getDateHour();
		Log.d("TAG", fechaHora);
	}
}