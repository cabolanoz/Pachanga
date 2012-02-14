package com.pachanga.android.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HttpCaller {

	private static final String LOG=HttpCaller.class.getName();
	private static HttpCaller instance;
	
	public static HttpCaller sharedInstance() {
		if (instance == null)
			instance = new HttpCaller();
		return instance;
	}
	
	public HttpCaller() { }
	
	public JSONObject doCall(Map<String, String> parameter) {
		HttpClient httpClient = new DefaultHttpClient();
		
		// Prepare a request object
		HttpGet httpGet = new HttpGet(parameter.get("server_url"));
		
		// Prepare a response object
		HttpResponse httpResponse;
		
		try {
			httpResponse = httpClient.execute(httpGet);
			
			// Get the response entity
			HttpEntity httpEntity = httpResponse.getEntity();
			
			// If the entity is null, we read nothing
			if (httpEntity != null) {
				
				// Read the JSON response
				InputStream inputStream = httpEntity.getContent();
				String result = convertStreamToString(inputStream);
				return new JSONObject(result);
			} else
				return null;
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (JSONException jsone) {
			jsone.printStackTrace();
		}
		
		return null;
	}
	
	private static String convertStreamToString(InputStream inputStream) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null)
				sb.append(line + "\n");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
}
