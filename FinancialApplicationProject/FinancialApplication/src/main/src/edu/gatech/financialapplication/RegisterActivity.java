package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity file for register activity.
 * 
 * @author Team 15.
 * 
 */
public class RegisterActivity extends Activity {

    /**
     * New DBHelper db.
     */
    private DBHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceStt) {
        super.onCreate(savedInstanceStt);
        setContentView(R.layout.activity_register);

        dbHelp = new DBHelper(this);
    }

    /**
     * On click for register activity to register a new user.
     * 
     * @param view The view being used.
     */
    public void onClick(View view) {
        String firstname = ((EditText) findViewById(R.id.firstnameText)).getText().toString();
        String lastname = ((EditText) findViewById(R.id.lastnameText)).getText().toString();
        String username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordText))
                .getText().toString();
        String passwordHint = ((EditText) findViewById(R.id.passwordHintText))
                .getText().toString();
        String email = ((EditText) findViewById(R.id.emailText)).getText()
                .toString();

        if (checkFirstname(firstname) && checkLastname(lastname)
                && checkUsername(username) && checkPassword(password)
                && checkPHint(passwordHint) && checkEmail(email)
                && isDuplicate(username, password)) {

            User user = new User(firstname, lastname, username, password,
                    passwordHint, email);
            dbHelp.addUser(user);
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Checks if username and password (the user) is a duplicate or not.
     * 
     * @param username
     *            The username of the user.
     * @param password
     *            The password of the user.
     * @return If the user is a duplicate or not.
     */
    public boolean isDuplicate(String username, String password) {
        boolean result = false;
        if (dbHelp.getUserByUsername(username) != null
                && dbHelp.getUserByUsername(username).getUsername()
                        .equalsIgnoreCase(username)) { // duplicate username
            new AlertDialog.Builder(this)
                    .setTitle("Duplicate")
                    .setMessage("Credential is duplicate!\nPlease check again.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Checks if the first name is blank or not.
     * 
     * @param firstname
     *            The first name being checked.
     * @return If the first name is blank or not.
     */
    private boolean checkFirstname(String firstname) {
        boolean result = false;

        if ("".equals(firstname)) {
            new AlertDialog.Builder(this)
                    .setTitle("First name error.")
                    .setMessage("Sorry, first name can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty
                                }
                            }).show();
        } else if (" ".equals(firstname.substring(0, 1))) {
            new AlertDialog.Builder(this)
                    .setTitle("First name error.")
                    .setMessage("Sorry, first name can't start with a space.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty
                                }
                            }).show();
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Checks if last name is blank or not.
     * 
     * @param lastname
     *            The last name being checked.
     * @return If the last name is blank or not.
     */
    private boolean checkLastname(String lastname) {
        boolean result = false;
        if ("".equals(lastname)) { // empty lastname
            new AlertDialog.Builder(this)
                    .setTitle("Last name error.")
                    .setMessage("Sorry, last name can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else if (" ".equals(lastname.substring(0, 1))) {
            new AlertDialog.Builder(this)
                    .setTitle("Last name error.")
                    .setMessage("Sorry, last name can't start with a space.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty
                                }
                            }).show();
        } else {
            result = true;
        }

        return result;
    }

    /**
     * Checks for valid username.
     * 
     * @param username
     *            The username being checked.
     * @return If the username is valid or not.
     */
    private boolean checkUsername(String username) {
        boolean result = false;
        if ("".equals(username)) { // empty username
            new AlertDialog.Builder(this)
                    .setTitle("Username error.")
                    .setMessage("Sorry, username can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else if ("admin".equals(username)) { // create admin account
            new AlertDialog.Builder(this)
                    .setTitle("Username error")
                    .setMessage("Sorry, cannot create new admin account.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else if (" ".equals(username.substring(0, 1))) {
            new AlertDialog.Builder(this)
                    .setTitle("Username error.")
                    .setMessage("Sorry, username can't start with a space.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty
                                }
                            }).show();
        } else {
            result = true;
        }

        return result;
    }

    /**
     * Checks for valid password.
     * 
     * @param password
     *            The password being checked.
     * @return If the password is valid or not.
     */
    private boolean checkPassword(String password) {
        boolean result = false;
        if ("".equals(password)) { // empty password
            new AlertDialog.Builder(this)
                    .setTitle("Password error")
                    .setMessage("Sorry, password can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else if (" ".equals(password.substring(0, 1))) {
            new AlertDialog.Builder(this)
                    .setTitle("Password error.")
                    .setMessage("Sorry, password can't start with a space.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty
                                }
                            }).show();
        } else if (password.length() < 6) { // short password
            new AlertDialog.Builder(this)
                    .setTitle("Password error")
                    .setMessage(
                            "Sorry, password must be at least 6 characters.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else {
            result = true;
        }

        return result;
    }

    /**
     * Checks if password hint is blank or not.
     * 
     * @param phint
     *            The password hint being checked.
     * @return If the password hint is blank or not.
     */
    private boolean checkPHint(String phint) {
        boolean result = false;
        if ("".equals(phint)) { // empty phint
            new AlertDialog.Builder(this)
                    .setTitle("Last name error.")
                    .setMessage("Sorry, password hint can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else if (" ".equals(phint.substring(0, 1))) {
            new AlertDialog.Builder(this)
                    .setTitle("Password hint error.")
                    .setMessage(
                            "Sorry, password hint can't start with a space.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty
                                }
                            }).show();
        } else {
            result = true;
        }

        return result;
    }

    /**
     * Checks for valid email.
     * 
     * @param email
     *            The email being checked.
     * @return If the email is valid or not.
     */
    private boolean checkEmail(String email) {
        boolean result = false;

        if ("".equals(email)) { // empty email
            new AlertDialog.Builder(this)
                    .setTitle("Email error")
                    .setMessage("Sorry, email can't be blank.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else if (" ".equals(email.substring(0, 1))) {
            new AlertDialog.Builder(this)
                    .setTitle("Email error.")
                    .setMessage("Sorry, email can't start with a space.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty
                                }
                            }).show();
        } else if (!email.matches("[a-zA-Z][^@&]*@[a-zA-Z][^@]*\\.(com|org|net|edu)")) { // invalid email
            new AlertDialog.Builder(this)
                    .setTitle("Information error")
                    .setMessage("Sorry, invalid email address; must end in com, org, net or edu.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                    // Empty method
                                }
                            }).show();
        } else {
            result = true;
        }

        return result;
    }
    
    public void onBackClick(View view){
        finish();
    }
}
