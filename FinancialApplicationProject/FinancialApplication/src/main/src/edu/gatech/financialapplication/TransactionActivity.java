package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TransactionActivity extends Activity {
	String username, password, lastname, firstname, email;
	String accountNumberTemp;
	int accountNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		System.out.println("ACCOUNT NUMBER HER; " + accountNumberTemp);
		Account account = WelcomeActivity.db.getAccountByAccountNumber(accountNumberTemp);
		System.out.println("BALANCE: " + account.getBalance());
//    	accountNumber = Integer.parseInt(accountNumberTemp);
//    	User user = new User(firstname, lastname, username, password, email);
//    	Log.d("Account: ", WelcomeActivity.db.getAccount(user).toString());
	}

	public void onDepositClick(View view) {
		Intent intent = new Intent(this, DepositActivity.class);
		intent.putExtra("accountNumber", accountNumberTemp);
		startActivity(intent);
	}

	public void onWithdrawalClick(View view) {
		Intent intent = new Intent(this, WithdrawalActivity.class);
		intent.putExtra("accountNumber", accountNumberTemp);
		startActivity(intent);
	}
}