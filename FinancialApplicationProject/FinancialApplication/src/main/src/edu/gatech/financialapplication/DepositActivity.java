package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DepositActivity extends Activity {
	private String accountNumber, username;
	private DBHelper db;
	private DateGrabber dg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit);
		db = new DBHelper(this);
		dg = new DateGrabber();
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
	}

	public void onClick(View view) {
		String tempDate = dg.createDate();
		String tempAmountString = ((EditText) findViewById(R.id.editTextAmount)).getText().toString();
		float tempAmount = Float.parseFloat(tempAmountString);
		String tempReason = ((EditText) findViewById(R.id.editTextReason)).getText().toString();

		if (tempReason.equals("")) { // empty reason
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, reason can't be blank.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (tempAmount <= 0) { // empty firstname
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage(
							"Sorry, invalid amount. \nAmount must be greater than 0.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else {
			Transaction transaction = new Deposit(accountNumber, tempDate, tempAmount, tempReason);
			System.out.println(transaction.toString());
			db.addTransaction(transaction);

			Intent intent = new Intent(this, TransactionActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("accountNumber", accountNumber);
			bundle.putString("username", username);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
}