package edu.gatech.financialapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class WithdrawalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.withdrawal, menu);
		return true;
	}

	public void onClick(View view) {
		String tempAccount = ((EditText)findViewById(R.id.editTextAccount)).getText().toString();
		String tempReason = ((EditText)findViewById(R.id.editTextReason)).getText().toString();
		String tempAmount = ((EditText)findViewById(R.id.editTextAmount)).getText().toString();
		String tempCategory = ((EditText)findViewById(R.id.editTextCategory)).getText().toString();
	}
}