package com.github.druk.rx2dnssd;

import p012io.reactivex.Flowable;
import p012io.reactivex.FlowableTransformer;

public interface Rx2Dnssd {
    Flowable<BonjourService> browse(String str, String str2);

    Flowable<BonjourService> queryIPRecords(BonjourService bonjourService);

    FlowableTransformer<BonjourService, BonjourService> queryIPRecords();

    Flowable<BonjourService> queryIPV4Records(BonjourService bonjourService);

    FlowableTransformer<BonjourService, BonjourService> queryIPV4Records();

    Flowable<BonjourService> queryIPV6Records(BonjourService bonjourService);

    FlowableTransformer<BonjourService, BonjourService> queryIPV6Records();

    @Deprecated
    FlowableTransformer<BonjourService, BonjourService> queryRecords();

    Flowable<BonjourService> queryTXTRecords(BonjourService bonjourService);

    Flowable<BonjourService> register(BonjourService bonjourService);

    FlowableTransformer<BonjourService, BonjourService> resolve();
}
