package com.ericmedeiros.week5_intnet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class ThirdActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		
		Intent intent = getIntent();
		if(intent != null){
			String text = intent.getStringExtra("text");
			if(text != null){
				CheckBox chkIsOn = (CheckBox) findViewById(R.id.chkisOn);
				chkIsOn.setText(text);
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
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

	public void returnOk(View view) {

		CheckBox chkIsOn = (CheckBox) findViewById(R.id.chkisOn);
		boolean isOn = chkIsOn.isChecked();

		Intent intent = new Intent();
		intent.putExtra("isOn", isOn);

		setResult(RESULT_OK, intent);
		finish();
	}

	public void returnCancel(View view) {

		setResult(RESULT_CANCELED);
		finish();
	}
}
