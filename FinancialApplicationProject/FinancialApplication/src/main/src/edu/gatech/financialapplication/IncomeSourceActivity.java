package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class IncomeSourceActivity extends Activity {

	private DBHelper db;
	private String username;
	private String accountNumberTemp;

	private int dayTo, dayFrom, day;
	private int monthTo, monthFrom, month;
	private int yearTo, yearFrom, year;
	private float totalDeposits;
	private List<Transaction> transactionList;
	private List<Transaction> withinDates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income_source);
		db = new DBHelper(this);
		withinDates = new ArrayList<Transaction>();

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		transactionList = db.getAllTransactionsByUsername(username);

		monthFrom = Integer.valueOf(getIntent().getStringExtra("monthFrom"));
		dayFrom = Integer.valueOf(getIntent().getStringExtra("dayFrom"));
		yearFrom = Integer.valueOf(getIntent().getStringExtra("yearFrom"));

		monthTo = Integer.valueOf(getIntent().getStringExtra("monthTo"));
		dayTo = Integer.valueOf(getIntent().getStringExtra("dayTo"));
		yearTo = Integer.valueOf(getIntent().getStringExtra("yearTo"));

		populateCorrectList();
		populateCashCategories();
	}

	/**
	 * Populates a list with transactions that are within date range.
	 */
	private void populateCorrectList() {
		for (Transaction t : transactionList) {
			Log.i("MONTH ", t.getDate());
			month = Integer.valueOf(t.getDate().substring(0, 2));
			day = Integer.valueOf(t.getDate().substring(3, 5));
			year = Integer.valueOf(t.getDate().substring(6, 10));
			boolean goodToGoForward = false;
			boolean goodToGoBackward = false;
			goodToGoForward = evalForward(goodToGoForward);
			goodToGoBackward = evalBackward(goodToGoForward, goodToGoBackward);
			if (goodToGoForward && goodToGoBackward) {
				Log.i("transaction in cs", t.toString());
				withinDates.add(t);
			}
		}
	}

	/**
	 * evaluates the forward direction.
	 * 
	 * @param toEval the boolean to be returned
	 * @return if the forward date range is good
	 */
	private boolean evalForward(boolean toEval) {
		boolean goodToGoForward = toEval;
		if (year > yearFrom) {
			goodToGoForward = true;
		} else if (year == yearFrom) {
			if (month > monthFrom) {
				goodToGoForward = true;
			} else if (month == monthFrom && day >= dayFrom) {
				goodToGoForward = true;
			}
		}
		return goodToGoForward;
	}

	/**
	 * Checks if the date range is fine going backwards.
	 * 
	 * @param efficiencyCheck
	 *            check and see if the forward range was good
	 * @param toEval
	 *            evaluate if the backward date range is good
	 * @return if the backward date range is good
	 */
	private boolean evalBackward(boolean efficiencyCheck, boolean toEval) {
		boolean goodToGoForward = efficiencyCheck;
		boolean goodToGoBackward = toEval;
		if (goodToGoForward) {
			if (year < yearTo) {
				goodToGoBackward = true;
			} else if (year == yearTo) {
				if (month < monthTo) {
					goodToGoBackward = true;
				} else if (month == monthTo && day <= dayTo) {
					goodToGoBackward = true;
				}
			}
		}
		return goodToGoBackward;
	}

	/**
	 * Fills in money buckets with corresponding transaction values.
	 */
	private void populateCashCategories() {
		for (Transaction t : withinDates) {
			if (t.getType().equals("deposit")) {
				totalDeposits += t.getAmount();
			}
		}
	}

}
