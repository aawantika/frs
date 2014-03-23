package edu.gatech.financialapplication;

import java.util.Arrays;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	public void onClick(View view) {
		Intent intent = new Intent(this, WelcomeActivity.class);
		username = ((EditText) findViewById(R.id.editTextUser)).getText()
				.toString();
		if (!username.equals("")) {
			User user = db.getUserByUsername(username);
			String email = user.getEmail();
			List<String> toEmailList = Arrays.asList(email);
			String password = user.getPassword();
			String emailBody = "Dear Customer, your forgotten password is: "
					+ password;

			new SendMailTask(ForgotPasswordActivity.this).execute(toEmailList,
					emailBody);
		}
		startActivity(intent);
	}
}
