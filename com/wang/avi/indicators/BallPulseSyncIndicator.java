package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.jch.racWiFi.C1662R2;
import com.wang.avi.Indicator;
import java.util.ArrayList;

public class BallPulseSyncIndicator extends Indicator {
    float[] translateYFloats = new float[3];

    public void draw(Canvas canvas, Paint paint) {
        float width = (((float) getWidth()) - 8.0f) / 6.0f;
        float f = 2.0f * width;
        float width2 = ((float) (getWidth() / 2)) - (f + 4.0f);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float f2 = (float) i;
            canvas.translate((f * f2) + width2 + (f2 * 4.0f), this.translateYFloats[i]);
            canvas.drawCircle(0.0f, 0.0f, width, paint);
            canvas.restore();
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        float width = (((float) getWidth()) - 8.0f) / 6.0f;
        int[] iArr = {70, C1662R2.attr.badgeGravity, C1662R2.attr.cardCornerRadius};
        for (final int i = 0; i < 3; i++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) (getHeight() / 2), ((float) (getHeight() / 2)) - (2.0f * width), (float) (getHeight() / 2)});
            ofFloat.setDuration(600);
            ofFloat.setRepeatCount(-1);
            ofFloat.setStartDelay((long) iArr[i]);
            addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallPulseSyncIndicator.this.translateYFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    BallPulseSyncIndicator.this.postInvalidate();
                }
            });
            arrayList.add(ofFloat);
        }
        return arrayList;
    }
}
