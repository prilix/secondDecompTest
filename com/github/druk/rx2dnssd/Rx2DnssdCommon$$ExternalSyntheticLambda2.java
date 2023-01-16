package com.github.druk.rx2dnssd;

import com.github.druk.dnssd.DNSSDService;
import com.github.druk.rx2dnssd.Rx2DnssdCommon;
import p012io.reactivex.FlowableEmitter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Rx2DnssdCommon$$ExternalSyntheticLambda2 implements Rx2DnssdCommon.DNSSDServiceCreator {
    public final /* synthetic */ Rx2DnssdCommon f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ Rx2DnssdCommon$$ExternalSyntheticLambda2(Rx2DnssdCommon rx2DnssdCommon, String str, String str2) {
        this.f$0 = rx2DnssdCommon;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final DNSSDService getService(FlowableEmitter flowableEmitter) {
        return this.f$0.m757lambda$browse$0$comgithubdrukrx2dnssdRx2DnssdCommon(this.f$1, this.f$2, flowableEmitter);
    }
}
