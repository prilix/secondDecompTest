package com.jch.racWiFi.userOnboarding.presenter;

import androidx.navigation.NavController;
import p015me.dm7.barcodescanner.zxing.ZXingScannerView;

public interface QRScanPresenter extends ZXingScannerView.ResultHandler {
    boolean setRunStatus(NavController navController);
}
