package com.jch.racWiFi.customViews.HintCase.utils;

import android.graphics.Path;

public class RoundRect {
    private Path path;

    private float normalizeValue(float f, float f2, float f3) {
        if (f < f2) {
            f = 0.0f;
        }
        return f > f3 ? f3 : f;
    }

    public RoundRect(float f, float f2, float f3, float f4, float f5, float f6) {
        init(f, f2, f3, f4, f5, f6, true, true, true, true);
    }

    public RoundRect(float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, boolean z3, boolean z4) {
        init(f, f2, f3, f4, f5, f6, z, z2, z3, z4);
    }

    private void init(float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, boolean z3, boolean z4) {
        float f7 = f3 - f;
        float f8 = f4 - f2;
        float normalizeValue = normalizeValue(f5, 0.0f, f7 / 2.0f);
        float normalizeValue2 = normalizeValue(f6, 0.0f, f8 / 2.0f);
        float f9 = f7 - (normalizeValue * 2.0f);
        float f10 = f8 - (2.0f * normalizeValue2);
        Path path2 = new Path();
        this.path = path2;
        path2.moveTo(f3, f2 + normalizeValue2);
        drawTopRightCorner(normalizeValue, normalizeValue2, z2);
        this.path.rLineTo(-f9, 0.0f);
        drawTopLeftCorner(normalizeValue, normalizeValue2, z);
        this.path.rLineTo(0.0f, f10);
        drawBottomLeftCorner(normalizeValue, normalizeValue2, z4);
        this.path.rLineTo(f9, 0.0f);
        drawBottomRightCorner(normalizeValue, normalizeValue2, z3);
        this.path.rLineTo(0.0f, -f10);
        this.path.close();
    }

    private void drawBottomRightCorner(float f, float f2, boolean z) {
        if (z) {
            this.path.rQuadTo(f, 0.0f, f, -f2);
            return;
        }
        this.path.rLineTo(f, 0.0f);
        this.path.rLineTo(0.0f, -f2);
    }

    private void drawBottomLeftCorner(float f, float f2, boolean z) {
        if (z) {
            this.path.rQuadTo(0.0f, f2, f, f2);
            return;
        }
        this.path.rLineTo(0.0f, f2);
        this.path.rLineTo(f, 0.0f);
    }

    private void drawTopLeftCorner(float f, float f2, boolean z) {
        if (z) {
            float f3 = -f;
            this.path.rQuadTo(f3, 0.0f, f3, f2);
            return;
        }
        this.path.rLineTo(-f, 0.0f);
        this.path.rLineTo(0.0f, f2);
    }

    private void drawTopRightCorner(float f, float f2, boolean z) {
        if (z) {
            float f3 = -f2;
            this.path.rQuadTo(0.0f, f3, -f, f3);
            return;
        }
        this.path.rLineTo(0.0f, -f2);
        this.path.rLineTo(-f, 0.0f);
    }

    public Path getPath() {
        return this.path;
    }
}
