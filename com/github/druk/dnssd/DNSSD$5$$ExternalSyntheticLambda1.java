package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$5$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DomainListener f$0;
    public final /* synthetic */ DNSSDService[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ DNSSD$5$$ExternalSyntheticLambda1(DomainListener domainListener, DNSSDService[] dNSSDServiceArr, int i, int i2, String str) {
        this.f$0 = domainListener;
        this.f$1 = dNSSDServiceArr;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = str;
    }

    public final void run() {
        this.f$0.domainFound(this.f$1[0], this.f$2, this.f$3, this.f$4);
    }
}
