package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ BrowseListener f$0;
    public final /* synthetic */ InternalDNSSDService[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ DNSSD$1$$ExternalSyntheticLambda1(BrowseListener browseListener, InternalDNSSDService[] internalDNSSDServiceArr, int i, int i2, String str, String str2, String str3) {
        this.f$0 = browseListener;
        this.f$1 = internalDNSSDServiceArr;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = str;
        this.f$5 = str2;
        this.f$6 = str3;
    }

    public final void run() {
        this.f$0.serviceFound(this.f$1[0], this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
