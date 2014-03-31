package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Activity file for withdrawal activity.
 * 
 * @author Team 15.
 */
public class WithdrawalActivity extends Activity {
    
    /**
     * New Spinner spinner.
     */
    private Spinner spinner;
    /**
     * New String account number.
     */
    private String accountNumber;
    /**
     * New String username.
     */
    private String username;
    /**
     * New String date.
     */
    private String date;
    /**
     * New String reason.
     */
    private String reason;
    /**
     * New String category.
     */
    private String category;
    
    /**
     * New float amount.
     */
    private float amount;
    /**
     * New DBHelper db.
     */
    private DBHelper db;
    /**
     * New DateGrabber dg.
     */
    private DateGrabber dg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        db = new DBHelper(this);
        dg = new DateGrabber();

        accountNumber = getIntent().getStringExtra("accountNumber");
        username = getIntent().getStringExtra("username");

        spinner = (Spinner) findViewById(R.id.categorySpinner);
        spinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener());
    }

    /**
     * SpinnerOnItemSelected class.
     * @author Team 15.
     *
     */
    public class SpinnerOnItemSelectedListener implements
            OnItemSelectedListener {

        /**
         * Changes the option to the one selected.
         * 
         * @param parent The AdapterView being used.
         * @param view The view being used.
         * @param pos The position of the item selected.
         * @param id The id of the item selected.
         */
        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                long id) {
            category = parent.getItemAtPosition(pos).toString();
            System.out.println("CATEGORY " + category);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    /**
     * On click for withdrawal activity to make a transaction.
     * @param view The view being used.
     */
    public void onClick(View view) {
        date = dg.createDate();
        amount = Float
                .parseFloat(((EditText) findViewById(R.id.editTextAmount))
                        .getText().toString());
        reason = ((EditText) findViewById(R.id.editTextReason)).getText()
                .toString();

        if (amount <= 0) { // empty amount
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage(
                            "Sorry, invalid amount. \nAmount must be greater than 0.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                }
                            }).show();
        } else if (reason.equals("")) { // empty reason
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage("Sorry, reason can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                }
                            }).show();
        } else {
            Transaction transaction = new Withdrawal(accountNumber, date,
                    amount, reason, category);
            db.addTransaction(transaction);

            Intent intent = new Intent(this, TransactionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("accountNumber", accountNumber);
            bundle.putString("username", username);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}