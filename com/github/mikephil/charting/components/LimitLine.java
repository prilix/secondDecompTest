package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.C1662R2;

public class LimitLine extends ComponentBase {
    private DashPathEffect mDashPathEffect = null;
    private String mLabel = "";
    private LimitLabelPosition mLabelPosition = LimitLabelPosition.RIGHT_TOP;
    private float mLimit = 0.0f;
    private int mLineColor = Color.rgb(C1662R2.attr.checkedIconSize, 91, 91);
    private float mLineWidth = 2.0f;
    private Paint.Style mTextStyle = Paint.Style.FILL_AND_STROKE;

    public enum LimitLabelPosition {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }

    public LimitLine(float f) {
        this.mLimit = f;
    }

    public LimitLine(float f, String str) {
        this.mLimit = f;
        this.mLabel = str;
    }

    public float getLimit() {
        return this.mLimit;
    }

    public void setLineWidth(float f) {
        if (f < 0.2f) {
            f = 0.2f;
        }
        if (f > 12.0f) {
            f = 12.0f;
        }
        this.mLineWidth = C1030Utils.convertDpToPixel(f);
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public void setLineColor(int i) {
        this.mLineColor = i;
    }

    public int getLineColor() {
        return this.mLineColor;
    }

    public void enableDashedLine(float f, float f2, float f3) {
        this.mDashPathEffect = new DashPathEffect(new float[]{f, f2}, f3);
    }

    public void disableDashedLine() {
        this.mDashPathEffect = null;
    }

    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public void setTextStyle(Paint.Style style) {
        this.mTextStyle = style;
    }

    public Paint.Style getTextStyle() {
        return this.mTextStyle;
    }

    public void setLabelPosition(LimitLabelPosition limitLabelPosition) {
        this.mLabelPosition = limitLabelPosition;
    }

    public LimitLabelPosition getLabelPosition() {
        return this.mLabelPosition;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String getLabel() {
        return this.mLabel;
    }
}
