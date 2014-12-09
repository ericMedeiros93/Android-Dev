package com.example.week11_listview_adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MyArmsArrayAdapter extends ArrayAdapter<ProvincialArms> {

	private Context context;
	private int resourceId;
	private ProvincialArms[] provincialArms;
	
	public MyArmsArrayAdapter(Context context, int resourceId,
			ProvincialArms[] provincialArms) {
		super(context, resourceId, provincialArms);
		
		this.context = context;
		this.resourceId = resourceId;
		this.provincialArms = provincialArms;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// here is where we build the view to display in the ListView as a row
		if(convertView == null){
			LayoutInflater inflater  = ((Activity)context).getLayoutInflater();
			convertView = inflater.inflate(resourceId, parent , false);
		}
		
		ImageView imgArms = (ImageView) convertView.findViewById(R.id.imgArm);
		
		imgArms.setImageResource(provincialArms[position].getResourceId());
		return convertView;
	}

}
