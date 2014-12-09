package com.ericmedeiros.week8_takehome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
        	Intent prefs = new Intent(this, UserPrefActivity.class);
			startActivity(prefs);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if(key.equals("historyAmounts")){
			String keyValue = sharedPreferences.getString(key, "not set");
			Toast.makeText(this, key +" = "+keyValue , Toast.LENGTH_SHORT).show();
		}else{
			boolean keyValue = sharedPreferences.getBoolean(key, false);
			Toast.makeText(this, key +" = "+keyValue , Toast.LENGTH_SHORT).show();
		}
		
	}
}
