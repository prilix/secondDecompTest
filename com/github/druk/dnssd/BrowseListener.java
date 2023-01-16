package com.github.druk.dnssd;

public interface BrowseListener extends BaseListener {
    void serviceFound(DNSSDService dNSSDService, int i, int i2, String str, String str2, String str3);

    void serviceLost(DNSSDService dNSSDService, int i, int i2, String str, String str2, String str3);
}
