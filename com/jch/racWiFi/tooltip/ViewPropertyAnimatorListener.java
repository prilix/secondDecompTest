package com.jch.racWiFi.tooltip;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J)\u0010\r\u001a\u00020\t2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J)\u0010\u000f\u001a\u00020\t2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J)\u0010\u0010\u001a\u00020\t2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J)\u0010\u0011\u001a\u00020\t2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016R+\u0010\u0003\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R+\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R+\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/ViewPropertyAnimatorListener;", "Landroid/animation/Animator$AnimatorListener;", "()V", "_onAnimationCancel", "Lkotlin/Function1;", "Landroid/animation/Animator;", "Lkotlin/ParameterName;", "name", "animation", "", "_onAnimationEnd", "_onAnimationRepeat", "_onAnimationStart", "onAnimationCancel", "func", "onAnimationEnd", "onAnimationRepeat", "onAnimationStart", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Utils.kt */
public final class ViewPropertyAnimatorListener implements Animator.AnimatorListener {
    private Function1<? super Animator, Unit> _onAnimationCancel;
    private Function1<? super Animator, Unit> _onAnimationEnd;
    private Function1<? super Animator, Unit> _onAnimationRepeat;
    private Function1<? super Animator, Unit> _onAnimationStart;

    public void onAnimationRepeat(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        Function1<? super Animator, Unit> function1 = this._onAnimationRepeat;
        if (function1 != null) {
            function1.invoke(animator);
        }
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        Function1<? super Animator, Unit> function1 = this._onAnimationCancel;
        if (function1 != null) {
            function1.invoke(animator);
        }
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        Function1<? super Animator, Unit> function1 = this._onAnimationEnd;
        if (function1 != null) {
            function1.invoke(animator);
        }
    }

    public void onAnimationStart(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        Function1<? super Animator, Unit> function1 = this._onAnimationStart;
        if (function1 != null) {
            function1.invoke(animator);
        }
    }

    public final void onAnimationRepeat(Function1<? super Animator, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "func");
        this._onAnimationRepeat = function1;
    }

    public final void onAnimationCancel(Function1<? super Animator, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "func");
        this._onAnimationCancel = function1;
    }

    public final void onAnimationEnd(Function1<? super Animator, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "func");
        this._onAnimationEnd = function1;
    }

    public final void onAnimationStart(Function1<? super Animator, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "func");
        this._onAnimationStart = function1;
    }
}
