package com.appideaexperts.thirdwheel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class LoadingScreen extends Activity{

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_screen);
		
		ImageView spinner = (ImageView) findViewById(R.id.spin_icon);
		
		RotateAnimation r = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .37f);
		r.setDuration(1000);
		r.setRepeatCount(2);
		r.setInterpolator(new LinearInterpolator());
		spinner.startAnimation(r);
		
		r.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(LoadingScreen.this, ProfileScreen.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
