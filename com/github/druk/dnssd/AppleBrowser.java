package com.github.druk.dnssd;

/* compiled from: InternalDNSSD */
class AppleBrowser extends AppleService {
    /* access modifiers changed from: protected */
    public native int CreateBrowser(int i, int i2, String str, String str2);

    public AppleBrowser(int i, int i2, String str, String str2, InternalBrowseListener internalBrowseListener) throws DNSSDException {
        super(internalBrowseListener);
        ThrowOnErr(CreateBrowser(i, i2, str, str2));
        if (!AppleDNSSD.hasAutoCallbacks) {
            new Thread(this).start();
        }
    }
}
