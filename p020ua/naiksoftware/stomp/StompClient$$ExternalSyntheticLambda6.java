package p020ua.naiksoftware.stomp;

import p012io.reactivex.functions.Predicate;
import p020ua.naiksoftware.stomp.dto.StompCommand;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda6 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda6 implements Predicate {
    public static final /* synthetic */ StompClient$$ExternalSyntheticLambda6 INSTANCE = new StompClient$$ExternalSyntheticLambda6();

    private /* synthetic */ StompClient$$ExternalSyntheticLambda6() {
    }

    public final boolean test(Object obj) {
        return ((StompMessage) obj).getStompCommand().equals(StompCommand.CONNECTED);
    }
}
