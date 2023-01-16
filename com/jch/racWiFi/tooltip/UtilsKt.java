package com.jch.racWiFi.tooltip;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a)\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u001a)\u0010\r\u001a\u00020\u0010*\u00020\u00102\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0012"}, mo36738d2 = {"addOnAttachStateChangeListener", "Landroid/view/View;", "func", "Lkotlin/Function1;", "Lcom/jch/racWiFi/tooltip/AttachStateChangeListener;", "", "Lkotlin/ExtensionFunctionType;", "rectContainsWithTolerance", "", "Landroid/graphics/Rect;", "childRect", "t", "", "setListener", "Landroid/view/ViewPropertyAnimator;", "Lcom/jch/racWiFi/tooltip/ViewPropertyAnimatorListener;", "Landroid/view/animation/Animation;", "Lcom/jch/racWiFi/tooltip/AnimationListener;", "app_release"}, mo36739k = 2, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Utils.kt */
public final class UtilsKt {
    public static final boolean rectContainsWithTolerance(Rect rect, Rect rect2, int i) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(rect2, "childRect");
        return rect.contains(rect2.left + i, rect2.top + i, rect2.right - i, rect2.bottom - i);
    }

    public static final View addOnAttachStateChangeListener(View view, Function1<? super AttachStateChangeListener, Unit> function1) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(function1, "func");
        AttachStateChangeListener attachStateChangeListener = new AttachStateChangeListener();
        function1.invoke(attachStateChangeListener);
        view.addOnAttachStateChangeListener(attachStateChangeListener);
        return view;
    }

    public static final ViewPropertyAnimator setListener(ViewPropertyAnimator viewPropertyAnimator, Function1<? super ViewPropertyAnimatorListener, Unit> function1) {
        Intrinsics.checkNotNullParameter(viewPropertyAnimator, "<this>");
        Intrinsics.checkNotNullParameter(function1, "func");
        ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener();
        function1.invoke(viewPropertyAnimatorListener);
        viewPropertyAnimator.setListener(viewPropertyAnimatorListener);
        return viewPropertyAnimator;
    }

    public static final Animation setListener(Animation animation, Function1<? super AnimationListener, Unit> function1) {
        Intrinsics.checkNotNullParameter(animation, "<this>");
        Intrinsics.checkNotNullParameter(function1, "func");
        AnimationListener animationListener = new AnimationListener();
        function1.invoke(animationListener);
        animation.setAnimationListener(animationListener);
        return animation;
    }
}
