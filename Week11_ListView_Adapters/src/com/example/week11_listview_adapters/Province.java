package com.example.week11_listview_adapters;
import android.content.Context;

import com.example.week11_listview_adapters.R;

public class Province {
	String name, captial;

	public Province(String name, String captial) {
		super();
		this.name = name;
		this.captial = captial;
	}

	public String getName() {
		return name;
	}

	public String getCaptial() {
		return captial;
	}

	public static Province[] getProvinces(Context context) {
		// get the array of provinces from the string-array
		String[] provinces = context.getResources().getStringArray(R.array.provinces);
		
		// get the array of capitals (parallel array)
		String[] capitals = context.getResources().getStringArray(R.array.capitals);
		
		Province[] provinceArray = new Province[provinces.length];
		
		// loop through and fill up the provinceArray
		for(int i = 0; i < provinces.length; i++){
			provinceArray[i] = new Province(provinces[i], capitals[i]);
		}
		
		// return the array of provinces we just created
		return provinceArray;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
