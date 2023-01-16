package com.jch.racWiFi.customViews.HintCase.shapeanimators;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import com.jch.racWiFi.customViews.HintCase.RectangularShape;
import com.jch.racWiFi.customViews.HintCase.Shape;
import com.jch.racWiFi.customViews.HintCase.ShapeAnimator;

public class UnrevealRectangularShapeAnimator extends ShapeAnimator {
    /* access modifiers changed from: private */
    public FloatEvaluator floatEvaluator;

    public UnrevealRectangularShapeAnimator() {
        init();
    }

    public UnrevealRectangularShapeAnimator(int i) {
        super(i);
        init();
    }

    private void init() {
        this.floatEvaluator = new FloatEvaluator();
    }

    public ValueAnimator getAnimator(final View view, Shape shape, final ShapeAnimator.OnFinishListener onFinishListener) {
        final RectangularShape rectangularShape = (RectangularShape) shape;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{rectangularShape.getMinHeight(), rectangularShape.getMaxHeight()});
        ofFloat.setStartDelay(this.startDelayInMilliseconds);
        ofFloat.setDuration((long) this.durationInMilliseconds).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ShapeAnimator.OnFinishListener onFinishListener;
                rectangularShape.setCurrentHeight(((Float) valueAnimator.getAnimatedValue()).floatValue());
                rectangularShape.setCurrentWidth(UnrevealRectangularShapeAnimator.this.floatEvaluator.evaluate(valueAnimator.getAnimatedFraction(), Float.valueOf(rectangularShape.getMinWidth()), Float.valueOf(rectangularShape.getMaxWidth())).floatValue());
                if (rectangularShape.getCurrentHeight() == rectangularShape.getMaxHeight() && (onFinishListener = onFinishListener) != null) {
                    onFinishListener.onFinish();
                }
                view.invalidate();
            }
        });
        return ofFloat;
    }
}
