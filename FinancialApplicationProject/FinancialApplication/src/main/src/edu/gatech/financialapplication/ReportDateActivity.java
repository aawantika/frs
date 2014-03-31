package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity file for report date activity.
 * @author Team 15
 */
public class ReportDateActivity extends Activity {
    
    /**
     * New string name.
     */
    private String username;
    /**
     * New string account number temp.
     */
    private String accountNumberTemp;
    /**
     * New string type.
     */
    private String type;
    
    /**
     * New strng monthFrom.
     */
    private String monthFrom;
    /**
     * New string dayFrom.
     */
    private String dayFrom;
    /**
     * New string yearFrom.
     */
    private String yearFrom;
    /**
     * New string monthTo.
     */
    private String monthTo;
    /**
     * New string dayTo.
     */
    private String dayTo;
    /**
     * New string yearTo.
     */
    private String yearTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_date);

        accountNumberTemp = getIntent().getStringExtra("accountNumber");
        username = getIntent().getStringExtra("username");
        type = getIntent().getStringExtra("type");
        monthFrom = ((EditText) findViewById(R.id.monthFrom)).getText().toString();
        dayFrom = ((EditText) findViewById(R.id.dayFrom)).getText().toString();
        yearFrom = ((EditText) findViewById(R.id.yearFrom)).getText().toString();
        monthTo = ((EditText) findViewById(R.id.monthTo)).getText().toString();
        dayTo = ((EditText) findViewById(R.id.dayTo)).getText().toString();
        yearTo = ((EditText) findViewById(R.id.yearTo)).getText().toString();
    }

    /**
     * The on click for the date button.
     * 
     * @param view The view being used.
     */
    public void onDateClick(View view) {
        Intent intent = null;
        if (type.equals("consumerSpending")) {
            intent = new Intent(this, ConsumerSpendingActivity.class);
        }

        Bundle bundle = new Bundle();
        bundle.putString("accountNumber", accountNumberTemp);
        bundle.putString("username", username);
        bundle.putString("monthFrom", monthFrom);
        bundle.putString("dayFrom", dayFrom);
        bundle.putString("yearFrom", yearFrom);
        bundle.putString("monthTo", monthTo);
        bundle.putString("dayTo", dayTo);
        bundle.putString("yearTo", yearTo);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
