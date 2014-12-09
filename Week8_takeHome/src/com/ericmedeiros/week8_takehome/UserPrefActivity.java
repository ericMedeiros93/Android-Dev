package com.ericmedeiros.week8_takehome;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class UserPrefActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.user_pref);
	}

}
