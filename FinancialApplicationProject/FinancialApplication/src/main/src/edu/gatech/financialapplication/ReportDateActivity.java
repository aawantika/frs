package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ReportDateActivity extends Activity {
	private String username, accountNumberTemp, type;
	private EditText monthFrom, dayFrom, yearFrom, monthTo, dayTo, yearTo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_date);

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		type = getIntent().getStringExtra("type");
		monthFrom = (EditText)findViewById(R.id.monthFrom);
		dayFrom = (EditText)findViewById(R.id.dayFrom);
		yearFrom = (EditText)findViewById(R.id.yearFrom);
		monthTo = (EditText)findViewById(R.id.monthTo);
		dayTo = (EditText)findViewById(R.id.dayTo);
		yearTo = (EditText)findViewById(R.id.yearTo);
	}


	public void onDateClick(View view) {
		Intent intent = null;
		if (type.equals("consumerSpending")) {
			intent = new Intent(this, ConsumerSpendingActivity.class);
		}
		//add other elseifs
		
		Bundle bundle = new Bundle();
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		bundle.putString("monthFrom", monthFrom.getText().toString());
		bundle.putString("dayFrom", dayFrom.getText().toString());
		bundle.putString("yearFrom", yearFrom.getText().toString());
		bundle.putString("monthTo", monthTo.getText().toString());
		bundle.putString("dayTo", dayTo.getText().toString());
		bundle.putString("yearTo", yearTo.getText().toString());
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
