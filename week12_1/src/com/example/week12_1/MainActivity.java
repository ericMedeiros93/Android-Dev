package com.example.week12_1;

import com.example.week12_1.db.StudentDataSource;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main) ;
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

	public void save(View view) {
		
		// load student info into instance
		Student student = loadStudent();
		
		// do db interactions off of main thread
		DatabaseTask dbTask = new DatabaseTask();
		dbTask.execute(student);
	}

	private Student loadStudent() {
		
		EditText edtName = (EditText) findViewById(R.id.edtName);
		String name = edtName.getText().toString();
		
		EditText edtNumber = (EditText) findViewById(R.id.edtNumber);
		String number = edtNumber.getText().toString();
		
		CheckBox chkFullTime = (CheckBox) findViewById(R.id.chkFullTime);
		boolean fullTime = chkFullTime.isChecked();
		
		return new Student(name, number, fullTime);
	}
	
	private class DatabaseTask extends AsyncTask<Student, Void, Void> {

		Student student;
		@Override
		protected Void doInBackground(Student... params) {

			// create StudentDataSource and call saveStudent method
			StudentDataSource studentData = new StudentDataSource(getBaseContext());
			
			// store returned Student; it now has the id
			student = studentData.saveStudent(params[0]);
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			String message = String.format("Student %s created with id: %d", 
					student.getName(), student.getDbId());
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
		}
	}
}
