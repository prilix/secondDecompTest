package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$3$$ExternalSyntheticLambda0 implements Consumer {
    public static final /* synthetic */ WifiUtils$3$$ExternalSyntheticLambda0 INSTANCE = new WifiUtils$3$$ExternalSyntheticLambda0();

    private /* synthetic */ WifiUtils$3$$ExternalSyntheticLambda0() {
    }

    public final void accept(Object obj) {
        WifiUtils.C27103.lambda$errorConnect$1((ConnectionSuccessListener) obj);
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
