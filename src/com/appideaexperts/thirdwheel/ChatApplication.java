package com.appideaexperts.thirdwheel;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

//not sure if any of this works
//may want to use external service like HipMob to implement chat

public class ChatApplication extends Application implements ChatModel{
	
	private static LinkedList<ParseUser> users;
	private static HashMap<String, String> chatMessages;
	private static String channel = "";
	private static ParseUser user = null;
	private static PendingIntent pIntent;
	private static AlarmManager alarm;
	
	public void onCreate(){
		super.onCreate();
		ParseACL acl = new ParseACL();
		acl.setPublicReadAccess(true);
		ParseACL.setDefaultACL(acl, true);
		users = new LinkedList<ParseUser>();
		chatMessages = new HashMap<String, String>();
		Intent sIntent = new Intent(this, UpdateService.class);
		pIntent = PendingIntent.getService(this, 0, sIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		logout();
		startTracking();
	}

	@Override
	public LinkedList<ParseUser> getUsers() {
		return users;
	}

	@Override
	public HashMap<String, String> getChatMessages() {
		return chatMessages;
	}

	@Override
	public String getChannel() {
		return channel;
	}

	@Override
	public boolean isAuthenticated() {
		return (user != null && user.isAuthenticated());
	}

	@Override
	public void logout() {
		if (user != null) {
			ParseUser.logOut();
			}
			stopTracking();
	}

	@Override
	public void updateUserList() {
		ParseQuery query = ParseUser.getQuery();
		// Only users with the last activities within 5 minutes
		final long TIME_SUB = 300000;
		Date queryDate = new Date(new Date().getTime() - TIME_SUB);
		query.whereGreaterThan("lastUpdate", queryDate);
		try {
			List<ParseObject> list = query.find();
			users.clear();
			for (ParseObject o : list) {
					users.add((ParseUser) o);
			}
		} catch (ParseException e) {
		}
		
	}

	@Override
	public void sendChatMessage(String target, String message) {
		try {
			ParsePush push = new ParsePush();
			push.setChannel(target);
			push.setExpirationTimeInterval(86400);
			push.setData(new JSONObject("{" +
			"action:" + /*com.appideaexperts.thirdwheel.MESSAGE*/", " +
			"from:" + channel + ", " +
			"to:" + target + ", " +
			"message:" + message + "}"));
			push.sendInBackground();
			} catch (JSONException e) {
			}
		
	}

	@Override
	public void updateUserTracking() {
		if (user != null && user.isAuthenticated()) {
			user.put("lastUpdate", new Date());
			user.saveEventually();
			}
	}

		private void startTracking() {
		stopTracking();
		ParseUser usr = ParseUser.getCurrentUser();
		if (usr != null && usr.isAuthenticated()) {
		channel = usr.getUsername();
		user = usr;
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10000, pIntent);
		}
		}

		private void stopTracking() {
		channel = "";
		user = null;
		alarm.cancel(pIntent);
		}

}
