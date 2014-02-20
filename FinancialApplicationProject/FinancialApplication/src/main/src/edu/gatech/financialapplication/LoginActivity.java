package edu.gatech.financialapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener, LoginResultReceiver.Receiver{
	private LoginResultReceiver receiver;
	private ArrayList<String> userArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBt = (Button)findViewById(R.id.loginBt);
        loginBt.setOnClickListener(this);
        receiver = new LoginResultReceiver(new Handler());
        receiver.setReceiver(this);
        userArray = new ArrayList<String>();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View v) {
		loadArray();
		String user = ((EditText)findViewById(R.id.username)).getText().toString();
		String pass = ((EditText)findViewById(R.id.password)).getText().toString();
		String checker = user + pass;
		Boolean found = false;
		for (String log : userArray) {
			if (log.equals(checker)) {
				Intent intent = new Intent(this, LoginChecker.class);
				intent.putExtra("receiverTag", receiver);
				intent.putExtra("username", "admin");
				intent.putExtra("password", "pass123");
				startService(intent);
				found = true;
			}
		}
		if (found == false) {
			Intent intent = new Intent(this, LoginChecker.class);
			intent.putExtra("receiverTag",  receiver);
			intent.putExtra("username", "wrong");
			intent.putExtra("password", "awioejf");
			startService(intent);
		}/**
		switch(v.getId()){
		case R.id.loginBt:
			String username = ((EditText)findViewById(R.id.username)).getText().toString();
			String password = ((EditText)findViewById(R.id.password)).getText().toString();
			Intent intent = new Intent(this, LoginChecker.class);
			intent.putExtra("receiverTag", receiver);
			intent.putExtra("username", username);
			intent.putExtra("password", password);
			startService(intent);
			break;
		default:
			break;
		}
		*/
	}
	
	public void loadArray() {
		String filePath = Environment.getExternalStorageDirectory() + File.separator + "accounts" + File.separator + "accounts.txt";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			boolean done = false;
			while (!done) {
				userArray.add(in.readLine());
				if(userArray.get(userArray.size() - 1) == null) {
					userArray.remove(userArray.size()-1);
					done = true;
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onReceiveResult(int resultCode, Bundle resultBundle) {
		boolean correcto = resultBundle.getBoolean("ServiceTag");
		if(correcto){
			new AlertDialog.Builder(this)
		    .setTitle("Success!")
		    .setMessage("Success")
		    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        	startActivity(new Intent(getApplicationContext(), MainActivity.class));
		        }
		     })
		     .show();
		}else{
			new AlertDialog.Builder(this)
		    .setTitle("Wrong crendential!")
		    .setMessage("Please check your username or password.")
		    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        }
		     })
		     .show();
		}
		
	}

}
