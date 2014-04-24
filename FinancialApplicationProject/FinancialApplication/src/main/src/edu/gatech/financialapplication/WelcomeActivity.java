package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Activity file for welcome activity.
 * 
 * @author Team 15.
 */
public class WelcomeActivity extends Activity {

	protected static DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		db = new DBHelper(this);

		db.addUser(new User("administrator", "lastName", "admin", "pass123",
				"passwordHint", "admin@gatech.edu"));

		// USE THIS FOR FUTURE TESTING IT WORKS PERFECTLY. DON'T SCREW IT UP!
		// //DO NOT TOUCH EITHER; WRITE YOUR OWN CODE FOR THIS.
//		db.addUser(new User("tika", "sahu", "yolouser", "password",
//				"passwordHint", "email"));
//		User getUser = db.getUserByUsername("yolouser");
//		// System.out.println(getUser);
//		Account newAccount = new Account(getUser.getFirstname(),
//				getUser.getLastname(), getUser.getUsername(), "1000.00",
//				"1234567890", "1000.00");
//		 db.addAccount(newAccount);
//		 Account getAccount =
//		 db.getAccountByAccountNumber(newAccount.getAccountNumber());
//		// System.out.println(getAccount);
//		// System.out.println("ORIGINAL AMOUNT: " + getAccount.getBalance());
//		db.addTransaction(new Deposit("1234567890", "04/08/2014", 200.00f, "TEST"));
//		db.addTransaction(new Deposit("1234567890", "04/08/2014", 600.00f, "TEST"));
//		db.addTransaction(new Deposit("1234567890", "04/10/2014", 800.00f, "TEST"));
//		
//		List<Pair<String, String>> list = db.getAllBalances(getAccount.getAccountNumber());
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).first + " " + list.get(i).second);
//		}
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

}