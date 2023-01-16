package p020ua.naiksoftware.stomp;

import p012io.reactivex.functions.Predicate;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda3 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda3 implements Predicate {
    public final /* synthetic */ StompClient f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ StompClient$$ExternalSyntheticLambda3(StompClient stompClient, String str) {
        this.f$0 = stompClient;
        this.f$1 = str;
    }

    public final boolean test(Object obj) {
        return this.f$0.m3179lambda$topic$12$uanaiksoftwarestompStompClient(this.f$1, (StompMessage) obj);
    }
}
