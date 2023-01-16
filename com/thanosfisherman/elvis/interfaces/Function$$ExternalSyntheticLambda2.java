package com.thanosfisherman.elvis.interfaces;

import com.thanosfisherman.elvis.interfaces.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Function$$ExternalSyntheticLambda2 implements Function {
    public static final /* synthetic */ Function$$ExternalSyntheticLambda2 INSTANCE = new Function$$ExternalSyntheticLambda2();

    private /* synthetic */ Function$$ExternalSyntheticLambda2() {
    }

    public /* synthetic */ Function andThen(Function function) {
        return Function.CC.$default$andThen(this, function);
    }

    public final Object apply(Object obj) {
        return Function.CC.lambda$identity$2(obj);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function.CC.$default$compose(this, function);
    }
}
