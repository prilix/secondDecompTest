package com.jch.racWiFi.customViews.CustomScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/* renamed from: com.jch.racWiFi.customViews.CustomScrollView.CustomScrollView */
public class C1683CustomScrollView extends ScrollView {
    private GestureDetector gestureDetector = new GestureDetector(new YScrollDetector());
    View.OnTouchListener gestureListener;

    public C1683CustomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFadingEdgeLength(0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (this.gestureDetector.onTouchEvent(motionEvent)) {
            return onInterceptTouchEvent;
        }
        return false;
    }

    /* renamed from: com.jch.racWiFi.customViews.CustomScrollView.CustomScrollView$YScrollDetector */
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        YScrollDetector() {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            try {
                return Math.abs(f2) > Math.abs(f);
            } catch (Exception unused) {
                return false;
            }
        }
    }
}
