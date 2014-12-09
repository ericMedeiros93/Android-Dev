package com.example.week_10_reading_from_network;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

	public void loadGrades(View view) {
		LoadGradesTask myTask = new LoadGradesTask();
		myTask.execute();
	}
	
	public void loadInfo(View view) {
		LoadInfoTask myTask = new LoadInfoTask();
		myTask.execute();
	}

	public class LoadGradesTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			URL url;
			InputStream inputStream = null;

			try {
				url = new URL(
						"http://mobile.sheridanc.on.ca/~bonenfan/PROG38448/student_grades.json");
				URLConnection connection = url.openConnection();
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					inputStream = httpConnection.getInputStream();

					// create a byte array buffer that will hold the contents
					byte[] buffer = new byte[inputStream.available()];

					// read in the contents of the input stream into the buffer
					inputStream.read(buffer);

					// return the buffer
					return new String(buffer);
				}

			} catch (Exception e) {
				Log.e("NetworkTask:doInBackground", e.toString());
			} finally {
				try {
					inputStream.close();
				} catch (Exception ignoreMe) {

				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// get the TextView to display the results coming in
			TextView txtInfo = (TextView) findViewById(R.id.txtInfo);

			// set the text to the result sent in
			txtInfo.setText(result);
		}

	}

	public class LoadInfoTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			URL url;
			InputStream inputStream = null;

			try {
				url = new URL(
						"http://mobile.sheridanc.on.ca/~bonenfan/PROG38448/student_info.json");
				URLConnection connection = url.openConnection();
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					inputStream = httpConnection.getInputStream();

					// create a byte array buffer that will hold the contents
					byte[] buffer = new byte[inputStream.available()];

					// read in the contents of the input stream into the buffer
					inputStream.read(buffer);

					// return the buffer
					return parseInfo(new String(buffer));
				}

			} catch (Exception e) {
				Log.e("NetworkTask:doInBackground", e.toString());
			} finally {
				try {
					inputStream.close();
				} catch (Exception ignoreMe) {

				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// get the TextView to display the results coming in
			TextView txtInfo = (TextView) findViewById(R.id.txtInfo);

			// set the text to the result sent in
			txtInfo.setText(result);
		}

		private String parseInfo(String jsonData) {

			StringBuilder infoBuilder = new StringBuilder();
			try {
				JSONObject wrapper = new JSONObject(jsonData);

				JSONObject studentInfo = wrapper
						.getJSONObject("student-info");

				infoBuilder.append("Name: " + studentInfo.getString("name")
						+ "\n");
				
				infoBuilder.append("Age: " + studentInfo.getString("age")
						+ "\n");
				infoBuilder.append("Address: " + studentInfo.getString("address")
						+ "\n");
				boolean married = Boolean.parseBoolean(studentInfo.getString("married"));
				if(married){
					infoBuilder.append("Status: Married\n\n");
				}else{
					infoBuilder.append("Status: Single\n\n");
				}
				JSONArray phoneList = studentInfo.getJSONArray("phone-numbers");

				for (int i = 0; i < phoneList.length(); i++) {
					JSONObject phone = phoneList.getJSONObject(i);
					infoBuilder.append(phone.getString("name")
							+": "+ phone.getString("number")+"\n");

				}

			} catch (JSONException e) {
				Log.e("MainActivity: parseInfo", e.toString());
			}
			return infoBuilder.toString();
		}

	}

}
