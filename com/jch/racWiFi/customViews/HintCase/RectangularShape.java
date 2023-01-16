package com.jch.racWiFi.customViews.HintCase;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.jch.racWiFi.customViews.HintCase.utils.RoundRect;

public class RectangularShape extends Shape {
    private static final float DEFAULT_MAX_HEIGHT = 10000.0f;
    private static final float DEFAULT_MIN_HEIGHT = 50.0f;
    private float currentHeight = 10000.0f;
    private float currentWidth = 10000.0f;
    private float maxHeight = 10000.0f;
    private float maxWidth = 10000.0f;
    private float minHeight = 50.0f;
    private float minWidth = 50.0f;

    public void setMinimumValue() {
        this.currentWidth = this.minWidth;
        this.currentHeight = this.minHeight;
    }

    public void setShapeInfo(View view, ViewGroup viewGroup, int i, Context context) {
        if (view != null) {
            int i2 = i * 2;
            this.minHeight = (float) (view.getMeasuredHeight() + i2);
            this.minWidth = (float) (view.getMeasuredWidth() + i2);
            this.maxHeight = (float) (viewGroup.getHeight() * 2);
            this.maxWidth = (float) (viewGroup.getWidth() * 2);
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            setCenterX((float) (iArr[0] + (view.getWidth() / 2)));
            setCenterY((float) (iArr[1] + (view.getHeight() / 2)));
            setLeft(iArr[0] - i);
            setRight((iArr[0] + ((int) this.minWidth)) - i);
            setTop(iArr[1] - i);
            setBottom((iArr[1] + ((int) this.minHeight)) - i);
            setWidth(this.minWidth);
            setHeight(this.minHeight);
        } else if (viewGroup != null) {
            this.minHeight = 0.0f;
            this.minWidth = 0.0f;
            this.maxHeight = (float) viewGroup.getHeight();
            this.maxWidth = (float) viewGroup.getWidth();
            setCenterX((float) (viewGroup.getMeasuredWidth() / 2));
            setCenterY((float) (viewGroup.getMeasuredHeight() / 2));
            setLeft(0);
            setTop(0);
            setRight(0);
            setBottom(0);
        }
        this.currentHeight = this.maxHeight;
        this.currentWidth = this.maxWidth;
    }

    public boolean isTouchEventInsideTheHint(MotionEvent motionEvent) {
        return motionEvent.getRawX() <= ((float) getRight()) && motionEvent.getRawX() >= ((float) getLeft()) && motionEvent.getRawY() <= ((float) getBottom()) && motionEvent.getRawY() >= ((float) getTop());
    }

    public void draw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawRoundRectAfterLollipop(canvas);
        } else {
            drawRoundRectPreLollipop(canvas);
        }
    }

    private void drawRoundRectAfterLollipop(Canvas canvas) {
        canvas.drawRoundRect(getCenterX() - (this.currentWidth / 2.0f), getCenterY() - (this.currentHeight / 2.0f), getCenterX() + (this.currentWidth / 2.0f), getCenterY() + (this.currentHeight / 2.0f), 10.0f, 10.0f, getShapePaint());
    }

    private void drawRoundRectPreLollipop(Canvas canvas) {
        canvas.drawPath(new RoundRect(getCenterX() - (this.currentWidth / 2.0f), getCenterY() - (this.currentHeight / 2.0f), (this.currentWidth / 2.0f) + getCenterX(), (this.currentHeight / 2.0f) + getCenterY(), 10.0f, 10.0f).getPath(), getShapePaint());
    }

    public float getMinHeight() {
        return this.minHeight;
    }

    public float getMaxHeight() {
        return this.maxHeight;
    }

    public float getMinWidth() {
        return this.minWidth;
    }

    public float getMaxWidth() {
        return this.maxWidth;
    }

    public float getCurrentHeight() {
        return this.currentHeight;
    }

    public void setCurrentHeight(float f) {
        this.currentHeight = f;
    }

    public void setCurrentWidth(float f) {
        this.currentWidth = f;
    }
}
