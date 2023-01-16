package p020ua.naiksoftware.stomp;

import p012io.reactivex.functions.Consumer;
import p012io.reactivex.subjects.PublishSubject;
import p020ua.naiksoftware.stomp.dto.StompMessage;

/* renamed from: ua.naiksoftware.stomp.StompClient$$ExternalSyntheticLambda13 */
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StompClient$$ExternalSyntheticLambda13 implements Consumer {
    public final /* synthetic */ PublishSubject f$0;

    public /* synthetic */ StompClient$$ExternalSyntheticLambda13(PublishSubject publishSubject) {
        this.f$0 = publishSubject;
    }

    public final void accept(Object obj) {
        this.f$0.onNext((StompMessage) obj);
    }
}
