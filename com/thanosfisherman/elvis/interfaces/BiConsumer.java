package com.thanosfisherman.elvis.interfaces;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.util.Objects;

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);

    BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> biConsumer);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.thanosfisherman.elvis.interfaces.BiConsumer$-CC  reason: invalid class name */
    public final /* synthetic */ class CC<T, U> {
        public static BiConsumer $default$andThen(BiConsumer _this, BiConsumer biConsumer) {
            Objects.requireNonNull(biConsumer);
            return new BiConsumer$$ExternalSyntheticLambda0(_this, biConsumer);
        }

        public static /* synthetic */ void lambda$andThen$0(BiConsumer _this, BiConsumer biConsumer, Object obj, Object obj2) {
            _this.accept(obj, obj2);
            biConsumer.accept(obj, obj2);
        }
    }
}
