package com.appideaexperts.thirdwheel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import android.widget.Toast;
import com.parse.ParseException;

public class SignInScreen extends Activity{
	
	private EditText username;
	private EditText password;
	private ParseUser user;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.sign_in_screen);
	    
	    username = (EditText)findViewById(R.id.username);
	    password = (EditText)findViewById(R.id.password);
	    ImageButton sendSign = (ImageButton)findViewById(R.id.sendSign);
	    
	    if (ParseUser.getCurrentUser() != null){
	    	user = ParseUser.getCurrentUser();
	    	username.setText(user.getUsername());
	    	username.setBackgroundResource(R.drawable.blank_text_tw);
	    }
	    
	    username.addTextChangedListener(new TextWatcher() {
	    	public void afterTextChanged(Editable s){}
	    	public void onTextChanged(CharSequence s, int start, int before, int count){
	    		if (username.getText().toString().equals("")){
	    			username.setBackgroundResource(R.drawable.username_tw);
	    		}
	    		else{
	    			username.setBackgroundResource(R.drawable.blank_text_tw);
	    		}
	    	}
	    	public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	    });
	    
	    password.addTextChangedListener(new TextWatcher() {
	    	public void afterTextChanged(Editable s){}
	    	public void onTextChanged(CharSequence s, int start, int before, int count){
	    		if (password.getText().toString().equals("")){
	    			password.setBackgroundResource(R.drawable.password_tw);
	    		}
	    		else{
	    			password.setBackgroundResource(R.drawable.blank_text_tw);
	    		}
	    	}
	    	public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	    });
	    
	    sendSign.setOnClickListener(
	    		new View.OnClickListener() {
					public void onClick(View view) {
						
						String usernameStr = username.getText().toString();
						String pass = password.getText().toString();
						
						String usr = usernameStr.replaceAll("<>&\'\"","");
						
						ParseUser.logInInBackground(usr, pass, new LogInCallback() {
							public void done(ParseUser user, ParseException e) {
								if(e == null && user != null){
									Intent intent = new Intent(SignInScreen.this, LoadingScreen.class);
									startActivity(intent);
									finish();
								}
								else if (user == null){
									Toast toast = Toast.makeText(getApplicationContext(), "Username or password is invalid", Toast.LENGTH_LONG);
									toast.setGravity(Gravity.TOP, 0, 0);
									toast.show();
								}
								else{
									Toast toast = Toast.makeText(getApplicationContext(), "There was an error.", Toast.LENGTH_LONG);
									toast.setGravity(Gravity.TOP, 0, 0);
									toast.show();
								}
							}
						});
					}
				});
   
	}
}
	