package p012io.reactivex.internal.operators.maybe;

import p012io.reactivex.Maybe;
import p012io.reactivex.Notification;
import p012io.reactivex.Single;
import p012io.reactivex.SingleObserver;
import p012io.reactivex.internal.operators.mixed.MaterializeSingleObserver;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeMaterialize */
public final class MaybeMaterialize<T> extends Single<Notification<T>> {
    final Maybe<T> source;

    public MaybeMaterialize(Maybe<T> maybe) {
        this.source = maybe;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.source.subscribe(new MaterializeSingleObserver(singleObserver));
    }
}
