package com.example.week11_listview_adapters;

import android.content.Context;
import android.content.res.TypedArray;

public class MergedProvince {
	private String name, captial;
	private int resourceId;

	public MergedProvince(String name, String captial, int resourceId) {
		super();
		this.name = name;
		this.captial = captial;
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public String getCaptial() {
		return captial;
	}
	
	public int getResourceId() {
		return resourceId;
	}

	public static MergedProvince[] getMergedProvinces(Context context) {
		// get the array of provinces from the string-array
		String[] provinces = context.getResources().getStringArray(
				R.array.provinces);

		// get the array of capitals (parallel array)
		String[] capitals = context.getResources().getStringArray(
				R.array.capitals);
		
		TypedArray arms = context.getResources().obtainTypedArray(R.array.arms);

		MergedProvince[] provinceArray = new MergedProvince[arms.length()];

		// loop through and fill up the provinceArray
		for (int i = 0; i < provinces.length; i++) {
			provinceArray[i] = new MergedProvince(provinces[i], capitals[i], arms.getResourceId(i,-1));
		}

		// return the array of provinces we just created
		return provinceArray;
	}
}
