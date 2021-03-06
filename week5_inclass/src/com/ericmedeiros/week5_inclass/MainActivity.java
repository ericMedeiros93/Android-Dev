package com.ericmedeiros.week5_inclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private static final int LAST_NAME = 1;
	private String firstName = "";
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
    
    public void sendFirstName(View view){
    	EditText edtFirstName = (EditText) findViewById(R.id.edtFirstName);
    	firstName = edtFirstName.getText().toString();
    	if(firstName.length() > 0){
    		Intent intent = new Intent(this, GetLastName.class);
        	intent.putExtra("firstName", firstName);
        	startActivityForResult(intent, LAST_NAME);
    	}else{
    		Toast.makeText(this, "You must enter a first name!", Toast.LENGTH_SHORT).show();
    	}
    	
    }


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == LAST_NAME) {
			if (resultCode == RESULT_OK) {
				String lastName = data.getStringExtra("lastName");

					TextView txtName = (TextView) findViewById(R.id.txtFullName);
					txtName.setText("Hello, "+firstName+" "+lastName );
				
			}
		}
	}
}
