package com.jch.racWiFi.userOnboarding.view;

import com.jch.racWiFi.userOnboarding.model.QRDetailsModel;

public interface IQRView {
    void onScanFailed();

    void onScanSuccessful(QRDetailsModel qRDetailsModel);

    void onWrongQRScanned();

    void runOnFreshMode();

    void runOnPreviouslyFailedMode();
}
