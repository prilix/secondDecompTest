package com.jch.racWiFi.userOnboarding.presenter.presenterImpl;

import android.content.Context;
import com.jch.racWiFi.userOnboarding.network.wifiHelper.WifiUtils;
import com.jch.racWiFi.userOnboarding.presenter.APTutorialPresenter;
import com.jch.racWiFi.userOnboarding.view.IAPTutorialView;

public class APTutorialPresenterImpl implements APTutorialPresenter {
    IAPTutorialView iapTutorialView;

    public APTutorialPresenterImpl(IAPTutorialView iAPTutorialView) {
        this.iapTutorialView = iAPTutorialView;
    }

    public void requestCurrentlyConnectedWifiNetwork(Context context) {
        String currentSsid = WifiUtils.getInstance().getCurrentSsid(context);
        this.iapTutorialView.onConnectedWifiNetworkFound(WifiUtils.getInstance().getCurrentWiFiInfo(context));
        if (currentSsid == null || currentSsid.isEmpty()) {
            this.iapTutorialView.onNoConnectedWifiNetworkFound();
        } else {
            this.iapTutorialView.onConnectedWifiNetworkFound(currentSsid);
        }
    }
}
