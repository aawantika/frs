package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * A class that generates consumer spending reports.
 * 
 * @author Team 15
 */
public class ConsumerSpendingActivity extends Activity {

	private DBHelper db;
	private String username, accountNumberTemp;
	private String finalStart, finalEnd;

	private float totalWithdrawals;
	private float gas, rent, clothing, business, groceries, entertainment;
	private List<Transaction> transactionList;
	private List<Transaction> withinDates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_spending);
		db = new DBHelper(this);
		withinDates = new ArrayList<Transaction>();

		// pull from intent
		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");
		
		TextView dateText = (TextView) findViewById(R.id.dateText);
		String fixStart = DateGrabber.convertStringToDate(finalStart);
		String fixEnd = DateGrabber.convertStringToDate(finalEnd);
		dateText.setText(fixStart + " - " + fixEnd);

		transactionList = db.getAllTransactionsByUsername(username);

		populateCorrectList();
		populateCashCategories();
		populateTextFields();
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
					&& t.getType().equals("withdrawal")) {
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
			totalWithdrawals += t.getAmount();
			if (t.getCategory().equals("Gas")) {
				gas += t.getAmount();
			} else if (t.getCategory().equals("Rent")) {
				rent += t.getAmount();
			} else if (t.getCategory().equals("Clothing")) {
				clothing += t.getAmount();
			} else if (t.getCategory().equals("Business")) {
				business += t.getAmount();
			} else if (t.getCategory().equals("Groceries")) {
				groceries += t.getAmount();
			} else if (t.getCategory().equals("Entertainment")) {
				entertainment += t.getAmount();
			}
		}
	}

	/**
	 * Loads up the report data into the text fields.
	 */
	private void populateTextFields() {
		TextView text = (TextView) findViewById(R.id.textView12);
		text.setText(Float.toString(totalWithdrawals));
		text = (TextView) findViewById(R.id.textView13);
		text.setText(Float.toString(gas));
		text = (TextView) findViewById(R.id.textView14);
		text.setText(Float.toString(rent));
		text = (TextView) findViewById(R.id.textView15);
		text.setText(Float.toString(clothing));
		text = (TextView) findViewById(R.id.textView16);
		text.setText(Float.toString(business));
		text = (TextView) findViewById(R.id.textView17);
		text.setText(Float.toString(groceries));
		text = (TextView) findViewById(R.id.textView18);
		text.setText(Float.toString(entertainment));
	}

	/**
	 * On click to go back to the general menu.
	 * 
	 * @param view The view being used.
	 */
	public void onClick(View view) {
		Intent intent = new Intent(this, TransactionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
