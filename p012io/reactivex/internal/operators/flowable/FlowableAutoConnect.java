package p012io.reactivex.internal.operators.flowable;

import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscriber;
import p012io.reactivex.Flowable;
import p012io.reactivex.disposables.Disposable;
import p012io.reactivex.flowables.ConnectableFlowable;
import p012io.reactivex.functions.Consumer;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableAutoConnect */
public final class FlowableAutoConnect<T> extends Flowable<T> {
    final AtomicInteger clients = new AtomicInteger();
    final Consumer<? super Disposable> connection;
    final int numberOfSubscribers;
    final ConnectableFlowable<? extends T> source;

    public FlowableAutoConnect(ConnectableFlowable<? extends T> connectableFlowable, int i, Consumer<? super Disposable> consumer) {
        this.source = connectableFlowable;
        this.numberOfSubscribers = i;
        this.connection = consumer;
    }

    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(subscriber);
        if (this.clients.incrementAndGet() == this.numberOfSubscribers) {
            this.source.connect(this.connection);
        }
    }
}
