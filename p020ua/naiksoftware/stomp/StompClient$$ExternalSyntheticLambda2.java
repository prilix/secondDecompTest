package p020ua.naiksoftware.stomp;

import p012io.reactivex.functions.Predicate;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda2 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ HeartBeatTask f$0;

    public /* synthetic */ StompClient$$ExternalSyntheticLambda2(HeartBeatTask heartBeatTask) {
        this.f$0 = heartBeatTask;
    }

    public final boolean test(Object obj) {
        return this.f$0.consumeHeartBeat((StompMessage) obj);
    }
}
