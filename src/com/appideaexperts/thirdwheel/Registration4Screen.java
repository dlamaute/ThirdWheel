package com.appideaexperts.thirdwheel;

import java.io.ByteArrayOutputStream;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
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

public class Registration4Screen extends Activity {
	
	private ParseUser user;
	private ParseFile file;
	private String username1;
	private String username2;
	private ImageButton pic_1;
	private ImageButton pic_2;
	private ImageButton pic_3;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.registration_4a_screen);
		
		pic_1 = (ImageButton) findViewById(R.id.pic_1);
		pic_2 = (ImageButton) findViewById(R.id.pic_2);
		pic_3 = (ImageButton) findViewById(R.id.pic_3);
		TextView direction_1 = (TextView) findViewById(R.id.headshot_1);
		TextView direction_2 = (TextView) findViewById(R.id.headshot_2);
		
		user = ParseUser.getCurrentUser();
		
		username1 = user.getUsername().substring(0, user.getUsername().indexOf(" "));
		username2 = user.getUsername().substring(user.getUsername().lastIndexOf(" ")+1);
		
		direction_1.setText("1. Take a headshot of "+ username1);
		direction_2.setText("2. Take a headshot of "+ username2);
		
		pic_1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takePictureIntent, 1);
				}
			}
		});
		
		pic_2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takePictureIntent, 2);
				}
			}
		});
		
		pic_3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takePictureIntent, 3);
				}
			}
		});
		
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	Intent upIntent = new Intent(this, Registration3Screen.class);
				this.startActivity(upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == 1 && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap profilePic = (Bitmap) extras.get("data");
	        pic_1.setImageBitmap(profilePic);
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        profilePic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	        byte[] bytearray= stream.toByteArray();
	        
	        if (bytearray != null){
	            file = new ParseFile(username1+".jpg",bytearray);
	            file.saveInBackground(new SaveCallback(){
	            	public void done(ParseException e){
	            		if (e == null){
	            			user.put("user1Photo",file); //figure out how to add the two other photos
	            			Toast.makeText(getApplicationContext(), "Picture 1 saved.", Toast.LENGTH_LONG).show();
	            			user.saveInBackground(new SaveCallback(){
	            				public void done(ParseException e){
	            					if (e == null){
	            						Toast.makeText(getApplicationContext(), "Profile picture 1 saved.", Toast.LENGTH_LONG).show();
	            					}
	            					else{
	            						Toast.makeText(getApplicationContext(), "Was not able to save picture.", Toast.LENGTH_LONG).show();
	            					}
	            				}
	            			});
	            		}
	            		else {
	            			Toast.makeText(getApplicationContext(), "Was not able to save picture 1.", Toast.LENGTH_LONG).show();
	            		}
	            	}
	            	
	            });
	        }
	    }
	    else if (requestCode == 2 && resultCode == RESULT_OK){
	    	Bundle extras = data.getExtras();
	        Bitmap profilePic = (Bitmap) extras.get("data");
	        pic_2.setImageBitmap(profilePic);
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        profilePic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	        byte[] bytearray= stream.toByteArray();
	        
	        if (bytearray != null){
	            file = new ParseFile(username2+".jpg",bytearray);
	            file.saveInBackground(new SaveCallback(){
	            	public void done(ParseException e){
	            		if (e == null){
	            			user.put("User2Photo",file); //figure out how to add the two other photos
	            			Toast.makeText(getApplicationContext(), "Picture 2 saved.", Toast.LENGTH_LONG).show();
	            			user.saveInBackground(new SaveCallback(){
	            				public void done(ParseException e){
	            					if (e == null){
	            						Toast.makeText(getApplicationContext(), "Profile picture 2 saved.", Toast.LENGTH_LONG).show();
	            					}
	            					else{
	            						Toast.makeText(getApplicationContext(), "Was not able to save picture 2.", Toast.LENGTH_LONG).show();
	            					}
	            				}
	            			});
	            		}
	            		else {
	            			Toast.makeText(getApplicationContext(), "Was not able to save picture 2.", Toast.LENGTH_LONG).show();
	            		}
	            	}
	            	
	            });
	        }
	    }
	    else if (requestCode == 3 && resultCode == RESULT_OK){
	    	Bundle extras = data.getExtras();
	        Bitmap profilePic = (Bitmap) extras.get("data");
	        pic_3.setImageBitmap(profilePic);
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        profilePic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	        byte[] bytearray= stream.toByteArray();
	        
	        if (bytearray != null){
	            file = new ParseFile(username1 +"_" + username2 +".jpg",bytearray);
	            file.saveInBackground(new SaveCallback(){
	            	public void done(ParseException e){
	            		if (e == null){
	            			user.put("couplePhoto",file); //figure out how to add the two other photos
	            			Toast.makeText(getApplicationContext(), "Picture saved.", Toast.LENGTH_LONG).show();
	            			user.saveInBackground(new SaveCallback(){
	            				public void done(ParseException e){
	            					if (e == null){
	            						Toast.makeText(getApplicationContext(), "Profile picture saved.", Toast.LENGTH_LONG).show();
	        	            			Intent intent = new Intent(Registration4Screen.this, SignInScreen.class);
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
