package com.example.souvik.projectivkitkatapi19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditICEActivity extends AppCompatActivity {

    EditText ice_1x;
    Button edit_ice_done_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ice);

        ice_1x = (EditText) findViewById(R.id.ice_1x);
        edit_ice_done_btn = (Button) findViewById(R.id.edit_ice_done_btn);

        SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
        ice_1x.setText(sh_obj.getString("client_ice_1", ""));

        edit_ice_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
                SharedPreferences.Editor sh_editor = sh_obj.edit();
                sh_editor.putString("client_ice_1", ice_1x.getText().toString());
                sh_editor.apply();

                Intent intent_to_main_activity = new Intent(EditICEActivity.this, MainActivity.class);
                startActivity(intent_to_main_activity);
            }
        });
    }
}
