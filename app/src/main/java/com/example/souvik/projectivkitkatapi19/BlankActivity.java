package com.example.souvik.projectivkitkatapi19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BlankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
        String get_status = sh_obj.getString("app_status", "0");
        if (get_status.contains("0")) {
            Intent intent_to_splash_screen = new Intent(this, SplashScreenActivity.class);
            startActivity(intent_to_splash_screen);
        }
        else {
            Intent intent_to_main_activity = new Intent(this, MainActivity.class);
            startActivity(intent_to_main_activity);
        }
    }
}
