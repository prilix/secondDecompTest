package com.jch.racWiFi.userOnboarding.network.wifiHelper;

import com.thanosfisherman.wifiutils.WifiConnectorBuilder;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ WifiUtils f$0;
    public final /* synthetic */ WifiConnectorBuilder.WifiUtilsBuilder f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ ConnectionSuccessListener f$4;

    public /* synthetic */ WifiUtils$$ExternalSyntheticLambda2(WifiUtils wifiUtils, WifiConnectorBuilder.WifiUtilsBuilder wifiUtilsBuilder, String str, String str2, ConnectionSuccessListener connectionSuccessListener) {
        this.f$0 = wifiUtils;
        this.f$1 = wifiUtilsBuilder;
        this.f$2 = str;
        this.f$3 = str2;
        this.f$4 = connectionSuccessListener;
    }

    public final void run() {
        this.f$0.mo33427xd6f5a0e7(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
