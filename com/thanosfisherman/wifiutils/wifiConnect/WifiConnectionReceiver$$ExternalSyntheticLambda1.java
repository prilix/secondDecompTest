package com.thanosfisherman.wifiutils.wifiConnect;

import android.net.wifi.ScanResult;
import com.thanosfisherman.elvis.interfaces.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiConnectionReceiver$$ExternalSyntheticLambda1 implements Function {
    public static final /* synthetic */ WifiConnectionReceiver$$ExternalSyntheticLambda1 INSTANCE = new WifiConnectionReceiver$$ExternalSyntheticLambda1();

    private /* synthetic */ WifiConnectionReceiver$$ExternalSyntheticLambda1() {
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function.CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return ((ScanResult) obj).BSSID;
    }

    public /* synthetic */ Function compose(Function function) {
        return Function.CC.$default$compose(this, function);
    }
}
