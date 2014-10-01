package com.ericmedeiros.week5_inclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetLastName extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_last_name);
		
		Intent intent = getIntent();
		TextView txtFirstName = (TextView) findViewById(R.id.txtFirstName);
		txtFirstName.setText("Hi, "+intent.getStringExtra("firstName"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_last_name, menu);
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
	
	public void returnLastName(View view){
		EditText edtLastName = (EditText) findViewById(R.id.edtLastName);
		String lastName = edtLastName.getText().toString();
		if(lastName.length()>0){
			Intent intent = new Intent();
			intent.putExtra("lastName", lastName);

			setResult(RESULT_OK, intent);
			finish();
		}else{
			Toast.makeText(this, "You must enter a last name!", Toast.LENGTH_SHORT).show();
		}
	}
}
