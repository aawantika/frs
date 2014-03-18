package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DepositActivity extends Activity {
	String accountNumber, username;
	private DBHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit);
		db = new DBHelper(this);
		
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
	}

	public void onClick(View view) {
		String tempDate = ((EditText)findViewById(R.id.editTextDate)).getText().toString();
		String tempAmountString = ((EditText)findViewById(R.id.editTextAmount)).getText().toString();
		float tempAmount = Float.parseFloat(tempAmountString);
		String tempReason = ((EditText)findViewById(R.id.editTextReason)).getText().toString();
		
		if (!tempReason.equals("")) {
			Transaction transaction = new Deposit(accountNumber, tempDate, tempAmount, tempReason);
			db.addTransaction(transaction);
			
			Intent intent = new Intent(this, TransactionActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("accountNumber", accountNumber);
			bundle.putString("username", username);
			intent.putExtras(bundle);
	    	startActivity(intent);
		}
	}
}