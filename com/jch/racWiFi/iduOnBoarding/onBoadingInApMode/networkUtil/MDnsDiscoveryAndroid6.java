package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.networkUtil;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import com.accord.common.utils.Logger;
import com.github.druk.rx2dnssd.BonjourService;
import com.github.druk.rx2dnssd.Rx2Dnssd;
import com.github.druk.rx2dnssd.Rx2DnssdEmbedded;
import p012io.reactivex.android.schedulers.AndroidSchedulers;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.schedulers.Schedulers;

public class MDnsDiscoveryAndroid6 implements IMDnsDiscovery {
    private static final boolean USE_BONJOUR = false;
    private final String TAG = "BroadcastHelper";
    private Context context;
    private NsdManager.DiscoveryListener mDiscoveryListener;
    private Disposable mDisposable;
    private Rx2Dnssd mRxDnssd;
    private boolean registered = false;
    private String serviceType = "";

    public NsdManager getNsdManager() {
        return null;
    }

    public MDnsDiscoveryAndroid6(Context context2, String str, NsdManager.DiscoveryListener discoveryListener) {
        this.mDiscoveryListener = discoveryListener;
        this.serviceType = str;
        this.mRxDnssd = new Rx2DnssdEmbedded(context2);
        this.context = context2;
    }

    public void startDiscovery() {
        this.mDisposable = this.mRxDnssd.browse(this.serviceType, "local.").compose(this.mRxDnssd.resolve()).compose(this.mRxDnssd.queryIPRecords()).subscribeOn(Schedulers.m691io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MDnsDiscoveryAndroid6$$ExternalSyntheticLambda0(this), MDnsDiscoveryAndroid6$$ExternalSyntheticLambda1.INSTANCE);
    }

    /* renamed from: lambda$startDiscovery$0$com-jch-racWiFi-iduOnBoarding-onBoadingInApMode-networkUtil-MDnsDiscoveryAndroid6 */
    public /* synthetic */ void mo31537xec7efcc8(BonjourService bonjourService) throws Exception {
        NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setAttribute("password", bonjourService.getTxtRecords().get("password"));
        nsdServiceInfo.setServiceName(bonjourService.getServiceName());
        this.mDiscoveryListener.onServiceFound(nsdServiceInfo);
        Logger.m47e("BONJOR", bonjourService.getHostname());
    }

    public void stopDiscovery() {
        if (this.registered) {
            Disposable disposable = this.mDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            this.registered = false;
        }
    }
}
