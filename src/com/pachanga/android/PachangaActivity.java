package com.pachanga.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 
 * @author CBOLANOS
 *
 */
public class PachangaActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        startActivity(new Intent(ActionsConstant.PLACES_VIEW_ACTION));
        
    }
}