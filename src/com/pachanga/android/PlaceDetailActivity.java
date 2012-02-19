package com.pachanga.android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.pachanga.android.db.DatabaseContract.Places;

public class PlaceDetailActivity extends Activity {

	private final static String LOG = PlaceDetailActivity.class.getName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_item_detail);
		
		Cursor cursor = null;
		
		Intent intent = getIntent();
		long detailId = intent.getLongExtra("DetailId", -1);
		if (detailId != -1)
			cursor = managedQuery(Places.buildUri(String.valueOf(detailId)), null, null, null, null);
		
		if (cursor != null)
			if (cursor.moveToNext())
				Log.i(LOG, "Fotografia: " + cursor.getString(0));
	}
	
}
