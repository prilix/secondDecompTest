package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil;

import android.content.Context;
import android.net.nsd.NsdManager;
import com.github.druk.rx2dnssd.Rx2Dnssd;
import com.github.druk.rx2dnssd.Rx2DnssdEmbedded;

public class MDnsDiscovery implements IMDnsDiscovery {
    private static final boolean USE_BONJOUR = false;
    private final String TAG = "BroadcastHelper";
    private Context context;
    private NsdManager.DiscoveryListener mDiscoveryListener;
    private Rx2Dnssd mRxDnssd;
    private NsdManager nsdManager;
    private boolean registered = false;
    private String serviceType = "";

    public NsdManager getNsdManager() {
        return this.nsdManager;
    }

    public MDnsDiscovery(Context context2, String str, NsdManager.DiscoveryListener discoveryListener) {
        this.mDiscoveryListener = discoveryListener;
        this.serviceType = str;
        this.nsdManager = (NsdManager) context2.getSystemService("servicediscovery");
        this.context = context2;
    }

    public void startDiscovery() {
        this.mRxDnssd = new Rx2DnssdEmbedded(this.context);
        if (!this.registered) {
            this.nsdManager.discoverServices(this.serviceType, 1, this.mDiscoveryListener);
            this.registered = true;
        }
    }

    public void stopDiscovery() {
        if (this.registered) {
            NsdManager nsdManager2 = this.nsdManager;
            if (nsdManager2 != null) {
                nsdManager2.stopServiceDiscovery(this.mDiscoveryListener);
            }
            this.registered = false;
        }
    }
}
