package edu.gatech.financialapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener{
	private static final String credentials = "USER_CREDENTIALS";
	private SharedPreferences sp;
	private String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences(credentials, MODE_PRIVATE);
        user = sp.getString("username", "admin");
        pass = sp.getString("password", "pass123");
        Button loginBt = (Button)findViewById(R.id.loginBt);
        loginBt.setOnClickListener(this);
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
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.loginBt:
			String username = ((EditText)findViewById(R.id.username)).getText().toString();
			String password = ((EditText)findViewById(R.id.password)).getText().toString();
			if(username.equals(user) && password.equals(pass)){
				new AlertDialog.Builder(this)
			    .setTitle("Success!")
			    .setMessage("Success")
			    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	startActivity(new Intent(getApplicationContext(), MainActivity.class));
			        }
			     })
			     .show();
				//Transition to another screen
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
			
			break;
		default:
			break;
		}
	}

}
