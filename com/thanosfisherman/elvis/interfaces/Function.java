package com.thanosfisherman.elvis.interfaces;

import com.android.tools.r8.annotations.SynthesizedClass;
import com.thanosfisherman.elvis.Objects;

@FunctionalInterface
public interface Function<T, R> {
    <V> Function<T, V> andThen(Function<? super R, ? extends V> function);

    R apply(T t);

    <V> Function<V, R> compose(Function<? super V, ? extends T> function);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.thanosfisherman.elvis.interfaces.Function$-CC  reason: invalid class name */
    public final /* synthetic */ class CC<T, R> {
        public static /* synthetic */ Object lambda$identity$2(Object obj) {
            return obj;
        }

        public static Function $default$compose(Function _this, Function function) {
            Objects.requireNonNull(function);
            return new Function$$ExternalSyntheticLambda1(_this, function);
        }

        public static Function $default$andThen(Function _this, Function function) {
            Objects.requireNonNull(function);
            return new Function$$ExternalSyntheticLambda0(_this, function);
        }

        public static <T> Function<T, T> identity() {
            return Function$$ExternalSyntheticLambda2.INSTANCE;
        }
    }
}
