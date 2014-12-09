package com.example.week11_listview_adapters;

import android.content.Context;
import android.content.res.TypedArray;

public class ProvincialArms {
	// the resourceID corresponding to the drawable 
	private int resourceId;
	

	public ProvincialArms(int resourceId) {
		this.resourceId = resourceId;
	}


	public int getResourceId() {
		return resourceId;
	}
	
	public static ProvincialArms[] getProvincalArms(Context context){

		TypedArray arms = context.getResources().obtainTypedArray(R.array.arms);
		
		ProvincialArms[] provincialArms = new ProvincialArms[arms.length()];
		
		
		for(int i = 0; i < arms.length(); i ++){
			provincialArms[i] = new ProvincialArms(arms.getResourceId(i, -1));
		}
		
		return provincialArms;
	}
	
}
