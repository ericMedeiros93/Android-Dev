package com.ericmedeiros.eric_medeiros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AccountType extends Activity {
	private String accountName = "";
	TextView txtAccountFor;
	Spinner spnAccountType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_type);
		
		//Get the users name
		Intent intent = getIntent();
		accountName = intent.getStringExtra("name");

		//Set the name of the user
		txtAccountFor = (TextView) findViewById(R.id.txtAccountFor);
		txtAccountFor.setText("Account for: " + accountName);

		//Fill the spinner
		spnAccountType = (Spinner) findViewById(R.id.spnAccountType);
		ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter
				.createFromResource(this, R.array.accountTypes,
						android.R.layout.simple_spinner_item);

		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnAccountType.setAdapter(typeAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_type, menu);
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

	/**
	 * Cancels the the account creation
	 * @param view
	 */
	public void cancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}

	/**
	 * Creates the account and sends back the account type
	 * @param view
	 */
	public void finish(View view) {
		String accountType = spnAccountType.getSelectedItem().toString();
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("accountType", accountType);

		setResult(RESULT_OK, intent);
		finish();
	}
}
