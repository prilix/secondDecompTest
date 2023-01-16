package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DNSSDService[] f$0;

    public /* synthetic */ DNSSD$$ExternalSyntheticLambda0(DNSSDService[] dNSSDServiceArr) {
        this.f$0 = dNSSDServiceArr;
    }

    public final void run() {
        this.f$0[0].stop();
    }
}
