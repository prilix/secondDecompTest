package com.github.druk.rx2dnssd;

import com.github.druk.dnssd.BrowseListener;
import com.github.druk.dnssd.DNSSDService;
import com.github.druk.rx2dnssd.BonjourService;
import p012io.reactivex.FlowableEmitter;

class Rx2BrowseListener implements BrowseListener {
    private final FlowableEmitter<? super BonjourService> emitter;

    Rx2BrowseListener(FlowableEmitter<? super BonjourService> flowableEmitter) {
        this.emitter = flowableEmitter;
    }

    public void serviceFound(DNSSDService dNSSDService, int i, int i2, String str, String str2, String str3) {
        if (!this.emitter.isCancelled()) {
            this.emitter.onNext(new BonjourService.Builder(i, i2, str, str2, str3).build());
        }
    }

    public void serviceLost(DNSSDService dNSSDService, int i, int i2, String str, String str2, String str3) {
        if (!this.emitter.isCancelled()) {
            this.emitter.onNext(new BonjourService.Builder(i | 256, i2, str, str2, str3).build());
        }
    }

    public void operationFailed(DNSSDService dNSSDService, int i) {
        if (!this.emitter.isCancelled()) {
            FlowableEmitter<? super BonjourService> flowableEmitter = this.emitter;
            flowableEmitter.onError(new RuntimeException("DNSSD browse error: " + i));
        }
    }
}
