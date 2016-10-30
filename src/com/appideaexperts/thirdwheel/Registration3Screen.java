package com.appideaexperts.thirdwheel;

import com.parse.GetCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;
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
import android.widget.TextView;
import android.widget.Toast;

public class Registration3Screen extends Activity {
	
	private EditText eyeColor1;
	private EditText eyeColor2;
	private EditText hairColor1;
	private EditText hairColor2;
	private EditText age1;
	private EditText age2;
	private EditText height1;
	private EditText height2;
	private EditText nationality1;
	private EditText nationality2;
	private EditText bodyType1;
	private EditText bodyType2;
	private Spinner desires;
	private ParseUser user;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.registration_3a_screen);
		
		user = ParseUser.getCurrentUser();
		
		TextView desc_1 = (TextView)findViewById(R.id.desc_1);
		TextView desc_2 = (TextView)findViewById(R.id.desc_2);
		
		String username1 = user.getUsername().substring(0, user.getUsername().indexOf(" "));
		String username2 = user.getUsername().substring(user.getUsername().lastIndexOf(" ")+1);
		
		desc_1.setText("Describe "+username1);
		desc_2.setText("Describe "+username2);
		
		eyeColor1 = (EditText)findViewById(R.id.eye_color_1);
		eyeColor2 = (EditText)findViewById(R.id.eye_color_2);
		hairColor1 = (EditText)findViewById(R.id.hair_color_1);
		hairColor2 = (EditText)findViewById(R.id.hair_color_2);
		age1 = (EditText)findViewById(R.id.age_1);
		age2 = (EditText)findViewById(R.id.age_2);
		height1 = (EditText)findViewById(R.id.height_1);
		height2 = (EditText)findViewById(R.id.height_2);
		nationality1 = (EditText)findViewById(R.id.nationality_1);
		nationality2 = (EditText)findViewById(R.id.nationality_2);
		bodyType1 = (EditText)findViewById(R.id.body_type_1);
		bodyType2 = (EditText)findViewById(R.id.body_type_2);
		
		desires = (Spinner)findViewById(R.id.desires);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.desires_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		desires.setAdapter(adapter);
		
		eyeColor1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (eyeColor1.getText().toString().equals("")) {
					eyeColor1.setBackgroundResource(R.drawable.eye_color);
				} else {
					eyeColor1.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		hairColor1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (hairColor1.getText().toString().equals("")) {
					hairColor1.setBackgroundResource(R.drawable.hair_color);
				} else {
					hairColor1.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		age1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (age1.getText().toString().equals("")) {
					age1.setBackgroundResource(R.drawable.age);
				} else {
					age1.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		height1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (height1.getText().toString().equals("")) {
					height1.setBackgroundResource(R.drawable.height);
				} else {
					height1.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		nationality1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (nationality1.getText().toString().equals("")) {
					nationality1.setBackgroundResource(R.drawable.nationality);
				} else {
					nationality1.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		bodyType1.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (bodyType1.getText().toString().equals("")) {
					bodyType1.setBackgroundResource(R.drawable.body_type);
				} else {
					bodyType1.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		eyeColor2.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (eyeColor2.getText().toString().equals("")) {
					eyeColor2.setBackgroundResource(R.drawable.eye_color);
				} else {
					eyeColor2.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		hairColor2.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (hairColor2.getText().toString().equals("")) {
					hairColor2.setBackgroundResource(R.drawable.hair_color);
				} else {
					hairColor2.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		age2.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (age2.getText().toString().equals("")) {
					age2.setBackgroundResource(R.drawable.age);
				} else {
					age2.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		height2.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (height2.getText().toString().equals("")) {
					height2.setBackgroundResource(R.drawable.height);
				} else {
					height2.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		nationality2.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (nationality2.getText().toString().equals("")) {
					nationality2.setBackgroundResource(R.drawable.nationality);
				} else {
					nationality2.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		bodyType2.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (bodyType2.getText().toString().equals("")) {
					bodyType2.setBackgroundResource(R.drawable.body_type);
				} else {
					bodyType2.setBackgroundResource(R.drawable.blank_3);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		
		ImageButton submit = (ImageButton)findViewById(R.id.submit);
		submit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				//do all the puts for users 1 and 2
				//save in background
				String important = desires.getSelectedItem().toString();
				
				user.put("EyeColor", eyeColor1.getText().toString()+" & "+eyeColor2.getText().toString());
				user.put("HairColor", hairColor1.getText().toString()+" & "+hairColor2.getText().toString());
				user.put("Age", age1.getText().toString()+" & "+age2.getText().toString());
				user.put("Height", height1.getText().toString()+" & "+height2.getText().toString());
				user.put("Nationality", nationality1.getText().toString()+" & "+nationality2.getText().toString());
				user.put("BodyType", bodyType1.getText().toString()+" & "+bodyType2.getText().toString());
				user.put("Important", important);
				
				user.saveInBackground(new SaveCallback(){
					public void done(ParseException e){
						if (e == null){
							Intent intent = new Intent(Registration3Screen.this, Registration4Screen.class);
							startActivity(intent);
							finish();
						}
						else{
							Toast.makeText(getApplicationContext(), "Problem saving user information", Toast.LENGTH_LONG).show();
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
            	Intent upIntent = new Intent(this, Registration2Screen.class);
				this.startActivity(upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
