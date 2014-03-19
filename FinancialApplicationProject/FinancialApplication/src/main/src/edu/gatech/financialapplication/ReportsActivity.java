package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReportsActivity extends Activity {
	private String username, accountNumberTemp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports);

		accountNumberTemp = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
	}

	public void onCSRClick(View view) {
		Intent intent = new Intent(this, ReportDateActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("type", "consumerSpending");
		bundle.putString("accountNumber", accountNumberTemp);
		bundle.putString("username", username);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
