package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TransactionHistoryActivity extends Activity {
	
	private DBHelper db;
	private String username, accountNumber, finalStart, finalEnd;
	
	private List<Transaction> transactionList;
	private List<Transaction> withinDates;
	private ArrayAdapter<Transaction> historyAdapter;
	private ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_history);
		
		db = new DBHelper(this);
		withinDates = new ArrayList<Transaction>();
		listview = (ListView) findViewById(R.id.transactionHistoryLV);

		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");
		
		TextView dateText = (TextView) findViewById(R.id.dateText);
		String fixStart = DateGrabber.convertStringToDate(finalStart);
		String fixEnd = DateGrabber.convertStringToDate(finalEnd);
		dateText.setText(fixStart + " - " + fixEnd);
		TextView accountNum = (TextView) findViewById(R.id.accountNum);
		accountNum.setText("Account Number: " + accountNumber);
		
		getProperTransactions();
		historyAdapter = new TransactionHistoryAdapter(this, R.layout.transaction_history_row, withinDates);
		listview.setAdapter(historyAdapter);
		historyAdapter.notifyDataSetChanged();
	}
	
	/**
	 * Populates a list with transactions that are within date range.
	 */
	private void getProperTransactions() {
		transactionList = db.getAllTransactionsByUsername(username);
		for (Transaction t : transactionList) {
			Log.i("MONTH ", t.getDate());
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0) && (finalEnd.compareTo(date) >= 0) 
					&& t.getAccount().equals(accountNumber)) {
				withinDates.add(t);
			}
		}
	}
	
	/**
	 * On click to go back to the general menu.
	 * 
	 * @param view The view being used.
	 */
	public void onBackClick (View view) {
		Intent intent = new Intent(this, TransactionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumber);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	/**
	 * Go back to date page
	 * 
	 * @param view View being used
	 */
	public void returnToPrev(View view){
        finish();
    }
}
