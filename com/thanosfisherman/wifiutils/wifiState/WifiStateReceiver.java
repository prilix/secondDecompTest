package com.thanosfisherman.wifiutils.wifiState;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class WifiStateReceiver extends BroadcastReceiver {
    private final WifiStateCallback wifiStateCallback;

    public WifiStateReceiver(WifiStateCallback wifiStateCallback2) {
        this.wifiStateCallback = wifiStateCallback2;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getIntExtra("wifi_state", 0) == 3) {
            this.wifiStateCallback.onWifiEnabled();
        }
    }
}
