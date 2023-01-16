package com.github.druk.dnssd;

import com.github.druk.dnssd.DNSSD;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$4$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ QueryListener f$0;
    public final /* synthetic */ DNSSDService[] f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DNSSD$4$$ExternalSyntheticLambda0(QueryListener queryListener, DNSSDService[] dNSSDServiceArr, int i) {
        this.f$0 = queryListener;
        this.f$1 = dNSSDServiceArr;
        this.f$2 = i;
    }

    public final void run() {
        DNSSD.C09824.lambda$operationFailed$1(this.f$0, this.f$1, this.f$2);
    }
}
