package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Allows the user to select different types of reports.
 * @author Team 15
 *
 */
public class ReportsActivity extends Activity {
    /**
     * The current username.
     */
    private String username;
	/**
	 * The current account number.
	 */
    private String accountNumberTemp;

    @Override
    protected void onCreate(Bundle savedInstanceStt) {
        super.onCreate(savedInstanceStt);
        setContentView(R.layout.activity_reports);

        accountNumberTemp = getIntent().getStringExtra("accountNumber");
        username = getIntent().getStringExtra("username");
    }
    /**
     * Opens a date reporter and tells what type of report to generate.
     * @param view the current view
     */
    public void onCSRClick(View view) {
        Intent intent = new Intent(this, ReportDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "consumerSpending");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    
    public void onBackClick(View view){
        finish();
    }

}
