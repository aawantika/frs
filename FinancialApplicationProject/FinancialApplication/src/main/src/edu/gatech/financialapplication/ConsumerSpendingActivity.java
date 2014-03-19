package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

public class ConsumerSpendingActivity extends Activity {
	private DBHelper db;
	private String username, accountNumberTemp;
	private ArrayList<Transaction> transactionList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_spending);
		db = new DBHelper(this);

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");

		transactionList = db.getAllTransactions(accountNumberTemp);
		//DO FANCY STUFF HERE.
	}
}
