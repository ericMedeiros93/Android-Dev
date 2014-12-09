package com.ericmedeiros.eric_medeiros;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText edtName, edtPassword, edtConfirm;
	private int account_type_code = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edtName = (EditText) findViewById(R.id.edtName);
		edtPassword = (EditText) findViewById(R.id.edtPassword);
		edtConfirm = (EditText) findViewById(R.id.edtConfirm);
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
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == account_type_code) {
			
			//If the request is canceled then notify user
			if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Request Canceled!", Toast.LENGTH_SHORT).show();
			} else {
				//If the account is created then notify the user about account type
				Toast.makeText(this, "Account type: \'" + data.getStringExtra("accountType")
								+ "\'\nsuccessfully created", Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * Validate user input and send it to the next intent if all is 
	 * correct
	 * @param view
	 */
	public void submit(View view) {
		//Check to make sure all of the edit texts have conetnt
		if (edtName.getText().toString().length() > 0
				&& edtPassword.getText().toString().length() > 0
				&& edtConfirm.getText().toString().length() > 0) {
			
			//If the passwords match go to the next intent
			if (edtPassword.getText().toString()
					.equals(edtConfirm.getText().toString())) {
				//Create intent and send users name
				Intent intent = new Intent(this, AccountType.class);
				intent.putExtra("name", edtName.getText().toString());
				startActivityForResult(intent, account_type_code);

			} else {
				//Notify user that the password don't match
				createDialog("Cannot continue...", "Passwords must match!");
			}

		} else {
			//Check which information is missing to show in the dialog
			String missing = "";
			if (edtName.getText().toString().length() == 0) {
				missing += "Missing Name! \n";
			}

			if (edtPassword.getText().toString().length() == 0) {
				missing += "Missing Password! \n";
			}

			if (edtConfirm.getText().toString().length() == 0) {
				missing += "Missing Confirm Password!";
			}
			
			//Create the dialog

			createDialog("Connot continue...", missing);
		}
	}

	/**
	 * Creates simple dialogs.
	 * @param title Title of the dialog to be created
	 * @param message Message of the dialog to be created
	 */
	private void createDialog(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		if (title.length() > 0) {
			builder.setTitle(title);
		}
		builder.setMessage(message);
		builder.setPositiveButton("Ok", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		Dialog dialog = builder.create();

		dialog.show();
	}
}
