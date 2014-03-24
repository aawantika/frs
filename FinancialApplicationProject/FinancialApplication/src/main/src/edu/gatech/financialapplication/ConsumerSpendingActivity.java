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
		withinDates = new ArrayList<Transaction>();
		
		//pull from intent
		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		
		monthFrom = Integer.valueOf(getIntent().getStringExtra("monthFrom"));
		dayFrom = Integer.valueOf(getIntent().getStringExtra("dayFrom"));
		yearFrom = Integer.valueOf(getIntent().getStringExtra("yearFrom"));
		
		monthTo = Integer.valueOf(getIntent().getStringExtra("monthTo"));
		dayTo = Integer.valueOf(getIntent().getStringExtra("dayTo"));
		yearTo = Integer.valueOf(getIntent().getStringExtra("yearTo"));

		transactionList = db.getAllTransactionsByUsername(username);
		System.out.println("SIZE " + transactionList.size());

		for (Transaction t : transactionList) {
			System.out.println("MONTH " + t.getDate());
			int month = Integer.valueOf(t.getDate().substring(0, 2));
			int day = Integer.valueOf(t.getDate().substring(3, 5));
			int year = Integer.valueOf(t.getDate().substring(6, 10));
			boolean goodToGoForward = false;
			boolean goodToGoBackward = false;
			//evaluate forward
			if (year > yearFrom) {
				goodToGoForward = true;
			} else if (year == yearFrom) {
				if (month > monthFrom) {
					goodToGoForward = true;
				} else if (month == monthFrom) {
					if (day >= dayFrom) {
						goodToGoForward = true;
					}
				}
			}
			
			if (goodToGoForward) {
				if (year < yearTo) {
					goodToGoBackward = true;
				} else if (year == yearTo) {
					if (month < monthTo) {
						goodToGoBackward = true;
					} else if (month == monthTo) {
						if (day <= dayTo) {
							goodToGoBackward = true;
						}
					}
				}
			}

			if (goodToGoForward && goodToGoBackward) {
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
