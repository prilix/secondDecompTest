package com.github.druk.dnssd;

/* compiled from: InternalDNSSD */
class AppleDomainEnum extends AppleService {
    /* access modifiers changed from: protected */
    public native int BeginEnum(int i, int i2);

    public AppleDomainEnum(int i, int i2, InternalDomainListener internalDomainListener) throws DNSSDException {
        super(internalDomainListener);
        ThrowOnErr(BeginEnum(i, i2));
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }
}
