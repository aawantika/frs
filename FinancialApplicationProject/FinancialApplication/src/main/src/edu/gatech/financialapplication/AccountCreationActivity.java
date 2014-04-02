package edu.gatech.financialapplication;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This activity provides the action for the account creation page.
 * 
 * @version 1.0
 */
public class AccountCreationActivity extends Activity {

    /**
     * Default amount textview.
     */
    private TextView defaultAmount;
    /**
     * New string username.
     */
    private String username;
    /**
     * New string firstname.
     */
    private String firstname;
    /**
     * New string lastname.
     */
    private String lastname;
    /**
     * New string accountNumber.
     */
    private String accountNumber = createAccountNumber() + "";
    /**
     * Default amount textview.
     */
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acount_creation);
        db = new DBHelper(this);

        // previous intent information
        username = getIntent().getStringExtra("username");
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");

        // input fields
        defaultAmount = (TextView) findViewById(R.id.defaultAmount);
    }

    /**
     * On click for account creation to create a new account.
     * 
     * @param view
     *            The view being used.
     */
    public void onAccountCreate(View view) {
        // check if initial amount is less than 100
        if (Float.parseFloat(defaultAmount.getText().toString()) < 100.00f) {
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage("Minimum amount must be 100 USD.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                }
                            }).show();
        } else { // CREATES ACCOUNT
            new AlertDialog.Builder(this)
                    .setTitle("Your account is created")
                    .setMessage(
                            "Account number " + accountNumber + " is created. ")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    Account account = new Account(firstname,
                                            lastname, username, defaultAmount
                                                    .getText().toString(),
                                            accountNumber);
                                    if (db.addAccount(account)) {
                                        Intent intent = new Intent(
                                                AccountCreationActivity.this,
                                                TransactionActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("accountNumber",
                                                accountNumber);
                                        bundle.putString("username", username);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }).show();
        }
    }

    /**
     * Creates a new account number.
     * 
     * @return a new account number.
     */
    public int createAccountNumber() {
        Random r = new Random();
        // Let's say ten digits

        int newAccountNumber = 0;
        for (int i = 0; i < 10; i++) {
            int base = (int) Math.pow(10.0, (double) i);
            int random = r.nextInt(10);
            newAccountNumber = newAccountNumber + (base * random);
        }

        return Math.abs(newAccountNumber);
    }
}
