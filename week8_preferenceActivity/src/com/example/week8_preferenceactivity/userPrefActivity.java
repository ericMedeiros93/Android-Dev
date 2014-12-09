package com.example.week8_preferenceactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class userPrefActivity extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.user_pref);
	}

}
