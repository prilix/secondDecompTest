package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.jch.racWiFi.C1662R2;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class BallGridPulseIndicator extends Indicator {
    public static final int ALPHA = 255;
    public static final float SCALE = 1.0f;
    int[] alphas = {255, 255, 255, 255, 255, 255, 255, 255, 255};
    float[] scaleFloats = {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};

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
                float[] fArr = this.scaleFloats;
                int i3 = (i * 3) + i2;
                canvas.scale(fArr[i3], fArr[i3]);
                paint.setAlpha(this.alphas[i3]);
                canvas.drawCircle(0.0f, 0.0f, width, paint);
                canvas.restore();
            }
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        int[] iArr = {C1662R2.attr.layout_constraintWidth_percent, 1020, C1662R2.attr.ttlm_arrowRatio, C1662R2.C1663color.color_auto_global, C1662R2.C1663color.cost_bar_color, C1662R2.attr.theme, C1662R2.attr.nestedScrollViewStyle, C1662R2.C1663color.cost_bar_color, C1662R2.attr.strokeWidth};
        int[] iArr2 = {-60, 250, -170, C1662R2.attr.errorTextAppearance, 310, 30, C1662R2.attr.elevationOverlayAccentColor, C1662R2.attr.materialCalendarHeaderCancelButton, C1662R2.attr.duration};
        for (final int i = 0; i < 9; i++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.5f, 1.0f});
            ofFloat.setDuration((long) iArr[i]);
            ofFloat.setRepeatCount(-1);
            ofFloat.setStartDelay((long) iArr2[i]);
            addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallGridPulseIndicator.this.scaleFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    BallGridPulseIndicator.this.postInvalidate();
                }
            });
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{255, C1662R2.attr.cardCornerRadius, 122, 255});
            ofInt.setDuration((long) iArr[i]);
            ofInt.setRepeatCount(-1);
            ofInt.setStartDelay((long) iArr2[i]);
            addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallGridPulseIndicator.this.alphas[i] = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    BallGridPulseIndicator.this.postInvalidate();
                }
            });
            arrayList.add(ofFloat);
            arrayList.add(ofInt);
        }
        return arrayList;
    }
}
