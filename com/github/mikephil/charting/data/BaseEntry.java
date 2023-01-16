package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;

public abstract class BaseEntry {
    private Object mData;
    private Drawable mIcon;

    /* renamed from: y */
    private float f176y;

    public BaseEntry() {
        this.f176y = 0.0f;
        this.mData = null;
        this.mIcon = null;
    }

    public BaseEntry(float f) {
        this.f176y = 0.0f;
        this.mData = null;
        this.mIcon = null;
        this.f176y = f;
    }

    public BaseEntry(float f, Object obj) {
        this(f);
        this.mData = obj;
    }

    public BaseEntry(float f, Drawable drawable) {
        this(f);
        this.mIcon = drawable;
    }

    public BaseEntry(float f, Drawable drawable, Object obj) {
        this(f);
        this.mIcon = drawable;
        this.mData = obj;
    }

    public float getY() {
        return this.f176y;
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public void setY(float f) {
        this.f176y = f;
    }

    public Object getData() {
        return this.mData;
    }

    public void setData(Object obj) {
        this.mData = obj;
    }
}
