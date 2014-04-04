package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Activity file for welcome activity.
 * 
 * @author Team 15.
 */
public class WelcomeActivity extends Activity {

    /**
     * New DBHelper db.
     */
    protected static DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        db = new DBHelper(this);
        // db.removeAll();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
        db.addUser(new User("administrator", "lastName", "admin", "pass123",
                "passwordHint", "admin@gatech.edu"));

        // USE THIS FOR FUTURE TESTING IT WORKS PERFECTLY. DON'T SCREW IT UP!
        // //DO NOT TOUCH EITHER; WRITE YOUR OWN CODE FOR THIS.
/*        db.addUser(new User("tika", "sahu", "yolouser", "password",
                "passwordHint", "email"));

        System.out.println(db.getUserByUsername("yolouser"));*/
        // User getUser = db.getUserByUsername("yolouser");
        // Account newAccount = new Account(getUser.getFirstname(),
        // getUser.getLastname(), getUser.getUsername(), "1000.00",
        // "1234567890");
        // db.addAccount(newAccount);
        // Account getAccount =
        // db.getAccountByAccountNumber(newAccount.getAccountNumber());
        // System.out.println("ORIGINAL AMOUNT: " + getAccount.getBalance());
        // db.addTransaction(new Deposit(getAccount.getAccountNumber(),
        // "04/08/2014", 200.00f, "TEST"));
        // db.addTransaction(new Deposit(getAccount.getAccountNumber(),
        // "04/09/2014", 600.00f, "TEST"));
        // db.addTransaction(new Deposit(getAccount.getAccountNumber(),
        // "04/10/2014", 800.00f, "TEST"));
        // System.out.println("NEW AMOUNT: " + getAccount.getBalance());
        // ArrayList<Transaction> list =
        // db.getAllTransactions(getAccount.getAccountNumber());
        // for (Transaction t : list) {
        // System.out.println("TRANSACTION: " + t);
        // }
        // old version, don't use
        // db.addTransaction(new Transaction(getAccount.getAccountNumber(), "A",
        // 200.00f, "TEST", "djfk", "type"));

        // int accountNum = Integer.parseInt(getAccount.getAccountNumber());
        // System.out.println("LOOK HERE " + getAccount.toString());
        // Transaction transaction = db.getTransactionDetails(accountNum);
        // Log.d("Transaction: " , transaction.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    /**
     * On click for login activity button.
     * 
     * @param view
     *            The view being used.
     */
    public void toLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * On click for register activity button.
     * 
     * @param view
     *            The view being used.
     */
    public void toRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        /**
         * Placeholder fragment constructor.
         */
        public PlaceholderFragment() {
            //empty method
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome,
                    container, false);
            return rootView;
        }
    }

}