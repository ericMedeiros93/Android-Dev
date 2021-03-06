package com.ericmedeiros.week5_intnet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private static final int THIRD_ACTIVITY = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == THIRD_ACTIVITY) {
			if (resultCode == RESULT_OK) {
				boolean isOn = data.getBooleanExtra("isOn", false);

					Toast.makeText(this, "Ok returned with "+isOn, Toast.LENGTH_SHORT)
							.show();
				
			} else {

				Toast.makeText(this, "Cancel returned", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	public void closeSelf(View view) {

		// here we'll close the activity

		finish();
	}

	public void thirdActivity(View view) {

		EditText edit = (EditText) findViewById(R.id.edtEdit);
		String text = edit.getText().toString();
		
		// here we'll create the intent and launch
		// the second activity

		Intent second = new Intent(this, ThirdActivity.class);
		second.putExtra("text", text);
		startActivityForResult(second, THIRD_ACTIVITY);
	}
}
