package com.jch.racWiFi.userOnboarding.presenter;

import com.jch.racWiFi.userOnboarding.view.uiComponents.dialog.ConnectedWifiDialog;

public interface WpsTutorialPresenter extends APTutorialPresenter {
    void updateWifiDialog(ConnectedWifiDialog connectedWifiDialog);
}
