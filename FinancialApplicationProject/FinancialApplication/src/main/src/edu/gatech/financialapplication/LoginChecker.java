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
		if (userFromDb != null && userFromDb.equals(userFromActivity)) {
			Bundle b = new Bundle();
			b.putBoolean("ServiceTag", true);
			b.putString("username", username);
			b.putString("password", password);
			b.putString("firstname", userFromDb.getFirstname());
			b.putString("lastname", userFromDb.getLastname());
			b.putString("email", userFromDb.getEmail());
			rec.send(0, b);
		} else {
			Bundle b = new Bundle();
			b.putBoolean("ServiceTag", false);
			rec.send(0, b);
		}
	}

}
