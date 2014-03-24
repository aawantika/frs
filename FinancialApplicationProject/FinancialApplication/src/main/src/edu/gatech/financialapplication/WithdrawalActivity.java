package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

public class WithdrawalActivity extends Activity {
	private Spinner spinner;
	private String accountNumber, username;
	private String date, reason, category;
	private float amount;
	private DBHelper db;
	private DateGrabber dg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal);
		db = new DBHelper(this);
		dg = new DateGrabber();
		
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");

		spinner = (Spinner) findViewById(R.id.categorySpinner);
		spinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener());
	}

	public class SpinnerOnItemSelectedListener implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			category = parent.getItemAtPosition(pos).toString();
			System.out.println("CATEGORY " + category);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	public void onClick(View view) {
		date = dg.createDate();
		amount = Float
				.parseFloat(((EditText) findViewById(R.id.editTextAmount))
						.getText().toString());
		reason = ((EditText) findViewById(R.id.editTextReason)).getText()
				.toString();

		if (amount > 0 && !reason.equals("")) {
			Transaction transaction = new Withdrawal(accountNumber, date,
					amount, reason, category);
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