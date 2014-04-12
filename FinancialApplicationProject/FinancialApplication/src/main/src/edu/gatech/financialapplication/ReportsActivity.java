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
    
	private String username, accountNumberTemp;

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
    public void onSCRClick(View view) {
        Intent intent = new Intent(this, ReportDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "spendingCategory");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    
    /**
     * Opens a date reporter and tells what type of report to generate.
     * @param view the current view
     */
    public void onISRClick(View view) {
        Intent intent = new Intent(this, ReportDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "incomeSource");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    
    public void onBackClick(View view){
        finish();
    }

}
