package p012io.reactivex.internal.operators.observable;

import java.util.concurrent.atomic.AtomicReference;
import p012io.reactivex.Observable;
import p012io.reactivex.Observer;
import p012io.reactivex.SingleObserver;
import p012io.reactivex.SingleSource;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.ObservableConcatWithSingle */
public final class ObservableConcatWithSingle<T> extends AbstractObservableWithUpstream<T, T> {
    final SingleSource<? extends T> other;

    public ObservableConcatWithSingle(Observable<T> observable, SingleSource<? extends T> singleSource) {
        super(observable);
        this.other = singleSource;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new ConcatWithObserver(observer, this.other));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableConcatWithSingle$ConcatWithObserver */
    static final class ConcatWithObserver<T> extends AtomicReference<Disposable> implements Observer<T>, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -1953724749712440952L;
        final Observer<? super T> downstream;
        boolean inSingle;
        SingleSource<? extends T> other;

        ConcatWithObserver(Observer<? super T> observer, SingleSource<? extends T> singleSource) {
            this.downstream = observer;
            this.other = singleSource;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable) && !this.inSingle) {
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onSuccess(T t) {
            this.downstream.onNext(t);
            this.downstream.onComplete();
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.inSingle = true;
            DisposableHelper.replace(this, (Disposable) null);
            SingleSource<? extends T> singleSource = this.other;
            this.other = null;
            singleSource.subscribe(this);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }
}