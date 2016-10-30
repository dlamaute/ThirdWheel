package com.appideaexperts.thirdwheel;

import com.appideaexperts.thirdwheel.location.ErrorDialogFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.ParseException;
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

public class Registration2bScreen extends Activity{

	private EditText username;
	private EditText contact;
	private String sit;
	private String usernameStr;
	private String email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.registration_2b_screen);
		sit = getIntent().getStringExtra("userSituation");

		username = (EditText) findViewById(R.id.username);
		contact = (EditText) findViewById(R.id.contact);
		ImageButton loc = (ImageButton) findViewById(R.id.addLoc);

		username.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (username.getText().toString().equals("")) {
					username.setBackgroundResource(R.drawable.username_1);
				} else {
					username.setBackgroundResource(R.drawable.blank_2);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});

		contact.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (contact.getText().toString().equals("")) {
					contact.setBackgroundResource(R.drawable.email_phone);
				} else {
					contact.setBackgroundResource(R.drawable.blank_2);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});

		loc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				usernameStr = username.getText().toString();
				email = contact.getText().toString();

				if (usernameStr.contains("\"") || usernameStr.contains("<")
						|| usernameStr.contains("&")
						|| usernameStr.contains("\'")
						|| usernameStr.contains("\\")
						|| usernameStr.contains(" ")) {
					Toast.makeText(Registration2bScreen.this,
							"Invalid character(s) in username",
							Toast.LENGTH_LONG).show();
					Intent retry = new Intent(Registration2bScreen.this,
							Registration2bScreen.class);
					startActivity(retry);
					finish();
				} else if (usernameStr.equals("")) {
					Toast.makeText(Registration2bScreen.this,
							"Username cannot be left blank", Toast.LENGTH_LONG)
							.show();
					Intent retry = new Intent(Registration2bScreen.this,
							Registration2bScreen.class);
					startActivity(retry);
					finish();
				} else {
					ParseUser user = new ParseUser();
					user.setUsername(usernameStr);
					user.setPassword("x7Pyz!4");
					user.put("eMailOrPhone", email); //need to modify later
					user.put("userSituation", sit);
					//user.put("location", getLocation());
					user.signUpInBackground(new SignUpCallback() {
						public void done(com.parse.ParseException e) {
							if (e == null) {
								Intent intent = new Intent(
										Registration2bScreen.this,
										Registration3bScreen.class);
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
