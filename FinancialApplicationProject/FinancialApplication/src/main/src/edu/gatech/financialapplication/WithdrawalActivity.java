package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class WithdrawalActivity extends Activity {
	String accountNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal);
		
		accountNumber = getIntent().getStringExtra("accountNumber");
		Log.d("LOOK HERE ", accountNumber + "");
	}

	public void onClick(View view) {
		String tempDate = ((EditText)findViewById(R.id.editTextDate)).getText().toString();
		float tempAmount = Float.parseFloat(((EditText)findViewById(R.id.editTextAmount)).getText().toString());
		String tempReason = ((EditText)findViewById(R.id.editTextReason)).getText().toString();
		String tempCategory = ((EditText)findViewById(R.id.editTextCategory)).getText().toString();
		
		if (tempAmount != 0 && !tempReason.equals("") && !tempCategory.equals("")) {
			System.out.println("DATE; " + tempDate);
			Transaction transaction = new Withdrawal(accountNumber, tempDate, tempAmount, tempReason, tempCategory);
			WelcomeActivity.db.addTransaction(transaction);
			
			System.out.println("NEW TRANSACTION IS CREATED!!!");
			System.out.println("TRANSACTION number: " + accountNumber);
			System.out.println("TRANSACTION amount " + tempAmount);
			
			Intent intent = new Intent(this, TransactionActivity.class);
			intent.putExtra("accountNumber", accountNumber);
	    	startActivity(intent);
		}
	}
}