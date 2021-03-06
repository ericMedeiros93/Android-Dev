package com.ericmedeiros.week2_spin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {
	Spinner spnProvinces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        spnProvinces = (Spinner) findViewById(R.id.provinces);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item);
        
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spnProvinces.setAdapter(adapter);
    }
    
    public void getSelected(View view){
    	
    	spnProvinces = (Spinner) findViewById(R.id.provinces);
    	Toast toasty = Toast.makeText(this , spnProvinces.getSelectedItem().toString() , Toast.LENGTH_SHORT);
    	toasty.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){ 
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
