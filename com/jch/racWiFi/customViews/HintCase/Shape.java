package com.jch.racWiFi.customViews.HintCase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public abstract class Shape {
    private static final int DEFAULT_ALPHA = 0;
    private static final int DEFAULT_COLOR = 16777215;
    private int bottom;
    private float centerX;
    private float centerY;
    private float height;
    private int left;
    private int right;
    private Paint shapePaint = getInitializedTypePaint();
    private int top;
    private float width;

    public abstract void draw(Canvas canvas);

    public abstract boolean isTouchEventInsideTheHint(MotionEvent motionEvent);

    public abstract void setMinimumValue();

    public abstract void setShapeInfo(View view, ViewGroup viewGroup, int i, Context context);

    private Paint getInitializedTypePaint() {
        Paint paint = new Paint(1);
        paint.setColor(16777215);
        paint.setAlpha(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        return paint;
    }

    public int getLeft() {
        return this.left;
    }

    public void setLeft(int i) {
        this.left = i;
    }

    public int getTop() {
        return this.top;
    }

    public void setTop(int i) {
        this.top = i;
    }

    public int getRight() {
        return this.right;
    }

    public void setRight(int i) {
        this.right = i;
    }

    public int getBottom() {
        return this.bottom;
    }

    public void setBottom(int i) {
        this.bottom = i;
    }

    public float getCenterX() {
        return this.centerX;
    }

    public void setCenterX(float f) {
        this.centerX = f;
    }

    public float getCenterY() {
        return this.centerY;
    }

    public void setCenterY(float f) {
        this.centerY = f;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float f) {
        this.width = f;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float f) {
        this.height = f;
    }

    public Paint getShapePaint() {
        return this.shapePaint;
    }
}
