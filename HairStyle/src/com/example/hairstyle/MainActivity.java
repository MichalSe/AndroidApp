package com.example.hairstyle;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.parse.*;

import android.widget.*;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this, "3lThhxtk9VqqxSBeRJaZBN1AxdO2bA7u5b8lCBlQ", "VbJDWibi5M3zEKKlP7OxHhJYjU9uhPKeMwP4k8on");
        
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
       
        //change location function - user send to hairdresser:
        createNotification("Eden");
    }
    private void createInstallationPush() {
    	ParseInstallation installation = ParseInstallation.getCurrentInstallation();
    	installation.put("userRequest",true);
    	installation.saveInBackground();  			
	}
	public void createNotification(String userName) {
    	
    
    	System.out.println("helllooooooo");
    	ParsePush push = new ParsePush();
    	push.setChannel("Giants");
    	push.setMessage(userName +" wants to get a haircut");
    	push.sendInBackground();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar1);
		pb.setVisibility(-1);
		Parse.initialize(this, "3lThhxtk9VqqxSBeRJaZBN1AxdO2bA7u5b8lCBlQ",
				"VbJDWibi5M3zEKKlP7OxHhJYjU9uhPKeMwP4k8on");
		loadTicketCard(getCurrentFocus());
	}

	public void onCheckBoxClicked(View view) {
		boolean checked = ((CheckBox) view).isChecked();
		if (checked) {
			GridLayout grid = (GridLayout) findViewById(R.id.GridLayout1);
			int childcount = grid.getChildCount();
			for (int i = 0; i < childcount; i++) {
				View view2 = grid.getChildAt(i);
				if (view2 instanceof CheckBox) {

					view2.setEnabled(false); // disable checkbox

				}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void loadTicketCard(View v){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("TicketObject");
		query.getInBackground("gIPq7MH7rK", new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (e == null) {
		    	int numOfCurrentPirces=object.getInt("numOfPierces");
		    	GridLayout grid = (GridLayout) findViewById(R.id.GridLayout1);
				for (int i = 0; i < numOfCurrentPirces; i++) {
					View view2 = grid.getChildAt(i);
					if (view2 instanceof CheckBox) {
						CheckBox marked = (CheckBox) view2;
						marked.setChecked(true);
						marked.setEnabled(false);
						}
				}
		    } else {
		      // something went wrong
		    }
		  }
		});
	}

	
	public void submitTicketCard(View v) {

		int count = 0;

		GridLayout grid = (GridLayout) findViewById(R.id.GridLayout1);
		int childcount = grid.getChildCount();
		for (int i = 0; i < childcount; i++) {
			View view2 = grid.getChildAt(i);
			if (view2 instanceof CheckBox) {
				CheckBox marked = (CheckBox) view2;
				if (marked.isChecked()) {
					count++;
				}
			}
		}
		Log.d(TAG, "Count = " + count);
		Button submitBut = (Button)findViewById(R.id.button1);
		submitBut.setEnabled(false);
		ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar1);
		pb.setVisibility(1);
		ParseObject ticketObject = new ParseObject("TicketObject");
		ticketObject.put("numOfPierces", count);
		ticketObject.saveInBackground();
	}
	
	
}
