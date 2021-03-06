package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity file for forgot password.
 * 
 * @author Team 15
 */
public class ForgotPasswordActivity extends Activity {
 
    private DBHelper database;
    private String username;
    private MediaPlayer errorPlayer;
    
    @Override
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_forgot_password);
        database = new DBHelper(this);
    }

    /**
     * On click for forgot password to send email for forgotten password.
     * 
     * @param view The view being used.
     */
    @SuppressWarnings("unchecked")
    public void onClick(final View view) {
        if (checkNetwork()) {
            username = ((EditText) findViewById(R.id.editTextUser)).getText().toString();
            User userDb = database.getUserByUsername(username);
            
            if (username.equals("admin")) {
            	playError();
                new AlertDialog.Builder(this)
                        .setTitle("Username error")
                        .setMessage("Cannot email the admin password.")
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            final DialogInterface dialog,
                                            final int which) {
                                    }
                                }).show();
            } else if (userDb == null) {
            	playError();
                new AlertDialog.Builder(this)
                        .setTitle("Username error")
                        .setMessage("Username doesn't exist in database.")
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            final DialogInterface dialog,
                                            final int which) {
                                    }
                                }).show();
            } else {
                User user = database.getUserByUsername(username);
                System.out.println("USER PRINTED HERE: " + user);
                List<String> toEmailList = new ArrayList<String>();
                toEmailList.add(user.getEmail());
                String password = user.getPassword();
                String emailBody = "Dear Customer, your forgotten password is: "
                        + password;

                new SendMailTask(ForgotPasswordActivity.this).execute(toEmailList, emailBody);
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
            }
        }
    }
    

    private boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }

    private boolean checkNetwork() {
        boolean result = true;
        if (!isNetworkAvailable(this)) {
        	playError();
            new AlertDialog.Builder(this)
                    .setTitle("Internet connection")
                    .setMessage("Dear customer, please turn on wifi or mobile data to proceed.")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog,
                                        final int which) {
                                }
                            }).show();
            result = false;
        }
        return result;
    }
    
    /**
     * On click, sends users back
     * 
     * @param view the view being used
     */
    public void onBackClick(View view) {
    	finish();
    }
    
    /**
     * Plays an error sound
     */
    private void playError() {
    	errorPlayer = new MediaPlayer();
    	errorPlayer = MediaPlayer.create(this, R.raw.error);
    	errorPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    	errorPlayer.setLooping(false);
    	errorPlayer.start();
    }
}
