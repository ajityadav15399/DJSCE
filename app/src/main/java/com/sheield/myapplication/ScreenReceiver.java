package com.sheield.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by HEMANT KUMAR on 02-Jul-17.
 */

public class ScreenReceiver extends BroadcastReceiver {
   // public static boolean wasScreenOn = true;
    public static int count = 0;
    public static long startTime;
    public static long differenceTime = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Log.e("LOB","onReceive");

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            count++;
            if(count==1){
                startTime=System.currentTimeMillis();
            }
           /* else if(count==4){
                count=0;
                differenceTime=System.currentTimeMillis()-startTime;
                if(differenceTime<2000){
                    //   Toast.makeText(context,"sms sent",Toast.LENGTH_LONG).show();
                    // SmsManager smsManager = SmsManager.getDefault();
                    //smsManager.sendTextMessage("9899646353", null,loc, null, null);
                }
            }
            wasScreenOn = false;
            // Log.e("LOB","wasScreenOn"+wasScreenOn);*/
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            count++;
            if(count==1){
                startTime=System.currentTimeMillis();
            }
            else if(count==4){
                count=0;
                differenceTime=System.currentTimeMillis()-startTime;
                if(differenceTime<2000){
                    //start location service
                    Intent it = new Intent(context,LocationService.class);
                    context.startService(it);

                    //  SmsManager smsManager = SmsManager.getDefault();
                    //smsManager.sendTextMessage("9899646353", null,loc, null, null);

                }
            }
           // wasScreenOn = true;
        }/*else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            // Log.e("LOB","userpresent");
            // Log.e("LOB","wasScreenOn"+wasScreenOn);
            String url = "http://www.stackoverflow.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            if(count==4){ i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(url));
                context.startActivity(i); }
        }*/
    }




}