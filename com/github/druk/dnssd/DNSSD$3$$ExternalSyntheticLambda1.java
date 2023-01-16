package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$3$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ RegisterListener f$0;
    public final /* synthetic */ DNSSDRegistration[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ DNSSD$3$$ExternalSyntheticLambda1(RegisterListener registerListener, DNSSDRegistration[] dNSSDRegistrationArr, int i, String str, String str2, String str3) {
        this.f$0 = registerListener;
        this.f$1 = dNSSDRegistrationArr;
        this.f$2 = i;
        this.f$3 = str;
        this.f$4 = str2;
        this.f$5 = str3;
    }

    public final void run() {
        this.f$0.serviceRegistered(this.f$1[0], this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
