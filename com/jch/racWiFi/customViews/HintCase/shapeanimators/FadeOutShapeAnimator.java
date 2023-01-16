package com.jch.racWiFi.customViews.HintCase.shapeanimators;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.jch.racWiFi.customViews.HintCase.Shape;
import com.jch.racWiFi.customViews.HintCase.ShapeAnimator;

public class FadeOutShapeAnimator extends ShapeAnimator {
    public FadeOutShapeAnimator() {
    }

    public FadeOutShapeAnimator(int i) {
        super(i);
    }

    public ValueAnimator getAnimator(View view, Shape shape, final ShapeAnimator.OnFinishListener onFinishListener) {
        shape.setMinimumValue();
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
