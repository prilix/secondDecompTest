package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "BootCompleteBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.i("global_boot", "triggered");
        BootCompleteJobIntentService.enqueueWork(context, new Intent());
    }
}
