package edu.gatech.financialapplication;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AccountCreationActivity extends Activity {
	private TextView cell, address, defaultAmount;
	private Account account;
	private String username, password, firstname, lastname, email;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acount_creation);
		cell = (TextView) findViewById(R.id.phone);
		address = (TextView) findViewById(R.id.address);
		defaultAmount = (TextView) findViewById(R.id.defaultAmount);
		username = getIntent().getStringExtra("username");
		password = getIntent().getStringExtra("password");
		firstname = getIntent().getStringExtra("firstname");
		lastname = getIntent().getStringExtra("lastname");
		email = getIntent().getStringExtra("email");
	}

	public void onAccountCreate(View view) {
		if (cell.getText().length() < 10
				|| Double.parseDouble(defaultAmount.getText().toString()) < 100.00) {
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage(
							"Our app requires you to have proper cell phone number along with minimum of 100 U.S Dollar for default amount.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();

		} else {
			final int accNum = createAccountNumber();
			new AlertDialog.Builder(this)
					.setTitle("Your account is crated")
					.setMessage("Account number " + accNum + " is created. ")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									WelcomeActivity.db.addAccount(new Account(
											firstname, lastname, username,
											Double.parseDouble(defaultAmount
													.getText().toString()),
											accNum));
									Intent intent = new Intent(
											getApplicationContext(),
											TransactionActivity.class);
									intent.putExtra("username", username);
									intent.putExtra("password", password);
									intent.putExtra("firstname", firstname);
									intent.putExtra("lastname", lastname);
									intent.putExtra("email", email);
									startActivity(intent);
									finish();
								}
							}).show();

		}
	}

	public int createAccountNumber() {
		Random ran = new Random();
		// Let's say ten digits
		int accountNum = 0;
		for (int i = 0; i < 10; i++) {
			int base = (int) Math.pow(10.0, (double) i);
			int random = ran.nextInt(10);
			accountNum = accountNum + (base * random);
		}
		return Math.abs(accountNum);
	}
}
