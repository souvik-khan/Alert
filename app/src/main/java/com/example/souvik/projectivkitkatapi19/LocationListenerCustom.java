package com.example.souvik.projectivkitkatapi19;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Souvik on 7-8-17.
 */

public class LocationListenerCustom implements LocationListener {

    public String location_string, location_string_json;
    private Location location_copy;
    private Context context_main;
    private LocationManager loc_mngr;

    LocationListenerCustom(Context c) {
        context_main = c;
    }

    public Location requestLocation() {

        loc_mngr = (LocationManager) context_main.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context_main, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context_main, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return null;
        }
        loc_mngr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        loc_mngr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        location_copy = loc_mngr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        try {
            location_string = location_copy.getLatitude() + ", " + location_copy.getLongitude();
            location_string_json = location_copy.getLatitude() + "," + location_copy.getLongitude();
        } catch (NullPointerException e) {
            Log.e("...in catch...", "Null point exception occurred");
        }
        return location_copy;


/*        loc_mngr = (LocationManager) context_main.getSystemService(Context.LOCATION_SERVICE);
        accuracyFlag = false;
        if ( ActivityCompat.checkSelfPermission(context_main, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context_main, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            Toast.makeText(context_main, "Allow location access", Toast.LENGTH_LONG).show();
            return null;
        }
        else {
            gpsFlag = loc_mngr.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gpsFlag) {
                loc_mngr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                location_copy = loc_mngr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                try {
                    location_string_cus = location_copy.getLatitude() + ", " + location_copy.getLongitude();
                    location_string_1_cus = location_copy.getLatitude() + "," + location_copy.getLongitude();
                    accuracyFlag = true;
                } catch (NullPointerException e) {

                }
                return location_copy;
            }
            else {
                Toast.makeText(context_main, "Turn on GPS location", Toast.LENGTH_LONG).show();
                return null;
            }
        }*/
    }

    @Override
    public void onLocationChanged(Location location) {
        location_string = location.getLatitude() + ", " + location.getLongitude();
        location_string_json = location.getLatitude() + "," + location.getLongitude();
        location_copy = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        location_string = location_copy.getLatitude() + ", " + location_copy.getLongitude();
        location_string_json = location_copy.getLatitude() + "," + location_copy.getLongitude();
    }

    @Override
    public void onProviderEnabled(String provider) {
        if (ActivityCompat.checkSelfPermission(context_main, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context_main, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        loc_mngr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        loc_mngr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        location_copy = loc_mngr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        try {
            location_string = location_copy.getLatitude() + ", " + location_copy.getLongitude();
            location_string_json = location_copy.getLatitude() + "," + location_copy.getLongitude();
        } catch (NullPointerException e) {
            Log.e("...in catch...check #2", "Null point exception occurred");
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context_main, "Please enable GPS", Toast.LENGTH_LONG).show();
    }
}
