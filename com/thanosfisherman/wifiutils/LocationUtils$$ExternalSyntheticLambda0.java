package com.thanosfisherman.wifiutils;

import android.location.LocationManager;
import com.thanosfisherman.elvis.interfaces.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationUtils$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ LocationUtils$$ExternalSyntheticLambda0 INSTANCE = new LocationUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ LocationUtils$$ExternalSyntheticLambda0() {
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function.CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((LocationManager) obj).isProviderEnabled("gps"));
    }

    public /* synthetic */ Function compose(Function function) {
        return Function.CC.$default$compose(this, function);
    }
}
