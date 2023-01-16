package com.github.mikephil.charting.highlight;

public final class Range {
    public float from;

    /* renamed from: to */
    public float f184to;

    public Range(float f, float f2) {
        this.from = f;
        this.f184to = f2;
    }

    public boolean contains(float f) {
        return f > this.from && f <= this.f184to;
    }

    public boolean isLarger(float f) {
        return f > this.f184to;
    }

    public boolean isSmaller(float f) {
        return f < this.from;
    }
}
