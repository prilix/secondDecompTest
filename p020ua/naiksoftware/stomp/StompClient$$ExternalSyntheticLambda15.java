package p020ua.naiksoftware.stomp;

import java.util.List;
import p012io.reactivex.functions.Consumer;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda15 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda15 implements Consumer {
    public final /* synthetic */ StompClient f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ StompClient$$ExternalSyntheticLambda15(StompClient stompClient, List list) {
        this.f$0 = stompClient;
        this.f$1 = list;
    }

    public final void accept(Object obj) {
        this.f$0.m3175lambda$connect$2$uanaiksoftwarestompStompClient(this.f$1, (LifecycleEvent) obj);
    }
}
