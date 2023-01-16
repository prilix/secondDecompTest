package com.github.druk.dnssd;

import com.github.druk.dnssd.DNSSD;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ResolveListener f$0;
    public final /* synthetic */ DNSSDService[] f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DNSSD$2$$ExternalSyntheticLambda0(ResolveListener resolveListener, DNSSDService[] dNSSDServiceArr, int i) {
        this.f$0 = resolveListener;
        this.f$1 = dNSSDServiceArr;
        this.f$2 = i;
    }

    public final void run() {
        DNSSD.C09802.lambda$operationFailed$1(this.f$0, this.f$1, this.f$2);
    }
}
