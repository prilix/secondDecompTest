package p020ua.naiksoftware.stomp.provider;

import com.accord.common.utils.Logger;
import p012io.reactivex.Completable;
import p012io.reactivex.Observable;
import p012io.reactivex.subjects.PublishSubject;
import p020ua.naiksoftware.stomp.dto.LifecycleEvent;

/* renamed from: ua.naiksoftware.stomp.provider.AbstractConnectionProvider */
public abstract class AbstractConnectionProvider implements ConnectionProvider {
    private static final String TAG = "AbstractConnectionProvider";
    private final PublishSubject<LifecycleEvent> lifecycleStream = PublishSubject.create();
    private final PublishSubject<String> messagesStream = PublishSubject.create();

    /* access modifiers changed from: protected */
    public abstract void createWebSocketConnection();

    /* access modifiers changed from: protected */
    public abstract Object getSocket();

    /* access modifiers changed from: protected */
    public abstract void rawDisconnect();

    /* access modifiers changed from: protected */
    public abstract void rawSend(String str);

    public Observable<String> messages() {
        return this.messagesStream.startWith(initSocket().toObservable());
    }

    public Completable disconnect() {
        return Completable.fromAction(new AbstractConnectionProvider$$ExternalSyntheticLambda1(this));
    }

    private Completable initSocket() {
        return Completable.fromAction(new AbstractConnectionProvider$$ExternalSyntheticLambda0(this));
    }

    public Completable send(String str) {
        return Completable.fromCallable(new AbstractConnectionProvider$$ExternalSyntheticLambda2(this, str));
    }

    /* renamed from: lambda$send$0$ua-naiksoftware-stomp-provider-AbstractConnectionProvider */
    public /* synthetic */ Object mo42946xc20029ee(String str) throws Exception {
        if (getSocket() != null) {
            String str2 = TAG;
            Logger.m45d(str2, "Send STOMP message: " + str);
            rawSend(str);
            return null;
        }
        throw new IllegalStateException("Not connected");
    }

    /* access modifiers changed from: protected */
    public void emitLifecycleEvent(LifecycleEvent lifecycleEvent) {
        String str = TAG;
        Logger.m45d(str, "Emit lifecycle event: " + lifecycleEvent.getType().name());
        this.lifecycleStream.onNext(lifecycleEvent);
    }

    /* access modifiers changed from: protected */
    public void emitMessage(String str) {
        String str2 = TAG;
        Logger.m45d(str2, "Receive STOMP message: " + str);
        this.messagesStream.onNext(str);
    }

    public Observable<LifecycleEvent> lifecycle() {
        return this.lifecycleStream;
    }
}
