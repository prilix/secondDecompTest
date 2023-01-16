package p012io.reactivex.internal.operators.completable;

import java.util.concurrent.Callable;
import p012io.reactivex.Completable;
import p012io.reactivex.CompletableObserver;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.disposables.Disposables;
import p012io.reactivex.exceptions.Exceptions;
import p012io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.completable.CompletableFromCallable */
public final class CompletableFromCallable extends Completable {
    final Callable<?> callable;

    public CompletableFromCallable(Callable<?> callable2) {
        this.callable = callable2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        Disposable empty = Disposables.empty();
        completableObserver.onSubscribe(empty);
        try {
            this.callable.call();
            if (!empty.isDisposed()) {
                completableObserver.onComplete();
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (!empty.isDisposed()) {
                completableObserver.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }
    }
}
