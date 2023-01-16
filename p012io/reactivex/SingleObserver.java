package p012io.reactivex;

import p012io.reactivex.disposables.Disposable;

/* renamed from: io.reactivex.SingleObserver */
public interface SingleObserver<T> {
    void onError(Throwable th);

    void onSubscribe(Disposable disposable);

    void onSuccess(T t);
}
