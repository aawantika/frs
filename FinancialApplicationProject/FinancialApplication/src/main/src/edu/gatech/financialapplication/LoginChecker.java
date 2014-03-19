package edu.gatech.financialapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class LoginChecker extends IntentService {
	private ResultReceiver rec;
	private DBHelper db;

	public void onCreate() {
		super.onCreate();

		db = new DBHelper(this);
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

		User userFromDb = db.getUserByUsername(username);
		User userFromActivity = new User();

		userFromActivity.setUsername(username);
		userFromActivity.setPassword(password);

		Bundle bundle = new Bundle();
		if (userFromDb != null && userFromDb.equals(userFromActivity)) {
			bundle.putBoolean("ServiceTag", true);
			bundle.putString("username", userFromDb.getUsername());
			bundle.putString("firstname", userFromDb.getLastname());
			bundle.putString("lastname", userFromDb.getFirstname());
			rec.send(0, bundle);
		} else {
			bundle.putBoolean("ServiceTag", false);
			rec.send(0, bundle);
		}
	}
}
