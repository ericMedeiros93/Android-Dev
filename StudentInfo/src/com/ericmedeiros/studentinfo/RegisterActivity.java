package com.ericmedeiros.studentinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_avtivity);

		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String program = intent.getStringExtra("program");
		boolean needResidence = intent.getBooleanExtra("needResidence", false);
		TextView txtName = (TextView) findViewById(R.id.txtName);
		TextView txtNeedResidence = (TextView) findViewById(R.id.txtNeedResidence);

		txtName.setText(name);
		txtNeedResidence.setText(needResidence ? "Needs Residence"
				: "Doesn't need residence");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_avtivity, menu);
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

	public void assignId(View view) {
		int id = 10001;
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("id", id);

		setResult(RESULT_OK, intent);
		finish();

	}

	public void residenceFull(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}
}
