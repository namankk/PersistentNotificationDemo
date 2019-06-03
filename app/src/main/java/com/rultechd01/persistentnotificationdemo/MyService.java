package com.rultechd01.persistentnotificationdemo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import static android.app.AlarmManager.ELAPSED_REALTIME;

public class MyService extends Service {
    private static final String LOG_TAG = "ForegroundService";
    public static boolean IS_SERVICE_RUNNING = false;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i(LOG_TAG, "Service started");
            showNotification();
            Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }

    private void showNotification() {
        Intent intent = new Intent(this, ResultActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            noti = new Notification.Builder(this)
                  //  .setOngoing(true)
                    .setSmallIcon(android.R.color.transparent)
                    .setContentTitle("New mail from " + "test@gmail.com")
                    .setContentText("Subject").setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pIntent)
                    .addAction(R.drawable.ic_launcher_background, "And more", pIntent).build();
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
        startForeground(0, noti);

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("tag", "onTaskRemoved: ");
        //        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
//
//        PendingIntent restartServicePendingIntent = PendingIntent.getService(
//                getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager alarmService = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
//        alarmService.set(ELAPSED_REALTIME,  1000,
//                restartServicePendingIntent);




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
//        stopSelf();
//        startForeground(0, noti);
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
       // showNotification();
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
        Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }
}
