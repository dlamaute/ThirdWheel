package com.appideaexperts.thirdwheel;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Registration2Screen extends Activity {

	//Location currLoc;
	protected static final long FAST_INTERVAL_CEILING_IN_MILLISECONDS = 10000;
	protected static final long UPDATE_INTERVAL_IN_MILLISECONDS = 150000;
	private EditText username1;
	private EditText username2;
	private EditText contact1;
	private EditText contact2;
	private String sit;
	//private LocationRequest locationRequest;
	//private LocationClient locationClient;
	private ParseUser user;
	private String contact;
	private String email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.registration_2a_screen);
		sit = getIntent().getStringExtra("userSituation");

		username1 = (EditText) findViewById(R.id.username1);
		username2 = (EditText) findViewById(R.id.username2);
		contact1 = (EditText) findViewById(R.id.contact1);
		contact2 = (EditText) findViewById(R.id.contact2);
		ImageButton loc = (ImageButton) findViewById(R.id.addLoc);

		username1.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (username1.getText().toString().equals("")) {
					username1.setBackgroundResource(R.drawable.username_1);
				} else {
					username1.setBackgroundResource(R.drawable.blank_2);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});

		contact1.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (contact1.getText().toString().equals("")) {
					contact1.setBackgroundResource(R.drawable.email_phone);
				} else {
					contact1.setBackgroundResource(R.drawable.blank_2);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});

		username2.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (username2.getText().toString().equals("")) {
					username2.setBackgroundResource(R.drawable.username_2);
				} else {
					username2.setBackgroundResource(R.drawable.blank_2);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});

		contact2.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (contact2.getText().toString().equals("")) {
					contact2.setBackgroundResource(R.drawable.email_phone);
				} else {
					contact2.setBackgroundResource(R.drawable.blank_2);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});

		loc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				/*
				 * add location
				 */

				// currLoc = locationClient.getLastLocation();
				String usr1 = username1.getText().toString();
				String usr2 = username2.getText().toString();
				
				email = contact1.getText().toString();

				String username = usr1 + " & " + usr2;
				contact = contact1.getText().toString() + " & " + contact2.getText().toString();

				if (username.contains("\"") || username.contains("<")
						|| usr1.contains("&")
						|| usr2.contains("&")
						|| username.contains("\'")
						|| username.contains("\\")
						|| usr1.contains(" ")
						|| usr2.contains(" ")) {
					Toast.makeText(Registration2Screen.this,
							"Invalid character(s) in username",
							Toast.LENGTH_LONG).show();
					Intent retry = new Intent(Registration2Screen.this,
							Registration2bScreen.class);
					startActivity(retry);
					finish();
				} else if (usr1.equals("") || usr2.equals("")) {
					Toast.makeText(Registration2Screen.this,
							"Username cannot be left blank", Toast.LENGTH_LONG)
							.show();
					Intent retry = new Intent(Registration2Screen.this,
							Registration2bScreen.class);
					startActivity(retry);
					finish();
				} else {
					user = new ParseUser();
					user.setUsername(username);
					user.setPassword("x7Pyz!4");
					user.put("eMailOrPhone", contact); //need to modify later
					user.put("userSituation", sit);
					//user.put("location", getLocation());
					user.signUpInBackground(new SignUpCallback() {
						public void done(ParseException e) {
							if (e == null) {
								Intent intent = new Intent(
										Registration2Screen.this,
										Registration3Screen.class);
								startActivity(intent);
								finish();
							} else {
								Toast.makeText(getApplicationContext(),
										e.getMessage(),
										Toast.LENGTH_LONG).show();
							}
						}
					});
					

				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent upIntent = new Intent(this, Registration1Screen.class);
			this.startActivity(upIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
