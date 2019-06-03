package com.rultechd01.persistentnotificationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("receiver", "onReceive: ");
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();

    }
}
