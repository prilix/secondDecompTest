package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.C1030Utils;
import com.github.mikephil.charting.utils.MPPointF;

public class Description extends ComponentBase {
    private MPPointF mPosition;
    private Paint.Align mTextAlign = Paint.Align.RIGHT;
    private String text = "Description Label";

    public Description() {
        this.mTextSize = C1030Utils.convertDpToPixel(8.0f);
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    public void setPosition(float f, float f2) {
        MPPointF mPPointF = this.mPosition;
        if (mPPointF == null) {
            this.mPosition = MPPointF.getInstance(f, f2);
            return;
        }
        mPPointF.f195x = f;
        this.mPosition.f196y = f2;
    }

    public MPPointF getPosition() {
        return this.mPosition;
    }

    public void setTextAlign(Paint.Align align) {
        this.mTextAlign = align;
    }

    public Paint.Align getTextAlign() {
        return this.mTextAlign;
    }
}
