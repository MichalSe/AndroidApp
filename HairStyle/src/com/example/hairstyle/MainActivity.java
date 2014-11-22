package com.example.hairstyle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
		Parse.initialize(this, "3lThhxtk9VqqxSBeRJaZBN1AxdO2bA7u5b8lCBlQ",
				"VbJDWibi5M3zEKKlP7OxHhJYjU9uhPKeMwP4k8on");		    
        
        // hairdresser - create installation for push notification:
        createInstallationPush();
        
        ParsePush.subscribeInBackground("Giants", new SaveCallback() {
        	  @Override
        	  public void done(ParseException e) {
        	    if (e == null) {
        	    	System.out.println("######success");
        	    	
        	    	Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
        	    } else {
        	    	System.out.println("#######failed");
        	      Log.e("com.parse.push", "failed to subscribe for push", e);
        	    }
        	  }
        	});               
    }
    public void costumerAction(View v) {
    	Intent intent = new Intent(getApplicationContext(), CheckBoxesActivity.class);
    	startActivity(intent);
    }
    private void createInstallationPush() {
    	ParseInstallation installation = ParseInstallation.getCurrentInstallation();
    	installation.put("userRequest",true);
    	installation.saveInBackground();  			
	}
	

	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	

	
}
