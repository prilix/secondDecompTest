package com.jch.racWiFi.userOnboarding.view;

import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiScanAdapter;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiScanCallBack;

public interface IRacWifiNetworkSelectionView extends WifiScanCallBack, WifiScanAdapter.WifiNetworkSelectionCallBack {
}
