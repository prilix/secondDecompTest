package p012io.reactivex.internal.operators.completable;

import p012io.reactivex.Completable;
import p012io.reactivex.CompletableObserver;
import p012io.reactivex.SingleObserver;
import p012io.reactivex.SingleSource;
import p012io.reactivex.disposables.Disposable;

/* renamed from: io.reactivex.internal.operators.completable.CompletableFromSingle */
public final class CompletableFromSingle<T> extends Completable {
    final SingleSource<T> single;

    public CompletableFromSingle(SingleSource<T> singleSource) {
        this.single = singleSource;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        this.single.subscribe(new CompletableFromSingleObserver(completableObserver));
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableFromSingle$CompletableFromSingleObserver */
    static final class CompletableFromSingleObserver<T> implements SingleObserver<T> {

        /* renamed from: co */
        final CompletableObserver f522co;

        CompletableFromSingleObserver(CompletableObserver completableObserver) {
            this.f522co = completableObserver;
        }

        public void onError(Throwable th) {
            this.f522co.onError(th);
        }

        public void onSubscribe(Disposable disposable) {
            this.f522co.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            this.f522co.onComplete();
        }
    }
}
