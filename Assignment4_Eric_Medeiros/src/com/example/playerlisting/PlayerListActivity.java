package com.example.playerlisting;

import com.example.playerlisting.db.PlayerDataSource;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class PlayerListActivity extends ListActivity {
	//Create a PlayerDataSource to be used throughout the activity. 
	private PlayerDataSource pds;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_list);
		
		//Start the database task.
		DatabaseTask task = new DatabaseTask();
		task.execute();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//Close the database. (Thanks Paul!)
		pds.closeDatabase();
	}


	public class DatabaseTask extends AsyncTask<Void, Void, Cursor>{

		@Override
		protected Cursor doInBackground(Void... params) {
			
			pds = new PlayerDataSource(getBaseContext());
			//Return the cursor of players.
			return pds.getAllPlayers();
		}

		@Override
		protected void onPostExecute(Cursor result) {
			super.onPostExecute(result);
			//Create an adapter....
			PlayerCursorAdapter adapter = new PlayerCursorAdapter(PlayerListActivity.this,
					R.layout.list_item_row, result);
			//...set the adapter
			setListAdapter(adapter);
			
			
		}
		
		
		
	}
}
