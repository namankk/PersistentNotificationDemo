package com.rultechd01.persistentnotificationdemo;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
@SuppressLint("OverrideAbstract")
public class MyNotificationListner extends NotificationListenerService {


    private String TAG = this.getClass().getSimpleName();
    private NLServiceReceiver nlservicereciver;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: notification listner");
        Intent service = new Intent(this, MyService.class);
        startService(service);
        nlservicereciver = new NLServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kpbird.nlsexample.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
        registerReceiver(nlservicereciver,filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: notification listner");
        unregisterReceiver(nlservicereciver);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Log.i(TAG,"**********  onNotificationPosted");
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
        Intent i = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
        i.putExtra("notification_event","onNotificationPosted :" + sbn.getPackageName() + "\n");
        sendBroadcast(i);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG,"********** onNOtificationRemoved");
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText +"\t" + sbn.getPackageName());
//        Intent i = new Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
//        i.putExtra("notification_event","onNotificationRemoved :" + sbn.getPackageName() + "\n");
//        Intent intent = new Intent(this, ResultActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
//
//        // Build notification
//        // Actions are just fake
//        Notification noti = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            noti = new Notification.Builder(this)
//                    .setOngoing(true)
//                    .setSmallIcon(android.R.color.transparent)
//                    .setContentTitle("New mail from " + "test@gmail.com")
//                    .setContentText("Subject").setSmallIcon(R.drawable.ic_launcher_background)
//                    .setContentIntent(pIntent)
//                    .addAction(R.drawable.ic_launcher_background, "And more", pIntent).build();
//        }
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        // hide the notification after its selected
//        noti.flags |= Notification.FLAG_AUTO_CANCEL;
//
//        notificationManager.notify(0, noti);
//        startForeground(0, noti);
//        sendBroadcast(i);
    }

    class NLServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//            if(intent.getStringExtra("command").equals("clearall")){
//                MyNotificationListner.this.cancelAllNotifications();
//            }
//            else if(intent.getStringExtra("command").equals("list")){
//                Intent i1 = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
//                i1.putExtra("notification_event","=====================");
//                sendBroadcast(i1);
//                int i=1;
//                for (StatusBarNotification sbn : MyNotificationListner.this.getActiveNotifications()) {
//                    Intent i2 = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
//                    i2.putExtra("notification_event",i +" " + sbn.getPackageName() + "\n");
//                    sendBroadcast(i2);
//                    i++;
//                }
//                Intent i3 = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
//                i3.putExtra("notification_event","===== Notification List ====");
//                sendBroadcast(i3);
//
//            }
            Log.i(TAG, "onReceive: called");
        }
    }

}
