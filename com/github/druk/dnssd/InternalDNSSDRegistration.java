package com.github.druk.dnssd;

import com.github.druk.dnssd.InternalDNSSDService;

class InternalDNSSDRegistration implements DNSSDRegistration {
    private boolean isStopped = false;
    private final InternalDNSSDService.DnssdServiceListener listener;
    private final DNSSDRegistration originalDNSSDService;

    InternalDNSSDRegistration(InternalDNSSDService.DnssdServiceListener dnssdServiceListener, DNSSDRegistration dNSSDRegistration) {
        this.listener = dnssdServiceListener;
        this.originalDNSSDService = dNSSDRegistration;
    }

    public DNSRecord getTXTRecord() throws DNSSDException {
        return this.originalDNSSDService.getTXTRecord();
    }

    public DNSRecord addRecord(int i, int i2, byte[] bArr, int i3) throws DNSSDException {
        return this.originalDNSSDService.addRecord(i, i2, bArr, i3);
    }

    public void stop() {
        this.originalDNSSDService.stop();
        synchronized (this) {
            if (!this.isStopped) {
                this.listener.onServiceStopped();
                this.isStopped = true;
            }
        }
    }
}
