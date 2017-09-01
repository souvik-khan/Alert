package com.example.souvik.projectivkitkatapi19;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    int splash_screen_timeout = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

/*        Thread thread_splash_screen = new Thread() {

            @Override
            public void run() {
                try {

                    sleep(splash_screen_timeout);
                    Intent intent_to_sign_up_activity = new Intent(SplashScreenActivity.this, SignUpActivity.class);
                    startActivity(intent_to_sign_up_activity);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread_splash_screen.start();*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent_to_introduction_activity = new Intent(SplashScreenActivity.this, IntroductionActivity.class);
                startActivity(intent_to_introduction_activity);
                finish();
            }
        }, splash_screen_timeout);
    }
}
