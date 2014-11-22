package com.example.hairstyle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.TextView;

import com.parse.*;
public class AcceptResponseActivity extends Activity {
private static final String TAG = AcceptResponseActivity.class.getSimpleName();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	  setContentView(R.layout.accept_response);
    	  //TextView newtext = (TextView) findViewById(R.id.userField);
    	  //newtext.setText(R.string.user_request);
    }
    public void acceptHaircut(View v) {
    	//TODO send eden
    	System.out.println("send to eden");
    }
}

	
	