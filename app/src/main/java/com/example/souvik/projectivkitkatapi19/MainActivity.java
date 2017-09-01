package com.example.souvik.projectivkitkatapi19;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar_main_activity;
    Button distress_btn;
    EditText taxi_no, fast_comment;
    TextView msg_status;
    String address_string;
    LocationListenerCustom loc_lstnr;
    Location location_copy;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_main_activity = (Toolbar) findViewById(R.id.toolbar_main_activity);
        distress_btn = (Button) findViewById(R.id.distress_btn);
        taxi_no = (EditText) findViewById(R.id.taxi_no);
        fast_comment = (EditText) findViewById(R.id.fast_comment);
        msg_status = (TextView) findViewById(R.id.msg_status);
        requestQueue = Volley.newRequestQueue(this);
        loc_lstnr = new LocationListenerCustom(getApplicationContext());

        setSupportActionBar(toolbar_main_activity);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
            }
        } else {
            location_copy = loc_lstnr.requestLocation();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 20);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 20);
            }
        }
        btnConfig();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    location_copy = loc_lstnr.requestLocation();
                    Toast.makeText(this, "Location access granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Location access denied", Toast.LENGTH_SHORT).show();
                }
                return;

            case 20:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Sending SMS permission granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Sending SMS permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    private void btnConfig() {

        distress_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                location_copy = loc_lstnr.requestLocation();
                Log.e("Latitude, Longitude", "" + loc_lstnr.location_string);


                JsonObjectRequest json_req = new JsonObjectRequest("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + loc_lstnr.location_string_json + "&key=AIzaSyCvSP4pz3aJqhtWFE1_TgEJ9rkUmrqzy5M", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            address_string = response.getJSONArray("results").getJSONObject(0).getString("formatted_address");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(json_req);
                Log.e("Address", "" + address_string);


                SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
                String name = sh_obj.getString("client_name", "default2");
                String phone_no = sh_obj.getString("client_ice_1", "9007XXXXX1");

                String distress_msg = name + " " + getResources().getString(R.string.distress_msg_pt_1) + "\n" + loc_lstnr.location_string + "\n" + address_string;
                String taxi_no_str = taxi_no.getText().toString();
                String fast_comment_str = fast_comment.getText().toString();
                if (taxi_no_str != null && !taxi_no_str.isEmpty())
                    distress_msg = distress_msg + "\nTaxi no " + taxi_no_str;
                if (fast_comment_str != null && !fast_comment_str.isEmpty())
                    distress_msg = distress_msg + "\nShe said '" + fast_comment_str + "'";

                if (address_string != null)
                {
                    try {
                        SmsManager sms_mngr = SmsManager.getDefault();
                        sms_mngr.sendTextMessage(phone_no, null, distress_msg, null, null);
                        msg_status.setText("Distress message sent");
                        msg_status.setTextColor(getResources().getColor(R.color.green));
                    } catch (Exception e) {
                        msg_status.setText("Sending message failed");
                        msg_status.setTextColor(getResources().getColor(R.color.red));
                        Log.e("Sending message failed", "Valid phone number missing");
                    }
                }
                else {
                    msg_status.setText("Sending message failed");
                    msg_status.setTextColor(getResources().getColor(R.color.red));
                    Log.e("Sending message failed", "address_string is null");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.edit_ice) {
            Intent intent_to_edit_ice = new Intent(this, EditICEActivity.class);
            startActivity(intent_to_edit_ice);
        }
        return super.onOptionsItemSelected(item);
    }
}
