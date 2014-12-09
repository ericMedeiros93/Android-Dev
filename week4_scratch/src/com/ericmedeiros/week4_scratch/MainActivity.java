package com.ericmedeiros.week4_scratch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        }else if(id == R.id.derp){
        	Toast.makeText(this, "DERP", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.herp){
        	Toast.makeText(this, "HERP", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void showDialog(View view){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
    	builder.setTitle("THIS IS A DIALOG");
    	builder.setMessage("FEAR ME");
    	builder.setPositiveButton("I DO", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "GOOD", Toast.LENGTH_SHORT).show();
				
			}
    	});
    	builder.setNegativeButton("I DON\'T", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "NOOOOOOOO!", Toast.LENGTH_SHORT).show();
				
			}
    	});
    	
    	Dialog dialog = builder.create();
    	dialog.show();
    }
}
