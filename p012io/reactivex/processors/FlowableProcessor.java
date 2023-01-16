package p012io.reactivex.processors;

import org.reactivestreams.Processor;
import p012io.reactivex.Flowable;
import p012io.reactivex.FlowableSubscriber;
import p012io.reactivex.annotations.CheckReturnValue;

/* renamed from: io.reactivex.processors.FlowableProcessor */
public abstract class FlowableProcessor<T> extends Flowable<T> implements Processor<T, T>, FlowableSubscriber<T> {
    public abstract Throwable getThrowable();

    public abstract boolean hasComplete();

    public abstract boolean hasSubscribers();

    public abstract boolean hasThrowable();

    @CheckReturnValue
    public final FlowableProcessor<T> toSerialized() {
        if (this instanceof SerializedProcessor) {
            return this;
        }
        return new SerializedProcessor(this);
    }
}
