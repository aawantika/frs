package edu.gatech.financialapplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	File accounts;
	BufferedWriter writer;
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_register);
    	File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "accounts");
    	directory.mkdirs();
    	accounts = new File(directory, "accounts.txt");
    	try {
    		if(!accounts.createNewFile()) {
    			accounts.createNewFile();
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    public void onClick(View view) {
    	String username = ((EditText)findViewById(R.id.userText)).getText().toString();
		String password = ((EditText)findViewById(R.id.passText)).getText().toString();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(accounts, true));
			writer.write(username + password + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Intent intent = new Intent(this, WelcomeActivity.class);
    	startActivity(intent);
    }
}
