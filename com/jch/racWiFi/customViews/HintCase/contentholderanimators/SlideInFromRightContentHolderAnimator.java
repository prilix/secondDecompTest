package com.jch.racWiFi.customViews.HintCase.contentholderanimators;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.jch.racWiFi.customViews.HintCase.ContentHolderAnimator;

public class SlideInFromRightContentHolderAnimator extends ContentHolderAnimator {
    public SlideInFromRightContentHolderAnimator() {
    }

    public SlideInFromRightContentHolderAnimator(int i) {
        super(i);
    }

    public ValueAnimator getAnimator(final View view, final ContentHolderAnimator.OnFinishListener onFinishListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{(float) (view.getRootView().getWidth() - view.getLeft()), 0.0f});
        ofFloat.setDuration((long) this.durationInMilliseconds);
        ofFloat.setStartDelay(this.startDelayInMilliseconds);
        ofFloat.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                view.setAlpha(1.0f);
            }

            public void onAnimationEnd(Animator animator) {
                if (onFinishListener != ContentHolderAnimator.NO_CALLBACK) {
                    onFinishListener.onFinish();
                }
            }
        });
        return ofFloat;
    }
}
