package edu.gatech.financialapplication;

import android.app.Activity;
import android.os.Bundle;
 
public class MainActivity extends Activity {
	
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	String username = getIntent().getStringExtra("username");
    	String password = getIntent().getStringExtra("password");
    	String firstname = getIntent().getStringExtra("firstname");
    	String lastname = getIntent().getStringExtra("lastname");
    	String email = getIntent().getStringExtra("email");
//    	User user = new User(firstname, lastname, username, password, email);
//    	Log.d("Account: ", WelcomeActivity.db.getAccount(user).toString());
    	//1862991617
    	//4
    }
//    454810184
}
