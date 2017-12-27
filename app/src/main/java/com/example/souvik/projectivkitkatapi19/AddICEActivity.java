package com.example.souvik.projectivkitkatapi19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddICEActivity extends AppCompatActivity {

    EditText ice_1;
    Button add_ice_done_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ice);

        ice_1 = (EditText) findViewById(R.id.ice_1);
        add_ice_done_btn = (Button) findViewById(R.id.add_ice_done_btn);

        add_ice_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
                SharedPreferences.Editor sh_editor = sh_obj.edit();
                sh_editor.putString("client_ice_1", ice_1.getText().toString());
                sh_editor.putString("app_status", "1");
                sh_editor.apply();

                Intent intent_to_permission_declaration_activity = new Intent(AddICEActivity.this, PermissionDeclarationActivity.class);
                startActivity(intent_to_permission_declaration_activity);
            }
        });
    }
}

