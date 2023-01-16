package com.jch.racWiFi.customViews.HintCase.contentholderanimators;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.jch.racWiFi.customViews.HintCase.ContentHolderAnimator;

public class FadeOutContentHolderAnimator extends ContentHolderAnimator {
    public FadeOutContentHolderAnimator() {
    }

    public FadeOutContentHolderAnimator(int i) {
        super(i);
    }

    public ValueAnimator getAnimator(View view, final ContentHolderAnimator.OnFinishListener onFinishListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f, 0.0f});
        ofFloat.setDuration((long) this.durationInMilliseconds);
        ofFloat.setStartDelay(this.startDelayInMilliseconds);
        if (onFinishListener != NO_CALLBACK) {
            ofFloat.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    onFinishListener.onFinish();
                }
            });
        }
        return ofFloat;
    }
}
