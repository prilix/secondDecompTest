package com.jch.racWiFi.userOnboarding.presenter;

import android.net.wifi.ScanResult;
import androidx.navigation.NavArgument;
import androidx.navigation.NavGraph;
import java.util.Map;

public interface HomeNetworkSelectionPresenter {
    NavArgument convertPasswordToNavArgument(String str);

    NavArgument getNavigatedFromAsNavArgument();

    void setNavArguments(Map<String, NavArgument> map);

    void updateSsidInGraph(NavGraph navGraph, String str, ScanResult scanResult);
}
