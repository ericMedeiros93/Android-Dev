package com.example.week13_1;


import com.example.week13_1.db.StudentDataSource;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class StudentListActivity extends ListActivity {
	
	// private data field to hold the currently selected student
	// set in the onCreateContextMenu and used in the onContextItemSelected
	private Student student;
	
	// constants used to differentiate which task
	// to perform asynchronously
	private static final long LOAD_STUDENTS = 0;
	private static final long REMOVE_STUDENT = 1; 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        
        // do the db access using an AsyncTask
        DatabaseTask dbTask = new DatabaseTask();
        dbTask.execute(LOAD_STUDENTS);
        
        // register the ListView as a source for a context menu
        registerForContextMenu(getListView());
    }
    

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		// get the ListView
		ListView lv = (ListView) v;
		
		// Get the AdapterContextMenuInfo corresponding to the menuInfo passed in
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		
		// get the student at the position selected. Calls the getItem method of our 
		// StudentCursorAdapter. Have to cast from Object
		student = (Student) lv.getItemAtPosition(info.position);

		// Setting the title of the context menu to the String representation
		// of a Student instance
		menu.setHeaderTitle(student.toString());
		
		// add two menu items
		menu.add("Remove from History");		
		menu.add("Load to Order");
	}

    
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		
		if (item.getTitle().equals("Remove from History")) {

	        // do the db access using an AsyncTask
	        DatabaseTask dbTask = new DatabaseTask();
	        dbTask.execute(REMOVE_STUDENT, student.getDbId());
			return true;
		} else if (item.getTitle().equals("Load to Main Screen")) {
			// put code here that will return the student to the parent 
			// Activity

			return true;
		} 
		
		return super.onContextItemSelected(item);
	}
	
	private class DatabaseTask extends AsyncTask<Long, Void, Cursor> {
		@Override
		protected Cursor doInBackground(Long... params) {

			// create StudentDataSource and call saveStudent method
			StudentDataSource studentData = new StudentDataSource(getBaseContext());
			
			if (params[0] == REMOVE_STUDENT) {
				// remove a student from the db 
				// second parameter is the db id of the student
				studentData.removeStudent(params[1]);
			}
			// return Cursor of all students to the onPostExecute
			return studentData.getAllStudents();
		}
		
		@Override
		protected void onPostExecute(Cursor result) {
			super.onPostExecute(result);

	        // use our cursor adapter and pass in the resulting cursor
			StudentCursorAdapter adapter = new StudentCursorAdapter(StudentListActivity.this, 
					R.layout.list_item_row, result);
	        
			// set the adapter for the list
	        setListAdapter(adapter);
		}
	}
}

