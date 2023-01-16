package p012io.reactivex.internal.operators.completable;

import p012io.reactivex.Completable;
import p012io.reactivex.CompletableObserver;
import p012io.reactivex.ObservableSource;
import p012io.reactivex.Observer;
import p012io.reactivex.disposables.Disposable;

/* renamed from: io.reactivex.internal.operators.completable.CompletableFromObservable */
public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    public CompletableFromObservable(ObservableSource<T> observableSource) {
        this.observable = observableSource;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        this.observable.subscribe(new CompletableFromObservableObserver(completableObserver));
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableFromObservable$CompletableFromObservableObserver */
    static final class CompletableFromObservableObserver<T> implements Observer<T> {

        /* renamed from: co */
        final CompletableObserver f521co;

        public void onNext(T t) {
        }

        CompletableFromObservableObserver(CompletableObserver completableObserver) {
            this.f521co = completableObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.f521co.onSubscribe(disposable);
        }

        public void onError(Throwable th) {
            this.f521co.onError(th);
        }

        public void onComplete() {
            this.f521co.onComplete();
        }
    }
}
