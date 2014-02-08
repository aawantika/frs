package edu.gatech.financialapplication;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ResultReceiver;

public class LoginChecker extends IntentService {
	protected static final String credentials = "USER_CREDENTIALS";
	private SharedPreferences sp;
	private ResultReceiver rec;
	private String user, pass;
	
	public void onCreate(){
		super.onCreate();
	}

	public LoginChecker() {
		super("LoginChecker");
		setIntentRedelivery(false);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		sp = getSharedPreferences(credentials, MODE_PRIVATE);
		user = sp.getString("username", "admin");
		pass = sp.getString("password", "pass123");
		rec = intent.getParcelableExtra("receiverTag");
		String username = intent.getStringExtra("username");
		String password = intent.getStringExtra("password");
		if (username.equals(user) && password.equals(pass)) {
			Bundle b = new Bundle();
			b.putBoolean("ServiceTag", true);
			rec.send(0, b);
		} else {
			Bundle b = new Bundle();
			b.putBoolean("ServiceTag", false);
			rec.send(0, b);
		}
	}

}
