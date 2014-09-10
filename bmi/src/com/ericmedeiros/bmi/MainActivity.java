package com.ericmedeiros.bmi;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

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
    
    public void getBmi(View view){
    	EditText edtWeight = (EditText) findViewById(R.id.weight);
    	EditText edtHeight = (EditText) findViewById(R.id.height);
    	
    	//Make sure there's text in the text boxes. 
    	if(edtWeight.length() >0 && edtHeight.length() > 0){
    		double weightNum = Double.parseDouble(edtWeight.getText().toString());
    		double heightNum = Double.parseDouble(edtHeight.getText().toString());
    		double bmi = weightNum / (heightNum * heightNum);
    		
    		Toast toasty = Toast.makeText(this, String.format("BMI is %.2f", bmi) , Toast.LENGTH_SHORT);
    		toasty.show();
    	}
    }
}
