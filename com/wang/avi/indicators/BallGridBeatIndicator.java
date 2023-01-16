package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.jch.racWiFi.C1662R2;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class BallGridBeatIndicator extends Indicator {
    public static final int ALPHA = 255;
    int[] alphas = {255, 255, 255, 255, 255, 255, 255, 255, 255};

    public void draw(Canvas canvas, Paint paint) {
        float width = (((float) getWidth()) - 16.0f) / 6.0f;
        float f = 2.0f * width;
        float f2 = f + 4.0f;
        float width2 = ((float) (getWidth() / 2)) - f2;
        float width3 = ((float) (getWidth() / 2)) - f2;
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                canvas.save();
                float f3 = (float) i2;
                float f4 = (f * f3) + width2 + (f3 * 4.0f);
                float f5 = (float) i;
                canvas.translate(f4, (f * f5) + width3 + (f5 * 4.0f));
                paint.setAlpha(this.alphas[(i * 3) + i2]);
                canvas.drawCircle(0.0f, 0.0f, width, paint);
                canvas.restore();
            }
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        int[] iArr = {C1662R2.attr.sb_background, C1662R2.attr.quantizeMotionPhase, C1662R2.attr.thumbTintMode, C1662R2.attr.textAppearanceHeadline6, C1662R2.bool.m3_sys_typescale_body_small_text_all_caps, C1662R2.attr.reactiveGuide_animateChange, C1662R2.attr.tint, C1662R2.attr.mimeType, C1662R2.attr.thumbTintMode};
        int[] iArr2 = {C1662R2.attr.contentInsetEndWithActions, 400, C1662R2.attr.layout_constraintBaseline_toTopOf, C1662R2.attr.dayStyle, C1662R2.attr.layout_constraintTop_creator, -150, -120, 10, C1662R2.attr.colorOnError};
        for (final int i = 0; i < 9; i++) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{255, C1662R2.attr.borderlessButtonStyle, 255});
            ofInt.setDuration((long) iArr[i]);
            ofInt.setRepeatCount(-1);
            ofInt.setStartDelay((long) iArr2[i]);
            addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallGridBeatIndicator.this.alphas[i] = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    BallGridBeatIndicator.this.postInvalidate();
                }
            });
            arrayList.add(ofInt);
        }
        return arrayList;
    }
}
