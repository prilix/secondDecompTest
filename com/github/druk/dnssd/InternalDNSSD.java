package com.github.druk.dnssd;

abstract class InternalDNSSD {
    public static final int ALL_INTERFACES = 0;
    public static final int BROWSE_DOMAINS = 64;
    public static final int DEFAULT = 4;
    public static final int LOCALHOST_ONLY = -1;
    public static final int MAX_DOMAIN_NAME = 1009;
    public static final int MORE_COMING = 1;
    public static final int NO_AUTO_RENAME = 8;
    public static final int REGISTRATION_DOMAINS = 128;
    public static final int SHARED = 16;
    public static final int UNIQUE = 32;
    protected static InternalDNSSD fInstance;

    /* access modifiers changed from: protected */
    public abstract String _constructFullName(String str, String str2, String str3) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDRecordRegistrar _createRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDService _enumerateDomains(int i, int i2, InternalDomainListener internalDomainListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract int _getIfIndexForName(String str);

    /* access modifiers changed from: protected */
    public abstract String _getNameForIfIndex(int i);

    /* access modifiers changed from: protected */
    public abstract void _init(String str);

    /* access modifiers changed from: protected */
    public abstract DNSSDService _makeBrowser(int i, int i2, String str, String str2, InternalBrowseListener internalBrowseListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDService _queryRecord(int i, int i2, String str, int i3, int i4, InternalQueryListener internalQueryListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract void _reconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract DNSSDRegistration _register(int i, int i2, String str, String str2, String str3, String str4, int i3, TXTRecord tXTRecord, InternalRegisterListener internalRegisterListener) throws DNSSDException;

    /* access modifiers changed from: protected */
    public abstract DNSSDService _resolve(int i, int i2, String str, String str2, String str3, InternalResolveListener internalResolveListener) throws DNSSDException;

    public static void init(String str) {
        getInstance()._init(str);
    }

    public static DNSSDService browse(int i, int i2, String str, String str2, InternalBrowseListener internalBrowseListener) throws DNSSDException {
        return getInstance()._makeBrowser(i, i2, str, str2, internalBrowseListener);
    }

    public static DNSSDService browse(String str, InternalBrowseListener internalBrowseListener) throws DNSSDException {
        return browse(0, 0, str, "", internalBrowseListener);
    }

    public static DNSSDService resolve(int i, int i2, String str, String str2, String str3, InternalResolveListener internalResolveListener) throws DNSSDException {
        return getInstance()._resolve(i, i2, str, str2, str3, internalResolveListener);
    }

    public static DNSSDRegistration register(int i, int i2, String str, String str2, String str3, String str4, int i3, TXTRecord tXTRecord, InternalRegisterListener internalRegisterListener) throws DNSSDException {
        return getInstance()._register(i, i2, str, str2, str3, str4, i3, tXTRecord, internalRegisterListener);
    }

    public static DNSSDRegistration register(String str, String str2, int i, InternalRegisterListener internalRegisterListener) throws DNSSDException {
        return register(0, 0, str, str2, (String) null, (String) null, i, (TXTRecord) null, internalRegisterListener);
    }

    public static DNSSDRecordRegistrar createRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException {
        return getInstance()._createRecordRegistrar(registerRecordListener);
    }

    public static DNSSDService queryRecord(int i, int i2, String str, int i3, int i4, InternalQueryListener internalQueryListener) throws DNSSDException {
        return getInstance()._queryRecord(i, i2, str, i3, i4, internalQueryListener);
    }

    public static DNSSDService enumerateDomains(int i, int i2, InternalDomainListener internalDomainListener) throws DNSSDException {
        return getInstance()._enumerateDomains(i, i2, internalDomainListener);
    }

    public static String constructFullName(String str, String str2, String str3) throws DNSSDException {
        return getInstance()._constructFullName(str, str2, str3);
    }

    public static void reconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr) {
        getInstance()._reconfirmRecord(i, i2, str, i3, i4, bArr);
    }

    public static String getNameForIfIndex(int i) {
        return getInstance()._getNameForIfIndex(i);
    }

    public static int getIfIndexForName(String str) {
        return getInstance()._getIfIndexForName(str);
    }

    protected InternalDNSSD() {
    }

    protected static final InternalDNSSD getInstance() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new RuntimePermission("getDNSSDInstance"));
        }
        return fInstance;
    }

    static {
        try {
            String property = System.getProperty("com.github.druk.dnssd.DNSSD");
            if (property == null) {
                property = "com.github.druk.dnssd.AppleDNSSD";
            }
            fInstance = (InternalDNSSD) Class.forName(property).newInstance();
        } catch (Exception e) {
            throw new InternalError("cannot instantiate DNSSD" + e);
        }
    }
}
