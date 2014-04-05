package edu.gatech.financialapplication;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity file for forgot password.
 * 
 * @author Team 15
 */
public class ForgotPasswordActivity extends Activity {
    /**
     * New DBHelper database.
     */
    private DBHelper database;
    private String username;

    @Override
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_forgot_password);
        database = new DBHelper(this);
    }

    private boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }

    private boolean checkNetwork() {
        boolean result = true;
        if (!isNetworkAvailable(this)) {
            new AlertDialog.Builder(this)
                    .setTitle("Internet connection")
                    .setMessage(
                            "Dear customer, please turn on wifi or mobile data to proceed.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog,
                                        final int which) {
                                }
                            }).show();
            result = false;
        }
        return result;
    }

    /**
     * On click for forgot password to send email for forgotten password.
     * 
     * @param view
     *            The view being used.
     */
    @SuppressWarnings("unchecked")
    public void onClick(final View view) {
        if (checkNetwork()) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            username = ((EditText) findViewById(R.id.editTextUser)).getText()
                    .toString();
            User userDb = database.getUserByUsername(username);
            if (username.equals("admin")) {
                new AlertDialog.Builder(this)
                        .setTitle("Username error")
                        .setMessage("Cannot email the admin password.")
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            final DialogInterface dialog,
                                            final int which) {
                                    }
                                }).show();
            } else if (userDb == null) {
                new AlertDialog.Builder(this)
                        .setTitle("Username error")
                        .setMessage("Username doesn't exist in database.")
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            final DialogInterface dialog,
                                            final int which) {
                                    }
                                }).show();
            } else {
                final User user = database.getUserByUsername(username);
                final String email = user.getEmail();
                final List<String> toEmailList = Arrays.asList(email);
                final String password = user.getPassword();
                final String emailBody = "Dear Customer, your forgotten password is: "
                        + password;

                new SendMailTask(ForgotPasswordActivity.this).execute(
                        toEmailList, emailBody);
                startActivity(intent);
            }
        }
    }
}
