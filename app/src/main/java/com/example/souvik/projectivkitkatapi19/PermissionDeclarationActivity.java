package com.example.souvik.projectivkitkatapi19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PermissionDeclarationActivity extends AppCompatActivity {

    TextView declare_text;
    Button okay_btn;
    String declare_text_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_declaration);

        declare_text = (TextView) findViewById(R.id.declare_text);
        okay_btn = (Button) findViewById(R.id.okay_btn);

        declare_text_str = getResources().getString(R.string.declare_text_pt_1) + " " + getResources().getString(R.string.app_name) + " " + getResources().getString(R.string.declare_text_pt_2);
        declare_text.setText(declare_text_str);

        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_to_main_activity = new Intent(PermissionDeclarationActivity.this, MainActivity.class);
                startActivity(intent_to_main_activity);
            }
        });
    }
}
