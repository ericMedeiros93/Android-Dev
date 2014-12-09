package com.example.playerlisting;

import com.example.playerlisting.db.PlayerDataSource;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class PlayerCursorAdapter extends ResourceCursorAdapter{

	int layoutResourceId;
	Cursor players;
	
	public PlayerCursorAdapter(Context context, int layoutResourceId, Cursor players) {
		super(context, layoutResourceId, players, 0);
		this.layoutResourceId = layoutResourceId;
		this.players = players;
	}
	
	

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflator = ((Activity)context).getLayoutInflater();
		View row = inflator.inflate(layoutResourceId, parent, false);
		return row;
	}



	@Override
	public void bindView(View row, Context context, Cursor cursor) {
		TextView txtName = (TextView) row.findViewById(R.id.rowTxtName);
		TextView txtPosition = (TextView) row.findViewById(R.id.rowTxtPosition);
		TextView txtGoals = (TextView) row.findViewById(R.id.rowTxtGoals);
		
		txtName.setText(cursor.getString(PlayerDataSource.NAME_COLUMN_POSITION));
		txtPosition.setText(cursor.getString(PlayerDataSource.POSITION_COLUMN_POSITION));
		txtGoals.setText("Goals: "+cursor.getString(PlayerDataSource.GOALS_COLUMN_POSITION));
		
	}

}
