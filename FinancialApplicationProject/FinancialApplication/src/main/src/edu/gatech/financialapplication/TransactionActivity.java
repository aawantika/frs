package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * The Activity file for the main directory for making a transaction.
 * 
 * @author Team 15
 */
public class TransactionActivity extends Activity {

	private ArrayList<Account> accounts;
	private ArrayAdapter<Account> adapter;
	private DBHelper db;
	private ListView listview;

	String username, password, firstname, lastname, email, accountNumberTemp;
	int accountNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		listview = (ListView) findViewById(R.id.accountLV);
		db = new DBHelper(this);

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");

		accounts = db.getAccountsByUsername(username);
		adapter = new AccountAdapter(this, R.layout.transaction_row, accounts);
		listview.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		listview.setSelection(0);
		accountNumberTemp = accounts.get(0).getAccountNumber();

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {

				if (position != 0) {
					View rowView = listview.getChildAt(0);
					TextView textView = (TextView) rowView
							.findViewById(R.id.textView);
					textView.setBackgroundColor(Color.rgb(227, 236, 239));
				} else {
					View rowView = listview.getChildAt(0);
					TextView textView = (TextView) rowView
							.findViewById(R.id.textView);
					textView.setBackgroundColor(Color.rgb(173, 211, 224));
				}

				view.setSelected(true);
				Account accountFromClick = accounts.get(position);
				accountNumberTemp = accountFromClick.getAccountNumber();
			}
		});

	}

	/**
	 * On click for switching to the view to create a deposit.
	 * 
	 * @param view  The view being used.
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
	 * @param view The view being used.
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
	 * @param view The view being used.
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
	 * @param view The view being used.
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
	 * On click for switching to the view to view the reports.
	 * 
	 * @param view The view being used.
	 */
	public void onGraphsClick(View view) {
		Intent intent = new Intent(this, GraphActivity.class);
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