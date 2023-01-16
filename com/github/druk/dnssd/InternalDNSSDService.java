package com.github.druk.dnssd;

class InternalDNSSDService implements DNSSDService {
    private boolean isStopped = false;
    private final DnssdServiceListener listener;
    private final DNSSDService originalDNSSDService;

    interface DnssdServiceListener {
        void onServiceStarting();

        void onServiceStopped();
    }

    InternalDNSSDService(DnssdServiceListener dnssdServiceListener, DNSSDService dNSSDService) {
        this.listener = dnssdServiceListener;
        this.originalDNSSDService = dNSSDService;
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
