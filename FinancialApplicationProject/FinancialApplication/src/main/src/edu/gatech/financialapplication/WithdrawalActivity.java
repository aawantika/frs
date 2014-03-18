package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WithdrawalActivity extends Activity {
	private String accountNumber, username;
	private DBHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal);
		db = new DBHelper(this);
		
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
	}

	public void onClick(View view) {
		String tempDate = ((EditText)findViewById(R.id.editTextDate)).getText().toString();
		float tempAmount = Float.parseFloat(((EditText)findViewById(R.id.editTextAmount)).getText().toString());
		String tempReason = ((EditText)findViewById(R.id.editTextReason)).getText().toString();
		String tempCategory = ((EditText)findViewById(R.id.editTextCategory)).getText().toString();
		
		if (tempAmount > 0 && !tempReason.equals("") && !tempCategory.equals("")) {
			Transaction transaction = new Withdrawal(accountNumber, tempDate, tempAmount, tempReason, tempCategory);
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