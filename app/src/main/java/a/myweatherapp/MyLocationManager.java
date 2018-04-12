package a.myweatherapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateFormat;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Date;

import a.myweatherapp.support.Constants;

import static android.content.Context.LOCATION_SERVICE;

public class MyLocationManager implements LocationListener {

    public static final int LONGITUDE = 0;
    public static final int LATITUDE = 1;
    public static final int DATE = 2;
    public static final int DAY = 3;
    public static final int TIME = 4;
    private LocationManager locationManager;
    private WeakReference<Context> contextWeakReference;
    private Location lastLoc;

    public MyLocationManager(Context context) {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        contextWeakReference = new WeakReference<>(context);
        setLastLoc();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e(Constants.myLogs, "LocationChanged " + location.toString());
        if (lastLoc != null) {
            lastLoc = location;
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        checkEnabled();
    }

    @Override
    public void onProviderEnabled(String provider) {
        setLastLoc();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public String[] getLocation() {
        if (lastLoc != null) {
            String[] location = new String[5];
            Date date = new Date(lastLoc.getTime());
            location[LONGITUDE] = String.valueOf(lastLoc.getLongitude());
            location[LATITUDE] = String.valueOf(lastLoc.getLatitude());
            location[DATE] = (String) DateFormat.format("dd.MM.yy", date);
            location[DAY]  = (String) DateFormat.format("EEEE", date);
            location[TIME] = (String) DateFormat.format("kk:mm:ss", date);
            return location;
        }
        else return null;
    }

    private boolean checkEnabled() {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void setLastLoc() {
        Log.e(Constants.myLogs, "setLastLoc : enter");
        if (ActivityCompat.checkSelfPermission(contextWeakReference.get(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(contextWeakReference.get(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            // ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            contextWeakReference.get().startActivity(new Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 60 * 1000, 10,
                this);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null)
            lastLoc = location;
        Log.e(Constants.myLogs, "setLastLoc : exit");
    }
}
