package com.github.druk.dnssd;

/* compiled from: InternalDNSSD */
class AppleDNSRecord implements DNSRecord {
    protected AppleService fOwner;
    protected long fRecord = 0;

    /* access modifiers changed from: protected */
    public native int Remove();

    /* access modifiers changed from: protected */
    public native int Update(int i, byte[] bArr, int i2);

    public AppleDNSRecord(AppleService appleService) {
        this.fOwner = appleService;
    }

    public void update(int i, byte[] bArr, int i2) throws DNSSDException {
        ThrowOnErr(Update(i, bArr, i2));
    }

    public void remove() throws DNSSDException {
        ThrowOnErr(Remove());
    }

    /* access modifiers changed from: protected */
    public void ThrowOnErr(int i) throws DNSSDException {
        if (i != 0) {
            throw new AppleDNSSDException(i);
        }
    }
}
