package edu.gatech.financialapplication;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CashFlowUserActivity extends Activity {

	private DBHelper db;
	private String username, accountNumber, finalStart, finalEnd;

	private float totalDeposits, totalWithdrawals, netFlow;
	private List<Transaction> transactionList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cash_flow_user);
		db = new DBHelper(this);

		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");

		TextView dateText = (TextView) findViewById(R.id.dateText);
		String fixStart = DateGrabber.convertStringToDate(finalStart);
		String fixEnd = DateGrabber.convertStringToDate(finalEnd);
		dateText.setText(fixStart + " - " + fixEnd);

		calculateTransactionAmounts();
		inputTransactionAmounts();
	}

	/**
	 * Populates a list with transactions that are within date range.
	 */
	private void calculateTransactionAmounts() {
		transactionList = db.getAllTransactionsByUsername(username);
		for (Transaction t : transactionList) {
			Log.i("MONTH ", t.getDate());
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0) && (finalEnd.compareTo(date) >= 0)) {
				if (t.getType().equals("deposit")) {
					totalDeposits += t.getAmount();
				} else if (t.getType().equals("withdrawal")) {
					totalWithdrawals += t.getAmount();
				}
			}
		}
	}

	private void inputTransactionAmounts() {
		TextView deposits = (TextView) findViewById(R.id.depositText);
		deposits.setText(Float.toString(totalDeposits));
		TextView withdrawals = (TextView) findViewById(R.id.withdrawalText);
		withdrawals.setText(Float.toString(totalWithdrawals));
		TextView netFlowText = (TextView) findViewById(R.id.netFlowText);

		netFlow = totalDeposits - totalWithdrawals;
		String netFlowString = "";
		if (netFlow > 0) {
			netFlowString = "+" + Float.toString(netFlow);
			netFlowText.setText(netFlowString);
			netFlowText.setTextColor(Color.rgb(34, 139, 34));
		} else if (netFlow < 0) {
			netFlowString = Float.toString(netFlow);
			netFlowText.setText(netFlowString);
			netFlowText.setTextColor(Color.RED);
		} else {
			netFlowText.setText("0");
		}
	}

	/**
	 * On click to go back to the general menu.
	 * 
	 * @param view The view being used.
	 */
	public void onBackClick(View view) {
		Intent intent = new Intent(this, TransactionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumber);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
