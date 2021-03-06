package com.ericmedeiros.pizzaordering;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txtFinalOrder;
	RadioGroup rgpSize;
	Switch swDeliveryPickup;
	Spinner spnAmount;
	CheckBox chkCheese, chkPepperoni, chkSausage, chkBacon, chkGreenPepper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initControls();
		FillSpinner();

	}

	protected void initControls() {
		txtFinalOrder = (TextView) findViewById(R.id.txtFinalOrder);
		rgpSize = (RadioGroup) findViewById(R.id.rgpSize);
		swDeliveryPickup = (Switch) findViewById(R.id.swDeliveryPickup);
		spnAmount = (Spinner) findViewById(R.id.spnPizzAmount);
		chkCheese = (CheckBox) findViewById(R.id.chkCheese);
		chkPepperoni = (CheckBox) findViewById(R.id.chkPepperoni);
		chkSausage = (CheckBox) findViewById(R.id.chkSausage);
		chkBacon = (CheckBox) findViewById(R.id.chkBacon);
		chkGreenPepper = (CheckBox) findViewById(R.id.chkGreenPepper);
	}

	protected void FillSpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.amounts, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnAmount.setAdapter(adapter);
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

	public void makePizza(View view) {
		String output = "";
		output += spnAmount.getSelectedItem().toString() + " ";

		int selected = rgpSize.getCheckedRadioButtonId();
		switch (selected) {
		case R.id.radSmall:
			output += "Small ";
			break;

		case R.id.radMedium:
			output += "Medium ";
			break;

		default:
			output += "Large ";
			break;
		}

		output += "with ";

		if (chkCheese.isChecked()) {
			output += "cheese ";
		}
		if (chkPepperoni.isChecked()) {
			output += "pepperoni ";
		}
		if (chkSausage.isChecked()) {
			output += "sausage ";
		}
		if (chkBacon.isChecked()) {
			output += "bacon ";
		}
		if (chkGreenPepper.isChecked()) {
			output += "green Pepper ";
		}

		if (swDeliveryPickup.isChecked()) {
			output += "for Delivery.";
		} else {
			output += "for Pickup.";
		}

		txtFinalOrder.setText(output);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		//Get back the order from the bundle coming in. 
		String previousOrder = savedInstanceState.getString("orderResult", ""); 
		txtFinalOrder.setText(previousOrder);
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		//Save to order into the bundle we're given. 
		outState.putString("orderResult", txtFinalOrder.getText().toString());
	}
}
