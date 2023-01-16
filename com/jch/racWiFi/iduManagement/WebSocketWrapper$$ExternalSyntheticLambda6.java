package com.jch.racWiFi.iduManagement;

import com.jch.racWiFi.iduManagement.WebSocketWrapper;
import p012io.reactivex.functions.Consumer;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WebSocketWrapper$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ WebSocketWrapper.OnStompEvents f$0;

    public /* synthetic */ WebSocketWrapper$$ExternalSyntheticLambda6(WebSocketWrapper.OnStompEvents onStompEvents) {
        this.f$0 = onStompEvents;
    }

    public final void accept(Object obj) {
        this.f$0.onStompMessageReceived((StompMessage) obj);
    }
}
