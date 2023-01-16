package com.jch.racWiFi.customViews.HintCase.shapeanimators;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import com.jch.racWiFi.customViews.HintCase.RectangularShape;
import com.jch.racWiFi.customViews.HintCase.Shape;
import com.jch.racWiFi.customViews.HintCase.ShapeAnimator;

public class RevealRectangularShapeAnimator extends ShapeAnimator {
    /* access modifiers changed from: private */
    public FloatEvaluator floatEvaluator;

    public RevealRectangularShapeAnimator() {
        init();
    }

    public RevealRectangularShapeAnimator(int i) {
        super(i);
        init();
    }

    private void init() {
        this.floatEvaluator = new FloatEvaluator();
    }

    public ValueAnimator getAnimator(final View view, Shape shape, final ShapeAnimator.OnFinishListener onFinishListener) {
        final RectangularShape rectangularShape = (RectangularShape) shape;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{rectangularShape.getMaxHeight(), rectangularShape.getMinHeight()});
        ofFloat.setStartDelay(this.startDelayInMilliseconds);
        ofFloat.setDuration((long) this.durationInMilliseconds).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                rectangularShape.setCurrentHeight(((Float) valueAnimator.getAnimatedValue()).floatValue());
                rectangularShape.setCurrentWidth(RevealRectangularShapeAnimator.this.floatEvaluator.evaluate(valueAnimator.getAnimatedFraction(), Float.valueOf(rectangularShape.getMaxWidth()), Float.valueOf(rectangularShape.getMinWidth())).floatValue());
                if (rectangularShape.getCurrentHeight() == rectangularShape.getMinHeight()) {
                    rectangularShape.setMinimumValue();
                    ShapeAnimator.OnFinishListener onFinishListener = onFinishListener;
                    if (onFinishListener != null) {
                        onFinishListener.onFinish();
                    }
                }
                view.invalidate();
            }
        });
        return ofFloat;
    }
}
