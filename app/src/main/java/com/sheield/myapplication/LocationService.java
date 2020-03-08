package com.sheield.myapplication;

/**
 * Created by YASH on 7/5/2017.
 */

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * Created by HEMANT KUMAR on 04-Jul-17.
 */

public class LocationService extends Service {
    private LocationManager locationManager;
    private LocationListener locationListener;
    public static String geoLocation;
    DatabaseHelper myDb = new DatabaseHelper(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                geoLocation = location.getLatitude()+","+ location.getLongitude();
                sendLocation();
                stopSelf();
                return;
            }

            private void sendLocation() {

                Cursor res = myDb.getAllData();
                int i=0;
                StringBuffer buffer = new StringBuffer();
                StringBuffer buffer1 = new StringBuffer();
                StringBuffer buffer2 = new StringBuffer();
                while (res.moveToNext()) {

                   if(i==0) buffer.append(res.getString(2));
                    else if(i==1) buffer1.append(res.getString(2));
                    else if(i==2) buffer2.append(res.getString(2));
                    i++;
                }
                    String telno = buffer.toString();
                    Toast.makeText(getApplicationContext(),"Emergency sms sent", Toast.LENGTH_LONG).show();
                    SmsManager smsManager = SmsManager.getDefault();
                if(i==0){Toast.makeText(getBaseContext(),"No contact stored",Toast.LENGTH_LONG).show(); }
                else
                   if(i==1) {
                       smsManager.sendTextMessage(buffer.toString(), null, "I am in a emergency and I need HELP!\nI am currenty not able to provide more information.\ni am at : http://maps.google.com/maps?q=" + geoLocation, null, null);
                   }
                    else if(i==2){
                        smsManager.sendTextMessage(buffer.toString(), null, "I am in a emergency and I need HELP!\nI am currenty not able to provide more information.\ni am at : http://maps.google.com/maps?q=" + geoLocation, null, null);
                       smsManager.sendTextMessage(buffer1.toString(), null, "I am in a emergency and I need HELP!\nI am currenty not able to provide more information.\ni am at : http://maps.google.com/maps?q=" + geoLocation, null, null);
                   }
                   else if(i==3){
                       smsManager.sendTextMessage(buffer.toString(), null, "I am in a emergency and I need HELP!\nI am currenty not able to provide more information.\ni am at : http://maps.google.com/maps?q=" + geoLocation, null, null);
                       smsManager.sendTextMessage(buffer1.toString(), null, "I am in a emergency and I need HELP!\nI am currenty not able to provide more information.\ni am at : http://maps.google.com/maps?q=" + geoLocation, null, null);
                       smsManager.sendTextMessage(buffer2.toString(), null, "I am in a emergency and I need HELP!\nI am currenty not able to provide more information.\ni am at : http://maps.google.com/maps?q=" + geoLocation, null, null);
                   }
                   // Intent in = new Intent(Intent.ACTION_CALL);
                    //in.setData(Uri.parse("tel:" + telno ));
                    //startActivity(in);

               // if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                 //   return;                }
                stopSelf();
                return;

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent in = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
            }
        };
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // Toast.makeText(getApplicationContext(),"onDestroy called",Toast.LENGTH_LONG).show();
        if(locationManager!=null){
            locationManager.removeUpdates(locationListener);
        }
    }
}

