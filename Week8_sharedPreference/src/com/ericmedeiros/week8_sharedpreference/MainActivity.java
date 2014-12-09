package com.ericmedeiros.week8_sharedpreference;

import com.example.week8_sharedpreference.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = (EditText) findViewById(R.id.edtName);
        
        SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        
        String name = myPrefs.getString("name", null);
        
        if(name != null){
        	edtName.setText(name);
        }
        
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
    
    public void saveName(View view){
    	
    	SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
    	
    	Editor editor = myPrefs.edit();
    	
    	if(edtName.getText().toString().length() > 0){
    		editor.putString("name", edtName.getText().toString());
    		editor.apply();
    	}
    	
    }
}
