package com.example.puzzle8;

import android.os.Bundle;
import android.preference.PreferenceActivity;



public class Preference  extends PreferenceActivity {
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
	addPreferencesFromResource(R.xml.prefs);

}

}


