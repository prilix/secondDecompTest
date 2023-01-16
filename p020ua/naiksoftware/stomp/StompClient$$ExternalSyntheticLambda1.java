package p020ua.naiksoftware.stomp;

import p012io.reactivex.functions.Function;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda1 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda1 implements Function {
    public static final /* synthetic */ StompClient$$ExternalSyntheticLambda1 INSTANCE = new StompClient$$ExternalSyntheticLambda1();

    private /* synthetic */ StompClient$$ExternalSyntheticLambda1() {
    }

    public final Object apply(Object obj) {
        return StompMessage.from((String) obj);
    }
}
