package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Activity file for withdrawal activity.
 * 
 * @author Team 15.
 */
public class WithdrawalActivity extends Activity {
    
    private Spinner spinner;
    private String accountNumber;
    private String username;
    private String date;
    private String reason;
    private String category;
    private float amount;
    private DBHelper db;
    private DateGrabber dg;
    private MediaPlayer errorPlayer;

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

    /**
     * SpinnerOnItemSelected class.
     * @author Team 15.
     *
     */
    public class SpinnerOnItemSelectedListener implements OnItemSelectedListener {

        /**
         * Changes the option to the one selected.
         * 
         * @param parent The AdapterView being used.
         * @param view The view being used.
         * @param pos The position of the item selected.
         * @param id The id of the item selected.
         */
        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                long id) {
            category = parent.getItemAtPosition(pos).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    /**
     * On click for withdrawal activity to make a transaction.
     * @param view The view being used.
     */
    public void onClick(View view) {
        date = dg.createDate();
        amount = Float.parseFloat(((EditText) findViewById(R.id.editTextAmount)).getText().toString());
        reason = ((EditText) findViewById(R.id.editTextReason)).getText().toString();

        if (amount <= 0) { // empty amount
        	playError();
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage("Sorry, invalid amount. \nAmount must be greater than 0.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    //empty method
                                }
                            }).show();
        } else if (reason.equals("")) { // empty reason
        	playError();
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage("Sorry, reason can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    //empty method
                                }
                            }).show();
        } else {
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
    
    public void onBackClick(View view){
        finish();
    }
    
    /**
     * Plays an error sound
     */
    private void playError() {
    	errorPlayer = new MediaPlayer();
    	errorPlayer = MediaPlayer.create(this, R.raw.error);
    	errorPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    	errorPlayer.setLooping(false);
    	errorPlayer.start();
    }
}