package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TransactionActivity extends Activity {
	private ArrayList<Account> accounts;
	private ArrayAdapter<Account> adapter;
	private DBHelper db;

	String username, password, lastname, firstname, email;
	String accountNumberTemp;
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
			
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parentAdapter, View view,
						int position, long id) {
					view.setSelected(true);
					Account accountFromClick = accounts.get(position);
					String accountNumberFromClick = accountFromClick
							.getAccountNumber();
					accountNumberTemp = accountNumberFromClick;
				}
			});
		}
	}

	public void onDepositClick(View view) {
		Intent intent = new Intent(this, DepositActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public void onWithdrawalClick(View view) {
		Intent intent = new Intent(this, WithdrawalActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public void onCreateAccount(View view) {
		Intent intent = new Intent(this, AccountCreationActivity.class);
		intent.putExtra("username", username);
		User user = db.getUserByUsername(username);
		intent.putExtra("password", user.getPassword());
		intent.putExtra("firstname", user.getFirstname());
		intent.putExtra("lastname", user.getLastname());
		startActivity(intent);
	}
	
	public void onReportsClick(View view) {
		Intent intent = new Intent(this, ReportsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}