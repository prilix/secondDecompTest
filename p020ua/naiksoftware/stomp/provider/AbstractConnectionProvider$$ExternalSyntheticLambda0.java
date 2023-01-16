package p020ua.naiksoftware.stomp.provider;

import p012io.reactivex.functions.Action;

/* renamed from: ua.naiksoftware.stomp.provider.AbstractConnectionProvider$$ExternalSyntheticLambda0 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractConnectionProvider$$ExternalSyntheticLambda0 implements Action {
    public final /* synthetic */ AbstractConnectionProvider f$0;

    public /* synthetic */ AbstractConnectionProvider$$ExternalSyntheticLambda0(AbstractConnectionProvider abstractConnectionProvider) {
        this.f$0 = abstractConnectionProvider;
    }

    public final void run() {
        this.f$0.createWebSocketConnection();
    }
}
