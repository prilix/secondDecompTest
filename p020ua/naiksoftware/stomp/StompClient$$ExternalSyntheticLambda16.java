package p020ua.naiksoftware.stomp;

import com.accord.common.utils.Logger;
import p012io.reactivex.functions.Consumer;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda16 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda16 implements Consumer {
    public static final /* synthetic */ StompClient$$ExternalSyntheticLambda16 INSTANCE = new StompClient$$ExternalSyntheticLambda16();

    private /* synthetic */ StompClient$$ExternalSyntheticLambda16() {
    }

    public final void accept(Object obj) {
        Logger.m48e(StompClient.TAG, "Disconnect error", (Throwable) obj);
    }
}
