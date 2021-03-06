package com.ericmedeiros.week4_inclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText name;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name = (EditText) findViewById(R.id.edtName);
		display = (TextView) findViewById(R.id.tvDisplay);
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
		} else if (id == R.id.display) {
			showName();
		} else if (id == R.id.reset) {
			clear();
		}
		return super.onOptionsItemSelected(item);
	}

	public void showName() {
		if (name.length() > 0) {
			display.setText("Hello " + name.getText().toString());
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Name empty");
			builder.setMessage("You must enter a name.");
			builder.setPositiveButton("Ok", null);

			Dialog dialog = builder.create();
			dialog.show();
		}
	}

	public void clear() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage("Reset all text?");

		builder.setPositiveButton("Ok", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				name.setText("");
				display.setText("");
			}
		});

		builder.setNegativeButton("Cancel", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		Dialog dialog = builder.create();
		dialog.show();
	}

}
