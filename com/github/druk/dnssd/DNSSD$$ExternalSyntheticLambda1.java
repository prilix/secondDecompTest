package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DNSSDService[] f$0;

    public /* synthetic */ DNSSD$$ExternalSyntheticLambda1(DNSSDService[] dNSSDServiceArr) {
        this.f$0 = dNSSDServiceArr;
    }

    public final void run() {
        this.f$0[0].stop();
    }
}
