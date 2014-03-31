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
     * New string username.
     */
    private String username;
    /**
     * New DBHelper db.
     */
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        db = new DBHelper(this);
    }

    /**
     * On click for forgot password to send email for forgotten password.
     * @param view The view being used.
     */
    @SuppressWarnings("unchecked")
    public void onClick(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        username = ((EditText) findViewById(R.id.editTextUser)).getText()
                .toString();
        if (!username.equals("")) {
            User user = db.getUserByUsername(username);
            String email = user.getEmail();
            List<String> toEmailList = Arrays.asList(email);
            String password = user.getPassword();
            String emailBody = "Dear Customer, your forgotten password is: "
                    + password;

            new SendMailTask(ForgotPasswordActivity.this).execute(toEmailList,
                    emailBody);
        }
        startActivity(intent);
    }
}
