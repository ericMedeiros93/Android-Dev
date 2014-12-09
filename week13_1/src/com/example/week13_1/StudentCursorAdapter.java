package com.example.week13_1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.week13_1.db.StudentDataSource;

public class StudentCursorAdapter extends ResourceCursorAdapter {

	int layoutResourceId; // will hold resource id of list_item_row.xml
	Cursor students; // cursor returned from db query

	public StudentCursorAdapter(Context context, int layoutResourceId, Cursor students) {
		super(context, layoutResourceId, students, 0);
		this.layoutResourceId = layoutResourceId;
		this.students = students;
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
		// Inflate the row that will be used to display the info
		LayoutInflater inflator = ((Activity)context).getLayoutInflater();
		View row = inflator.inflate(layoutResourceId, parent, false);
		
		return row; // return the row
	}

	@Override
	public void bindView(View row, Context context, Cursor cursor) {
		
		// get the row widgets and bind the data to them; called when 
		// rows become visible
		TextView txtName = (TextView) row.findViewById(R.id.rowTxtName);
		TextView txtId = (TextView) row.findViewById(R.id.rowTxtId);
		TextView txtFullTime = (TextView) row.findViewById(R.id.rowTxtFullTime);
		
		// set values; cursor is already at proper position
		txtName.setText(cursor.getString(StudentDataSource.NAME_COLUMN_POSITION));
		txtId.setText(cursor.getString(StudentDataSource.NUMBER_COLUMN_POSITION));
		
		// now for the Full Time flag
		if (cursor.getInt(StudentDataSource.FULL_TIME_COLUMN_POSITION) == 1) {
			txtFullTime.setText("Full Time");
		} else {
			txtFullTime.setText("Part Time");
		}
	}

	@Override
	public Object getItem(int position) {
		
		// get the Student that corresponds to the position in cursor
		// first move the cursor to the correct position
		students.moveToPosition(position);
		
		// put cursor values in variables
		String name = students.getString(StudentDataSource.NAME_COLUMN_POSITION);
		String number = students.getString(StudentDataSource.NUMBER_COLUMN_POSITION);
		boolean fullTime = 
			(students.getInt(StudentDataSource.FULL_TIME_COLUMN_POSITION) == 1) ? true: false;
		long dbId = students.getLong(StudentDataSource.ID_COLUMN_POSITION);	
		
		// return new instance 
		return new Student(name, number, fullTime, dbId);
	}	
}
