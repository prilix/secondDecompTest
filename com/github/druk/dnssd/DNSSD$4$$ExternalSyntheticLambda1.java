package com.github.druk.dnssd;

import com.github.druk.dnssd.DNSSD;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$4$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ QueryListener f$0;
    public final /* synthetic */ DNSSDService[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ byte[] f$7;
    public final /* synthetic */ int f$8;

    public /* synthetic */ DNSSD$4$$ExternalSyntheticLambda1(QueryListener queryListener, DNSSDService[] dNSSDServiceArr, int i, int i2, String str, int i3, int i4, byte[] bArr, int i5) {
        this.f$0 = queryListener;
        this.f$1 = dNSSDServiceArr;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = str;
        this.f$5 = i3;
        this.f$6 = i4;
        this.f$7 = bArr;
        this.f$8 = i5;
    }

    public final void run() {
        DNSSD.C09824.lambda$queryAnswered$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
