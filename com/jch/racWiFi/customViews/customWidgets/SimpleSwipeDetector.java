package com.jch.racWiFi.customViews.customWidgets;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.accord.common.utils.Logger;

public class SimpleSwipeDetector extends GestureDetector.SimpleOnGestureListener {
    private final String TAG = getClass().getSimpleName();

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent2.getX() < motionEvent.getX()) {
            Logger.m49i("", this.TAG + " Swipe left");
        }
        if (motionEvent2.getX() <= motionEvent.getX()) {
            return true;
        }
        Logger.m49i("", this.TAG + " Swipe right");
        return true;
    }
}
