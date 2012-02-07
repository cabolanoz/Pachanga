package com.pachanga.android.processor;

import org.json.JSONObject;

import android.content.Context;

public abstract class Processor {

	protected Context context;
	
	public Processor(Context _context) {
		this.context = _context;
	}
	
	public abstract void process(JSONObject jsonObject);
	
}
