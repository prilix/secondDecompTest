package androidx.core.transition;

import android.transition.Transition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo36737d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, mo36738d2 = {"<anonymous>", "", "it", "Landroid/transition/Transition;"}, mo36739k = 3, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Transition.kt */
public final class TransitionKt$addListener$4 extends Lambda implements Function1<Transition, Unit> {
    public static final TransitionKt$addListener$4 INSTANCE = new TransitionKt$addListener$4();

    public TransitionKt$addListener$4() {
        super(1);
    }

    public final void invoke(Transition transition) {
        Intrinsics.checkNotNullParameter(transition, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Transition) obj);
        return Unit.INSTANCE;
    }
}
