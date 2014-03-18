package edu.gatech.financialapplication;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AccountCreationActivity extends Activity {
	private TextView cell, address, defaultAmount;
	private String username, password, firstname, lastname;
	private String accountNumber = createAccountNumber() + "";
	private Context ctx;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acount_creation);
		ctx = this;

		// previous intent information
		username = getIntent().getStringExtra("username");
		password = getIntent().getStringExtra("password");
		firstname = getIntent().getStringExtra("firstname");
		lastname = getIntent().getStringExtra("lastname");

		// input fields
		cell = (TextView) findViewById(R.id.phone);
		address = (TextView) findViewById(R.id.address);
		defaultAmount = (TextView) findViewById(R.id.defaultAmount);
	}

	public void onAccountCreate(View view) {
		if (cell.getText().length() < 10) {
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Please enter in a ten-digit phone number")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();

		} else if (Double.parseDouble(defaultAmount.getText().toString()) < 100.00) {
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Minimum amount must be 100 USD.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else {
			// CREATES ACCOUNT
			accountNumber = createAccountNumber() + "";
			new AlertDialog.Builder(this)
					.setTitle("Your account is created")
					.setMessage(
							"Account number " + accountNumber + " is created. ")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									//DBHelper db = new DBHelper(ctx);
									Account account = new Account(firstname,
											lastname, username, defaultAmount
													.getText().toString(),
											accountNumber);
									if (WelcomeActivity.db.addAccount(account)) {
										Intent intent = new Intent(ctx,
												TransactionActivity.class);
										Bundle bundle = new Bundle();
										bundle.putString("accountNumber", accountNumber);
										bundle.putString("username", username);
										intent.putExtras(bundle);
										startActivity(intent);
										finish();
									}
								}
							}).show();
		}
	}

	public int createAccountNumber() {
		Random ran = new Random();
		// Let's say ten digits

		int newAccountNumber = 0;
		for (int i = 0; i < 10; i++) {
			int base = (int) Math.pow(10.0, (double) i);
			int random = ran.nextInt(10);
			newAccountNumber = newAccountNumber + (base * random);
		}

		return Math.abs(newAccountNumber);
	}
}
