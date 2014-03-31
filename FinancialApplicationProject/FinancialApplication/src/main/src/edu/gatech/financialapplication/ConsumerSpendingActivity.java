package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/**
 * A class that generates consumer spending reports.
 * @author AI to EU
 *
 */
public class ConsumerSpendingActivity extends Activity {
	/**
	 * A helper for accessing the database.
	 */
    private DBHelper db;
    /**
     * String for accessing the proper user.
     */
    private String username;
    /**
     * String for accessing the correct accounts.
     */
    private String accountNumberTemp;
    /**
     * represents the maximum day range.
     */
    private int dayTo;
    /**
     * represents min day range.
     */
    private int dayFrom;
    /**
     * represents day of transaction.
     */
    private int day;
    /**
     * represents max month range.
     */
    private int monthTo;
    /**
     * represents min month range.
     */
    private int monthFrom;
    /**
     * represents month of transaction.
     */
    private int month;
    /**
     * represents max year range.
     */
    private int yearTo;
    /**
     * represents min year range.
     */
    private int yearFrom;
    /**
     * represents year of transaction.
     */
    private int year;
    /**
     * represents total amt withdrawn.
     */
    private float totalWithdrawals;
    /**
     * represents total amt gas spent.
     */
    private float gas;
    /**
     * represents total amt rent spent.
     */
    private float rent;
    /**
     * represents total amt clothing spent.
     */
    private float clothing;
    /**
     * represents total amt business spent.
     */
    private float business;
    /**
     * represents total amt groceries spent.
     */
    private float groceries;
    /**
     * represents total amt entertainment spent.
     */
    private float entertainment;
    /**
     * represents all transactions.
     */
    private ArrayList<Transaction> transactionList;
    /**
     * represents all date range valid transactions.
     */
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

        populateCorrectList();
        populateCashCategories();
        populateTextFields();
    }
    /**
     * Populates a list with transactions that are within date range.
     */
    private void populateCorrectList() {
        for (Transaction t : transactionList) {
            System.out.println("MONTH " + t.getDate());
            month = Integer.valueOf(t.getDate().substring(0, 2));
            day = Integer.valueOf(t.getDate().substring(3, 5));
            year = Integer.valueOf(t.getDate().substring(6, 10));
            boolean goodToGoForward = false;
            boolean goodToGoBackward = false;
            goodToGoForward = evalForward(goodToGoForward);
            goodToGoBackward = evalBackward(goodToGoForward, goodToGoBackward);
            if (goodToGoForward && goodToGoBackward) {
                System.out.println(t);
                withinDates.add(t);
            }
        }
    }
    /**
     * evaluates the forward direction.
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
            } else if (month == monthFrom) {
                if (day >= dayFrom) {
                    goodToGoForward = true;
                }
            }
        }
    	return goodToGoForward;
    }
    /**
     * Checks if the date range is fine going backwards.
     * @param efficiencyCheck check and see if the forward range was good
     * @param toEval evaluate if the backward date range is good
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
                } else if (month == monthTo) {
                    if (day <= dayTo) {
                        goodToGoBackward = true;
                    }
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
