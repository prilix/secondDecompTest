package com.github.druk.dnssd;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DNSSD$3$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ RegisterListener f$0;
    public final /* synthetic */ DNSSDRegistration[] f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DNSSD$3$$ExternalSyntheticLambda0(RegisterListener registerListener, DNSSDRegistration[] dNSSDRegistrationArr, int i) {
        this.f$0 = registerListener;
        this.f$1 = dNSSDRegistrationArr;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.operationFailed(this.f$1[0], this.f$2);
    }
}
