package com.thanosfisherman.elvis.interfaces;

import com.thanosfisherman.elvis.interfaces.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Predicate$$ExternalSyntheticLambda5 implements Predicate {
    public static final /* synthetic */ Predicate$$ExternalSyntheticLambda5 INSTANCE = new Predicate$$ExternalSyntheticLambda5();

    private /* synthetic */ Predicate$$ExternalSyntheticLambda5() {
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
        return Predicate$$ExternalSyntheticBackport0.m670m(obj);
    }
}
