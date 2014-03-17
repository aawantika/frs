package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionActivity extends Activity {
	private Context ctx;
	private ArrayList<Account> account;
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

		// accountNumberTemp = getIntent().getStringExtra("accountNumber");
		// System.out.println("ACCOUNT NUMBER HER; " + accountNumberTemp);

		// Account account = db.getAccountByAccountNumber(accountNumberTemp);
		// System.out.println("BALANCE: " + account.getBalance());
		// accountNumber = Integer.parseInt(accountNumberTemp);
		// User user = new User(firstname, lastname, username, password, email);
		// Log.d("Account: ", WelcomeActivity.db.getAccount(user).toString());

		db = new DBHelper(this);
		if (getIntent() != null) {
			accountNumberTemp = getIntent().getStringExtra("accountNumber");

			username = getIntent().getStringExtra("username");
			account = db.getAccountsByUsername(username);
			adapter = new AccountAdapter(this, R.layout.transaction_row,
					account);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
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
		User user = db.getUserObject(username);
		intent.putExtra("password", user.getPassword());
		intent.putExtra("firstname", user.getFirstname());
		intent.putExtra("lastname", user.getLastname());
		startActivity(intent);
	}

	public class AccountAdapter extends ArrayAdapter<Account> implements
			AdapterView.OnItemClickListener {
		private Context context;
		private ArrayList<Account> objects;
		private int rowResourceId;

		public AccountAdapter(Context context, int resource,
				ArrayList<Account> objects) {
			super(context, resource, objects);
			this.context = context;
			this.objects = objects;
			this.rowResourceId = resource;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(rowResourceId, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.textView);
			textView.setTextColor(Color.BLACK);

			textView.setText(objects.get(position).debug());
			// Log.e(objects.get(position).getName(),
			// objects.get(position).debug());

			return rowView;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, final View view,
				final int position, long id) {
			

			String item = ((TextView)view).getText().toString();
            Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
			System.out.println("LOOK HERE FOR ACCOUNT NUMBER: " + item);
		}

		@Override
		public int getCount() {
			return objects.size();
		}

		public int getPosition(Account item) {
			return objects.indexOf(item);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public Account getItem(int position) {
			return objects.get(position);
		}

		public boolean hasStableIds() {
			return true;
		}

	}
}