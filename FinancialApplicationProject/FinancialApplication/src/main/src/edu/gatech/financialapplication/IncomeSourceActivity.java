package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class IncomeSourceActivity extends Activity {

	private DBHelper db;
	private String username, accountNumberTemp;
	private String finalStart, finalEnd;

	private float totalDeposits;
	private List<Transaction> transactionList;
	private List<Transaction> withinDates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income_source);
		db = new DBHelper(this);
		withinDates = new ArrayList<Transaction>();

		// pull from intent
		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");

		transactionList = db.getAllTransactionsByUsername(username);

		populateCorrectList();
		populateCashCategories();
	}

	/**
	 * Populates a list with transactions that are within date range.
	 */
	private void populateCorrectList() {
		for (Transaction t : transactionList) {
			Log.i("MONTH ", t.getDate());
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0)
					&& (finalEnd.compareTo(date) >= 0)
					&& t.getType().equals("deposit")) {

				Log.i("transaction in cs", t.toString());
				withinDates.add(t);
			}
		}
	}

	/**
	 * Fills in money buckets with corresponding transaction values.
	 */
	private void populateCashCategories() {
		for (Transaction t : withinDates) {
			totalDeposits += t.getAmount();
		}
	}
	
	/**
	 * On click to go back to the general menu.
	 * 
	 * @param view The view being used.
	 */
	public void onTransactionClick (View view) {
		Intent intent = new Intent(this, TransactionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
