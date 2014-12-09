package com.ericmedeiros.eric_medeiros_assignment3;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PaymentActivity extends Activity {

	private TextView txtSize, txtToppings, txtTotal;
	private RadioGroup grpPayment;
	private double total;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
		//Set up the controls. 
		grpPayment = (RadioGroup) findViewById(R.id.grpPayment);
		txtSize = (TextView) findViewById(R.id.txtSize);
		txtToppings = (TextView) findViewById(R.id.txtToppings);
		txtTotal = (TextView) findViewById(R.id.txtTotal);

		//Get the pizza size ordered
		Intent intent = getIntent();
		String pizzaSize = intent.getStringExtra("size");
		txtSize.setText(pizzaSize + " pizza with...");

		//Get the toppings picked by the customer
		ArrayList<Topping> t = intent.getParcelableArrayListExtra("toppings");
		String tops = "";
		total = intent.getIntExtra("basePrice", 0);
		for (int i = 0; i < t.size(); i++) {
			tops += t.get(i).getName() + "\n";
			total += t.get(i).getPrice();
		}
		//put the Toppings into the textview
		txtToppings.setText(tops);
		
		//Put the total cost into the textview
		txtTotal.setText(String.format("Total price with HST: $%.2f ",
				(total * 1.13)));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.payment, menu);
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

	public void pay(View view) {
		boolean paid = true;
		
		//Fifty fifty chance of the credit card option working
		if (grpPayment.getCheckedRadioButtonId() == R.id.rdbCredit) {
			Random rand = new Random();
			int worked = rand.nextInt(2);
			if (worked == 0) {
				paid = true;
			} else {
				paid = false;
			}
		}
		
		Intent returnIntent = new Intent();
		//return if the payment worked
		returnIntent.putExtra("accepted", paid);
		//return the payment total
		returnIntent.putExtra("total", (total * 1.13));
		setResult(RESULT_OK, returnIntent);
		finish();
	}
}
