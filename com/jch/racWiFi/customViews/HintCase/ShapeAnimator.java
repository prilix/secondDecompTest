package com.jch.racWiFi.customViews.HintCase;

import android.animation.ValueAnimator;
import android.view.View;

public abstract class ShapeAnimator {
    public static final int DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS = 300;
    public static final ShapeAnimator NO_ANIMATOR = null;
    public static final OnFinishListener NO_CALLBACK = null;
    protected int durationInMilliseconds;
    protected long startDelayInMilliseconds;

    public interface OnFinishListener {
        void onFinish();
    }

    public abstract ValueAnimator getAnimator(View view, Shape shape, OnFinishListener onFinishListener);

    public ShapeAnimator() {
        this.durationInMilliseconds = 300;
    }

    public ShapeAnimator(int i) {
        this.durationInMilliseconds = i;
    }

    public ValueAnimator getAnimator(View view, Shape shape) {
        return getAnimator(view, shape, NO_CALLBACK);
    }

    public ShapeAnimator setStartDelay(long j) {
        this.startDelayInMilliseconds = j;
        return this;
    }
}
