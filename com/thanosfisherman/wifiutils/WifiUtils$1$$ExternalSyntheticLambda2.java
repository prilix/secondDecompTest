package com.thanosfisherman.wifiutils;

import com.thanosfisherman.elvis.interfaces.Consumer;
import com.thanosfisherman.wifiutils.wifiWps.ConnectionWpsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiUtils$1$$ExternalSyntheticLambda2 implements Consumer {
    public static final /* synthetic */ WifiUtils$1$$ExternalSyntheticLambda2 INSTANCE = new WifiUtils$1$$ExternalSyntheticLambda2();

    private /* synthetic */ WifiUtils$1$$ExternalSyntheticLambda2() {
    }

    public final void accept(Object obj) {
        ((ConnectionWpsListener) obj).isSuccessful(false);
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
