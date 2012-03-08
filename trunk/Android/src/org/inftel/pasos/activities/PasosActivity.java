package org.inftel.pasos.activities;

import org.inftel.pasos.R;
import org.inftel.pasos.receiver.ProximityIntentReceiver;
import org.inftel.pasos.receiver.SMS_Receiver;
import org.inftel.pasos.utils.Utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;

public class PasosActivity extends Activity {

	private static final String TAG = PasosActivity.class.getSimpleName();
	private int level;
	private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1;
	private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; 

	private static final long EXPIRATION = -1;

	private static final String PROX_ALERT_INTENT = "org.inftel.pasos.receiver.ProximityAlert";
	private static final String SMS_RECEIVER_INTENT = "android.provider.Telephony.SMS_RECEIVED";

	private LocationManager locationManager;

	private PendingIntent proximityIntent;
	private Intent intent;

	private SMS_Receiver sms_Receiver;
	private ProximityIntentReceiver proximityIntentReceiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATE, MINIMUM_DISTANCECHANGE_FOR_UPDATE,
				new MyLocationListener());

		IntentFilter filter = new IntentFilter(SMS_RECEIVER_INTENT);
		sms_Receiver = new SMS_Receiver(this);
		registerReceiver(sms_Receiver, filter);
		ImageButton button = (ImageButton) findViewById(R.id.imageButton1);
		this.registerReceiver(this.infoReceiver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
		button.setOnLongClickListener(new OnLongClickListener() {

			public boolean onLongClick(View v) {
				sendFrame();
				return true;
			}
		});
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		locationManager.removeProximityAlert(proximityIntent);
		unregisterReceiver(sms_Receiver);
		unregisterReceiver(proximityIntentReceiver);
	}

	private BroadcastReceiver infoReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent intent) {
			level = intent.getIntExtra("level", 0);
			// contentTxt.setText(String.valueOf(level) + "%");

		}
	};

	// MENU
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;

		switch (item.getItemId()) {
		case R.id.menu_configuracion:

			intent = new Intent(this, PreferenciasActivity.class);
			startActivity(intent);

			return true;
			
		case R.id.menu_contactos:
			
			intent = new Intent(this, ContactosActivity.class);
			startActivity(intent);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendFrame(){

		String location= Utils.currentLocation(this.getBaseContext());
		String fechaHora =Utils.getDateHour();
		String imei = Utils.getIMEI(this.getBaseContext());
		String trama = "$AU11"+fechaHora+location+imei;
		Log.d(TAG, trama);
		Utils.sendMessage(trama);

	}

	public void saveProximityAlertPoint(double longitude, double latitude,
			double radio) {

		if (proximityIntent != null) {
			Log.d(getClass().getSimpleName(), "removeProximityAlert");
			locationManager.removeProximityAlert(proximityIntent);
		} else {
			IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
			proximityIntentReceiver = new ProximityIntentReceiver();
			registerReceiver(proximityIntentReceiver, filter);
		}

		intent = new Intent(PROX_ALERT_INTENT);
		proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		Log.d(getClass().getSimpleName(), "configurando proximityAlert");
		locationManager.addProximityAlert(latitude, longitude, (float) radio,
				EXPIRATION, proximityIntent);

	}
	public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
        	
        }
        public void onStatusChanged(String s, int i, Bundle b) {            
        }
        public void onProviderDisabled(String s) {
        }
        public void onProviderEnabled(String s) {            
        }
    }
}