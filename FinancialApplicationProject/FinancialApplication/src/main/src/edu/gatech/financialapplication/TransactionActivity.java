package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TransactionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
    	String username = getIntent().getStringExtra("username");
    	String password = getIntent().getStringExtra("password");
    	String firstname = getIntent().getStringExtra("firstname");
    	String lastname = getIntent().getStringExtra("lastname");
    	String email = getIntent().getStringExtra("email");
    	User user = new User(firstname, lastname, username, password, email);
    	Log.d("Account: ", WelcomeActivity.db.getAccount(user).toString());
	}

//needs to be filled in
	public void onDepositClick(View view) {
		Intent intent = new Intent(this, DepositActivity.class);
		startActivity(intent);
	}

	public void onWithdrawalClick(View view) {
		Intent intent = new Intent(this, WithdrawalActivity.class);
		startActivity(intent);
	}
}