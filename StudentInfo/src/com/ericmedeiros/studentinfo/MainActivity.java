package com.ericmedeiros.studentinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int REGISTER_ACTIVITY = 100;

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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REGISTER_ACTIVITY) {
			if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Residence Full!", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(this,
						"Registration Succeeded " + data.getIntExtra("id", -1),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void register(View view) {

		EditText edtName = (EditText) findViewById(R.id.edtName);
		RadioGroup grpProgram = (RadioGroup) findViewById(R.id.grpProgram);

		String name = edtName.getText().toString();
		int selected = grpProgram.getCheckedRadioButtonId();
		String program = "";

		switch (selected) {

		case R.id.radSDNE:
			program = "SDNE";
			break;

		case R.id.radSA:
			program = "SA";
			break;

		case R.id.radCP:
			program = "CP";
			break;

		default:
			program = "N/A";

		}

		boolean needResidence = ((CheckBox) findViewById(R.id.chkNeedResidence))
				.isChecked();

		Intent intent = new Intent(this, RegisterActivity.class);
		intent.putExtra("name", name);
		intent.putExtra("program", program);
		intent.putExtra("needResidence", needResidence);

		startActivityForResult(intent, REGISTER_ACTIVITY);

	}
}
