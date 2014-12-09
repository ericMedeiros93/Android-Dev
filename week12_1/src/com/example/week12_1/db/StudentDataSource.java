package com.example.week12_1.db;

import com.example.week12_1.Student;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class StudentDataSource {
	
	// instance members for accessing the database
	private SQLiteDatabase database;
	private SQLiteOpenHelper dbOpenHelper;

	// static members for describing the Student table structure
	public static final String TABLE_NAME = "Students";

	public static final String ID_COLUMN = "_id";
	public static final int ID_COLUMN_POSITION = 0;
	public static final String NAME_COLUMN = "name";
	public static final int NAME_COLUMN_POSITION = 1;
	public static final String NUMBER_COLUMN = "number";
	public static final int NUMBER_COLUMN_POSITION = 2;	
	public static final String FULL_TIME_COLUMN = "fullTime";
	public static final int FULL_TIME_COLUMN_POSITION = 3;	
	
	public static final String CREATE_TABLE = 
			"CREATE TABLE " + TABLE_NAME + " (" +
			ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			NAME_COLUMN + " TEXT, " +
			NUMBER_COLUMN + " TEXT, " + // store number as text
			FULL_TIME_COLUMN + " INTEGER)"; // SQLite does not have boolean data type

	// constructor; must pass in Context
	public StudentDataSource(Context context) {
		// instantiate our helper class
		dbOpenHelper = new StudentDBOpenHelper(context);
	}

	// method for saving student info into database
	public Student saveStudent(Student student) {
		
		// get writable database since we want to insert a row
		if (database == null || !database.isOpen() || database.isReadOnly()) {
			database = dbOpenHelper.getWritableDatabase();
		}

		// use ContentValues to group field names and values
		ContentValues values = new ContentValues();
		values.put(NAME_COLUMN, student.getName());
		values.put(NUMBER_COLUMN, student.getNumber());
		values.put(FULL_TIME_COLUMN, (student.isFullTime() ? 1: 0));
		
		long dbId = database.insert(TABLE_NAME, null, values);
		student.setDbId(dbId);
		
		database.close(); // close the database
		
		return student; // return student with dbId assigned

	}
}
