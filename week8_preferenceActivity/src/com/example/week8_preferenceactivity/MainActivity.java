package com.example.week8_preferenceactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences myPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		myPrefs.registerOnSharedPreferenceChangeListener(this);

		String name = myPrefs.getString("name", null);
		Toast.makeText(this, "Name: " + name, Toast.LENGTH_SHORT).show();

		boolean password = myPrefs.getBoolean("savePassword", false);
		Toast.makeText(this, "Password: " + password, Toast.LENGTH_SHORT)
				.show();

		String interval = myPrefs.getString("intreval", null);
		Toast.makeText(this, "Interval: " + interval, Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent userPref = new Intent(this, userPrefActivity.class);
			startActivity(userPref);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		Log.wtf("PREFS", "PREF CHANGE");
		if(!key.equals("savePassword")){
			String newValue = sharedPreferences.getString(key, "not set");
					Toast.makeText(MainActivity.this, key +" = "+newValue , Toast.LENGTH_SHORT).show();
		}else{
			boolean newValue = sharedPreferences.getBoolean(key, false);
			Toast.makeText(MainActivity.this, key +" = "+newValue , Toast.LENGTH_SHORT).show();
		}
	}
}
