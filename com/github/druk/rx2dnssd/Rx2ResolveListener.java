package com.github.druk.rx2dnssd;

import com.github.druk.dnssd.DNSSDService;
import com.github.druk.dnssd.ResolveListener;
import com.github.druk.rx2dnssd.BonjourService;
import java.util.Map;
import p012io.reactivex.FlowableEmitter;

class Rx2ResolveListener implements ResolveListener {
    private final BonjourService.Builder builder;
    private final FlowableEmitter<? super BonjourService> emitter;

    Rx2ResolveListener(FlowableEmitter<? super BonjourService> flowableEmitter, BonjourService bonjourService) {
        this.emitter = flowableEmitter;
        this.builder = new BonjourService.Builder(bonjourService);
    }

    public void serviceResolved(DNSSDService dNSSDService, int i, int i2, String str, String str2, int i3, Map<String, String> map) {
        if (!this.emitter.isCancelled()) {
            this.emitter.onNext(this.builder.port(i3).hostname(str2).dnsRecords(map).build());
            this.emitter.onComplete();
        }
    }

    public void operationFailed(DNSSDService dNSSDService, int i) {
        if (!this.emitter.isCancelled()) {
            FlowableEmitter<? super BonjourService> flowableEmitter = this.emitter;
            flowableEmitter.onError(new RuntimeException("DNSSD resolve error: " + i));
        }
    }
}
