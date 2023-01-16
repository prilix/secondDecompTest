package com.github.druk.dnssd;

import com.github.druk.dnssd.DNSSD;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$2$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ResolveListener f$0;
    public final /* synthetic */ DNSSDService[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ Map f$7;

    public /* synthetic */ DNSSD$2$$ExternalSyntheticLambda1(ResolveListener resolveListener, DNSSDService[] dNSSDServiceArr, int i, int i2, String str, String str2, int i3, Map map) {
        this.f$0 = resolveListener;
        this.f$1 = dNSSDServiceArr;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = str;
        this.f$5 = str2;
        this.f$6 = i3;
        this.f$7 = map;
    }

    public final void run() {
        DNSSD.C09802.lambda$serviceResolved$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
