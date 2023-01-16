package com.thanosfisherman.elvis.interfaces;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.util.Objects;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    java.util.function.Consumer<T> andThen(java.util.function.Consumer<? super T> consumer);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.thanosfisherman.elvis.interfaces.Consumer$-CC  reason: invalid class name */
    public final /* synthetic */ class CC<T> {
        public static java.util.function.Consumer $default$andThen(Consumer _this, java.util.function.Consumer consumer) {
            Objects.requireNonNull(consumer);
            return new Consumer$$ExternalSyntheticLambda0(_this, consumer);
        }

        public static /* synthetic */ void lambda$andThen$0(Consumer _this, java.util.function.Consumer consumer, Object obj) {
            _this.accept(obj);
            consumer.accept(obj);
        }
    }
}
