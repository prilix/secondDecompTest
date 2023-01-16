package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.wifiScan.ScanResultsListener;
import java.util.ArrayList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$1$$ExternalSyntheticLambda0 implements Consumer {
    public static final /* synthetic */ WifiUtils$1$$ExternalSyntheticLambda0 INSTANCE = new WifiUtils$1$$ExternalSyntheticLambda0();

    private /* synthetic */ WifiUtils$1$$ExternalSyntheticLambda0() {
    }

    public final void accept(Object obj) {
        ((ScanResultsListener) obj).onScanResults(new ArrayList());
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
