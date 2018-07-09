package com.example.souvik.projectivkitkatapi19;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.souvik.projectivkitkatapi19.data.JsonConverter;
import com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Request.Body;
import com.example.souvik.projectivkitkatapi19.data.RebrandlyModel.Response.Response;
import com.example.souvik.projectivkitkatapi19.data.Remote.RebrandlyApiService;
import com.example.souvik.projectivkitkatapi19.data.Remote.RebrandlyApiUtils;
import com.example.souvik.projectivkitkatapi19.data.Result;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    final String rootUrl = "https://maps.googleapis.com/maps/api/geocode/";
    final String apiKey = "AIzaSyCvSP4pz3aJqhtWFE1_TgEJ9rkUmrqzy5M";

    Toolbar toolbar_main_activity;
    Button distress_btn;
    EditText taxi_no, fast_comment;
    TextView msg_status;
    String address_string, address;
    LocationListenerCustom loc_lstnr;
    Location location_copy;
    RequestQueue requestQueue;
    StringBuilder distressMsg;
    private RebrandlyApiService rebrandlyApiService;
    private Body requestBody;

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
        rebrandlyApiService = RebrandlyApiUtils.getAPIService();
        requestBody = new Body();

        setSupportActionBar(toolbar_main_activity);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]
                        {
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET
                        },
                        10);
            }
        }
        loc_lstnr.requestLocation();
        if (loc_lstnr.location_string_json != null)
        {
            fetchAddressFromGoogle(loc_lstnr.location_string_json);
        }
    }

    protected void fetchAddressFromGoogle(@NonNull String location_string_json)
    {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(rootUrl).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        String endUrl = rootUrl + "json?latlng=" + location_string_json + "&key=" + apiKey;
        MapsReverseGeocodingApi mapsReverseGeocodingApi = retrofit.create(MapsReverseGeocodingApi.class);
        Call<JsonConverter> call = mapsReverseGeocodingApi.getResponse(endUrl);
        call.enqueue(new Callback<JsonConverter>()
        {
            @Override
            public void onResponse(Call<JsonConverter> call, retrofit2.Response<JsonConverter> response)
            {
                List<Result> resultList = response.body().getResults();
                address = resultList.get(0).getFormattedAddress();
            }

            @Override
            public void onFailure(Call<JsonConverter> call, Throwable t)
            {
            }
        });
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
            {
                if (grantResults.length <= 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Location access denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
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

    private void btnConfig()
    {
        distress_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*loc_lstnr.requestLocation();
                JsonObjectRequest json_req = new JsonObjectRequest("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + loc_lstnr.location_string_json + "&key=" + apiKey, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            address_string = response.getJSONArray("results").getJSONObject(0).getString("formatted_address");
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });
                requestQueue.add(json_req);
                SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
                String name = sh_obj.getString("client_name", "default2");
                String phoneNo = sh_obj.getString("client_ice_1", "9007XXXXX1");
                distressMsg = new StringBuilder();
                distressMsg.append(name + " " + getResources().getString(R.string.distress_msg_pt_1) + "\n" + loc_lstnr.location_string + "\n" + address_string);
                String taxi_no_str = taxi_no.getText().toString();
                String fast_comment_str = fast_comment.getText().toString();
                if (taxi_no_str != null && !taxi_no_str.isEmpty())
                {
                    distressMsg.append("\nCab no. " + taxi_no_str);
                }
                if (fast_comment_str != null && !fast_comment_str.isEmpty())
                {
                    distressMsg.append("\nHer message - " + fast_comment_str);
                }
                addMapSupport();*/

                //request location update
                loc_lstnr.requestLocation();
                if (loc_lstnr.location_string_json != null)
                {
                    //get address from location
                    fetchAddressFromGoogle(loc_lstnr.location_string_json);

                    //construct message
                    SharedPreferences sh_obj = getSharedPreferences("modWSb1", Context.MODE_PRIVATE);
                    String name = sh_obj.getString("client_name", "default2");
                    final String phoneNo = sh_obj.getString("client_ice_1", "9007XXXXX1");
                    distressMsg = new StringBuilder();
                    distressMsg.append(name + " " + getResources().getString(R.string.distress_msg_pt_1) + "\n" + loc_lstnr.location_string + "\n" + address);
                    String taxi_no_str = taxi_no.getText().toString();
                    String fast_comment_str = fast_comment.getText().toString();
                    if (taxi_no_str != null && !taxi_no_str.isEmpty())
                    {
                        distressMsg.append("\nCab no. " + taxi_no_str);
                    }
                    if (fast_comment_str != null && !fast_comment_str.isEmpty())
                    {
                        distressMsg.append("\nHer message - " + fast_comment_str);
                    }
                    addMapSupport(name);

                    //finally send message
                    class SendMsg implements Runnable
                    {

                        @Override
                        public void run()
                        {
                            try
                            {
                                Thread.sleep(5000);
                                SmsManager sms_mngr = SmsManager.getDefault();
                                sms_mngr.sendTextMessage(phoneNo, null, distressMsg.toString(), null, null);
                                /*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                sendIntent.putExtra("sms_body", distressMsg.toString());
                                sendIntent.putExtra("address", phoneNo);
                                sendIntent.setType("vnd.android-dir/mms-sms");
                                startActivity(sendIntent);*/
                                Bundle bundle = new Bundle();
                                bundle.putString("MSG_STATUS", "1");
                                Message msg = new Message();
                                msg.setData(bundle);
                                updateMsgStatus.sendMessage(msg);
                            }
                            catch (Exception e)
                            {
                                Bundle bundle = new Bundle();
                                bundle.putString("MSG_STATUS", "0");
                                Message msg = new Message();
                                msg.setData(bundle);
                                updateMsgStatus.sendMessage(msg);
                            }
                        }
                    }
                    Thread sendMsg = new Thread(new SendMsg(), "sendMsg");
                    sendMsg.start();
                }
            }
        });
    }

    private void addMapSupport(String name)
    {
        String mapUrl = "https://www.google.com/maps/search/?api=1&query=" + loc_lstnr.location_string_json;
        if (isNetworkConnected())
        {
            requestBody.setTitle("Location of " + name);
            requestBody.setDestination(mapUrl);
            rebrandlyApiService.savePost(requestBody).enqueue(new Callback<Response>()
            {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response)
                {
                    String shortMapUrl = response.body().getShortUrl();
                    distressMsg.append("\n" + shortMapUrl);
                    Toast.makeText(MainActivity.this, "Short url generated", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t)
                {
                    Toast.makeText(MainActivity.this, "Short url not generated", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            distressMsg.append("\n" + mapUrl);
        }
    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
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

    Handler updateMsgStatus = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Bundle bundle = msg.getData();
            String msgStr = bundle.getString("MSG_STATUS");
            switch (msgStr)
            {
                case "1":
                {
                    msg_status.setText("Message sent");
                    msg_status.setTextColor(getResources().getColor(R.color.green));
                    break;
                }
                case "0":
                {
                    msg_status.setText("Message sending failed");
                    msg_status.setTextColor(getResources().getColor(R.color.red));
                    break;
                }
            }
        }
    };
}