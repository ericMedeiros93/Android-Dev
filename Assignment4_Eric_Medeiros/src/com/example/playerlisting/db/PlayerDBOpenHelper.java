package com.example.playerlisting.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class PlayerDBOpenHelper extends SQLiteOpenHelper {

	// create a constant for db name
	private static final String DATABASE_NAME = "Players.db";
	// create a constant for db version
	private static final int DATABASE_VERSION = 4;	
	
	public PlayerDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// must call super class constructor...
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// called by runtime if db doesn't exist yet and in onUpgrade below
		// here is where we create our table(s)
		db.execSQL(PlayerDataSource.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// for now just drop table and call onCreate not checking for version info
		// in real app, probably migrate data from old structure to new one
		db.execSQL("DROP TABLE IF EXISTS " + PlayerDataSource.TABLE_NAME);
		onCreate(db);
	}
}
