package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class GeoFenceTriggerIntentService extends IntentService {
    private static String TAG = "GeoFenceTriggerIntentService";

    public GeoFenceTriggerIntentService(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.i(TAG, intent.toString());
            GeoFenceTransitionsJobIntentService.enqueueWork(getApplicationContext(), intent);
        }
    }
}
