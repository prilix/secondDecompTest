package com.jch.racWiFi.userOnboarding.view;

import android.net.wifi.WifiInfo;

public interface IAPTutorialView {
    void onConnectedWifiNetworkFound(WifiInfo wifiInfo);

    void onConnectedWifiNetworkFound(String str);

    void onNoConnectedWifiNetworkFound();
}
