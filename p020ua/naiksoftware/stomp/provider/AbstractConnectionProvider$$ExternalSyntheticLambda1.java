package p020ua.naiksoftware.stomp.provider;

import p012io.reactivex.functions.Action;

/* renamed from: ua.naiksoftware.stomp.provider.AbstractConnectionProvider$$ExternalSyntheticLambda1 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractConnectionProvider$$ExternalSyntheticLambda1 implements Action {
    public final /* synthetic */ AbstractConnectionProvider f$0;

    public /* synthetic */ AbstractConnectionProvider$$ExternalSyntheticLambda1(AbstractConnectionProvider abstractConnectionProvider) {
        this.f$0 = abstractConnectionProvider;
    }

    public final void run() {
        this.f$0.rawDisconnect();
    }
}
