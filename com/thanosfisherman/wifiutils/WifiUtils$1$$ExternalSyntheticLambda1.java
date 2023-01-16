package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.wifiState.WifiStateListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$1$$ExternalSyntheticLambda1 implements Consumer {
    public static final /* synthetic */ WifiUtils$1$$ExternalSyntheticLambda1 INSTANCE = new WifiUtils$1$$ExternalSyntheticLambda1();

    private /* synthetic */ WifiUtils$1$$ExternalSyntheticLambda1() {
    }

    public final void accept(Object obj) {
        ((WifiStateListener) obj).isSuccess(true);
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
