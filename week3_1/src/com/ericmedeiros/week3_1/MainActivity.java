package com.ericmedeiros.week3_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	EditText edtAmount;
	//CheckBox chkAddHst;
	TextView txtTotal;
	RadioGroup rgpTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtAmount = (EditText) findViewById(R.id.edtAmount);
        //chkAddHst = (CheckBox) findViewById(R.id.chkAddHst);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        rgpTax = (RadioGroup) findViewById(R.id.rgpTax);
        
        Button btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				calcTotal();
				
			}
		});
    }
    
    protected void calcTotal(){
    	double amount = Double.parseDouble(edtAmount.getText().toString());
    	
    	/*
    	if(chkAddHst.isChecked() == true){
    		amount *= 1.13;
    	}*/
    	
    	int selected = rgpTax.getCheckedRadioButtonId();
    	
    	switch (selected){
    	
    	case R.id.radGst:
    		amount *=1.05;
    		break;
    	
    	case R.id.radHst:
    		amount *=1.13;
    		break;
    		
    	default:
    		break;
    	}
    	
    	String format = "Total: $%.2f";
    	txtTotal.setText(String.format(format, amount));
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
}
