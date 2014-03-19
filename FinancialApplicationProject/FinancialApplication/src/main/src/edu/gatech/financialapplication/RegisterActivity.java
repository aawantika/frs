package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	private DBHelper db;

    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_register);
    	
    	db = new DBHelper(this);
    }

    
    public boolean isDuplicate(String username, String password) {
    	if (db.getUserByUsername(username) != null &&
    			db.getUserByUsername(username).getUsername().equalsIgnoreCase(username))
    		return true;
    	return false;
    }
    
    public void onClick(View view) {
    	String username = ((EditText)findViewById(R.id.usernameText)).getText().toString();
		String password = ((EditText)findViewById(R.id.passwordText)).getText().toString();
		String firstname = ((EditText)findViewById(R.id.firstnameText)).getText().toString();
		String lastname = ((EditText)findViewById(R.id.lastnameText)).getText().toString();
		String email = ((EditText)findViewById(R.id.emailText)).getText().toString();
		if (!username.equals("") && !password.equals("") && !firstname.equals("") && !lastname.equals("") && !email.equals("")) {
			if (isDuplicate(username, password)) {
				new AlertDialog.Builder(this)
			    .setTitle("Duplicate")
			    .setMessage("Credential is duplicate!\nPlease check again.")
			    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        }
			     })
			     .show();
			}else{
				User user = new User(firstname, lastname, username, password, email);
				db.addUser(user);
				Intent intent = new Intent(this, WelcomeActivity.class);
		    	startActivity(intent);
			}
		}
    }
}
