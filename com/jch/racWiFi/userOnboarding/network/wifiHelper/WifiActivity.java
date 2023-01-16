package com.jch.racWiFi.userOnboarding.network.wifiHelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.List;

public abstract class WifiActivity extends DaggerAppCompatActivity implements WifiScanCallBack {
    WifiManager wifiManager;
    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            WifiActivity.this.wifiScanCallBack.onWifiScanCompleted(WifiActivity.this.wifiManager.getScanResults());
            WifiActivity.this.unregisterReceiver(this);
        }
    };
    WifiScanCallBack wifiScanCallBack = this;

    public void onWifiScanCompleted(List<ScanResult> list) {
    }

    public WifiManager getWifiManager() {
        return this.wifiManager;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
    }
}
