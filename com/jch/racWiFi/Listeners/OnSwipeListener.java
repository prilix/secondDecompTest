package com.jch.racWiFi.Listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class OnSwipeListener extends GestureDetector.SimpleOnGestureListener {
    public boolean onSwipe(Direction direction) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return onSwipe(getDirection(motionEvent.getX(), motionEvent.getY(), motionEvent2.getX(), motionEvent2.getY()));
    }

    public Direction getDirection(float f, float f2, float f3, float f4) {
        return Direction.fromAngle(getAngle(f, f2, f3, f4));
    }

    public double getAngle(float f, float f2, float f3, float f4) {
        return ((((Math.atan2((double) (f2 - f4), (double) (f3 - f)) + 3.141592653589793d) * 180.0d) / 3.141592653589793d) + 180.0d) % 360.0d;
    }

    public enum Direction {
        up,
        down,
        left,
        right;

        private static boolean inRange(double d, float f, float f2) {
            return d >= ((double) f) && d < ((double) f2);
        }

        public static Direction fromAngle(double d) {
            if (inRange(d, 45.0f, 135.0f)) {
                return up;
            }
            if (inRange(d, 0.0f, 45.0f) || inRange(d, 315.0f, 360.0f)) {
                return right;
            }
            if (inRange(d, 225.0f, 315.0f)) {
                return down;
            }
            return left;
        }
    }
}
