package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashActivity extends Activity {
 
	//Timer constant
    private static final int SPLASH_TIMER = 3500;
	private MediaPlayer startUpPlayer;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        playStartup();
        
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
    
    /**
     * Plays the startup sound
     */
    private void playStartup() {
    	startUpPlayer = new MediaPlayer();
    	startUpPlayer = MediaPlayer.create(this, R.raw.doorbell);
    	startUpPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    	startUpPlayer.setLooping(false);
    	startUpPlayer.start();
    }
 
}
