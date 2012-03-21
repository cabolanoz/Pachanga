package com.pachanga.android;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class PlacesAdapter extends SimpleCursorAdapter {

	private static final String LOG=PlacesAdapter.class.getName();
	
	public PlacesAdapter(Context context, int layout, Cursor c, String[] from,
			int[] to, int flags) {
		super(context, layout, c, from, to, flags);
	}
	
	
	public View getView(int position,View convertView, ViewGroup parentView ){
		
		return super.getView(position, convertView, parentView);
		
	}
	
	public long getItemId(int position){
		long id=super.getItemId(position);
		
		Log.d(LOG,"ID: "+id);
		
		return id;
	}

}
