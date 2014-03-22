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
		return (db.getUserByUsername(username) != null && 
				db.getUserByUsername(username).getUsername().equalsIgnoreCase(username));
	}

	public void onClick(View view) {
		String username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
		String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();
		String firstname = ((EditText) findViewById(R.id.firstnameText)).getText().toString();
		String lastname = ((EditText) findViewById(R.id.lastnameText)).getText().toString();
		String email = ((EditText) findViewById(R.id.emailText)).getText().toString();
		int emailLength = email.length();

		if (firstname.equals("")) { //empty firstname
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, first name can't be blank.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (lastname.equals("")) { //empty lastname
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, last name can't be blank.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (username.equals("")) { //empty username
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, username can't be blank.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (username.equals("admin")) { //create admin account 
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, cannot create new admin account.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (password.equals("")) { //empty password
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, password can't be blank.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (password.length() < 6) { //short password
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage(
							"Sorry, password must be at least 6 characters.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (email.equals("")) { //empty email
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, email can't be blank.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (!email.contains("@")
				|| !email.substring(emailLength - 4, emailLength - 3).equals(
						".")) { //invalid email
			new AlertDialog.Builder(this)
					.setTitle("Information error")
					.setMessage("Sorry, invalid email address.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else if (isDuplicate(username, password)) { //duplicate username
			new AlertDialog.Builder(this)
					.setTitle("Duplicate")
					.setMessage("Credential is duplicate!\nPlease check again.")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else { //add user
			User user = new User(firstname, lastname, username, password, email);
			db.addUser(user);
			Intent intent = new Intent(this, WelcomeActivity.class);
			startActivity(intent);
		}
		
	}
}
