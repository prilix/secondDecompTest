package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.wifiState.WifiStateListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$$ExternalSyntheticLambda2 implements Consumer {
    public static final /* synthetic */ WifiUtils$$ExternalSyntheticLambda2 INSTANCE = new WifiUtils$$ExternalSyntheticLambda2();

    private /* synthetic */ WifiUtils$$ExternalSyntheticLambda2() {
    }

    public final void accept(Object obj) {
        ((WifiStateListener) obj).isSuccess(false);
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
