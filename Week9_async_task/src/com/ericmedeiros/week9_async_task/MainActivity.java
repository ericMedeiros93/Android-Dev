package com.ericmedeiros.week9_async_task;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final static String TAG = "MainActivity";

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

	public void go(View view) {
		ProcessTask myTask = new ProcessTask();
		EditText edtFiles = (EditText) findViewById(R.id.edtFiles);
		myTask.execute(5000 , Integer.parseInt(edtFiles.getText().toString()));
	}

	private class ProcessTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			for (int i = 0; i < params[1]; i++) {
				try {
					Thread.sleep(params[0]);
					publishProgress(i+1);
				} catch (InterruptedException e) {
					Log.e(TAG, e.toString());
				}
			}
			return params[1];
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// Call the method that updates the UI. Will be called on
			//UI thread because this method call is on the UI thread. 
			updateUI("Done processing " + values[0]+" file(s)");
			
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			updateUI("Total Files processed: "+ result);
		}

		private void updateUI(String message){
			//This will run on the main UI thread
			TextView txtUpdate = (TextView) findViewById(R.id.txtUpdate);
			//update the text field with the progress
			txtUpdate.setText(message);
		}
		
		
		
		
	}
}
