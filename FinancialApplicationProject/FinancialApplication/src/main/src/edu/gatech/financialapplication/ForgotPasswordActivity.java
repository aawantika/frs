package edu.gatech.financialapplication;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_forgot_password);
        database = new DBHelper(this);
    }

    /**
     * On click for forgot password to send email for forgotten password.
     * @param view The view being used.
     */
    @SuppressWarnings("unchecked")
    public void onClick(final View view) {
    	String username;
        final Intent intent = new Intent(this, WelcomeActivity.class);
        username = ((EditText) findViewById(R.id.editTextUser)).getText()
                .toString();
        if (!"".equals(username)) {
            final User user = database.getUserByUsername(username);
            final String email = user.getEmail();
            final List<String> toEmailList = Arrays.asList(email);
            final String password = user.getPassword();
            final String emailBody = "Dear Customer, your forgotten password is: "
                    + password;

            new SendMailTask(ForgotPasswordActivity.this).execute(toEmailList,
                    emailBody);
        }
        startActivity(intent);
    }
}
