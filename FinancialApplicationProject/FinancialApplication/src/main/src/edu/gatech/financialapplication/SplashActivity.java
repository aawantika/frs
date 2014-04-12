package edu.gatech.financialapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashActivity extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIMER = 2000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(i);
 
                finish();
            }
        }, SPLASH_TIMER);
    }
 
}
