package com.jch.racWiFi.userOnboarding.presenter;

import android.net.wifi.ScanResult;
import androidx.navigation.NavController;

public interface RacWifiConnectivityPresenter {
    void addWifiDataToNavController(NavController navController, String str, String str2, ScanResult scanResult);
}
