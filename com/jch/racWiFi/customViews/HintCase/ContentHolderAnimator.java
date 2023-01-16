package com.jch.racWiFi.customViews.HintCase;

import android.animation.ValueAnimator;
import android.view.View;

public abstract class ContentHolderAnimator {
    public static final int DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS = 300;
    public static final ContentHolderAnimator NO_ANIMATOR = null;
    public static final OnFinishListener NO_CALLBACK = null;
    protected int durationInMilliseconds;
    protected long startDelayInMilliseconds;

    public interface OnFinishListener {
        void onFinish();
    }

    public abstract ValueAnimator getAnimator(View view, OnFinishListener onFinishListener);

    public ContentHolderAnimator() {
        this.durationInMilliseconds = 300;
    }

    public ContentHolderAnimator(int i) {
        this.durationInMilliseconds = i;
    }

    public ValueAnimator getAnimator(View view) {
        return getAnimator(view, NO_CALLBACK);
    }

    public ContentHolderAnimator setStartDelay(long j) {
        this.startDelayInMilliseconds = j;
        return this;
    }
}
