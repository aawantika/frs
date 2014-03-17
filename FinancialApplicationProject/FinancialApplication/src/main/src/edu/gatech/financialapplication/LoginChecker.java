package edu.gatech.financialapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class LoginChecker extends IntentService {
	private ResultReceiver rec;
	
	public void onCreate(){
		super.onCreate();
	}

	public LoginChecker() {
		super("LoginChecker");
		setIntentRedelivery(false);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		rec = intent.getParcelableExtra("receiverTag");
		String username = intent.getStringExtra("username");
		String password = intent.getStringExtra("password");
		
		User userFromDb = WelcomeActivity.db.getUserDetailsByUsername(username);
		User userFromActivity = new User();
		
		userFromActivity.setUsername(username);
		userFromActivity.setPassword(password);
		
		Bundle bundle = new Bundle();
		if (userFromDb != null && userFromDb.equals(userFromActivity)) {
			
			bundle.putBoolean("ServiceTag", true);
			bundle.putString("username", username);
			bundle.putString("password", password);
			bundle.putString("firstname", userFromDb.getFirstname());
			bundle.putString("lastname", userFromDb.getLastname());
			rec.send(0, bundle);
		} else {
			bundle.putBoolean("ServiceTag", false);
			rec.send(0, bundle);
		}
	}

}
