package com.jch.racWiFi.Listeners;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class RepeatListener implements View.OnTouchListener {
    /* access modifiers changed from: private */
    public final View.OnClickListener clickListener;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    /* access modifiers changed from: private */
    public Runnable handlerRunnable = new Runnable() {
        public void run() {
            if (RepeatListener.this.touchedView.isEnabled()) {
                RepeatListener.this.handler.postDelayed(this, RepeatListener.this.normalInterval);
                RepeatListener.this.clickListener.onClick(RepeatListener.this.touchedView);
                return;
            }
            RepeatListener.this.handler.removeCallbacks(RepeatListener.this.handlerRunnable);
            RepeatListener.this.touchedView.setPressed(false);
            RepeatListener.this.touchedView = null;
        }
    };
    private long initialInterval;
    /* access modifiers changed from: private */
    public final long normalInterval;
    /* access modifiers changed from: private */
    public View touchedView;

    public RepeatListener(long j, long j2, View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            throw new IllegalArgumentException("null runnable");
        } else if (j < 0 || j2 < 0) {
            throw new IllegalArgumentException("negative interval");
        } else {
            this.initialInterval = j;
            this.normalInterval = j2;
            this.clickListener = onClickListener;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.handler.removeCallbacks(this.handlerRunnable);
            this.handler.postDelayed(this.handlerRunnable, this.initialInterval);
            this.touchedView = view;
            view.setPressed(true);
            this.clickListener.onClick(view);
            return true;
        } else if (action != 1 && action != 3) {
            return false;
        } else {
            this.handler.removeCallbacks(this.handlerRunnable);
            this.touchedView.setPressed(false);
            this.touchedView = null;
            return true;
        }
    }

    public void cleanUp() {
        this.handler.removeCallbacksAndMessages((Object) null);
    }
}
