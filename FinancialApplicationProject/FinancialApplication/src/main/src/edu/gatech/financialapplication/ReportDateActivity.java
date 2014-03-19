package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReportDateActivity extends Activity {
	private String username, accountNumberTemp, type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_date);

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		type = getIntent().getStringExtra("type");
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
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
