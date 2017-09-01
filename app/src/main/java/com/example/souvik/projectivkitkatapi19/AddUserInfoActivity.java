package com.example.souvik.projectivkitkatapi19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserInfoActivity extends AppCompatActivity {

    EditText name_x;
    Button next_btn_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_info);

        name_x = (EditText) findViewById(R.id.name_x);
        next_btn_x = (Button) findViewById(R.id.next_btn_x);

        next_btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
                SharedPreferences.Editor sh_editor = sh_obj.edit();
                sh_editor.putString("client_name", name_x.getText().toString());
                sh_editor.apply();

                Intent intent_to_add_ice = new Intent(AddUserInfoActivity.this, com.example.souvik.projectivkitkatapi19.AddICEActivity.class);
                startActivity(intent_to_add_ice);
            }
        });
    }
}
