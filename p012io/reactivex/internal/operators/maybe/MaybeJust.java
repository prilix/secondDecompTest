package p012io.reactivex.internal.operators.maybe;

import p012io.reactivex.Maybe;
import p012io.reactivex.MaybeObserver;
import p012io.reactivex.disposables.Disposables;
import p012io.reactivex.internal.fuseable.ScalarCallable;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeJust */
public final class MaybeJust<T> extends Maybe<T> implements ScalarCallable<T> {
    final T value;

    public MaybeJust(T t) {
        this.value = t;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        maybeObserver.onSubscribe(Disposables.disposed());
        maybeObserver.onSuccess(this.value);
    }

    public T call() {
        return this.value;
    }
}
