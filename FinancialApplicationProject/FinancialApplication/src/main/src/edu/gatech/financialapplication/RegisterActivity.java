package edu.gatech.financialapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_register);
    	Log.d("A", WelcomeActivity.db.getUserDetails("tika").getUsername());
    }

    
    public boolean isDuplicate(String username, String password) {
    	if (WelcomeActivity.db.getUserDetailsByUsername(username) != null &&
    			WelcomeActivity.db.getUserDetailsByUsername(username).getUsername().equalsIgnoreCase(username))
    		return true;
    	return false;
    }
    
    public void onClick(View view) {
    	String username = ((EditText)findViewById(R.id.userText)).getText().toString();
		String password = ((EditText)findViewById(R.id.passText)).getText().toString();
		String firstname = ((EditText)findViewById(R.id.firstText)).getText().toString();
		String lastname = ((EditText)findViewById(R.id.lastText)).getText().toString();
		String email = ((EditText)findViewById(R.id.emailText)).getText().toString();
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
			WelcomeActivity.db.addUser(user);
			Intent intent = new Intent(this, WelcomeActivity.class);
	    	startActivity(intent);
		}
    }
}
