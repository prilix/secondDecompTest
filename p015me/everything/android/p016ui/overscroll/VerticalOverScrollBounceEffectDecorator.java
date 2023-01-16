package p015me.everything.android.p016ui.overscroll;

import android.view.MotionEvent;
import android.view.View;
import p015me.everything.android.p016ui.overscroll.OverScrollBounceEffectDecoratorBase;
import p015me.everything.android.p016ui.overscroll.adapters.IOverScrollDecoratorAdapter;

/* renamed from: me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator */
public class VerticalOverScrollBounceEffectDecorator extends OverScrollBounceEffectDecoratorBase {

    /* renamed from: me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator$MotionAttributesVertical */
    protected static class MotionAttributesVertical extends OverScrollBounceEffectDecoratorBase.MotionAttributes {
        protected MotionAttributesVertical() {
        }

        public boolean init(View view, MotionEvent motionEvent) {
            boolean z = false;
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.mAbsOffset = view.getTranslationY();
            this.mDeltaOffset = y;
            if (this.mDeltaOffset > 0.0f) {
                z = true;
            }
            this.mDir = z;
            return true;
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator$AnimationAttributesVertical */
    protected static class AnimationAttributesVertical extends OverScrollBounceEffectDecoratorBase.AnimationAttributes {
        public AnimationAttributesVertical() {
            this.mProperty = View.TRANSLATION_Y;
        }

        /* access modifiers changed from: protected */
        public void init(View view) {
            this.mAbsOffset = view.getTranslationY();
            this.mMaxOffset = (float) view.getHeight();
        }
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter) {
        this(iOverScrollDecoratorAdapter, 3.0f, 1.0f, -2.0f);
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, float f2, float f3) {
        super(iOverScrollDecoratorAdapter, f3, f, f2);
    }

    /* access modifiers changed from: protected */
    public OverScrollBounceEffectDecoratorBase.MotionAttributes createMotionAttributes() {
        return new MotionAttributesVertical();
    }

    /* access modifiers changed from: protected */
    public OverScrollBounceEffectDecoratorBase.AnimationAttributes createAnimationAttributes() {
        return new AnimationAttributesVertical();
    }

    /* access modifiers changed from: protected */
    public void translateView(View view, float f) {
        view.setTranslationY(f);
    }

    /* access modifiers changed from: protected */
    public void translateViewAndEvent(View view, float f, MotionEvent motionEvent) {
        view.setTranslationY(f);
        motionEvent.offsetLocation(f - motionEvent.getY(0), 0.0f);
    }
}
