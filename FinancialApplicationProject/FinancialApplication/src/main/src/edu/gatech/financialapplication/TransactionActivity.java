package edu.gatech.financialapplication;

import java.util.ArrayList;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TransactionActivity extends Activity  {
	private Context ctx;
	private ArrayList<Account> acc;
	private ArrayAdapter<Account> adapter;

	String username, password, lastname, firstname, email;
	String accountNumberTemp;
	int accountNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		ListView lv = (ListView) findViewById(R.id.accountLV);

//		accountNumberTemp = getIntent().getStringExtra("accountNumber");
//		System.out.println("ACCOUNT NUMBER HER; " + accountNumberTemp);
		
//		Account account = db.getAccountByAccountNumber(accountNumberTemp);
//		System.out.println("BALANCE: " + account.getBalance());
//    	accountNumber = Integer.parseInt(accountNumberTemp);
//    	User user = new User(firstname, lastname, username, password, email);
//    	Log.d("Account: ", WelcomeActivity.db.getAccount(user).toString());
		
		DBHelper db = new DBHelper(this);
		if (getIntent() != null) {
			acc = db.getAccountsByUsername(getIntent().getStringExtra("username"));
			adapter = new AccountAdapter(this, R.layout.transaction_row, acc);
			lv.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}

	public void onDepositClick(View view) {
		Intent intent = new Intent(this, DepositActivity.class);
		intent.putExtra("accountNumber", accountNumberTemp);
		startActivity(intent);
	}

	public void onWithdrawalClick(View view) {
		Intent intent = new Intent(this, WithdrawalActivity.class);
		intent.putExtra("accountNumber", accountNumberTemp);
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