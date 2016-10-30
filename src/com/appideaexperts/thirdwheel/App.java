package com.appideaexperts.thirdwheel;

import com.parse.Parse;
import android.app.Application;

public class App extends Application {
 /*currently the app permits full registration (but without location added)
  *has layouts for main functionality
  *no chat
  */	
	
	
	@Override
	public void onCreate(){
		super.onCreate();
		//can change to keys of the iOS database
		Parse.initialize(this, "VYDkCNkKdNgoDpBNaxo0Qu0f7ZONr7ll9rSeF4lZ", "EsYkmVGhN4Y2j5ZfZUhB3WWYxaPhMIAl76RUx0iR");
		
	}
}
