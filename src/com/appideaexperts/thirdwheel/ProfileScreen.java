package com.appideaexperts.thirdwheel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class ProfileScreen extends Activity{
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_buttons, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.main_screen);
		
		ImageButton passBtn = (ImageButton) findViewById(R.id.pass);
		ImageButton likeBtn = (ImageButton) findViewById(R.id.like);
		
		passBtn.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				//on to the next one
			}
		});
		
		likeBtn.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				//send external intent  (use internal to test like screen)
				//iOS version created LIKE class, contains destination id, sender id, usersNameOfLike
				//start chat
			}
		});
	}

	//populate with data of profile called
	//if pass, recall with data of next profile
	//if like, intent sent to liked person's device, open chat
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.action_chat:
			//open chat intent
			return true;
		case R.id.action_settings:
			//open settings intent
			return true;
		case android.R.id.home:
			Intent upIntent = new Intent(this, SignInScreen.class);  //change this intent
			this.startActivity(upIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
