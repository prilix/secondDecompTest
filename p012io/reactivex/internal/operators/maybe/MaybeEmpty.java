package p012io.reactivex.internal.operators.maybe;

import p012io.reactivex.Maybe;
import p012io.reactivex.MaybeObserver;
import p012io.reactivex.internal.disposables.EmptyDisposable;
import p012io.reactivex.internal.fuseable.ScalarCallable;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeEmpty */
public final class MaybeEmpty extends Maybe<Object> implements ScalarCallable<Object> {
    public static final MaybeEmpty INSTANCE = new MaybeEmpty();

    public Object call() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super Object> maybeObserver) {
        EmptyDisposable.complete((MaybeObserver<?>) maybeObserver);
    }
}
