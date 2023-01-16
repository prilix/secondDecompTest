package com.jch.racWiFi.userOnboarding.network.wifiHelper;

import android.net.wifi.ScanResult;
import java.util.List;

public interface WifiScanCallBack {
    void onWifiScanCompleted(List<ScanResult> list);
}
