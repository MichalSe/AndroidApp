package com.example.hairstyle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ProgressBar;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

public class CheckBoxesActivity extends Activity {
	private static final String TAG = CheckBoxesActivity.class.getSimpleName();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkboxes_activity);
		loadTicketCard(getCurrentFocus());
		ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(-1);
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
	public void loadTicketCard(View v) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("TicketObject");
		query.getInBackground("gIPq7MH7rK", new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (e == null) {
					int numOfCurrentPirces = object.getInt("numOfPierces");
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

	public void createNotification(String userName) {
		System.out.println("helllooooooo");
		ParsePush push = new ParsePush();
		push.setChannel("Giants");
		push.setMessage(userName + " wants to get a haircut");
		push.sendInBackground();
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
		Button submitBut = (Button) findViewById(R.id.button1);
		submitBut.setEnabled(false);
		ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(1);

		createNotification("Eden");
	}

}
