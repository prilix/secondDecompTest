package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil;

import android.net.nsd.NsdManager;

public interface IMDnsDiscovery {
    NsdManager getNsdManager();

    void startDiscovery();

    void stopDiscovery();
}
