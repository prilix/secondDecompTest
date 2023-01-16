package com.thanosfisherman.elvis.interfaces;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.util.Objects;

@FunctionalInterface
public interface Predicate<T> {
    Predicate<T> and(Predicate<? super T> predicate);

    Predicate<T> negate();

    /* renamed from: or */
    Predicate<T> mo33830or(Predicate<? super T> predicate);

    boolean test(T t);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.thanosfisherman.elvis.interfaces.Predicate$-CC  reason: invalid class name */
    public final /* synthetic */ class CC<T> {
        public static Predicate $default$and(Predicate _this, Predicate predicate) {
            Objects.requireNonNull(predicate);
            return new Predicate$$ExternalSyntheticLambda2(_this, predicate);
        }

        public static /* synthetic */ boolean lambda$and$0(Predicate _this, Predicate predicate, Object obj) {
            return _this.test(obj) && predicate.test(obj);
        }

        public static Predicate $default$negate(Predicate _this) {
            return new Predicate$$ExternalSyntheticLambda1(_this);
        }

        public static /* synthetic */ boolean lambda$negate$1(Predicate _this, Object obj) {
            return !_this.test(obj);
        }

        public static Predicate $default$or(Predicate _this, Predicate predicate) {
            Objects.requireNonNull(predicate);
            return new Predicate$$ExternalSyntheticLambda3(_this, predicate);
        }

        public static /* synthetic */ boolean lambda$or$2(Predicate _this, Predicate predicate, Object obj) {
            return _this.test(obj) || predicate.test(obj);
        }

        public static <T> Predicate<T> isEqual(Object obj) {
            return obj == null ? Predicate$$ExternalSyntheticLambda5.INSTANCE : new Predicate$$ExternalSyntheticLambda4(obj);
        }
    }
}
