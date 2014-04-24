package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GraphActivity extends Activity {

	private String username, accountNumberTemp;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph);

        accountNumberTemp = getIntent().getStringExtra("accountNumber");
        username = getIntent().getStringExtra("username");
	}

    /**
     * Opens a date reporter and tells what type of report to generate.
     * @param view the current view
     */
    public void onBalanceClick(View view) {
        Intent intent = new Intent(this, GraphDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "balanceVSDate");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Opens a date reporter and tells what type of report to generate.
     * @param view the current view
     */
    public void onDepositClick(View view) {
        Intent intent = new Intent(this, GraphDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "depositVSDate");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Opens a date reporter and tells what type of report to generate.
     * @param view the current view
     */
    public void onWithdrawalClick(View view) {
        Intent intent = new Intent(this, GraphDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "withdrawalVSDate");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Opens a date reporter and tells what type of report to generate.
     * @param view the current view
     */
    public void onCategoryClick(View view) {
        Intent intent = new Intent(this, GraphDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "moneyPerCategory");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    
    public void onTransactionsClick(View view) {
        Intent intent = new Intent(this, GraphDateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "depositWithdrawal");
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBackClick(View view){
        finish();
    }

}
