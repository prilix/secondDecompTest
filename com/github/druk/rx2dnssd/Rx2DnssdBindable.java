package com.github.druk.rx2dnssd;

import android.content.Context;
import com.github.druk.dnssd.DNSSD;
import com.github.druk.dnssd.DNSSDBindable;
import p012io.reactivex.Flowable;
import p012io.reactivex.FlowableTransformer;

public class Rx2DnssdBindable extends Rx2DnssdCommon {
    public /* bridge */ /* synthetic */ Flowable browse(String str, String str2) {
        return super.browse(str, str2);
    }

    public /* bridge */ /* synthetic */ DNSSD getDNSSD() {
        return super.getDNSSD();
    }

    public /* bridge */ /* synthetic */ Flowable queryIPRecords(BonjourService bonjourService) {
        return super.queryIPRecords(bonjourService);
    }

    public /* bridge */ /* synthetic */ FlowableTransformer queryIPRecords() {
        return super.queryIPRecords();
    }

    public /* bridge */ /* synthetic */ Flowable queryIPV4Records(BonjourService bonjourService) {
        return super.queryIPV4Records(bonjourService);
    }

    public /* bridge */ /* synthetic */ FlowableTransformer queryIPV4Records() {
        return super.queryIPV4Records();
    }

    public /* bridge */ /* synthetic */ Flowable queryIPV6Records(BonjourService bonjourService) {
        return super.queryIPV6Records(bonjourService);
    }

    public /* bridge */ /* synthetic */ FlowableTransformer queryIPV6Records() {
        return super.queryIPV6Records();
    }

    @Deprecated
    public /* bridge */ /* synthetic */ FlowableTransformer queryRecords() {
        return super.queryRecords();
    }

    public /* bridge */ /* synthetic */ Flowable queryTXTRecords(BonjourService bonjourService) {
        return super.queryTXTRecords(bonjourService);
    }

    public /* bridge */ /* synthetic */ Flowable register(BonjourService bonjourService) {
        return super.register(bonjourService);
    }

    public /* bridge */ /* synthetic */ FlowableTransformer resolve() {
        return super.resolve();
    }

    public Rx2DnssdBindable(Context context) {
        super(new DNSSDBindable(context));
    }
}
