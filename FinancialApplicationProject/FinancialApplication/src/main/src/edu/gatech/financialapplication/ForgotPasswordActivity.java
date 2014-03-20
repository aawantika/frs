package edu.gatech.financialapplication;

import javax.mail.MessagingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity {
	private String username;
	private DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		db = new DBHelper(this);
	}

	public void onClick(View view) {
		Intent intent = new Intent(this, WelcomeActivity.class);
		username = ((EditText) findViewById(R.id.editTextUser)).getText()
				.toString();
		System.out.println(username + "USERNAME");
		// check valid username
		if (!username.equals("")) {
			User user = db.getUserByUsername(username);
			String email = user.getEmail();
			String password = user.getPassword();
			System.out.println("email " + email + " password " + password);
			ForgotPassword fp = new ForgotPassword();
			fp.sendMessage(email, password);
		}
		startActivity(intent);
	}
}
