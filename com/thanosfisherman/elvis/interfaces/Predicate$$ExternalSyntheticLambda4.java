package com.thanosfisherman.elvis.interfaces;

import com.thanosfisherman.elvis.interfaces.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Predicate$$ExternalSyntheticLambda4 implements Predicate {
    public final /* synthetic */ Object f$0;

    public /* synthetic */ Predicate$$ExternalSyntheticLambda4(Object obj) {
        this.f$0 = obj;
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
        return this.f$0.equals(obj);
    }
}
