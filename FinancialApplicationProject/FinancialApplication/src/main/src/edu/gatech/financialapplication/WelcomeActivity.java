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
		
		
//		db.addUser(new User("tika", "sahu", "yolouser", "password", "email",
//				8889999, "june", 78906543, "accounts", "yellowbrickroad",
//				"atlanta", "georgia", 90876));
//		Log.d("Reading: ", "Reading all contacts..");
//		Log.d("LOOK HERE: ", db.getUserDetails("tika").getLastname());
//		String log = "DB HELPER";
//		Log.d("Name: ", log);
//		db.addAccount(new Account("Joseph", "Lee", "aaa", 1000.00, 1234567890));
//		Log.d("ACCOUNT: ", db.getAccountDetails(1234567890).getFirstname());
//		db.addTransaction(new Transaction(1234567890, 1234567891, "A", 100.00, "TEST"));
//		Log.d("Transaction: " , db.getTransactionWithTo(1234567890).getAmount() + " ");
		
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
