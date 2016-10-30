package com.appideaexperts.thirdwheel;

import java.io.ByteArrayOutputStream;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Registration4bScreen extends Activity {
	
	private ParseUser user;
	private ParseFile profilePic;
	private ImageButton picture;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.registration_4b_screen);

		picture = (ImageButton) findViewById(R.id.pic);
		user = ParseUser.getCurrentUser();

		picture.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takePictureIntent,1);
				}
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent upIntent = new Intent(this, Registration3bScreen.class);
			this.startActivity(upIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == 1 && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap pic = (Bitmap) extras.get("data");
	        picture.setImageBitmap(pic);
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        pic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	        byte[] bytearray= stream.toByteArray();
	        
	        if (bytearray != null){
	            profilePic = new ParseFile(user.getUsername()+".jpg",bytearray);
	            profilePic.saveInBackground(new SaveCallback(){
	            	public void done(ParseException e){
	            		if (e == null){
	            			user.put("singlePhoto",profilePic);
	            			user.saveInBackground(new SaveCallback(){
	            				public void done(ParseException e){
	            					if (e == null){
	            						Toast.makeText(getApplicationContext(), "Profile picture saved.", Toast.LENGTH_LONG).show();
	        	            			Intent intent = new Intent(Registration4bScreen.this, SignInScreen.class);
	        	            			startActivity(intent);
	        	            			finish();
	            					}
	            					else{
	            						Toast.makeText(getApplicationContext(), "Was not able to save picture.", Toast.LENGTH_LONG).show();
	            					}
	            				}
	            			});
	            		}
	            		else {
	            			Toast.makeText(getApplicationContext(), "Was not able to save picture.", Toast.LENGTH_LONG).show();
	            		}
	            	}
	            });
	        }
	    }
	}
}
