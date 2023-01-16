package com.github.druk.dnssd;

/* compiled from: InternalDNSSD */
class AppleDNSSD extends InternalDNSSD {
    public static boolean hasAutoCallbacks;

    protected static native int InitLibrary(int i);

    /* access modifiers changed from: protected */
    public native int ConstructName(String str, String str2, String str3, String[] strArr);

    /* access modifiers changed from: protected */
    public native int GetIfIndexForName(String str);

    /* access modifiers changed from: protected */
    public native byte[] GetNameForIfIndex(int i);

    /* access modifiers changed from: protected */
    public native void ReconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr);

    AppleDNSSD() {
    }

    /* access modifiers changed from: protected */
    public void _init(String str) {
        System.loadLibrary(str);
        int InitLibrary = InitLibrary(2);
        if (InitLibrary != 0) {
            throw new InternalError("cannot instantiate DNSSD: " + new AppleDNSSDException(InitLibrary).getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public DNSSDService _makeBrowser(int i, int i2, String str, String str2, InternalBrowseListener internalBrowseListener) throws DNSSDException {
        return new AppleBrowser(i, i2, str, str2, internalBrowseListener);
    }

    /* access modifiers changed from: protected */
    public DNSSDService _resolve(int i, int i2, String str, String str2, String str3, InternalResolveListener internalResolveListener) throws DNSSDException {
        return new AppleResolver(i, i2, str, str2, str3, internalResolveListener);
    }

    /* access modifiers changed from: protected */
    public DNSSDRegistration _register(int i, int i2, String str, String str2, String str3, String str4, int i3, TXTRecord tXTRecord, InternalRegisterListener internalRegisterListener) throws DNSSDException {
        return new AppleRegistration(i, i2, str, str2, str3, str4, i3, tXTRecord != null ? tXTRecord.getRawBytes() : null, internalRegisterListener);
    }

    /* access modifiers changed from: protected */
    public DNSSDRecordRegistrar _createRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException {
        return new AppleRecordRegistrar(registerRecordListener);
    }

    /* access modifiers changed from: protected */
    public DNSSDService _queryRecord(int i, int i2, String str, int i3, int i4, InternalQueryListener internalQueryListener) throws DNSSDException {
        return new AppleQuery(i, i2, str, i3, i4, internalQueryListener);
    }

    /* access modifiers changed from: protected */
    public DNSSDService _enumerateDomains(int i, int i2, InternalDomainListener internalDomainListener) throws DNSSDException {
        return new AppleDomainEnum(i, i2, internalDomainListener);
    }

    /* access modifiers changed from: protected */
    public String _constructFullName(String str, String str2, String str3) throws DNSSDException {
        String[] strArr = new String[1];
        int ConstructName = ConstructName(str, str2, str3, strArr);
        if (ConstructName == 0) {
            return strArr[0];
        }
        throw new AppleDNSSDException(ConstructName);
    }

    /* access modifiers changed from: protected */
    public void _reconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr) {
        ReconfirmRecord(i, i2, str, i3, i4, bArr);
    }

    /* access modifiers changed from: protected */
    public String _getNameForIfIndex(int i) {
        return new String(GetNameForIfIndex(i));
    }

    /* access modifiers changed from: protected */
    public int _getIfIndexForName(String str) {
        return GetIfIndexForName(str);
    }
}
