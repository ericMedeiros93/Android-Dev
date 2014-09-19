package com.ericmedeiros.tipsplit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	Spinner spnTips, spnPeople;
	EditText edtOtherTip, edtCost;
	TextView tvTip, tvAmount, tvSplit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spnTips = (Spinner) findViewById(R.id.tip_amounts);
		spnPeople = (Spinner) findViewById(R.id.people_amount);
		edtOtherTip = (EditText) findViewById(R.id.edtOtherAmount);
		edtCost = (EditText) findViewById(R.id.edtCost);
		tvTip = (TextView) findViewById(R.id.tipTotal);
		tvAmount = (TextView) findViewById(R.id.total);
		tvSplit = (TextView) findViewById(R.id.split);
		edtOtherTip.setEnabled(false);

		fillSpinners();

		spnTips.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				if (spnTips.getSelectedItemPosition() == 3
						|| spnTips.getSelectedItemPosition() == 4) {

					edtOtherTip.setEnabled(true);
				} else {
					edtOtherTip.setEnabled(false);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void fillSpinners() {
		ArrayAdapter<CharSequence> tipAdapter = ArrayAdapter
				.createFromResource(this, R.array.tip,
						android.R.layout.simple_spinner_item);
		tipAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTips.setAdapter(tipAdapter);

		ArrayAdapter<CharSequence> peopleAdapter = ArrayAdapter
				.createFromResource(this, R.array.people,
						android.R.layout.simple_spinner_item);
		peopleAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnPeople.setAdapter(peopleAdapter);
	}

	public void calculate(View view) {

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
			double amount = Double
					.parseDouble(edtOtherTip.getText().toString());
			tipAmountPercent = (amount / 100);
			break;
		case 4:
			tipAmountDollar = Double.parseDouble(edtOtherTip.getText()
					.toString());
			dollar = true;
			break;

		}

		if (dollar == true) {
			tvTip.setText("Tip is: $" + tipAmountDollar);
		} else {
			tip = tipAmountPercent * cost;
			tvTip.setText(String.format("Tip is: $%.2f ", tip));
		}
		double total = tip + cost;
		tvAmount.setText(String.format("Total is: $%.2f ", total));

		double split = total
				/ (Double.parseDouble(spnPeople.getSelectedItem().toString()));
		tvSplit.setText(String.format("$%.2f Per Person", split));

	}

}
