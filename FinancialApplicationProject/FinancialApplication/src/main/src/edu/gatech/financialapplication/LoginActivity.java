package edu.gatech.financialapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * The Activity that enables users to enter their credentials and login.
 * @author Team 15
 */
public class LoginActivity extends Activity implements OnClickListener, LoginResultReceiver.Receiver {
    /**
     * The LoginResultReceiver takes in login submissions.
     */
    private LoginResultReceiver receiver;
    /**
     * The Context to be used by the activity.
     */
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ctx = this;
        Button loginBt = (Button) findViewById(R.id.loginBt);
        loginBt.setOnClickListener(this);
        receiver = new LoginResultReceiver(new Handler());
        receiver.setmReceiver(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.loginBt:
        	String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                Intent intent = new Intent(this, LoginChecker.class);
                intent.putExtra("receiverTag", receiver);
                //CHECKSTYLE:OFF
                intent.putExtra("username", username); //string necessary
                intent.putExtra("password", password); //string necessary
                //CHECKSTYLE:ON
                startService(intent);
                break;
            default:
                break;
        }
    }
    
    /**
     * The click for the ForgotPassword Activity that will switch activities.
     * @param view The view being used
     */
    public void onForgotPasswordClick(View view) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, final Bundle resultBundle) {
        boolean correcto = resultBundle.getBoolean("ServiceTag");
        if (correcto) {
            new AlertDialog.Builder(this)
                .setTitle("Success!")
                .setMessage("Success")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                        DBHelper db = new DBHelper(ctx);
                        //CHECKSTYLE:OFF
                        User user = new User(resultBundle.getString("firstname"), //string necessary
                            resultBundle.getString("lastname"), resultBundle.getString("username"), //strings necessary
                            //CHECKSTYLE:ON
                            resultBundle.getString("password"), "");
                        if (db.hasAccount(user)) {
                            Intent intent = new Intent(ctx, TransactionActivity.class);
                            intent.putExtra("username", resultBundle.getString("username"));
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(ctx, AccountCreationActivity.class);
                            intent.putExtra("username", resultBundle.getString("username"));
                            intent.putExtra("password", resultBundle.getString("password"));
                            intent.putExtra("firstname", resultBundle.getString("firstname"));
                            intent.putExtra("lastname", resultBundle.getString("lastname"));
                            startActivity(intent);
                        }
                    }
                }  )
                .show();
        } else {
            new AlertDialog.Builder(this)
               .setTitle("Wrong crendential!")
                .setMessage("Please check your username or password.")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { 
                    }
                }   )
                .show();
        }    
    }

}
