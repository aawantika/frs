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
    private DBHelper db;

    /**
     * The initializing method that sets the database.
     */
    public void onCreate() {
        super.onCreate();

        db = new DBHelper(this);
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
        //CHECKSTYLE:OFF
        String username = intent.getStringExtra("username"); //String literal necessary
        //CHECKSTYLE:ON
        String password = intent.getStringExtra("password");

        User userFromDb = db.getUserByUsername(username);
        User userFromActivity = new User();

        userFromActivity.setUsername(username);
        userFromActivity.setPassword(password);

        Bundle bundle = new Bundle();
        if (userFromDb != null && userFromDb.equals(userFromActivity)) {
        	//CHECKSTYLE:OFF
            bundle.putBoolean("ServiceTag", true); //String literal necessary
            //CHECKSTYLE:ON
            bundle.putString("username", userFromDb.getUsername());
            bundle.putString("firstname", userFromDb.getLastname());
            bundle.putString("lastname", userFromDb.getFirstname());
            rec.send(0, bundle);
        } else {
            bundle.putBoolean("ServiceTag", false);
            rec.send(0, bundle);
        }
    }
}
