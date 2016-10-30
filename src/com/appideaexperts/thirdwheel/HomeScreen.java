package com.appideaexperts.thirdwheel;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.content.Intent;
import android.widget.PopupWindow;
import android.widget.Button;

public class HomeScreen extends Activity {

	/** Need to implement scrolling pictures thing */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.home_screen);
	}
	    
	public void signIn(View v){
		Intent intent = new Intent(HomeScreen.this, SignInScreen.class);
		startActivity(intent);
		finish();
	}
	
	public void register(View v){
		Intent intent = new Intent(HomeScreen.this, Registration1Screen.class);
		startActivity(intent);
		finish();
	}
	
	public void policyPop(View view){
		LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(R.layout.privacy_policy, null);
			final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Button okButton = (Button)popupView.findViewById(R.id.okbutton);
			okButton.setOnClickListener(new Button.OnClickListener() {
			
				@Override
				public void onClick(View v){
					popupWindow.dismiss();
				}
			});
		popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
	}
	
}
