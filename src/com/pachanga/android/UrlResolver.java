package com.pachanga.android;

import android.content.Context;

import com.pachanga.android.db.PachangaMatchers;

public class UrlResolver implements PachangaMatchers{

	
	private Context context;
	public UrlResolver(Context context){
		this.context=context;
	}
	
	public String parseMatch(int match){
		if(match==PLACES || match == PLACES_ID){
			return context.getString(R.string.server_url) + "/places/";
		}
		
		return null;
	}
	
}
