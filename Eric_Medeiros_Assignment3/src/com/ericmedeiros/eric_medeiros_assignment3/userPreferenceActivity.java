package com.ericmedeiros.eric_medeiros_assignment3;
import android.os.Bundle;
import android.preference.PreferenceActivity;


public class userPreferenceActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.user_pref);
	}
}
