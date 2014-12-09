package com.example.week11_listview_adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void loadProvinces(View view) {
		// get the ListView 
		ListView lstProvinces = (ListView) findViewById(R.id.lstProvinces);

		// get the Provinces array from the Province class
		Province[] province = Province.getProvinces(this);

		// create a ArrayAdapter to use
		//MyArrayAdapter arrayAdapter = new MyArrayAdapter(
		//		this,R.layout.list_provinces, province);

		//ProvincialArms[] provincialArms = ProvincialArms.getProvincalArms(this);
		MergedProvince[] mergedProvincial = MergedProvince.getMergedProvinces(this);
		
		MyMergedArrayAdapter arrayAdapter = new MyMergedArrayAdapter(
				this, R.layout.list_provinces_merged, mergedProvincial );
		// set the ListView's adapter to arrayAdapter
		lstProvinces.setAdapter(arrayAdapter);
	}

	
	
	
}
