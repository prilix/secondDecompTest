package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        GeoFenceTransitionsJobIntentService.enqueueWork(context, intent);
    }
}
