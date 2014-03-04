package edu.gatech.financialapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class WithdrawalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal);
	}

	public void onClick(View view) {
		int tempAccountTo = Integer.parseInt(((EditText)findViewById(R.id.editTextAccountFrom)).getText().toString());
		int tempAccountFrom = Integer.parseInt(((EditText)findViewById(R.id.editTextAccountTo)).getText().toString());
		float tempAmount = Float.parseFloat(((EditText)findViewById(R.id.editTextAmount)).getText().toString());
		String tempReason = ((EditText)findViewById(R.id.editTextReason)).getText().toString();
		String tempCategory = ((EditText)findViewById(R.id.editTextCategory)).getText().toString();
		
		if (tempAccountFrom != 0 && tempAccountTo != 0 && tempAmount != 0 && !tempReason.equals("") && !tempCategory.equals("")) {
			
			Transaction transaction = new Withdrawal(tempAccountTo, tempAccountFrom, tempAmount, tempReason, tempCategory);
			WelcomeActivity.db.addTransaction(transaction);
			Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
		}
	}
}