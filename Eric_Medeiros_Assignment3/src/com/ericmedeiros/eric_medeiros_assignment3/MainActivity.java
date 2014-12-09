package com.ericmedeiros.eric_medeiros_assignment3;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements
		OnSharedPreferenceChangeListener {
	
   /* 
    * Controls are named after toppings, if topping amount or names change this will
	* No longer work. Creating the topping UI elements programmatically would be best.
	* Unfortunately I don't know how to modify or get information on these elements after
	* they have been created
	*/
	private CheckBox chkCheese, chkPepperoni, chkSausage, chkGreenPepper,
			chkBacon;
	private RadioGroup grpSizes;
	private RadioButton rdbSmall, rdbMedium, rdbLarge;
	private Button btnPayment;
	private TextView txtPaymentTotal;
	private Pizza p;
	private boolean ORDER_MENU_ENABLED_FLAG;
	private int PAYMENT_REQUEST_CODE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Set up the controls
		
		chkCheese = (CheckBox) findViewById(R.id.chkCheese);
		chkPepperoni = (CheckBox) findViewById(R.id.chkPepperoni);
		chkSausage = (CheckBox) findViewById(R.id.chkSausage);
		chkGreenPepper = (CheckBox) findViewById(R.id.chkGreenPepper);
		chkBacon = (CheckBox) findViewById(R.id.chkBacon);
		grpSizes = (RadioGroup) findViewById(R.id.grpSize);
		rdbSmall = (RadioButton) findViewById(R.id.rdbSmall);
		rdbMedium = (RadioButton) findViewById(R.id.rdbMedium);
		rdbLarge = (RadioButton) findViewById(R.id.rdbLarge);
		btnPayment = (Button) findViewById(R.id.btnPayment);
		txtPaymentTotal = (TextView) findViewById(R.id.txtPaymentTotal);

		// Get the information from the JSON file
		getPrices prices = new getPrices();
		prices.execute();

		// Listener changes the prices of the toppings based on
		// which pizza size is selected.
		grpSizes.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				double factor;
				if (checkedId == R.id.rdbMedium) {
					factor = (p.getMediumFactor());

				} else if (checkedId == R.id.rdbLarge) {

					factor = (p.getLargeFactor());
				} else {
					factor = 0;
				}
				setPrices(factor);

			}

		});

		SharedPreferences myPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		myPrefs.registerOnSharedPreferenceChangeListener(this);
		ORDER_MENU_ENABLED_FLAG = myPrefs.getBoolean("saveOrder", false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		//Get the save and load item menu items
		MenuItem saveItem = menu.findItem(R.id.saveOrderItem);
		MenuItem loadItem = menu.findItem(R.id.loadOrderItem);
		//Change visibility based on the flag
		saveItem.setVisible(ORDER_MENU_ENABLED_FLAG);
		loadItem.setVisible(ORDER_MENU_ENABLED_FLAG);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent userPref = new Intent(this, userPreferenceActivity.class);
			startActivity(userPref);
			return true;
		}else if(id == R.id.saveOrderItem){
			saveOrder();
		}else if(id == R.id.loadOrderItem){
			loadOrder();
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This method calculates and sets the pizza topping prices
	 * 
	 * @param factor what to add to the base pizza topping price.
	 */
	public void setPrices(double factor) {
		chkCheese.setText(String.format(p.getToppings()[0].getName() + " $"
				+ "%.2f", (p.getToppings()[0].getPrice() + factor)));

		chkGreenPepper.setText(String.format(p.getToppings()[1].getName()
				+ " $" + "%.2f", (p.getToppings()[1].getPrice() + factor)));

		chkPepperoni.setText(String.format(p.getToppings()[2].getName() + " $"
				+ "%.2f", (p.getToppings()[2].getPrice() + factor)));

		chkSausage.setText(String.format(p.getToppings()[3].getName() + " $"
				+ "%.2f", (p.getToppings()[3].getPrice() + factor)));

		chkBacon.setText(String.format(p.getToppings()[4].getName() + " $"
				+ "%.2f", (p.getToppings()[4].getPrice() + factor)));
	}

	/**
	 * Gets what toppings are selected and the pizza size then send the information
	 * to the payment activity. Totals are calculated by the payment activity.
	 * @param view
	 */
	public void payment(View view) {
		Intent i = new Intent(this, PaymentActivity.class);
		ArrayList<Topping> t = new ArrayList<Topping>();
		if (chkCheese.isChecked() == true) {
			t.add(p.getToppings()[0]);
		}
		if (chkPepperoni.isChecked() == true) {
			t.add(p.getToppings()[1]);
		}
		if (chkSausage.isChecked() == true) {
			t.add(p.getToppings()[2]);
		}
		if (chkGreenPepper.isChecked() == true) {
			t.add(p.getToppings()[3]);
		}
		if (chkBacon.isChecked() == true) {
			t.add(p.getToppings()[4]);
		}

		if (grpSizes.getCheckedRadioButtonId() == R.id.rdbSmall) {
			i.putExtra("size", "Small");
			i.putExtra("basePrice", p.getSmallPrice());
		} else if (grpSizes.getCheckedRadioButtonId() == R.id.rdbMedium) {
			i.putExtra("size", "Medium");
			i.putExtra("basePrice", p.getMediumPrice());
		} else {
			i.putExtra("size", "Large");
			i.putExtra("basePrice", p.getLargePrice());
		}

		i.putExtra("toppings", t);

		startActivityForResult(i, PAYMENT_REQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PAYMENT_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				
				if (data.getBooleanExtra("accepted", false) == true) {
					//if the payment was successful change the button text and 
					//put the total below it.
					btnPayment.setText("New Order");
					double total = data.getDoubleExtra("total", 0.0);
					txtPaymentTotal.setText(String.format("Payment Accepted.\nTotal is $%.2f inc. HST",
											total));
					txtPaymentTotal.setTextColor(Color.parseColor("black"));
				} else {
					//if the payment wasn't successful change the text to say it failed
					//and set the text colour as red.
					txtPaymentTotal.setText("Payment not accepted!");
					txtPaymentTotal.setTextColor(Color.parseColor("red"));
				}
			}
		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		//if saveOrder is changed then change the flag and update the menu items.
		if (key.equals("saveOrder")) {
			boolean newValue = sharedPreferences.getBoolean("saveOrder", false);
			if (newValue == false) {
				ORDER_MENU_ENABLED_FLAG = false;
				invalidateOptionsMenu();
			} else {
				ORDER_MENU_ENABLED_FLAG = true;
				invalidateOptionsMenu();
			}
		}
	}
	
	/**
	 * Saves the favourite order to Shared Preferences
	 */
	private void saveOrder(){
		SharedPreferences favPrefs = getSharedPreferences("favPref", MODE_PRIVATE);
		Editor edt = favPrefs.edit();
		edt.putBoolean("cheese", chkCheese.isChecked());
		edt.putBoolean("pepperoni", chkPepperoni.isChecked());
		edt.putBoolean("sausage", chkSausage.isChecked());
		edt.putBoolean("greenPepper", chkGreenPepper.isChecked());
		edt.putBoolean("bacon", chkBacon.isChecked());
		

		if (grpSizes.getCheckedRadioButtonId() == R.id.rdbSmall) {
			edt.putString("size", "small");
		} else if (grpSizes.getCheckedRadioButtonId() == R.id.rdbMedium) {

			edt.putString("size", "medium");
		} else {

			edt.putString("size", "large");
		}
		edt.commit();
	}
	
	/**
	 * Loads the favourite order from shared preferences.
	 */
	private void loadOrder(){
		SharedPreferences favPrefs = getSharedPreferences("favPref", MODE_PRIVATE);
		chkCheese.setChecked(favPrefs.getBoolean("cheese", false));
		chkPepperoni.setChecked(favPrefs.getBoolean("pepperoni", false));
		chkSausage.setChecked(favPrefs.getBoolean("sausage", false));
		chkGreenPepper.setChecked(favPrefs.getBoolean("greenPepper", false));
		chkBacon.setChecked(favPrefs.getBoolean("bacon", false));
		
		String size = favPrefs.getString("size", "small");
		if(size.equals("small")){
			rdbSmall.setChecked(true);
			setPrices(p.getSmallFactor());
		}else if(size.equals("medium")){
			rdbMedium.setChecked(true);
			setPrices(p.getMediumFactor());
		}else{
			rdbLarge.setChecked(true);
			setPrices(p.getLargeFactor());
		}
	}

	/**
	 * This Async task gets the prices from the pizza.json file in the RAW folder.
	 * Using the Pizza and Toping objects may have been over complicating things. It seems
	 * to work well here.
	 * @author eric
	 *
	 */
	public class getPrices extends AsyncTask<Void, Void, Pizza> {

		@Override
		protected Pizza doInBackground(Void... params) {
			//read in the file
			Resources res = getResources();
			InputStream inputStream = res.openRawResource(R.raw.pizzas);
			try {
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				// return
				return parsePizzaFile(new String(buffer));

			} catch (Exception ex) {

			}
			return null;
		}

		private Pizza parsePizzaFile(String jsonData) {

			StringBuilder pizzaBuilder = new StringBuilder();

			try {
				JSONObject wrapper = new JSONObject(jsonData);

				JSONObject pizzaInfo = wrapper.getJSONObject("pizza-prices");

				pizzaBuilder.append(wrapper.getJSONObject("pizza-prices"));

				JSONArray toppingList = pizzaInfo.getJSONArray("toppings");

				Topping[] toppings = new Topping[toppingList.length()];
				
				//Go through the JSON file and get each topping, make a topping object.
				for (int i = 0; i < toppingList.length(); i++) {
					JSONObject aTopping = toppingList.getJSONObject(i);
					String name = aTopping.getString("topping");
					double price = aTopping.getDouble("price");
					Topping t = new Topping(name, price);
					toppings[i] = t;
				}
				
				//Create a pizza object with all the prices and factors then add
				//the topping array to the pizza object.
				p = new Pizza(pizzaInfo.getInt("small"),
						pizzaInfo.getInt("medium"), pizzaInfo.getInt("large"),
						pizzaInfo.getDouble("small-factor"),
						pizzaInfo.getDouble("medium-factor"),
						pizzaInfo.getDouble("large-factor"), 
						toppings);
				//Set the topping labels and prices
				setPrices(0);
				//Set the pizza size prices.
				rdbSmall.setText("Small\n$" + p.getSmallPrice());
				rdbMedium.setText("Medium\n$" + p.getMediumPrice());
				rdbLarge.setText("Large\n$" + p.getLargePrice());

			} catch (JSONException e) {

				e.printStackTrace();
			}
			return p;
		}

	}

}
