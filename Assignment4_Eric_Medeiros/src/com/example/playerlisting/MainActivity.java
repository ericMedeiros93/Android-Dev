package com.example.playerlisting;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.playerlisting.db.PlayerDataSource;

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

	public void savePlayer(View view) {
		
		//Initialize controls.
		EditText edtPlayerName = (EditText) findViewById(R.id.edtPlayerName);
		RadioGroup grpPosition = (RadioGroup) findViewById(R.id.grpPosition);
		EditText edtGoals = (EditText) findViewById(R.id.edtGoals);
		
		//Get data from controls.
		String playerName = edtPlayerName.getText().toString();
		int goals = Integer.parseInt(edtGoals.getText().toString());
		
		//Find which position the player plays. 
		int positionNum = grpPosition.getCheckedRadioButtonId();
		String playerPosition = "";
		if (positionNum == 0) {
			playerPosition = "Goalie";
		} else if (positionNum == 1) {
			playerPosition = "Defence";
		} else {
			playerPosition = "Forward";
		}

		

		//Create a player object to be passed to the database task.
		Player p = new Player(playerName, playerPosition, goals);

		//Start the database task
		DatabaseTask dbTask = new DatabaseTask();
		dbTask.execute(p);
		
		//Reset controls once the parameters have been sent to the database task.
		edtPlayerName.setText("");
		edtGoals.setText("");
		RadioButton radGoalie = (RadioButton) findViewById(R.id.radGoalie);
		radGoalie.setChecked(true);
	}

	//Start the intent to show the players in the database.
	public void viewPlayers(View view) {
		Intent intent = new Intent(this, PlayerListActivity.class);
		startActivity(intent);
	}
	
	
	/**
	 * 
	 * @author Eric Medeiros
	 * An async task to save the player database.
	 *
	 */
	private class DatabaseTask extends AsyncTask<Player, Void, Void> {

		Player player;

		@Override
		protected Void doInBackground(Player... params) {

			// create PlayerDataSource and call savePlayer method
			PlayerDataSource playerData = new PlayerDataSource(getBaseContext());

			//Save the player to the database and get the ID of the saved player
			player = playerData.savePlayer(params[0]);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			//Create a toast to show that the player was saved. 
			String message = String.format("Player %s created with id: %d",
					player.getName(), player.getdbId());
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG)
					.show();
		}
	}

}
