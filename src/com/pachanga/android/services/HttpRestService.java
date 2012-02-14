package com.pachanga.android.services;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.pachanga.android.R;
import com.pachanga.android.processor.Processor;
import com.pachanga.android.processor.ProcessorFactory;

public class HttpRestService extends IntentService {

	private static final String LOG = HttpRestService.class.getName();

	public HttpRestService() {
		super("Pachanga Service");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String url = intent.getStringExtra("server_url");

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("server_url", url);

		/*
		 * for (String key : intent.getExtras().keySet()) if
		 * (!key.equals("server_url")) parameters.put(key, (String)
		 * intent.getExtras().get(key));
		 */

		JSONObject jsonObject = HttpCaller.sharedInstance().doCall(parameters);
		Processor processor = ProcessorFactory.buildProcessor(this, intent.getAction(), url.replace(getString(R.string.server_url), ""));
		if (processor != null) {
			processor.process(jsonObject);
			
			Intent broadcastIntent=new Intent();
			broadcastIntent.setAction(intent.getAction());
			broadcastIntent.putExtra("success", true);
			
			sendBroadcast(broadcastIntent);
			Log.d(LOG,broadcastIntent.toString());
			
		}
	}

}
