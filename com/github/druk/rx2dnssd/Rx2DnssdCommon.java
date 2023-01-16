package com.github.druk.rx2dnssd;

import com.github.druk.dnssd.DNSSD;
import com.github.druk.dnssd.DNSSDException;
import com.github.druk.dnssd.DNSSDService;
import com.github.druk.dnssd.TXTRecord;
import com.github.druk.rx2dnssd.BonjourService;
import java.util.Map;
import org.reactivestreams.Publisher;
import p012io.reactivex.BackpressureStrategy;
import p012io.reactivex.Flowable;
import p012io.reactivex.FlowableEmitter;
import p012io.reactivex.FlowableOnSubscribe;
import p012io.reactivex.FlowableTransformer;
import p012io.reactivex.functions.Action;

abstract class Rx2DnssdCommon implements Rx2Dnssd {
    private final DNSSD mDNSSD;

    private interface DNSSDServiceCreator<T> {
        DNSSDService getService(FlowableEmitter<? super T> flowableEmitter) throws DNSSDException;
    }

    Rx2DnssdCommon(DNSSD dnssd) {
        this.mDNSSD = dnssd;
    }

    public DNSSD getDNSSD() {
        return this.mDNSSD;
    }

    public Flowable<BonjourService> browse(String str, String str2) {
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda2(this, str, str2));
    }

    /* renamed from: lambda$browse$0$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m757lambda$browse$0$comgithubdrukrx2dnssdRx2DnssdCommon(String str, String str2, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.browse(0, 0, str, str2, new Rx2BrowseListener(flowableEmitter));
    }

    /* renamed from: lambda$resolve$3$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m776lambda$resolve$3$comgithubdrukrx2dnssdRx2DnssdCommon(Flowable flowable) {
        return flowable.flatMap(new Rx2DnssdCommon$$ExternalSyntheticLambda12(this));
    }

    public FlowableTransformer<BonjourService, BonjourService> resolve() {
        return new Rx2DnssdCommon$$ExternalSyntheticLambda7(this);
    }

    /* renamed from: lambda$null$2$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m764lambda$null$2$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService) throws Exception {
        if ((bonjourService.getFlags() & 256) == 256) {
            return Flowable.just(bonjourService);
        }
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda0(this, bonjourService));
    }

    /* renamed from: lambda$null$1$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m758lambda$null$1$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.resolve(bonjourService.getFlags(), bonjourService.getIfIndex(), bonjourService.getServiceName(), bonjourService.getRegType(), bonjourService.getDomain(), new Rx2ResolveListener(flowableEmitter, bonjourService));
    }

    /* renamed from: lambda$queryRecords$7$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m774lambda$queryRecords$7$comgithubdrukrx2dnssdRx2DnssdCommon(Flowable flowable) {
        return flowable.flatMap(new Rx2DnssdCommon$$ExternalSyntheticLambda13(this));
    }

    @Deprecated
    public FlowableTransformer<BonjourService, BonjourService> queryRecords() {
        return new Rx2DnssdCommon$$ExternalSyntheticLambda6(this);
    }

    /* renamed from: lambda$null$6$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m767lambda$null$6$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService) throws Exception {
        if ((bonjourService.getFlags() & 256) == 256) {
            return Flowable.just(bonjourService);
        }
        BonjourService.Builder builder = new BonjourService.Builder(bonjourService);
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda17(this, bonjourService, builder)).mergeWith(createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda18(this, bonjourService, builder)));
    }

    /* renamed from: lambda$null$4$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m765lambda$null$4$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, BonjourService.Builder builder, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 1, 1, true, new Rx2QueryListener(flowableEmitter, builder, true));
    }

    /* renamed from: lambda$null$5$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m766lambda$null$5$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, BonjourService.Builder builder, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 28, 1, true, new Rx2QueryListener(flowableEmitter, builder, true));
    }

    /* renamed from: lambda$queryIPRecords$11$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m770lambda$queryIPRecords$11$comgithubdrukrx2dnssdRx2DnssdCommon(Flowable flowable) {
        return flowable.flatMap(new Rx2DnssdCommon$$ExternalSyntheticLambda8(this));
    }

    public FlowableTransformer<BonjourService, BonjourService> queryIPRecords() {
        return new Rx2DnssdCommon$$ExternalSyntheticLambda3(this);
    }

    /* renamed from: lambda$null$10$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m759lambda$null$10$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService) throws Exception {
        if ((bonjourService.getFlags() & 256) == 256) {
            return Flowable.just(bonjourService);
        }
        BonjourService.Builder builder = new BonjourService.Builder(bonjourService);
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda19(this, bonjourService, builder)).mergeWith(createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda20(this, bonjourService, builder)));
    }

    /* renamed from: lambda$null$8$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m768lambda$null$8$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, BonjourService.Builder builder, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 1, 1, true, new Rx2QueryListener(flowableEmitter, builder, true));
    }

    /* renamed from: lambda$null$9$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m769lambda$null$9$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, BonjourService.Builder builder, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 28, 1, true, new Rx2QueryListener(flowableEmitter, builder, true));
    }

    /* renamed from: lambda$queryIPV4Records$14$com-github-druk-rx2dnssd-Rx2DnssdCommon */
    public /* synthetic */ Publisher mo14784xa7efd3de(Flowable flowable) {
        return flowable.flatMap(new Rx2DnssdCommon$$ExternalSyntheticLambda9(this));
    }

    public FlowableTransformer<BonjourService, BonjourService> queryIPV4Records() {
        return new Rx2DnssdCommon$$ExternalSyntheticLambda4(this);
    }

    /* renamed from: lambda$null$13$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m761lambda$null$13$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService) throws Exception {
        if ((bonjourService.getFlags() & 256) == 256) {
            return Flowable.just(bonjourService);
        }
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda11(this, bonjourService));
    }

    /* renamed from: lambda$null$12$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m760lambda$null$12$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 1, 1, true, new Rx2QueryListener(flowableEmitter, new BonjourService.Builder(bonjourService), true));
    }

    /* renamed from: lambda$queryIPV6Records$17$com-github-druk-rx2dnssd-Rx2DnssdCommon */
    public /* synthetic */ Publisher mo14785xd4e69063(Flowable flowable) {
        return flowable.flatMap(new Rx2DnssdCommon$$ExternalSyntheticLambda10(this));
    }

    public FlowableTransformer<BonjourService, BonjourService> queryIPV6Records() {
        return new Rx2DnssdCommon$$ExternalSyntheticLambda5(this);
    }

    /* renamed from: lambda$null$16$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ Publisher m763lambda$null$16$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService) throws Exception {
        if ((bonjourService.getFlags() & 256) == 256) {
            return Flowable.just(bonjourService);
        }
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda14(this, bonjourService));
    }

    /* renamed from: lambda$null$15$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m762lambda$null$15$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 28, 1, true, new Rx2QueryListener(flowableEmitter, new BonjourService.Builder(bonjourService), true));
    }

    public Flowable<BonjourService> queryIPRecords(BonjourService bonjourService) {
        if ((bonjourService.getFlags() & 256) == 256) {
            return Flowable.just(bonjourService);
        }
        BonjourService.Builder builder = new BonjourService.Builder(bonjourService);
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda21(this, bonjourService, builder)).mergeWith(createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda1(this, bonjourService, builder)));
    }

    /* renamed from: lambda$queryIPRecords$18$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m771lambda$queryIPRecords$18$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, BonjourService.Builder builder, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 1, 1, false, new Rx2QueryListener(flowableEmitter, builder, false));
    }

    /* renamed from: lambda$queryIPRecords$19$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m772lambda$queryIPRecords$19$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, BonjourService.Builder builder, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), 28, 1, false, new Rx2QueryListener(flowableEmitter, builder, false));
    }

    public Flowable<BonjourService> queryIPV4Records(BonjourService bonjourService) {
        return queryRecords(bonjourService, 1);
    }

    public Flowable<BonjourService> queryIPV6Records(BonjourService bonjourService) {
        return queryRecords(bonjourService, 28);
    }

    public Flowable<BonjourService> queryTXTRecords(BonjourService bonjourService) {
        return queryRecords(bonjourService, 16);
    }

    private Flowable<BonjourService> queryRecords(BonjourService bonjourService, int i) {
        if ((bonjourService.getFlags() & 256) == 256) {
            return Flowable.just(bonjourService);
        }
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda16(this, bonjourService, i));
    }

    /* renamed from: lambda$queryRecords$20$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m773lambda$queryRecords$20$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, int i, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.queryRecord(0, bonjourService.getIfIndex(), bonjourService.getHostname(), i, 1, false, new Rx2QueryListener(flowableEmitter, new BonjourService.Builder(bonjourService), false));
    }

    public Flowable<BonjourService> register(BonjourService bonjourService) {
        return createFlowable(new Rx2DnssdCommon$$ExternalSyntheticLambda15(this, bonjourService));
    }

    /* renamed from: lambda$register$21$com-github-druk-rx2dnssd-Rx2DnssdCommon  reason: not valid java name */
    public /* synthetic */ DNSSDService m775lambda$register$21$comgithubdrukrx2dnssdRx2DnssdCommon(BonjourService bonjourService, FlowableEmitter flowableEmitter) throws DNSSDException {
        return this.mDNSSD.register(bonjourService.getFlags(), bonjourService.getIfIndex(), bonjourService.getServiceName(), bonjourService.getRegType(), bonjourService.getDomain(), (String) null, bonjourService.getPort(), createTxtRecord(bonjourService.getTxtRecords()), new Rx2RegisterListener(flowableEmitter));
    }

    private static class DNSSDServiceAction<T> implements FlowableOnSubscribe<T>, Action {
        private final DNSSDServiceCreator<T> creator;
        private DNSSDService service;

        DNSSDServiceAction(DNSSDServiceCreator<T> dNSSDServiceCreator) {
            this.creator = dNSSDServiceCreator;
        }

        public void subscribe(FlowableEmitter<T> flowableEmitter) throws Exception {
            DNSSDServiceCreator<T> dNSSDServiceCreator;
            if (!flowableEmitter.isCancelled() && (dNSSDServiceCreator = this.creator) != null) {
                try {
                    this.service = dNSSDServiceCreator.getService(flowableEmitter);
                } catch (DNSSDException e) {
                    flowableEmitter.onError(e);
                }
            }
        }

        public void run() throws Exception {
            DNSSDService dNSSDService = this.service;
            if (dNSSDService != null) {
                dNSSDService.stop();
                this.service = null;
            }
        }
    }

    private <T> Flowable<T> createFlowable(DNSSDServiceCreator<T> dNSSDServiceCreator) {
        DNSSDServiceAction dNSSDServiceAction = new DNSSDServiceAction(dNSSDServiceCreator);
        return Flowable.create(dNSSDServiceAction, BackpressureStrategy.BUFFER).doFinally(dNSSDServiceAction);
    }

    private static TXTRecord createTxtRecord(Map<String, String> map) {
        TXTRecord tXTRecord = new TXTRecord();
        for (Map.Entry next : map.entrySet()) {
            tXTRecord.set((String) next.getKey(), (String) next.getValue());
        }
        return tXTRecord;
    }
}
