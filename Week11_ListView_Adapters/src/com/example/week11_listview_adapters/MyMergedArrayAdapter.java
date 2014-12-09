package com.example.week11_listview_adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyMergedArrayAdapter extends ArrayAdapter<MergedProvince> {

	private Context context;
	private int resourceId;
	private MergedProvince[] mergedProvinces;

	public MyMergedArrayAdapter(Context context, int resourceId,
			MergedProvince[] mergedProvinces) {
		super(context, resourceId, mergedProvinces);

		this.context = context;
		this.resourceId = resourceId;
		this.mergedProvinces = mergedProvinces;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// here is where we build the view to display in the ListView as a row
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(resourceId, parent, false);
		}

		// get txtProvince to update with the province
		TextView txtProvince = (TextView) convertView
				.findViewById(R.id.txtProvince);
		// call the getter on Province at position position
		txtProvince.setText(mergedProvinces[position].getName());

		// get txtCaptial to update with the corresponding captial
		TextView txtCapital = (TextView) convertView
				.findViewById(R.id.txtCapital);
		// now get the provinces capital
		txtCapital.setText(mergedProvinces[position].getCaptial());

		ImageView imgArms = (ImageView) convertView.findViewById(R.id.imgArm);

		imgArms.setImageResource(mergedProvinces[position].getResourceId());
		return convertView;
	}

}
