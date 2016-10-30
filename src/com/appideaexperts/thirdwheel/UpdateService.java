package com.appideaexperts.thirdwheel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**for chat
 * 
 * @author Diana
 *
 */

public class UpdateService extends Service {
	
	private ChatModel model;

	public UpdateService() {
		model = (ChatApplication) getApplication();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags,  int startId){
		super.onStartCommand(intent, flags, startId);
		model.updateUserList();
		model.updateUserTracking();
		return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
