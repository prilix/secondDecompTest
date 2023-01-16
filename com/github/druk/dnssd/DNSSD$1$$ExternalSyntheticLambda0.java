package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BrowseListener f$0;
    public final /* synthetic */ InternalDNSSDService[] f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DNSSD$1$$ExternalSyntheticLambda0(BrowseListener browseListener, InternalDNSSDService[] internalDNSSDServiceArr, int i) {
        this.f$0 = browseListener;
        this.f$1 = internalDNSSDServiceArr;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.operationFailed(this.f$1[0], this.f$2);
    }
}
