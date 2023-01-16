package com.jch.racWiFi.customViews.HintCase.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.jch.racWiFi.customViews.HintCase.Shape;

public class CircularShape extends Shape {
    private static final float DEFAULT_MAX_RADIUS = 10000.0f;
    private static final float DEFAULT_MIN_RADIUS = 50.0f;
    private float currentRadius = 10000.0f;
    private float maxRadius = 10000.0f;
    private float minRadius = 50.0f;

    public float getMinRadius() {
        return this.minRadius;
    }

    public float getMaxRadius() {
        return this.maxRadius;
    }

    public float getCurrentRadius() {
        return this.currentRadius;
    }

    public void setCurrentRadius(float f) {
        this.currentRadius = f;
    }

    public void setMinimumValue() {
        this.currentRadius = this.minRadius;
    }

    public void setShapeInfo(View view, ViewGroup viewGroup, int i, Context context) {
        if (view != null) {
            this.minRadius = (float) ((Math.max(view.getMeasuredWidth(), view.getMeasuredHeight()) / 2) + i);
            this.maxRadius = (float) Math.max(viewGroup.getHeight(), viewGroup.getWidth());
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            setCenterX((float) (iArr[0] + (view.getWidth() / 2)));
            setCenterY((float) (iArr[1] + (view.getHeight() / 2)));
            setLeft((int) (getCenterX() - this.minRadius));
            setRight((int) (getCenterX() + this.minRadius));
            setTop((int) (getCenterY() - this.minRadius));
            setBottom((int) (getCenterY() + this.minRadius));
            setWidth(this.minRadius * 2.0f);
            setHeight(this.minRadius * 2.0f);
        } else if (viewGroup != null) {
            this.minRadius = 0.0f;
            this.maxRadius = (float) viewGroup.getHeight();
            setCenterX((float) (viewGroup.getMeasuredWidth() / 2));
            setCenterY((float) (viewGroup.getMeasuredHeight() / 2));
            setLeft(0);
            setTop(0);
            setRight(0);
            setBottom(0);
        }
        this.currentRadius = this.maxRadius;
    }

    public boolean isTouchEventInsideTheHint(MotionEvent motionEvent) {
        return Math.sqrt(Math.pow((double) Math.abs(motionEvent.getRawX() - getCenterX()), 2.0d) + Math.pow((double) Math.abs(motionEvent.getRawY() - getCenterY()), 2.0d)) <= ((double) this.minRadius);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(getCenterX(), getCenterY(), this.currentRadius, getShapePaint());
    }
}
