package com.example.week11_listview_adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Province> {

	private Context context;
	private int resourceId;
	private Province[] provinces;
	
	public MyArrayAdapter(Context context, int resourceId,
			Province[] provinces) {
		super(context, resourceId, provinces);
		
		this.context = context;
		this.resourceId = resourceId;
		this.provinces = provinces;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// here is where we build the view to display in the ListView as a row
		if(convertView == null){
			LayoutInflater inflater  = ((Activity)context).getLayoutInflater();
			convertView = inflater.inflate(resourceId, parent , false);
		}
		
		// get txtProvince to update with the province
		TextView txtProvince = (TextView) convertView.findViewById(R.id.txtProvince);
		// call the getter on Province at position position
		txtProvince.setText(provinces[position].getName());
		
		// get txtCaptial to update with the corresponding captial
		TextView txtCapital = (TextView) convertView.findViewById(R.id.txtCapital);
		//now get the provinces capital
		txtCapital.setText(provinces[position].getCaptial());
		
		return convertView;
	}

}
