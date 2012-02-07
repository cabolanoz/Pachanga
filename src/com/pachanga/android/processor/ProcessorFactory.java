package com.pachanga.android.processor;

import android.content.Context;

import com.pachanga.android.UrlResolver;

public class ProcessorFactory {

	public static Processor buildProcessor(Context context, String action, String suffix_url) {
		if (suffix_url.equals(UrlResolver.PLACES_SUFFIX))
			return new PlacesProcessor(context);
		
		return null;
	}
	
}
