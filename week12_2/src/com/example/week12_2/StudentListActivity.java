package com.example.week12_2;


import com.example.week12_2.db.StudentDataSource;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class StudentListActivity extends ListActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        
        // do the db access using an AsyncTask
        DatabaseTask dbTask = new DatabaseTask();
        dbTask.execute();
    }
    
	private class DatabaseTask extends AsyncTask<Void, Void, Cursor> {
		@Override
		protected Cursor doInBackground(Void... params) {

			// create StudentDataSource and call saveStudent method
			StudentDataSource studentData = new StudentDataSource(getBaseContext());
			
			// return Cursor of all students to the onPostExecute
			return studentData.getAllStudents();
		}
		
		@Override
		protected void onPostExecute(Cursor result) {
			super.onPostExecute(result);

	        // use our cursor adapter and pass in the resulting cursor
			StudentCursorAdapter adapter = new StudentCursorAdapter(StudentListActivity.this, R.layout.list_item_row, result);
	        
			// set the adapter for the list
	        setListAdapter(adapter);
		}
	}
	
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
				
		// get the adapter for the list
		StudentCursorAdapter sca = (StudentCursorAdapter) getListAdapter();
		
		// call the getItem method to retrieve a Student (have to cast)
		Student currentStudent = (Student) sca.getItem(position);
		
		Toast.makeText(this, "You selected: " + currentStudent.getName(), Toast.LENGTH_LONG).show();
	}
}
