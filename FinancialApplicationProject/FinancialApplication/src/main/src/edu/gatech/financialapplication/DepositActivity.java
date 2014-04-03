package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * The Activity file for making a deposit.
 * @author Team 15
 *
 */
public class DepositActivity extends Activity {
	/**
	 * An account number string.
	 */
    private String accountNumber;
    /**
     * An username string.
     */
    private String username;
    /**
     * The database helper for getting database information.
     */
    private DBHelper database;
    /**
     * DataGrabber to get and format the date.
     */
    private DateGrabber dateGrabber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        database = new DBHelper(this);
        dateGrabber = new DateGrabber();
        //CHECKSTYLE:OFF
        accountNumber = getIntent().getStringExtra("accountNumber"); //string necessary
        username = getIntent().getStringExtra("username"); //string necessary
        //CHECKSTYLE:ON
    }

    /**
     * On click for deposits to create a deposit transaction.
     * @param view The view being used.
     */
    public void onClick(final View view) {
        final String tempDate = dateGrabber.createDate();
        final String tempAmountString = ((EditText) findViewById(R.id.editTextAmount)).getText().toString(); //ignore pmd
        final float tempAmount = Float.parseFloat(tempAmountString);
        final String tempReason = ((EditText) findViewById(R.id.editTextReason)).getText().toString(); //ignore pmd

        if ("".equals(tempReason)) { // empty reason
            new AlertDialog.Builder(this)
                    //CHECKSTYLE:OFF
                    .setTitle("Information error") //string necessary
                    //CHECKSTYLE:ON
                    .setMessage("Sorry, reason can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog,
                                        final int which) {
                                }
                            }).show();
        } else if (tempAmount <= 0) { // empty firstname
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage(
                            "Sorry, invalid amount. \nAmount must be greater than 0.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog,
                                        final int which) {
                                }
                            }).show();
        } else {
            final Transaction transaction = new Deposit(accountNumber, tempDate, tempAmount, tempReason);
            database.addTransaction(transaction);

            final Intent intent = new Intent(this, TransactionActivity.class);
            final Bundle bundle = new Bundle();
            bundle.putString("accountNumber", accountNumber);
            bundle.putString("username", username);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}