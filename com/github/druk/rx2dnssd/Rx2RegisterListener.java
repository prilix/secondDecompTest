package com.github.druk.rx2dnssd;

import com.github.druk.dnssd.DNSSDRegistration;
import com.github.druk.dnssd.DNSSDService;
import com.github.druk.dnssd.RegisterListener;
import com.github.druk.rx2dnssd.BonjourService;
import p012io.reactivex.FlowableEmitter;

class Rx2RegisterListener implements RegisterListener {
    private final FlowableEmitter<? super BonjourService> emitter;

    Rx2RegisterListener(FlowableEmitter<? super BonjourService> flowableEmitter) {
        this.emitter = flowableEmitter;
    }

    public void serviceRegistered(DNSSDRegistration dNSSDRegistration, int i, String str, String str2, String str3) {
        if (!this.emitter.isCancelled()) {
            this.emitter.onNext(new BonjourService.Builder(i, 0, str, str2, str3).build());
        }
    }

    public void operationFailed(DNSSDService dNSSDService, int i) {
        if (!this.emitter.isCancelled()) {
            FlowableEmitter<? super BonjourService> flowableEmitter = this.emitter;
            flowableEmitter.onError(new RuntimeException("DNSSD browse error: " + i));
        }
    }
}
