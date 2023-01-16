package p020ua.naiksoftware.stomp.provider;

import p012io.reactivex.Completable;
import p012io.reactivex.Observable;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;

/* renamed from: ua.naiksoftware.stomp.provider.ConnectionProvider */
public interface ConnectionProvider {
    Completable disconnect();

    Observable<LifecycleEvent> lifecycle();

    Observable<String> messages();

    Completable send(String str);
}
