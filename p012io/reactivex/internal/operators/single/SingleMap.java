package p012io.reactivex.internal.operators.single;

import p012io.reactivex.Single;
import p012io.reactivex.SingleObserver;
import p012io.reactivex.SingleSource;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.exceptions.Exceptions;
import p012io.reactivex.functions.Function;
import p012io.reactivex.internal.functions.ObjectHelper;

/* renamed from: io.reactivex.internal.operators.single.SingleMap */
public final class SingleMap<T, R> extends Single<R> {
    final Function<? super T, ? extends R> mapper;
    final SingleSource<? extends T> source;

    public SingleMap(SingleSource<? extends T> singleSource, Function<? super T, ? extends R> function) {
        this.source = singleSource;
        this.mapper = function;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.source.subscribe(new MapSingleObserver(singleObserver, this.mapper));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleMap$MapSingleObserver */
    static final class MapSingleObserver<T, R> implements SingleObserver<T> {
        final Function<? super T, ? extends R> mapper;

        /* renamed from: t */
        final SingleObserver<? super R> f585t;

        MapSingleObserver(SingleObserver<? super R> singleObserver, Function<? super T, ? extends R> function) {
            this.f585t = singleObserver;
            this.mapper = function;
        }

        public void onSubscribe(Disposable disposable) {
            this.f585t.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            try {
                this.f585t.onSuccess(ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.f585t.onError(th);
        }
    }
}
