package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class WelcomeActivity extends Activity {
	protected static DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		db = new DBHelper(this);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		db.addUser(new User("administrator", "lastName", "admin", "pass123", "admin@gatech.edu" ));
		
		//USE THIS FOR FUTURE TESTING IT WORKS PERFECTLY. DON'T SCREW IT UP!
		//DO NOT TOUCH EITHER; WRITE YOUR OWN CODE FOR THIS.
//		db.addUser(new User("tika", "sahu", "yolouser", "password", "email"));
//		User getUser = db.getUserDetailsByUsername("yolouser");
//		db.addAccount(new Account(getUser.getFirstname(), getUser.getLastname(), getUser.getUsername(), "1000.00", "1234567890"));
//		Account getAccount = db.getAccountDetails("yolouser");
//		System.out.println("ORIGINAL AMOUNT: " + getAccount.getBalance());
//		db.addTransaction(new Deposit(getAccount.getAccountNumber(), 200.00f, "TEST"));
//		System.out.println("NEW AMOUNT: " + getAccount.getBalance());
		//db.addTransaction(new Transaction(getAccount.getAccountNumber(), "A", 200.00f, "TEST", "djfk", "type"));
		//int accountNum = Integer.parseInt(getAccount.getAccountNumber());
		//System.out.println("LOOK HERE " + getAccount.toString());
//		Transaction transaction = db.getTransactionDetails(accountNum);
//		Log.d("Transaction: " , transaction.toString());
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(getApplicationContext(),
					HateMaxActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void toLoginActivity(View view) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	public void toRegisterActivity(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
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
