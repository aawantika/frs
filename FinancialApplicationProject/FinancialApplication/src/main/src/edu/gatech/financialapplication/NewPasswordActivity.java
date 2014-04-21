package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewPasswordActivity extends Activity {
	
	private static final String seed = "GATECH_CS2340";
	private String username, oldPassword, newPassword, confirmPassword;
	private DBHelper db;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_password);
		db = new DBHelper(this);
		context = this;
		
		username = getIntent().getStringExtra("username");
	}

	public void onChangeClick(View view) {
		oldPassword = ((EditText)findViewById(R.id.oldPassText)).getText().toString();
		newPassword = ((EditText)findViewById(R.id.newPassText)).getText().toString();
		confirmPassword = ((EditText)findViewById(R.id.confirmPassText)).getText().toString();
		
		if (checkOldPassword(oldPassword) && checkPassword(newPassword)
				&& checkPasswords(newPassword, confirmPassword) 
				&& checkOldNewPassword(oldPassword, newPassword)) {
			
			try {
				AESCrypt aes = new AESCrypt(seed);
				String encryptedPassword = aes.encrypt(newPassword);
				db.changePassword(username, encryptedPassword);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			new AlertDialog.Builder(this)
            .setTitle("Password Change.")
            .setMessage("The password was changed successfully!")
            .setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	Intent intent = new Intent(context, ChangePasswordActivity.class);
                			startActivity(intent);
                        }
                    }).show();
		}
	}
	
	private boolean checkPasswords(String password1, String password2) {
		boolean result = true;
		if (!password1.equals(password2)) {
			result = false;
			 new AlertDialog.Builder(this)
             .setTitle("Password input error.")
             .setMessage("Sorry, the passwords entered do not match.")
             .setPositiveButton(android.R.string.ok,
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog,
                                 int which) {
                         }
                     }).show();
		}
		return result;
	}
	
	private boolean checkPassword(String password) {
	        boolean result = true;
	        if ("".equals(password)) {
	        	result = false;
	            new AlertDialog.Builder(this)
	                    .setTitle("Password error")
	                    .setMessage("Sorry, password can't be blank.")
	                    .setPositiveButton(android.R.string.ok,
	                            new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog,
	                                        int which) {
	                                }
	                            }).show();
	        } else if (" ".equals(password.substring(0, 1))) {
	        	result = false;
	            new AlertDialog.Builder(this)
	                    .setTitle("Password error.")
	                    .setMessage("Sorry, password can't start with a space.")
	                    .setPositiveButton(android.R.string.ok,
	                            new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog,
	                                        int which) {
	                                }
	                            }).show();
	        } else if (password.length() < 6) {
	        	result = false;
	            new AlertDialog.Builder(this)
	                    .setTitle("Password error")
	                    .setMessage(
	                            "Sorry, password must be at least 6 characters.")
	                    .setPositiveButton(android.R.string.ok,
	                            new DialogInterface.OnClickListener() {
	                                public void onClick(DialogInterface dialog,
	                                        int which) {
	                                }
	                            }).show();
	        }
	        return result;
	    }

	private boolean checkOldPassword(String password) {
		boolean result = true;
		User userDB = db.getUserByUsername(username);
		
		if (!userDB.getPassword().equals(password)) {
			result = false;
			new AlertDialog.Builder(this)
            .setTitle("Old Password Error.")
            .setMessage("The old password you entered is incorrect.")
            .setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                int which) {
                        }
                    }).show();
		}
		return result;
	}

	private boolean checkOldNewPassword(String oldPass, String newPass) {
		boolean result = true;
		
		if (oldPass.equals(newPass)) {
			result = false;
			new AlertDialog.Builder(this)
            .setTitle("Password.")
            .setMessage("The new password cannot match the old password.")
            .setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                int which) {
                        }
                    }).show();
		}
		return result;
	}
}
