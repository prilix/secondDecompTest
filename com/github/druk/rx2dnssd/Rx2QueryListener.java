package com.github.druk.rx2dnssd;

import com.github.druk.dnssd.DNSSD;
import com.github.druk.dnssd.DNSSDService;
import com.github.druk.dnssd.QueryListener;
import com.github.druk.rx2dnssd.BonjourService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import p012io.reactivex.FlowableEmitter;

class Rx2QueryListener implements QueryListener {
    private final BonjourService.Builder builder;
    private final boolean completable;
    private final FlowableEmitter<? super BonjourService> emitter;

    Rx2QueryListener(FlowableEmitter<? super BonjourService> flowableEmitter, BonjourService.Builder builder2, boolean z) {
        this.emitter = flowableEmitter;
        this.builder = builder2;
        this.completable = z;
    }

    public void queryAnswered(DNSSDService dNSSDService, int i, int i2, String str, int i3, int i4, byte[] bArr, int i5) {
        if (!this.emitter.isCancelled()) {
            if (i3 == 1 || i3 == 28) {
                try {
                    this.builder.inetAddress(InetAddress.getByAddress(bArr));
                } catch (UnknownHostException e) {
                    this.emitter.tryOnError(e);
                }
            } else if (i3 == 16) {
                this.builder.dnsRecords(DNSSD.parseTXTRecords(bArr));
            } else {
                FlowableEmitter<? super BonjourService> flowableEmitter = this.emitter;
                flowableEmitter.tryOnError(new Exception("Unsupported type of record: " + i3));
            }
            this.emitter.onNext(this.builder.build());
            if (this.completable) {
                this.emitter.onComplete();
            }
        }
    }

    public void operationFailed(DNSSDService dNSSDService, int i) {
        if (!this.emitter.isCancelled()) {
            FlowableEmitter<? super BonjourService> flowableEmitter = this.emitter;
            flowableEmitter.onError(new RuntimeException("DNSSD queryRecord error: " + i));
        }
    }
}
