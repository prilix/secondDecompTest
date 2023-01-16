package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$5$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DomainListener f$0;
    public final /* synthetic */ DNSSDService[] f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DNSSD$5$$ExternalSyntheticLambda0(DomainListener domainListener, DNSSDService[] dNSSDServiceArr, int i) {
        this.f$0 = domainListener;
        this.f$1 = dNSSDServiceArr;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.operationFailed(this.f$1[0], this.f$2);
    }
}
