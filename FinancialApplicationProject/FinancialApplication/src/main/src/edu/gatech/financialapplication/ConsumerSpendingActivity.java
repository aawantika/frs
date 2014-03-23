package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

public class ConsumerSpendingActivity extends Activity {
	private DBHelper db;
	private String username, accountNumberTemp, dateFrom, dateTo;
	private int dayTo, dayFrom, monthTo, monthFrom, yearTo, yearFrom;
	private float totalWithdrawals, totalDeposits, balance, gas, rent, clothing, business, groceries, entertainment;
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
		
		monthFrom = Integer.valueOf(dateFrom.substring(0,2));
		dayFrom = Integer.valueOf(dateFrom.substring(3,5));
		yearFrom = Integer.valueOf(dateFrom.substring(6,10));

		monthTo = Integer.valueOf(dateTo.substring(0,2));
		dayTo = Integer.valueOf(dateTo.substring(3,5));
		yearTo = Integer.valueOf(dateTo.substring(6,10));
		
		transactionList = db.getAllTransactions(accountNumberTemp);
		for (Transaction t : transactionList) {
			int month = Integer.valueOf(t.getDate().substring(0,2));
			int day = Integer.valueOf(t.getDate().substring(3,5));
			int year = Integer.valueOf(t.getDate().substring(6,10));
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
				withinDates.add(t);
			}
		}
		
		for (Transaction t : withinDates) {
			if (t instanceof Deposit) {
				totalDeposits += t.getAmount();
			} else if (t instanceof Withdrawal) {
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
		
		balance = totalDeposits - totalWithdrawals;
		
		// make sure to remove the date fields from the deposit/withdrawal xmls
		// and fix their activities!
	}
}
