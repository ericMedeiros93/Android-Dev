package com.example.week9_reading_raw;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources;
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
		setContentView(com.example.week9_reading_raw.R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.example.week9_reading_raw.R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == com.example.week9_reading_raw.R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void loadGrades(View view) {
			RawFileHelper myHelper = new RawFileHelper();
			myHelper.execute();
	}
	
	private class RawFileHelper extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {
			
			//Get resources 
			
			Resources res = getResources();
			
			InputStream inputStream = res.openRawResource(com.example.week9_reading_raw.R.raw.student_grades);
			try{
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				return parseStudentGradesFile(buffer.toString());
				
			}catch(Exception ex){
				
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			TextView txtInfo = (TextView) findViewById(com.example.week9_reading_raw.R.id.txtInfo);
			txtInfo.setText(result);
		}
		
		private String parseStudentGradesFile(String jsonData){
			
			StringBuilder studentBuilder = new StringBuilder();
			try{
				JSONObject wrapper = new JSONObject(jsonData);
				
				JSONObject studentInfo = wrapper.getJSONObject("student-grades");
				
				studentBuilder.append("Name: "+studentInfo.getString("name") + "\n\n");
				
				JSONArray courseList = studentInfo.getJSONArray("courses");
				
				for(int i = 0; i < courseList.length(); i++){
					JSONObject course = courseList.getJSONObject(i);
					studentBuilder.append("Name: "+ course.getString("name") +"\n");
					studentBuilder.append("Grade: "+ course.getString("grade") +"\n\n");
					
						
				}
				
				
			}catch(JSONException e){
				Log.e("MainActivity: RawFileHelper: parseStudentInformation", e.toString());
			}
			return studentBuilder.toString();
		}
		
		
		
	}
}
