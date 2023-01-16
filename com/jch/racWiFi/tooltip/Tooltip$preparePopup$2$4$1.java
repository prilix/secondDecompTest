package com.jch.racWiFi.tooltip;

import android.animation.ValueAnimator;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo36737d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, mo36738d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "<anonymous parameter 1>", "Landroid/view/View$OnAttachStateChangeListener;"}, mo36739k = 3, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Tooltip.kt */
final class Tooltip$preparePopup$2$4$1 extends Lambda implements Function2<View, View.OnAttachStateChangeListener, Unit> {
    final /* synthetic */ Tooltip $this_run;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Tooltip$preparePopup$2$4$1(Tooltip tooltip) {
        super(2);
        this.$this_run = tooltip;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((View) obj, (View.OnAttachStateChangeListener) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(View view, View.OnAttachStateChangeListener onAttachStateChangeListener) {
        Intrinsics.checkNotNullParameter(onAttachStateChangeListener, "$noName_1");
        ValueAnimator access$getMAnimator$p = this.$this_run.mAnimator;
        if (access$getMAnimator$p != null) {
            access$getMAnimator$p.start();
        }
        if (this.$this_run.mShowDuration > 0) {
            this.$this_run.mHandler.removeCallbacks(this.$this_run.hideRunnable);
            this.$this_run.mHandler.postDelayed(this.$this_run.hideRunnable, this.$this_run.mShowDuration);
        }
        this.$this_run.mHandler.removeCallbacks(this.$this_run.activateRunnable);
        this.$this_run.mHandler.postDelayed(this.$this_run.activateRunnable, this.$this_run.mActivateDelay);
    }
}
