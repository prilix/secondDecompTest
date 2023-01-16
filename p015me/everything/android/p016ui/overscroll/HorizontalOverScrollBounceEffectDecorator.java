package p015me.everything.android.p016ui.overscroll;

import android.view.MotionEvent;
import android.view.View;
import p015me.everything.android.p016ui.overscroll.OverScrollBounceEffectDecoratorBase;
import p015me.everything.android.p016ui.overscroll.adapters.IOverScrollDecoratorAdapter;

/* renamed from: me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator */
public class HorizontalOverScrollBounceEffectDecorator extends OverScrollBounceEffectDecoratorBase {

    /* renamed from: me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator$MotionAttributesHorizontal */
    protected static class MotionAttributesHorizontal extends OverScrollBounceEffectDecoratorBase.MotionAttributes {
        protected MotionAttributesHorizontal() {
        }

        public boolean init(View view, MotionEvent motionEvent) {
            boolean z = false;
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            float x = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
            if (Math.abs(x) < Math.abs(y)) {
                return false;
            }
            this.mAbsOffset = view.getTranslationX();
            this.mDeltaOffset = x;
            if (this.mDeltaOffset > 0.0f) {
                z = true;
            }
            this.mDir = z;
            return true;
        }
    }

    /* renamed from: me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator$AnimationAttributesHorizontal */
    protected static class AnimationAttributesHorizontal extends OverScrollBounceEffectDecoratorBase.AnimationAttributes {
        public AnimationAttributesHorizontal() {
            this.mProperty = View.TRANSLATION_X;
        }

        /* access modifiers changed from: protected */
        public void init(View view) {
            this.mAbsOffset = view.getTranslationX();
            this.mMaxOffset = (float) view.getWidth();
        }
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter) {
        this(iOverScrollDecoratorAdapter, 3.0f, 1.0f, -2.0f);
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, float f2, float f3) {
        super(iOverScrollDecoratorAdapter, f3, f, f2);
    }

    /* access modifiers changed from: protected */
    public OverScrollBounceEffectDecoratorBase.MotionAttributes createMotionAttributes() {
        return new MotionAttributesHorizontal();
    }

    /* access modifiers changed from: protected */
    public OverScrollBounceEffectDecoratorBase.AnimationAttributes createAnimationAttributes() {
        return new AnimationAttributesHorizontal();
    }

    /* access modifiers changed from: protected */
    public void translateView(View view, float f) {
        view.setTranslationX(f);
    }

    /* access modifiers changed from: protected */
    public void translateViewAndEvent(View view, float f, MotionEvent motionEvent) {
        view.setTranslationX(f);
        motionEvent.offsetLocation(f - motionEvent.getX(0), 0.0f);
    }
}
