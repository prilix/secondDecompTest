package com.thanosfisherman.wifiutils.wifiConnect;

import android.net.wifi.ScanResult;
import com.thanosfisherman.elvis.interfaces.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WifiConnectionReceiver$1$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ WifiConnectionReceiver$1$$ExternalSyntheticLambda0 INSTANCE = new WifiConnectionReceiver$1$$ExternalSyntheticLambda0();

    private /* synthetic */ WifiConnectionReceiver$1$$ExternalSyntheticLambda0() {
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
