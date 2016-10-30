package com.appideaexperts.thirdwheel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Registration1Screen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.registration_1_screen);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);

		RadioGroup situation = (RadioGroup) findViewById(R.id.situation);

		situation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (checkedId == R.id.sit1) {
					Intent intent = new Intent(Registration1Screen.this,
							Registration2Screen.class);
					intent.putExtra("userSituation", "coupleToCouple");
					startActivity(intent);
					finish();
				} else if (checkedId == R.id.sit2) {
					Intent intent = new Intent(Registration1Screen.this,
							Registration2Screen.class);
					intent.putExtra("userSituation", "coupleToSingle");
					startActivity(intent);
					finish();
				} else if (checkedId == R.id.sit3) {
					Intent intent = new Intent(Registration1Screen.this,
							Registration2bScreen.class);
					intent.putExtra("userSituation", "singleToCouple");
					startActivity(intent);
					finish();
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent upIntent = new Intent(this, HomeScreen.class);
			this.startActivity(upIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
