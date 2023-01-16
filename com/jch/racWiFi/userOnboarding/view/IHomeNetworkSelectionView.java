package com.jch.racWiFi.userOnboarding.view;

import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiScanAdapter;

public interface IHomeNetworkSelectionView extends WifiScanAdapter.WifiNetworkSelectionCallBack {
    void onNoSelectedHomeNetworkFound();

    void onSelectedHomeNetworkFound(String str);
}
