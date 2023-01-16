package com.jch.racWiFi.customViews.HintCase.shapeanimators;

import android.animation.ValueAnimator;
import android.view.View;
import com.jch.racWiFi.customViews.HintCase.Shape;
import com.jch.racWiFi.customViews.HintCase.ShapeAnimator;
import com.jch.racWiFi.customViews.HintCase.shapes.CircularShape;

public class UnrevealCircleShapeAnimator extends ShapeAnimator {
    public UnrevealCircleShapeAnimator() {
    }

    public UnrevealCircleShapeAnimator(int i) {
        super(i);
    }

    public ValueAnimator getAnimator(final View view, Shape shape, final ShapeAnimator.OnFinishListener onFinishListener) {
        final CircularShape circularShape = (CircularShape) shape;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{circularShape.getMinRadius(), circularShape.getMaxRadius()});
        ofFloat.setStartDelay(this.startDelayInMilliseconds);
        ofFloat.setDuration((long) this.durationInMilliseconds).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ShapeAnimator.OnFinishListener onFinishListener;
                circularShape.setCurrentRadius(((Float) valueAnimator.getAnimatedValue()).floatValue());
                if (circularShape.getCurrentRadius() == circularShape.getMaxRadius() && (onFinishListener = onFinishListener) != null) {
                    onFinishListener.onFinish();
                }
                view.invalidate();
            }
        });
        return ofFloat;
    }
}
