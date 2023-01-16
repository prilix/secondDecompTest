package p012io.reactivex.internal.operators.completable;

import org.reactivestreams.Subscriber;
import p012io.reactivex.CompletableSource;
import p012io.reactivex.Flowable;
import p012io.reactivex.internal.observers.SubscriberCompletableObserver;

/* renamed from: io.reactivex.internal.operators.completable.CompletableToFlowable */
public final class CompletableToFlowable<T> extends Flowable<T> {
    final CompletableSource source;

    public CompletableToFlowable(CompletableSource completableSource) {
        this.source = completableSource;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new SubscriberCompletableObserver(subscriber));
    }
}
