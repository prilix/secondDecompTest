package com.thanosfisherman.elvis;

import com.thanosfisherman.elvis.interfaces.Consumer;
import java.io.PrintStream;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Main$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ PrintStream f$0;

    public /* synthetic */ Main$$ExternalSyntheticLambda0(PrintStream printStream) {
        this.f$0 = printStream;
    }

    public final void accept(Object obj) {
        this.f$0.println(obj);
    }

    public /* synthetic */ java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
        return Consumer.CC.$default$andThen(this, consumer);
    }
}
