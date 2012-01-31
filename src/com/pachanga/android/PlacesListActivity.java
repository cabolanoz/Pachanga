package com.pachanga.android;

import android.app.ListActivity;
import android.os.Bundle;

public class PlacesListActivity extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.places_list);
		
	}

}
