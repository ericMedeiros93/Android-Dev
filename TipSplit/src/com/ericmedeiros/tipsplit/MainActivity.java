package com.ericmedeiros.tipsplit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Spinner spnTips, spnPeople;
	private EditText edtOtherTip, edtCost;
	private TextView tvTip, tvAmount, tvSplit;
	private double total = 0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize the controls
		spnTips = (Spinner) findViewById(R.id.spnTipAmounts);
		spnPeople = (Spinner) findViewById(R.id.spnPeopleAmount);
		edtOtherTip = (EditText) findViewById(R.id.edtOtherAmount);
		edtCost = (EditText) findViewById(R.id.edtCost);
		tvTip = (TextView) findViewById(R.id.tvTipTotal);
		tvAmount = (TextView) findViewById(R.id.tvTotal);
		tvSplit = (TextView) findViewById(R.id.tvSplit);
		edtOtherTip.setEnabled(false);

		// Fill the spinners with content
		fillSpinners();

		// Create an onItemSelected listener to enable the other EditText
		spnTips.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				if (spnTips.getSelectedItemPosition() == 3
						|| spnTips.getSelectedItemPosition() == 4) {

					edtOtherTip.setEnabled(true);
				} else {
					edtOtherTip.setEnabled(false);
					
					//If the tip can be calculated without user input then the 
					//total and tip are re-calculated. 
					if(total != 0){
						calculate(spnTips);
					}
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		//Create an OnItemSelected listnener so when the amount of people is changed
		//the split total is changed. 
		spnPeople.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				if (!spnPeople.getSelectedItem().toString().equals("1")) {
					if (total != 0) {
						double split = total
								/ (Double.parseDouble(spnPeople
										.getSelectedItem().toString()));
						tvSplit.setText(String
								.format("$%.2f Per Person", split));
					}
				} else {
					tvSplit.setText("");
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}

		});
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
			makeDialog("TipSplit", "An easy to use Tip calulator!", true);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Fill the spinners with the arrays set in strings.xml
	 */
	private void fillSpinners() {
		ArrayAdapter<CharSequence> tipAdapter = ArrayAdapter
				.createFromResource(this, R.array.tip,
						android.R.layout.simple_spinner_item);
		
		tipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTips.setAdapter(tipAdapter);

		ArrayAdapter<CharSequence> peopleAdapter = ArrayAdapter
				.createFromResource(this, R.array.people,
						android.R.layout.simple_spinner_item);
		
		peopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spnPeople.setAdapter(peopleAdapter);
	}

	/**
	 * Calculates the totals for tip and per person then displays it
	 * 
	 * @param view
	 */
	public void calculate(View view) {

		if (edtCost.getText().toString().length() > 0) {
			double tipAmountPercent = 0;
			double tipAmountDollar = 0;
			double tip = 0;
			double cost = Double.parseDouble(edtCost.getText().toString());
			boolean dollar = false;

			switch (spnTips.getSelectedItemPosition()) {
			case 0:
				tipAmountPercent = .1;
				break;
			case 1:
				tipAmountPercent = .15;
				break;
			case 2:
				tipAmountPercent = .20;
				break;
			case 3:
				if (edtOtherTip.getText().toString().length() > 0) {
					double amount = Double.parseDouble(edtOtherTip.getText()
							.toString());
					tipAmountPercent = (amount / 100);
				} else {

					makeDialog("", "You must enter a tip amount!", false);
					return;
				}

				break;
			case 4:
				if (edtOtherTip.getText().toString().length() > 0) {
					tipAmountDollar = Double.parseDouble(edtOtherTip.getText()
							.toString());
					dollar = true;
					break;
				} else {
					makeDialog("", "You must enter a tip amount!", false);
					return;
				}

			}

			if (dollar == true) {
				tvTip.setText("Tip is: $" + tipAmountDollar);
				tip = tipAmountDollar;
			} else {
				tip = tipAmountPercent * cost;
				tvTip.setText(String.format("Tip is: $%.2f ", tip));
			}
			total = tip + cost;
			tvAmount.setText(String.format("Total is: $%.2f ", total));

			if (!spnPeople.getSelectedItem().toString().equals("1")) {
				double split = total
						/ (Double.parseDouble(spnPeople.getSelectedItem()
								.toString()));
				tvSplit.setText(String.format("$%.2f Per Person", split));
			}

		} else {
			makeDialog("", "You must enter the amount you owe!", false);
		}

	}

	/**
	 * Clears all of the controls to the defaults.
	 * @param view
	 */
	public void clear(View view) {
		spnTips.setSelection(0);
		spnPeople.setSelection(0);
		edtOtherTip.setText("");
		edtCost.setText("");
		tvTip.setText("");
		tvAmount.setText("");
		tvSplit.setText("");
		edtOtherTip.setText("");
	}

	@Override
	/**
	 * Gets any data that was in the text views showing the totals
	 */
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		tvTip.setText(savedInstanceState.getString("tvTip"));
		tvAmount.setText(savedInstanceState.getString("tvAmount"));
		tvSplit.setText(savedInstanceState.getString("tvSplit"));
	}

	@Override
	/**
	 * Puts any data that was in the text views showing the totals
	 */
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tvTip", tvTip.getText().toString());
		outState.putString("tvAmount", tvAmount.getText().toString());
		outState.putString("tvSplit", tvSplit.getText().toString());
		
	}

	/**
	 * This method creates dialog boxes throughout the app.
	 * @param title Title of the dialog box. Not required.
	 * @param message Main message of the dialog box.
	 * @param logo Adds the app logo to the dialog. Used for the about dialog.
	 */
	private void makeDialog(String title, String message, boolean logo) {
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
		if (logo) {
			builder.setIcon(R.drawable.ic_launcher);
		}
		Dialog dialog = builder.create();

		dialog.show();

	}

}
