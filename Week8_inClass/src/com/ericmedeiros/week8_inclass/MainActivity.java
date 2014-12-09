package com.ericmedeiros.week8_inclass;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.week9_inclass.R;

public class MainActivity extends Activity {

	private EditText edtName, edtEmail;
	private CheckBox chkNewsletter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edtName = (EditText) findViewById(R.id.edtName);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		chkNewsletter = (CheckBox) findViewById(R.id.chkNewsLetter);

		getPrefs();
	}

	private void getPrefs() {
		SharedPreferences myPrefs = getSharedPreferences("myPrefs",
				MODE_PRIVATE);

		String name = myPrefs.getString("name", null);
		String email = myPrefs.getString("email", null);
		boolean news = myPrefs.getBoolean("newsletter", false);
		
		chkNewsletter.setChecked(news);
		
		if (name != null) {
			edtName.setText(name);
		}
		
		if(email != null){
			edtEmail.setText(email);
		}
		
		

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

	public void save(View view) {
		SharedPreferences myPrefs = getSharedPreferences("myPrefs",
				MODE_PRIVATE);

		Editor editor = myPrefs.edit();

		if (edtName.getText().toString().length() > 0
				&& edtEmail.getText().toString().length() > 0) {
			editor.putString("name", edtName.getText().toString());
			editor.putString("email", edtEmail.getText().toString());
			editor.putBoolean("newsletter", chkNewsletter.isChecked());
			editor.apply();
		}
	}

	public void clear(View view) {
		SharedPreferences myPrefs = getSharedPreferences("myPrefs",
				MODE_PRIVATE);

		Editor editor = myPrefs.edit();
		editor.clear();
		editor.apply();

		edtName.setText("");
		edtEmail.setText("");
		chkNewsletter.setChecked(false);
		
	}
}
