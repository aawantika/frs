package edu.gatech.financialapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	File accounts;
	BufferedWriter writer;
	private ArrayList<String> userArray;

    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        userArray = new ArrayList<String>();

    	setContentView(R.layout.activity_register);
    	File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "accounts");
    	if (!directory.exists())
    		directory.mkdirs();
	    	
    	accounts = new File(directory, "accounts.txt");
    	try {
    		if(!accounts.createNewFile()) {
    			accounts.createNewFile();
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadArray();
    }
    
    public void loadArray() {
		String filePath = Environment.getExternalStorageDirectory() + File.separator + "accounts" + File.separator + "accounts.txt";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line = "";
			while((line = in.readLine()) != null) {
				userArray.add(line);
			}
//			boolean done = false;
//			while (!done) {
//				userArray.add(in.readLine());
//				if(userArray.get(userArray.size() - 1) == null) {
//					userArray.remove(userArray.size()-1);
//					done = true;
//				}
//			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public boolean isDuplicate(String username, String password) {
    	for(String u : userArray){
    		u = u.split("&")[0];
    		if (u.equalsIgnoreCase(username))
    			return true;
    	}
    	return false;
    }
    
    public void onClick(View view) {
    	String username = ((EditText)findViewById(R.id.userText)).getText().toString();
		String password = ((EditText)findViewById(R.id.passText)).getText().toString();
		try {
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
				BufferedWriter writer = new BufferedWriter(new FileWriter(accounts, true));
				writer.write(username + "&"+password + "\n");
				writer.close();
				Intent intent = new Intent(this, WelcomeActivity.class);
		    	startActivity(intent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
