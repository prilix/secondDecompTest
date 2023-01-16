package p012io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p012io.reactivex.Flowable;
import p012io.reactivex.FlowableSubscriber;
import p012io.reactivex.exceptions.Exceptions;
import p012io.reactivex.functions.Action;
import p012io.reactivex.functions.Consumer;
import p012io.reactivex.functions.LongConsumer;
import p012io.reactivex.internal.subscriptions.EmptySubscription;
import p012io.reactivex.internal.subscriptions.SubscriptionHelper;
import p012io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableDoOnLifecycle */
public final class FlowableDoOnLifecycle<T> extends AbstractFlowableWithUpstream<T, T> {
    private final Action onCancel;
    private final LongConsumer onRequest;
    private final Consumer<? super Subscription> onSubscribe;

    public FlowableDoOnLifecycle(Flowable<T> flowable, Consumer<? super Subscription> consumer, LongConsumer longConsumer, Action action) {
        super(flowable);
        this.onSubscribe = consumer;
        this.onRequest = longConsumer;
        this.onCancel = action;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new SubscriptionLambdaSubscriber(subscriber, this.onSubscribe, this.onRequest, this.onCancel));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableDoOnLifecycle$SubscriptionLambdaSubscriber */
    static final class SubscriptionLambdaSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> downstream;
        final Action onCancel;
        final LongConsumer onRequest;
        final Consumer<? super Subscription> onSubscribe;
        Subscription upstream;

        SubscriptionLambdaSubscriber(Subscriber<? super T> subscriber, Consumer<? super Subscription> consumer, LongConsumer longConsumer, Action action) {
            this.downstream = subscriber;
            this.onSubscribe = consumer;
            this.onCancel = action;
            this.onRequest = longConsumer;
        }

        public void onSubscribe(Subscription subscription) {
            try {
                this.onSubscribe.accept(subscription);
                if (SubscriptionHelper.validate(this.upstream, subscription)) {
                    this.upstream = subscription;
                    this.downstream.onSubscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                subscription.cancel();
                this.upstream = SubscriptionHelper.CANCELLED;
                EmptySubscription.error(th, this.downstream);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            if (this.upstream != SubscriptionHelper.CANCELLED) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        public void onComplete() {
            if (this.upstream != SubscriptionHelper.CANCELLED) {
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            try {
                this.onRequest.accept(j);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.upstream.request(j);
        }

        public void cancel() {
            Subscription subscription = this.upstream;
            if (subscription != SubscriptionHelper.CANCELLED) {
                this.upstream = SubscriptionHelper.CANCELLED;
                try {
                    this.onCancel.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
                subscription.cancel();
            }
        }
    }
}
