package com.jch.racWiFi.iduManagement;

import com.jch.racWiFi.CoreActivity;
import p012io.reactivex.functions.Consumer;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WebSocketWrapper$$ExternalSyntheticLambda9 implements Consumer {
    public final /* synthetic */ WebSocketWrapper f$0;
    public final /* synthetic */ CoreActivity f$1;

    public /* synthetic */ WebSocketWrapper$$ExternalSyntheticLambda9(WebSocketWrapper webSocketWrapper, CoreActivity coreActivity) {
        this.f$0 = webSocketWrapper;
        this.f$1 = coreActivity;
    }

    public final void accept(Object obj) {
        this.f$0.mo29530x4cf9b766(this.f$1, (LifecycleEvent) obj);
    }
}
