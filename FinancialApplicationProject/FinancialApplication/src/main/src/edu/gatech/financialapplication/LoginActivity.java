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
import android.widget.EditText;
import android.widget.TextView;

/**
 * The Activity that enables users to enter their credentials and login.
 * 
 * @author Team 15
 */
public class LoginActivity extends Activity implements
        LoginResultReceiver.Receiver {

    private LoginResultReceiver receiver;
    private Context ctx;
    private String usernameInput, passwordInput;
    private String firstnameDB, lastnameDB, usernameDB, passwordDB, phintDB;
    private DBHelper db;
    private User user;
    private boolean userInDatabase;

    @Override
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);
        ctx = this;
        receiver = new LoginResultReceiver(new Handler());
        receiver.setmReceiver(this);
        
//        CurrencyConverter c = new CurrencyConverter();
//        c.execute();
//        if (c.getExchangeValue("USD") != null)
//        	Toast.makeText(getApplicationContext(), "USD is " + c.getExchangeValue("USD") , Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {
    	usernameInput = ((EditText) findViewById(R.id.username)).getText().toString();
        passwordInput = ((EditText) findViewById(R.id.password)).getText().toString();
        
        Intent intent = new Intent(this, LoginChecker.class);
        intent.putExtra("receiverTag", receiver);
        intent.putExtra("username", usernameInput);
        intent.putExtra("password", passwordInput);
        startService(intent);
    }

    /**
     * The click for the ForgotPassword Activity that will switch activities.
     * 
     * @param view The view being used
     */
    public void onForgotPasswordClick(final View view) {
        final Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReceiveResult(final int resultCode, final Bundle resultBundle) {
        firstnameDB = resultBundle.getString("firstname");
        lastnameDB = resultBundle.getString("lastname");
        usernameDB = resultBundle.getString("username");
        passwordDB = resultBundle.getString("password");
        phintDB = resultBundle.getString("phint");
        user = db.getUserByUsername(usernameDB);

        if (user != null) {
            userInDatabase = db.hasAccount(user);
            if (user != null && user.getUsername().equals(usernameInput)
                    && user.getPassword().equals(passwordInput)) {
                new AlertDialog.Builder(this)
                        .setTitle("Success!")
                        .setMessage("Successful login.")
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            final DialogInterface dialog,
                                            final int which) {
                                    	if (usernameInput.equals("admin") 
                                    			&& passwordInput.equals("pass123")) {
                                    		 Intent intent = new Intent(ctx, ChangePasswordActivity.class);
                                             startActivity(intent);
                                    	} else if (userInDatabase) {
                                            Intent intent = new Intent(ctx, TransactionActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("username", usernameDB);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        } else {
                                            Intent intent = new Intent(ctx, AccountCreationActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("username", usernameDB);
                                            bundle.putString("password", passwordDB);
                                            bundle.putString("firstname", firstnameDB);
                                            bundle.putString("lastname", lastnameDB);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    }
                                }).show();
            } else if (user != null && user.getUsername().equals(usernameInput)
                    && !user.getPassword().equals(passwordInput)) {
                new AlertDialog.Builder(this)
                        .setTitle("Wrong Password")
                        .setMessage("Check password hint for hint.")
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            final DialogInterface dialog,
                                            final int which) {
                                    }
                                }).show();
                TextView pHintText = (TextView) findViewById(R.id.passwordHintText);
                pHintText.setText("Password Hint: " + phintDB);
            }
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Wrong crendential!")
                    .setMessage("Please check your username or password.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog,
                                        final int which) {
                                }
                            }).show();
        }
    }
    
    public void onCancelClick(View view){
        finish();
    }
}