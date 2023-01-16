package com.thanosfisherman.wifiutils;

import android.net.wifi.WifiConfiguration;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConnectorUtils$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ ConnectorUtils$$ExternalSyntheticLambda0 INSTANCE = new ConnectorUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ ConnectorUtils$$ExternalSyntheticLambda0() {
    }

    public final int compare(Object obj, Object obj2) {
        return ConnectorUtils.lambda$sortByPriority$0((WifiConfiguration) obj, (WifiConfiguration) obj2);
    }
}
