package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DepositActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public void onClick(View view) {
		String tempAccountToString = ((EditText)findViewById(R.id.editTextAccountTo)).getText().toString();
		int tempAccountTo = Integer.parseInt(tempAccountToString);
		String tempAmountString = ((EditText)findViewById(R.id.editTextAmount)).getText().toString();
		float tempAmount = Float.parseFloat(tempAmountString);
		String tempReason = ((EditText)findViewById(R.id.editTextReason)).getText().toString();
		
		if (tempAccountTo != 0 && tempAmount != 0 && !tempReason.equals("")) {
			Transaction transaction = new Deposit(tempAccountTo, tempAmount, tempReason);
			WelcomeActivity.db.addTransaction(transaction);
			Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
		}
	}
	
	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/


}