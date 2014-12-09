package com.example.playerlisting.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.playerlisting.Player;
public class PlayerDataSource {
	
	// instance members for accessing the database
	private SQLiteDatabase database;
	private SQLiteOpenHelper dbOpenHelper;

	// static members for describing the Players table structure
	public static final String TABLE_NAME = "Players";

	public static final String ID_COLUMN = "_id";
	public static final int ID_COLUMN_POSITION = 0;
	public static final String NAME_COLUMN = "name";
	public static final int NAME_COLUMN_POSITION = 1;
	public static final String POSITION_COLUMN = "position";
	public static final int POSITION_COLUMN_POSITION = 2;	
	public static final String GOALS_COLUMN = "goals";
	public static final int GOALS_COLUMN_POSITION = 3;	
	
	public static final String CREATE_TABLE = 
			"CREATE TABLE " + TABLE_NAME + " (" +
			ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			NAME_COLUMN + " TEXT, " +
			POSITION_COLUMN + " TEXT, " + // store number as text
			GOALS_COLUMN + " INTEGER)"; // SQLite does not have boolean data type

	// constructor; must pass in Context
	public PlayerDataSource(Context context) {
		// instantiate our helper class
		dbOpenHelper = new PlayerDBOpenHelper(context);
	}

	// method for saving player info into database
	public Player savePlayer(Player player) {
		
		// get writable database since we want to insert a row
		if (database == null || !database.isOpen() || database.isReadOnly()) {
			database = dbOpenHelper.getWritableDatabase();
		}

		// use ContentValues to group field names and values
		ContentValues values = new ContentValues();
		values.put(NAME_COLUMN, player.getName());
		values.put(POSITION_COLUMN, player.getPosition());
		values.put(GOALS_COLUMN, player.getGoals());
		
		long dbId = database.insert(TABLE_NAME, null, values);
		player.setdbId(dbId);
		
		database.close(); // close the database
		
		return player; // return player with dbId assigned

	}
	
	//Returns a cursor of the players from the database.
	public Cursor getAllPlayers(){
		if (database == null || !database.isOpen()) {
			database = dbOpenHelper.getReadableDatabase();
		}
		Cursor players = database.query(TABLE_NAME, null, null, null, null, null, null);
		
		return players;
	}
	
	//Closes the database connection if it's open.
	public void closeDatabase(){
		if (database != null && database.isOpen()) {
			database.close();
		}
	}
	

}
