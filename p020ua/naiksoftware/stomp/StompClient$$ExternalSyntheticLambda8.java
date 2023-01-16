package p020ua.naiksoftware.stomp;

import p020ua.naiksoftware.stomp.HeartBeatTask;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda8 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda8 implements HeartBeatTask.SendCallback {
    public final /* synthetic */ StompClient f$0;

    public /* synthetic */ StompClient$$ExternalSyntheticLambda8(StompClient stompClient) {
        this.f$0 = stompClient;
    }

    public final void sendClientHeartBeat(String str) {
        this.f$0.sendHeartBeat(str);
    }
}
