package com.jch.racWiFi.tooltip;

import android.view.animation.Animation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo36737d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, mo36738d2 = {"<anonymous>", "", "it", "Landroid/view/animation/Animation;"}, mo36739k = 3, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Tooltip.kt */
final class Tooltip$fadeOut$1$1 extends Lambda implements Function1<Animation, Unit> {
    final /* synthetic */ Tooltip this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Tooltip$fadeOut$1$1(Tooltip tooltip) {
        super(1);
        this.this$0 = tooltip;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Animation) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Animation animation) {
        this.this$0.isVisible = false;
        this.this$0.removeCallbacks();
        this.this$0.dismiss();
    }
}
