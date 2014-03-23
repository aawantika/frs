package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ReportDateActivity extends Activity {
	private String username, accountNumberTemp, type;
	private EditText dateFrom;
	private EditText dateTo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_date);

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		type = getIntent().getStringExtra("type");
		dateFrom = (EditText)findViewById(R.id.editText1);
		dateTo = (EditText)findViewById(R.id.editText2);
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
		bundle.putString("dateFrom", dateFrom.getText().toString());
		bundle.putString("dateTo", dateTo.getText().toString());
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
