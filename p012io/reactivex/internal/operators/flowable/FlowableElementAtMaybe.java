package p012io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscription;
import p012io.reactivex.Flowable;
import p012io.reactivex.FlowableSubscriber;
import p012io.reactivex.Maybe;
import p012io.reactivex.MaybeObserver;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.internal.fuseable.FuseToFlowable;
import p012io.reactivex.internal.subscriptions.SubscriptionHelper;
import p012io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableElementAtMaybe */
public final class FlowableElementAtMaybe<T> extends Maybe<T> implements FuseToFlowable<T> {
    final long index;
    final Flowable<T> source;

    public FlowableElementAtMaybe(Flowable<T> flowable, long j) {
        this.source = flowable;
        this.index = j;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new ElementAtSubscriber(maybeObserver, this.index));
    }

    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableElementAt(this.source, this.index, null, false));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableElementAtMaybe$ElementAtSubscriber */
    static final class ElementAtSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        long count;
        boolean done;
        final MaybeObserver<? super T> downstream;
        final long index;
        Subscription upstream;

        ElementAtSubscriber(MaybeObserver<? super T> maybeObserver, long j) {
            this.downstream = maybeObserver;
            this.index = j;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.count;
                if (j == this.index) {
                    this.done = true;
                    this.upstream.cancel();
                    this.upstream = SubscriptionHelper.CANCELLED;
                    this.downstream.onSuccess(t);
                    return;
                }
                this.count = j + 1;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.upstream = SubscriptionHelper.CANCELLED;
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            this.upstream.cancel();
            this.upstream = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.upstream == SubscriptionHelper.CANCELLED;
        }
    }
}
