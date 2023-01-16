package com.github.druk.dnssd;

import android.content.Context;

public final class DNSSDBindable extends DNSSD {
    private final Context context;

    public DNSSDBindable(Context context2) {
        super(context2, "jdns_sd");
        this.context = context2.getApplicationContext();
    }

    public void onServiceStarting() {
        super.onServiceStarting();
        this.context.getSystemService("servicediscovery");
    }

    public void onServiceStopped() {
        super.onServiceStopped();
    }

    public String getNameForIfIndex(int i) {
        return InternalDNSSD.getNameForIfIndex(i);
    }
}
