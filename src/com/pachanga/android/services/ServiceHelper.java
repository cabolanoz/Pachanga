package com.pachanga.android.services;

import android.content.Context;
import android.content.Intent;

public class ServiceHelper {

	private Context context;
	private static ServiceHelper serviceHelper;
	
	private ServiceHelper(Context context){
		this.context=context;
	}
	
	public static ServiceHelper getInstance(Context context){
		if(serviceHelper==null){
			serviceHelper=new ServiceHelper(context);
		}
		serviceHelper.setContext(context);
		return serviceHelper;
	}
	
	private void setContext(Context context){
		this.context=context;
	}
	
	public void startService(Intent intent){
		context.startService(intent);
	}
	
}
