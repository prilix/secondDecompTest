package com.thanosfisherman.elvis.interfaces;

import com.thanosfisherman.elvis.interfaces.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Predicate$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ Predicate f$0;

    public /* synthetic */ Predicate$$ExternalSyntheticLambda1(Predicate predicate) {
        this.f$0 = predicate;
    }

    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate.CC.$default$and(this, predicate);
    }

    public /* synthetic */ Predicate negate() {
        return Predicate.CC.$default$negate(this);
    }

    /* renamed from: or */
    public /* synthetic */ Predicate mo33830or(Predicate predicate) {
        return Predicate.CC.$default$or(this, predicate);
    }

    public final boolean test(Object obj) {
        return Predicate.CC.lambda$negate$1(this.f$0, obj);
    }
}
