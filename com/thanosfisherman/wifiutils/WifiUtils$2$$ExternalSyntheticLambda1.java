package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.wifiScan.ScanResultsListener;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$2$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ WifiUtils$2$$ExternalSyntheticLambda1(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        ((ScanResultsListener) obj).onScanResults(this.f$0);
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
