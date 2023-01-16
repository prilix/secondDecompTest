package p012io.reactivex.internal.operators.completable;

import p012io.reactivex.Completable;
import p012io.reactivex.CompletableObserver;
import p012io.reactivex.internal.disposables.EmptyDisposable;

/* renamed from: io.reactivex.internal.operators.completable.CompletableError */
public final class CompletableError extends Completable {
    final Throwable error;

    public CompletableError(Throwable th) {
        this.error = th;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        EmptyDisposable.error(this.error, completableObserver);
    }
}
