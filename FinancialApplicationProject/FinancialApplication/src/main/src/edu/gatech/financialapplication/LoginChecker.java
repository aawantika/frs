package edu.gatech.financialapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

/**
 * Class that checks for existing credentials on the login page.
 * @author Team 15
 */
public class LoginChecker extends IntentService {
	/**
	 * Receives user input.
	 */
    private ResultReceiver rec;
    /**
     * The database to search for data in.
     */
    private DBHelper dbHelp;

    /**
     * The initializing method that sets the database.
     */
    public void onCreate() {
        super.onCreate();

        dbHelp = new DBHelper(this);
    }

    /**
     * The default constructor for LoginChecker.
     */
    public LoginChecker() {
        super("LoginChecker");
        setIntentRedelivery(false);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        rec = intent.getParcelableExtra("receiverTag");
        String username = intent.getStringExtra("username"); 
        String password = intent.getStringExtra("password");

        User userFromDb = dbHelp.getUserByUsername(username);
        User userFromActivity = new User();

        userFromActivity.setUsername(username);
        userFromActivity.setPassword(password);

        Bundle bundle = new Bundle();
        if (userFromDb != null && 
                userFromDb.getUsername().equals(userFromActivity.getUsername())) {
            bundle.putString("firstname", userFromDb.getLastname());
            bundle.putString("lastname", userFromDb.getFirstname());
            bundle.putString("username", userFromDb.getUsername());
            bundle.putString("password", userFromDb.getPassword());
            bundle.putString("phint", userFromDb.getPasswordHint());
            rec.send(0, bundle);
        } else {
            bundle.putBoolean("ServiceTag", false);
            rec.send(0, bundle);
        }
    }
}
