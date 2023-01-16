package com.github.druk.rx2dnssd;

import com.github.druk.dnssd.DNSSDService;
import com.github.druk.rx2dnssd.Rx2DnssdCommon;
import p012io.reactivex.FlowableEmitter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Rx2DnssdCommon$$ExternalSyntheticLambda16 implements Rx2DnssdCommon.DNSSDServiceCreator {
    public final /* synthetic */ Rx2DnssdCommon f$0;
    public final /* synthetic */ BonjourService f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ Rx2DnssdCommon$$ExternalSyntheticLambda16(Rx2DnssdCommon rx2DnssdCommon, BonjourService bonjourService, int i) {
        this.f$0 = rx2DnssdCommon;
        this.f$1 = bonjourService;
        this.f$2 = i;
    }

    public final DNSSDService getService(FlowableEmitter flowableEmitter) {
        return this.f$0.m773lambda$queryRecords$20$comgithubdrukrx2dnssdRx2DnssdCommon(this.f$1, this.f$2, flowableEmitter);
    }
}
