package com.appideaexperts.thirdwheel;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration3bScreen extends Activity{
	
	private EditText eyeColor;
	private EditText hairColor;
	private EditText age;
	private EditText height;
	private EditText nationality;
	private EditText bodyType;
	private Spinner desires;
	private ParseUser user;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.registration_3b_screen);
		
		eyeColor = (EditText)findViewById(R.id.eye_color);
		hairColor = (EditText)findViewById(R.id.hair_color);
		age = (EditText)findViewById(R.id.age);
		height = (EditText)findViewById(R.id.height);
		nationality = (EditText)findViewById(R.id.nationality);
		bodyType = (EditText)findViewById(R.id.body_type);
		
		desires = (Spinner)findViewById(R.id.desires);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.desires_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		desires.setAdapter(adapter);
		
		eyeColor.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (eyeColor.getText().toString().equals("")) {
					eyeColor.setBackgroundResource(R.drawable.eye_color);
				} else {
					eyeColor.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		hairColor.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (hairColor.getText().toString().equals("")) {
					hairColor.setBackgroundResource(R.drawable.hair_color);
				} else {
					hairColor.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		age.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (age.getText().toString().equals("")) {
					age.setBackgroundResource(R.drawable.age);
				} else {
					age.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		height.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (height.getText().toString().equals("")) {
					height.setBackgroundResource(R.drawable.height);
				} else {
					height.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		nationality.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (nationality.getText().toString().equals("")) {
					nationality.setBackgroundResource(R.drawable.nationality);
				} else {
					nationality.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		bodyType.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (bodyType.getText().toString().equals("")) {
					bodyType.setBackgroundResource(R.drawable.body_type);
				} else {
					bodyType.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		ImageButton submit = (ImageButton)findViewById(R.id.submit);
		submit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				
				String important = desires.getSelectedItem().toString();
				
				String eyeColorStr = eyeColor.getText().toString();
				String hairColorStr = hairColor.getText().toString();
				String ageStr = age.getText().toString();
				String heightStr = height.getText().toString();
				String nationalityStr = nationality.getText().toString();
				String bodyTypeStr = bodyType.getText().toString();
				
				user = ParseUser.getCurrentUser();
				user.put("EyeColor", eyeColorStr);
				user.put("HairColor", hairColorStr);
				user.put("Age", ageStr);
				user.put("Height", heightStr);
				user.put("Nationality", nationalityStr);
				user.put("BodyType", bodyTypeStr);
				user.put("Important", important);
				user.saveInBackground(new SaveCallback(){
					public void done(ParseException e){
						if (e == null){
							Intent intent = new Intent(Registration3bScreen.this, Registration4bScreen.class);
							startActivity(intent);
							finish();
						}
						else{
							Toast.makeText(getApplicationContext(), "Was not able to save information.", Toast.LENGTH_LONG).show();
						}
					}
				});
			}
		});
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	Intent upIntent = new Intent(this, Registration2bScreen.class);
				this.startActivity(upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


