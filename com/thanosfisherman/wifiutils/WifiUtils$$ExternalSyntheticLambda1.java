package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.wifiScan.ScanResultsListener;
import java.util.ArrayList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$$ExternalSyntheticLambda1 implements Consumer {
    public static final /* synthetic */ WifiUtils$$ExternalSyntheticLambda1 INSTANCE = new WifiUtils$$ExternalSyntheticLambda1();

    private /* synthetic */ WifiUtils$$ExternalSyntheticLambda1() {
    }

    public final void accept(Object obj) {
        ((ScanResultsListener) obj).onScanResults(new ArrayList());
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
