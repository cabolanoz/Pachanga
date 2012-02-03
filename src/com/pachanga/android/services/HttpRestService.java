package com.pachanga.android.services;

import java.util.HashMap;
import java.util.Map;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class HttpRestService extends IntentService{


	private static final String LOG = HttpRestService.class.getName();

	public HttpRestService() {
		super("Pachanga Service");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		String url = intent.getStringExtra("server_url");
		Log.i(LOG, "Action is : "+url);

		Map<String, String> parameters = new HashMap<String, String>();
		
		/*for (String key : intent.getExtras().keySet())
			if (!key.equals("server_url"))
				parameters.put(key, (String) intent.getExtras().get(key));
		*/
	}
	
}
