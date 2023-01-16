package com.github.druk.dnssd;

import com.github.druk.dnssd.InternalDNSSDService;

class InternalDNSSDRecordRegistrar implements DNSSDRecordRegistrar {
    private boolean isStopped = false;
    private final InternalDNSSDService.DnssdServiceListener listener;
    private final DNSSDRecordRegistrar originalService;

    InternalDNSSDRecordRegistrar(InternalDNSSDService.DnssdServiceListener dnssdServiceListener, DNSSDRecordRegistrar dNSSDRecordRegistrar) {
        this.listener = dnssdServiceListener;
        this.originalService = dNSSDRecordRegistrar;
    }

    public DNSRecord registerRecord(int i, int i2, String str, int i3, int i4, byte[] bArr, int i5) throws DNSSDException {
        return this.originalService.registerRecord(i, i2, str, i3, i4, bArr, i5);
    }

    public void stop() {
        this.originalService.stop();
        synchronized (this) {
            if (!this.isStopped) {
                this.listener.onServiceStopped();
                this.isStopped = true;
            }
        }
    }
}
