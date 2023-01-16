package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionScanResultsListener;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$2$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ WifiUtils.C27092 f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ WifiUtils$2$$ExternalSyntheticLambda0(WifiUtils.C27092 r1, List list) {
        this.f$0 = r1;
        this.f$1 = list;
    }

    public final void accept(Object obj) {
        this.f$0.mo33879x558f0fbf(this.f$1, (ConnectionScanResultsListener) obj);
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
