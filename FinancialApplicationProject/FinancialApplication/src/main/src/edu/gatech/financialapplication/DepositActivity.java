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
 * 
 * @author Team 15
 * 
 */
public class DepositActivity extends Activity {
	
	private String accountNumber, username;
    private DBHelper database;
    private DateGrabber dateGrabber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        database = new DBHelper(this);
        dateGrabber = new DateGrabber();

        accountNumber = getIntent().getStringExtra("accountNumber");
        username = getIntent().getStringExtra("username"); 
    }

    /**
     * On click for deposits to create a deposit transaction.
     * 
     * @param view The view being used.
     */
    public void onClick(final View view) {
    	String date = dateGrabber.createDate();
        String amount = ((EditText) findViewById(R.id.editTextAmount)).getText().toString();
        String reason = ((EditText) findViewById(R.id.editTextReason)).getText().toString();

      if (checkReason(reason) && checkAmount(amount)){
            final Transaction transaction = new Deposit(accountNumber,
                    date, Float.parseFloat(amount), reason);
            database.addTransaction(transaction);

            final Intent intent = new Intent(this, TransactionActivity.class);
            final Bundle bundle = new Bundle();
            bundle.putString("accountNumber", accountNumber);
            bundle.putString("username", username);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    
    private boolean checkReason(String reason) {
    	boolean result = true;
    	
    	 if ("".equals(reason)) { 
    		 result = false;
             new AlertDialog.Builder(this)
                     .setTitle("Information error")
                     .setMessage("Sorry, reason can't be blank.")
                     .setPositiveButton(android.R.string.ok,
                             new DialogInterface.OnClickListener() {
                                 public void onClick(
                                         final DialogInterface dialog,
                                         final int which) {
                                 }
                             }).show();
         } else if (" ".equals(reason.substring(0,1))) { 
        	 result = false;
             new AlertDialog.Builder(this)
                     .setTitle("Information error")
                     .setMessage("Sorry, reason can't be a space.")
                     .setPositiveButton(android.R.string.ok,
                    		 new DialogInterface.OnClickListener() {
                         public void onClick(
                                 final DialogInterface dialog,
                                 final int which) {
                         }
                     }).show();
         } 
    	 
    	 return result;
    }
    
    private boolean checkAmount(String amount) {
    	boolean result = true;
    	
    	if (amount.equals("")) {
   		 result = false;
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage("Sorry, amount can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog,
                                        final int which) {
                                }
                            }).show();
        } else if (Float.parseFloat(amount) <= 0) {
    		 result = false;
             new AlertDialog.Builder(this)
                     .setTitle("Information error")
                     .setMessage("Sorry, invalid amount. \nAmount must be greater than 0.")
                     .setPositiveButton(android.R.string.ok,
                             new DialogInterface.OnClickListener() {
                                 public void onClick(
                                         final DialogInterface dialog,
                                         final int which) {
                                 }
                             }).show();
         } 
    	 return result;
    }
    
    public void onBackClick(View view){
        finish();
    }
}