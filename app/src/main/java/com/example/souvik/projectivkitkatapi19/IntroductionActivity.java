package com.example.souvik.projectivkitkatapi19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntroductionActivity extends AppCompatActivity {

    TextView intro;
    Button next_btn_y;
    String intro_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        intro = (TextView) findViewById(R.id.intro);
        next_btn_y = (Button) findViewById(R.id.next_btn_y);

        intro_str = getResources().getString(R.string.app_name) + " " + getResources().getString(R.string.intro_msg_pt_1) + " " + getResources().getString(R.string.distress_btn) + " " + getResources().getString(R.string.intro_msg_pt_2);
        intro.setText(intro_str);

        next_btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_to_add_user_info_activity = new Intent(IntroductionActivity.this, AddUserInfoActivity.class);
                startActivity(intent_to_add_user_info_activity);
            }
        });
    }
}
