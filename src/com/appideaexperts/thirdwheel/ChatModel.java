package com.appideaexperts.thirdwheel;

import java.util.HashMap;
import java.util.LinkedList;

import com.parse.ParseUser;

public interface ChatModel {
	LinkedList<ParseUser> getUsers();
	HashMap<String, String> getChatMessages();
	String getChannel();
	boolean isAuthenticated();
	void logout();
	void updateUserList();
	void sendChatMessage(String userKey, String message);
	void updateUserTracking();
	
	//can get rid of some functions, honestly; just copied from online example
}
