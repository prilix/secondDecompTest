package com.github.druk.dnssd;

/* compiled from: InternalDNSSD */
class AppleResolver extends AppleService {
    /* access modifiers changed from: protected */
    public native int CreateResolver(int i, int i2, String str, String str2, String str3);

    public AppleResolver(int i, int i2, String str, String str2, String str3, InternalResolveListener internalResolveListener) throws DNSSDException {
        super(internalResolveListener);
        ThrowOnErr(CreateResolver(i, i2, str, str2, str3));
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }
}
