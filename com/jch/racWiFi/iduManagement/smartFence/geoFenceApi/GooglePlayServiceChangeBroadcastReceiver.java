package com.jch.racWiFi.iduManagement.smartFence.geoFenceApi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;

public class GooglePlayServiceChangeBroadcastReceiver extends BroadcastReceiver {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public void onReceive(Context context, Intent intent) {
        Log.i("Cleared", intent.toString());
        String nameForUid = context.getPackageManager().getNameForUid(Integer.valueOf(intent.getIntExtra("android.intent.extra.UID", Integer.MIN_VALUE)).intValue());
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.google.android.ims");
        arrayList.add("com.google.android.gms");
        if (arrayList.contains(nameForUid)) {
            BootCompleteJobIntentService.enqueueWork(context, new Intent());
        }
    }
}
