package com.appideaexperts.thirdwheel;

import java.io.FileDescriptor;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LikedScreen extends Activity {
	
	//remember, this is an intent to be sent
	
	private ParseUser user;
	private String username;
	private String username1;
	private String username2;
	

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		ImageButton decline = (ImageButton) findViewById(R.id.decline);
		ImageButton greet = (ImageButton) findViewById(R.id.say_hi);
		
		//set onclicklisteners for these. decline should send back to stack, greet opens chat.
		
		user = ParseUser.getCurrentUser();
		username = user.getUsername();
		TextView like = (TextView) findViewById(R.id.like_text);
		like.setText(username + " " + R.string.like);
		
		//in the try/catch, images of the couple are made into circles
		
		if (username.contains("&")){
			setContentView(R.layout.like_screen_couple);
			ImageView pic1 = (ImageView) findViewById(R.id.liker_1);
			ImageView pic2 = (ImageView) findViewById(R.id.liker_2);
			username1 = username.substring(0, user.getUsername().indexOf(" "));
			username2 = username.substring(user.getUsername().lastIndexOf(" ")+1);
			try{
				byte[] liker1 = user.getParseFile(username1+".jpg").getData();
				Bitmap picBit1 = BitmapFactory.decodeByteArray(liker1, 0, liker1.length);
				picBit1 = Bitmap.createScaledBitmap(picBit1, pic1.getWidth(), pic1.getHeight(), true);
				BitmapShader shader1;
				shader1 = new BitmapShader(picBit1, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

				Paint paint1 = new Paint();
				paint1.setAntiAlias(true);
				paint1.setShader(shader1);
				
				RectF rect1 = new RectF(0.0f, 0.0f, picBit1.getWidth(), picBit1.getHeight());
				Canvas canvas1 = new Canvas(picBit1);
				canvas1.drawRoundRect(rect1, pic1.getWidth()/2, pic1.getWidth()/2, paint1);
				
				pic1.setImageBitmap(picBit1);
				
			}
			catch (ParseException e){
				Toast.makeText(this, "There was a problem retrieving an image", Toast.LENGTH_LONG).show();
			}
			
			try{
				byte[] liker2 = user.getParseFile(username2+".jpg").getData();
				Bitmap picBit2 = BitmapFactory.decodeByteArray(liker2, 0, liker2.length);
				picBit2 = Bitmap.createScaledBitmap(picBit2, pic2.getWidth(), pic2.getHeight(), true);
				BitmapShader shader2;
				shader2 = new BitmapShader(picBit2, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

				Paint paint2 = new Paint();
				paint2.setAntiAlias(true);
				paint2.setShader(shader2);

				RectF rect2 = new RectF(0.0f, 0.0f, picBit2.getWidth(), picBit2.getHeight());
				Canvas canvas2 = new Canvas(picBit2);
				canvas2.drawRoundRect(rect2, pic1.getWidth()/2, pic1.getWidth()/2, paint2);
				
				pic2.setImageBitmap(picBit2);
				
			}
			catch (ParseException e){
				Toast.makeText(this, "There was a problem retrieving an image", Toast.LENGTH_LONG).show();
			}		
		}
		else {
			setContentView(R.layout.like_screen_single);
			ImageView pic = (ImageView) findViewById(R.id.liker);
			try{
				byte[] liker = user.getParseFile(username1+".jpg").getData();
				Bitmap picBit = BitmapFactory.decodeByteArray(liker, 0, liker.length);
				picBit = Bitmap.createScaledBitmap(picBit, pic.getWidth(), pic.getHeight(), true);
				BitmapShader shader;
				shader = new BitmapShader(picBit, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

				Paint paint = new Paint();
				paint.setAntiAlias(true);
				paint.setShader(shader);
				
				RectF rect = new RectF(0.0f, 0.0f, picBit.getWidth(), picBit.getHeight());
				Canvas canvas = new Canvas(picBit);
				canvas.drawRoundRect(rect, pic.getWidth()/2, pic.getWidth()/2, paint);
				
				pic.setImageBitmap(picBit);
			}
			catch (ParseException e){
				Toast.makeText(this, "There was a problem retrieving the image", Toast.LENGTH_LONG).show();
			}
		}
	}

}
