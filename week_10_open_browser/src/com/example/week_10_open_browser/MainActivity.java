package com.example.week_10_open_browser;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


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
    
    public void openUrl(View view){
    	EditText edtUrl = (EditText) findViewById(R.id.edtUrl);
    	
    	String url = edtUrl.getText().toString();
    	
    	if(url.length() > 0 && isConnected()){
    		// create a new Intent
    		Intent intent = new Intent();
    		
    		intent.setAction(Intent.ACTION_VIEW);
    		
    		intent.setData(Uri.parse(url));
    		
    		//Or in one step
    		//Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    		
    		startActivity(intent);
    	}
    	
    }
    
    private boolean isConnected(){
    	ConnectivityManager mgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    	NetworkInfo info = mgr.getActiveNetworkInfo();
    	if(info != null && info.isConnected()){
    		return true;
    	}else{
    		return false;
    	}
    }
}
