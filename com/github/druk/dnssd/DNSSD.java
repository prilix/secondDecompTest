package com.github.druk.dnssd;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.github.druk.dnssd.InternalDNSSDService;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public abstract class DNSSD implements InternalDNSSDService.DnssdServiceListener {
    public static final int ALL_INTERFACES = 0;
    public static final int BROWSE_DOMAINS = 64;
    public static final int DEFAULT = 4;
    public static final int DNSSD_DEFAULT_TIMEOUT = 60000;
    public static final int LOCALHOST_ONLY = -1;
    public static final int MAX_DOMAIN_NAME = 1009;
    public static final int MORE_COMING = 1;
    private static final String MULTICAST_LOCK_NAME = "com.github.druk.dnssd.DNSSD";
    public static final int NO_AUTO_RENAME = 8;
    public static final int REGISTRATION_DOMAINS = 128;
    public static final int SHARED = 16;
    public static final int UNIQUE = 32;
    /* access modifiers changed from: private */
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Context context;
    /* access modifiers changed from: private */
    public final Handler handler;
    private volatile WifiManager.MulticastLock multicastLock;
    private final int serviceTimeout;

    DNSSD(Context context2, String str) {
        this(context2, str, Looper.getMainLooper());
    }

    DNSSD(Context context2, String str, Looper looper) {
        this.multicastLock = null;
        this.context = context2.getApplicationContext();
        InternalDNSSD.init(str);
        this.handler = new Handler(looper);
        this.serviceTimeout = 60000;
    }

    DNSSD(Context context2, String str, Handler handler2) {
        this.multicastLock = null;
        this.context = context2.getApplicationContext();
        InternalDNSSD.init(str);
        this.handler = handler2;
        this.serviceTimeout = 60000;
    }

    DNSSD(Context context2, String str, Handler handler2, int i) {
        this.multicastLock = null;
        this.context = context2.getApplicationContext();
        InternalDNSSD.init(str);
        this.handler = handler2;
        this.serviceTimeout = i;
    }

    public DNSSDService browse(int i, int i2, String str, String str2, final BrowseListener browseListener) throws DNSSDException {
        onServiceStarting();
        final InternalDNSSDService[] internalDNSSDServiceArr = {new InternalDNSSDService(this, InternalDNSSD.browse(i, i2, str, str2, new InternalBrowseListener() {
            public void serviceFound(DNSSDService dNSSDService, int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
                DNSSD.this.handler.post(new DNSSD$1$$ExternalSyntheticLambda1(browseListener, internalDNSSDServiceArr, i, i2, new String(bArr, DNSSD.UTF_8), new String(bArr2, DNSSD.UTF_8), new String(bArr3, DNSSD.UTF_8)));
            }

            public void serviceLost(DNSSDService dNSSDService, int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
                DNSSD.this.handler.post(new DNSSD$1$$ExternalSyntheticLambda2(browseListener, internalDNSSDServiceArr, i, i2, new String(bArr, DNSSD.UTF_8), new String(bArr2, DNSSD.UTF_8), new String(bArr3, DNSSD.UTF_8)));
            }

            public void operationFailed(DNSSDService dNSSDService, int i) {
                DNSSD.this.handler.post(new DNSSD$1$$ExternalSyntheticLambda0(browseListener, internalDNSSDServiceArr, i));
            }
        }))};
        return internalDNSSDServiceArr[0];
    }

    public DNSSDService browse(String str, BrowseListener browseListener) throws DNSSDException {
        return browse(0, 0, str, "", browseListener);
    }

    public DNSSDService resolve(int i, int i2, String str, String str2, String str3, final ResolveListener resolveListener) throws DNSSDException {
        onServiceStarting();
        final DNSSD$$ExternalSyntheticLambda1 dNSSD$$ExternalSyntheticLambda1 = new DNSSD$$ExternalSyntheticLambda1(r0);
        final DNSSDService[] dNSSDServiceArr = {new InternalDNSSDService(this, InternalDNSSD.resolve(i, i2, str, str2, str3, new InternalResolveListener() {
            public void serviceResolved(DNSSDService dNSSDService, int i, int i2, byte[] bArr, byte[] bArr2, int i3, TXTRecord tXTRecord) {
                String str = new String(bArr, DNSSD.UTF_8);
                String str2 = new String(bArr2, DNSSD.UTF_8);
                Map<String, String> parseTXTRecords = DNSSD.parseTXTRecords(tXTRecord);
                DNSSD.this.handler.removeCallbacks(dNSSD$$ExternalSyntheticLambda1);
                DNSSD.this.handler.post(new DNSSD$2$$ExternalSyntheticLambda1(resolveListener, dNSSDServiceArr, i, i2, str, str2, i3, parseTXTRecords));
            }

            static /* synthetic */ void lambda$serviceResolved$0(ResolveListener resolveListener, DNSSDService[] dNSSDServiceArr, int i, int i2, String str, String str2, int i3, Map map) {
                resolveListener.serviceResolved(dNSSDServiceArr[0], i, i2, str, str2, i3, map);
                dNSSDServiceArr[0].stop();
            }

            public void operationFailed(DNSSDService dNSSDService, int i) {
                DNSSD.this.handler.removeCallbacks(dNSSD$$ExternalSyntheticLambda1);
                DNSSD.this.handler.post(new DNSSD$2$$ExternalSyntheticLambda0(resolveListener, dNSSDServiceArr, i));
            }

            static /* synthetic */ void lambda$operationFailed$1(ResolveListener resolveListener, DNSSDService[] dNSSDServiceArr, int i) {
                resolveListener.operationFailed(dNSSDServiceArr[0], i);
                dNSSDServiceArr[0].stop();
            }
        }))};
        this.handler.postDelayed(dNSSD$$ExternalSyntheticLambda1, (long) this.serviceTimeout);
        return dNSSDServiceArr[0];
    }

    public DNSSDRegistration register(int i, int i2, String str, String str2, String str3, String str4, int i3, TXTRecord tXTRecord, RegisterListener registerListener) throws DNSSDException {
        onServiceStarting();
        final RegisterListener registerListener2 = registerListener;
        final DNSSDRegistration[] dNSSDRegistrationArr = {new InternalDNSSDRegistration(this, InternalDNSSD.register(i, i2, str, str2, str3, str4, i3, tXTRecord, new InternalRegisterListener() {
            public void serviceRegistered(DNSSDRegistration dNSSDRegistration, int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
                DNSSD.this.handler.post(new DNSSD$3$$ExternalSyntheticLambda1(registerListener2, dNSSDRegistrationArr, i, new String(bArr, DNSSD.UTF_8), new String(bArr2, DNSSD.UTF_8), new String(bArr3, DNSSD.UTF_8)));
            }

            public void operationFailed(DNSSDService dNSSDService, int i) {
                DNSSD.this.handler.post(new DNSSD$3$$ExternalSyntheticLambda0(registerListener2, dNSSDRegistrationArr, i));
            }
        }))};
        return dNSSDRegistrationArr[0];
    }

    public DNSSDService register(String str, String str2, int i, RegisterListener registerListener) throws DNSSDException {
        return register(0, 0, str, str2, (String) null, (String) null, i, (TXTRecord) null, registerListener);
    }

    public InternalDNSSDRecordRegistrar createRecordRegistrar(RegisterRecordListener registerRecordListener) throws DNSSDException {
        onServiceStarting();
        return new InternalDNSSDRecordRegistrar(this, InternalDNSSD.createRecordRegistrar(registerRecordListener));
    }

    public DNSSDService queryRecord(int i, int i2, String str, int i3, int i4, QueryListener queryListener) throws DNSSDException {
        return queryRecord(i, i2, str, i3, i4, false, queryListener);
    }

    public DNSSDService queryRecord(int i, int i2, String str, int i3, int i4, boolean z, QueryListener queryListener) throws DNSSDException {
        onServiceStarting();
        final DNSSD$$ExternalSyntheticLambda0 dNSSD$$ExternalSyntheticLambda0 = new DNSSD$$ExternalSyntheticLambda0(r1);
        final QueryListener queryListener2 = queryListener;
        final DNSSDService[] dNSSDServiceArr = {new InternalDNSSDService(this, InternalDNSSD.queryRecord(i, i2, str, i3, i4, new InternalQueryListener() {
            public void queryAnswered(DNSSDService dNSSDService, int i, int i2, byte[] bArr, int i3, int i4, byte[] bArr2, int i5) {
                String str = new String(bArr, DNSSD.UTF_8);
                DNSSD.this.handler.removeCallbacks(dNSSD$$ExternalSyntheticLambda0);
                DNSSD.this.handler.post(new DNSSD$4$$ExternalSyntheticLambda1(queryListener2, dNSSDServiceArr, i, i2, str, i3, i4, bArr2, i5));
            }

            static /* synthetic */ void lambda$queryAnswered$0(QueryListener queryListener, DNSSDService[] dNSSDServiceArr, int i, int i2, String str, int i3, int i4, byte[] bArr, int i5) {
                queryListener.queryAnswered(dNSSDServiceArr[0], i, i2, str, i3, i4, bArr, i5);
                dNSSDServiceArr[0].stop();
            }

            public void operationFailed(DNSSDService dNSSDService, int i) {
                DNSSD.this.handler.removeCallbacks(dNSSD$$ExternalSyntheticLambda0);
                DNSSD.this.handler.post(new DNSSD$4$$ExternalSyntheticLambda0(queryListener2, dNSSDServiceArr, i));
            }

            static /* synthetic */ void lambda$operationFailed$1(QueryListener queryListener, DNSSDService[] dNSSDServiceArr, int i) {
                queryListener.operationFailed(dNSSDServiceArr[0], i);
                dNSSDServiceArr[0].stop();
            }
        }))};
        if (z) {
            this.handler.postDelayed(dNSSD$$ExternalSyntheticLambda0, (long) this.serviceTimeout);
        }
        return dNSSDServiceArr[0];
    }

    public DNSSDService enumerateDomains(int i, int i2, final DomainListener domainListener) throws DNSSDException {
        onServiceStarting();
        final DNSSDService[] dNSSDServiceArr = {new InternalDNSSDService(this, InternalDNSSD.enumerateDomains(i, i2, new InternalDomainListener() {
            public void domainFound(DNSSDService dNSSDService, int i, int i2, byte[] bArr) {
                DNSSD.this.handler.post(new DNSSD$5$$ExternalSyntheticLambda1(domainListener, dNSSDServiceArr, i, i2, new String(bArr, DNSSD.UTF_8)));
            }

            public void domainLost(DNSSDService dNSSDService, int i, int i2, byte[] bArr) {
                DNSSD.this.handler.post(new DNSSD$5$$ExternalSyntheticLambda2(domainListener, dNSSDServiceArr, i, i2, new String(bArr, DNSSD.UTF_8)));
            }

            public void operationFailed(DNSSDService dNSSDService, int i) {
                DNSSD.this.handler.post(new DNSSD$5$$ExternalSyntheticLambda0(domainListener, dNSSDServiceArr, i));
            }
        }))};
        return dNSSDServiceArr[0];
    }

    public String constructFullName(String str, String str2, String str3) throws DNSSDException {
        onServiceStarting();
        String constructFullName = InternalDNSSD.constructFullName(str, str2, str3);
        onServiceStopped();
        return constructFullName;
    }

    public void reconfirmRecord(int i, int i2, String str, int i3, int i4, byte[] bArr) {
        onServiceStarting();
        InternalDNSSD.reconfirmRecord(i, i2, str, i3, i4, bArr);
        onServiceStopped();
    }

    public void onServiceStarting() {
        if (this.multicastLock == null) {
            synchronized (this) {
                if (this.multicastLock == null) {
                    WifiManager wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi");
                    if (wifiManager == null) {
                        Log.wtf("DNSSD", "Can't get WIFI Service");
                        return;
                    } else {
                        this.multicastLock = wifiManager.createMulticastLock(MULTICAST_LOCK_NAME);
                        this.multicastLock.setReferenceCounted(true);
                    }
                }
            }
        }
        this.multicastLock.acquire();
    }

    public void onServiceStopped() {
        if (this.multicastLock == null) {
            Log.wtf("DNSSD", "Multicast lock doesn't exist");
        } else {
            this.multicastLock.release();
        }
    }

    public static int getIfIndexForName(String str) {
        return InternalDNSSD.getIfIndexForName(str);
    }

    public static Map<String, String> parseTXTRecords(byte[] bArr) {
        return parseTXTRecords(new TXTRecord(bArr));
    }

    static Map<String, String> parseTXTRecords(TXTRecord tXTRecord) {
        HashMap hashMap = new HashMap(tXTRecord.size());
        for (int i = 0; i < tXTRecord.size(); i++) {
            try {
                if (!TextUtils.isEmpty(tXTRecord.getKey(i)) && !TextUtils.isEmpty(tXTRecord.getValueAsString(i))) {
                    hashMap.put(tXTRecord.getKey(i), tXTRecord.getValueAsString(i));
                }
            } catch (Exception e) {
                Log.w("RxResolveListener", "Parsing error of " + i + " TXT record", e);
            }
        }
        return hashMap;
    }
}
