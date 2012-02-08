package com.pachanga.android.processor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;

import com.pachanga.android.db.DatabaseContract.Places;
import com.pachanga.android.db.DatabaseContract.PlacesColumns;

public class PlacesProcessor extends Processor {

	private static final String LOG = PlacesProcessor.class.getName();
	
	public PlacesProcessor(Context _context) {
		super(_context);
	}

	@Override
	public void process(JSONObject jsonObject) {
		if (jsonObject != null) {
			try {
				JSONArray jsonArray = jsonObject.getJSONArray("places");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					
					ContentValues contentValues = new ContentValues();
					contentValues.put(PlacesColumns.PLACE_ID, obj.getString("Id"));
					contentValues.put(PlacesColumns.NAME, obj.getString("Name"));
					contentValues.put(PlacesColumns.DESCRIPTION, obj.getString("Description"));
					contentValues.put(PlacesColumns.ADDRESS, obj.getString("Address"));
					contentValues.put(PlacesColumns.PICTURE, obj.getString("picture"));
					contentValues.put(PlacesColumns.RATE, obj.getDouble("Rate"));
					
					context.getContentResolver().insert(Places.CONTENT_URI, contentValues);
				}
			} catch (JSONException jsone) {
				jsone.printStackTrace();
			}
		}
	}

}
