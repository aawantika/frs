package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * The Activity file for the main directory for making a transaction.
 * 
 * @author Team 15
 */
public class TransactionActivity extends Activity {

    private ArrayList<Account> accounts; // need to use ArrayList
    private ArrayAdapter<Account> adapter;
    private DBHelper db;

    String username, password, firstname, lastname, email, accountNumberTemp;
    int accountNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ListView listview = (ListView) findViewById(R.id.accountLV);

        db = new DBHelper(this);
        if (getIntent() != null) {
            accountNumberTemp = getIntent().getStringExtra("accountNumber");
            username = getIntent().getStringExtra("username");

            accounts = db.getAccountsByUsername(username);
            adapter = new AccountAdapter(this, R.layout.transaction_row,
                    accounts);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            
            listview.setSelection(0);
            accountNumberTemp = accounts.get(0).getAccountNumber();
            
            
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parentAdapter,
                        View view, int position, long id) {
                    view.setSelected(true);
                    Account accountFromClick = accounts.get(position);
                    accountNumberTemp = accountFromClick.getAccountNumber();
                }
            });
        }
    }

    /**
     * On click for switching to the view to create a deposit.
     * 
     * @param view
     *            The view being used.
     */
    public void onDepositClick(View view) {
        Intent intent = new Intent(this, DepositActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * On click for switching to the view to create a withdrawal.
     * 
     * @param view
     *            The view being used.
     */
    public void onWithdrawalClick(View view) {
        Intent intent = new Intent(this, WithdrawalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * On click for switching to the view to create an account.
     * 
     * @param view
     *            The view being used.
     */
    public void onCreateAccount(View view) {
        Intent intent = new Intent(this, AccountCreationActivity.class);
        intent.putExtra("username", username);
        User user = db.getUserByUsername(username);
        intent.putExtra("password", user.getPassword());
        intent.putExtra("firstname", user.getFirstname());
        intent.putExtra("lastname", user.getLastname());
        startActivity(intent);
    }

    /**
     * On click for switching to the view to view the reports.
     * 
     * @param view
     *            The view being used.
     */
    public void onReportsClick(View view) {
        Intent intent = new Intent(this, ReportsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    
    /**
     * Logs the user out
     * 
     * @param view the view being used
     */
    public void onLogoutClick(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}