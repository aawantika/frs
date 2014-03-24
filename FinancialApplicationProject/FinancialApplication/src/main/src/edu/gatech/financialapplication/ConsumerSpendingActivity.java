package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConsumerSpendingActivity extends Activity {
	private DBHelper db;
	private String username, accountNumberTemp, dateFrom, dateTo;
	private int dayTo, dayFrom, monthTo, monthFrom, yearTo, yearFrom;
	private float totalWithdrawals, gas, rent,
			clothing, business, groceries, entertainment;
	private ArrayList<Transaction> transactionList;
	private ArrayList<Transaction> withinDates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consumer_spending);
		db = new DBHelper(this);

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		dateFrom = getIntent().getStringExtra("dateFrom");
		dateTo = getIntent().getStringExtra("dateTo");
		withinDates = new ArrayList<Transaction>();
		System.out.println("accountNumber and username: " + accountNumberTemp
				+ " " + username);

		monthFrom = Integer.valueOf(dateFrom.substring(0, 2));
		dayFrom = Integer.valueOf(dateFrom.substring(3, 5));
		yearFrom = Integer.valueOf(dateFrom.substring(6, 10));

		monthTo = Integer.valueOf(dateTo.substring(0, 2));
		dayTo = Integer.valueOf(dateTo.substring(3, 5));
		yearTo = Integer.valueOf(dateTo.substring(6, 10));

		transactionList = db.getAllTransactionsByUsername(username);
		System.out.println("SIZE " + transactionList.size());

		for (Transaction t : transactionList) {
			System.out.println("MONTH " + t.getDate());
			int month = Integer.valueOf(t.getDate().substring(0, 2));
			int day = Integer.valueOf(t.getDate().substring(3, 5));
			int year = Integer.valueOf(t.getDate().substring(6, 10));
			boolean withinMonthRange = (monthFrom < month && month < monthTo);
			boolean withinDayRange = (dayFrom <= day && day <= dayTo);
			boolean withinYearRange = (yearFrom < year && year < yearTo);
			boolean goodToGo = false;
			if (withinYearRange == true) {
				goodToGo = true;
			} else {
				if (year == yearFrom || year == yearTo) {
					if (withinMonthRange) {
						goodToGo = true;
					} else {
						if (month == monthFrom || month == monthTo) {
							if (withinDayRange) {
								goodToGo = true;
							}
						}
					}
				}
			}

			if (goodToGo) {
				System.out.println(t);
				withinDates.add(t);
			}
		}

		for (Transaction t : withinDates) {
			if (t.getType().equals("withdrawal")) {
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

		TextView text = (TextView)findViewById(R.id.textView12);
		text.setText(Float.toString(totalWithdrawals));
		text = (TextView)findViewById(R.id.textView13);
		text.setText(Float.toString(gas));
		text = (TextView)findViewById(R.id.textView14);
		text.setText(Float.toString(rent));
		text = (TextView)findViewById(R.id.textView15);
		text.setText(Float.toString(clothing));
		text = (TextView)findViewById(R.id.textView16);
		text.setText(Float.toString(business));
		text = (TextView)findViewById(R.id.textView17);
		text.setText(Float.toString(groceries));
		text = (TextView)findViewById(R.id.textView18);
		text.setText(Float.toString(entertainment));
	}
	
	public void onClick(View view) {
		Intent intent = new Intent(this, TransactionActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		intent.putExtras(bundle);
    	startActivity(intent);
	}
}
