package p012io.reactivex.internal.operators.completable;

import p012io.reactivex.Completable;
import p012io.reactivex.CompletableObserver;
import p012io.reactivex.CompletableSource;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.completable.CompletableHide */
public final class CompletableHide extends Completable {
    final CompletableSource source;

    public CompletableHide(CompletableSource completableSource) {
        this.source = completableSource;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe(new HideCompletableObserver(completableObserver));
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableHide$HideCompletableObserver */
    static final class HideCompletableObserver implements CompletableObserver, Disposable {
        final CompletableObserver downstream;
        Disposable upstream;

        HideCompletableObserver(CompletableObserver completableObserver) {
            this.downstream = completableObserver;
        }

        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
