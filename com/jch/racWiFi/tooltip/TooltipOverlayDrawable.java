package com.jch.racWiFi.tooltip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.jch.racWiFi.C1655R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 @2\u00020\u0001:\u0001@B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\u0005H\u0016J\b\u00100\u001a\u00020\u0005H\u0016J\b\u00101\u001a\u00020\u0005H\u0016J\u0010\u00102\u001a\u00020,2\u0006\u00103\u001a\u000204H\u0014J\b\u00105\u001a\u00020,H\u0002J\b\u00106\u001a\u00020,H\u0002J\u0010\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020\u0005H\u0016J\u0012\u00109\u001a\u00020,2\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u0018\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020#H\u0016J\b\u0010?\u001a\u00020,H\u0002R$\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00058B@BX\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R$\u0010$\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00058B@BX\u000e¢\u0006\f\u001a\u0004\b%\u0010\n\"\u0004\b&\u0010\fR$\u0010'\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8B@BX\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010\u0011¨\u0006A"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/TooltipOverlayDrawable;", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "defStyleResId", "", "(Landroid/content/Context;I)V", "value", "innerAlpha", "getInnerAlpha", "()I", "setInnerAlpha", "(I)V", "rippleRadius", "", "innerRadius", "setInnerRadius", "(F)V", "mDuration", "", "mFirstAnimator", "Landroid/animation/ValueAnimator;", "mFirstAnimatorSet", "Landroid/animation/AnimatorSet;", "mInnerAlpha", "mInnerPaint", "Landroid/graphics/Paint;", "mOuterAlpha", "mOuterPaint", "mOuterRadius", "mRepeatCount", "mRepeatIndex", "mSecondAnimator", "mSecondAnimatorSet", "mStarted", "", "outerAlpha", "getOuterAlpha", "setOuterAlpha", "outerRadius", "getOuterRadius", "()F", "setOuterRadius", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getIntrinsicHeight", "getIntrinsicWidth", "getOpacity", "onBoundsChange", "bounds", "Landroid/graphics/Rect;", "play", "replay", "setAlpha", "i", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "setVisible", "visible", "restart", "stop", "Companion", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: TooltipOverlayDrawable.kt */
public final class TooltipOverlayDrawable extends Drawable {
    public static final float ALPHA_MAX = 255.0f;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final double FADEIN_DURATION = 0.3d;
    public static final double FADEOUT_START_DELAY = 0.55d;
    public static final double SECOND_ANIM_START_DELAY = 0.25d;
    private float innerRadius;
    private long mDuration = 400;
    private final ValueAnimator mFirstAnimator;
    /* access modifiers changed from: private */
    public final AnimatorSet mFirstAnimatorSet;
    private final int mInnerAlpha;
    private final Paint mInnerPaint;
    private final int mOuterAlpha;
    private final Paint mOuterPaint;
    private float mOuterRadius;
    /* access modifiers changed from: private */
    public int mRepeatCount = 1;
    /* access modifiers changed from: private */
    public int mRepeatIndex;
    private final ValueAnimator mSecondAnimator;
    /* access modifiers changed from: private */
    public final AnimatorSet mSecondAnimatorSet;
    private boolean mStarted;

    public int getIntrinsicHeight() {
        return 96;
    }

    public int getIntrinsicWidth() {
        return 96;
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public TooltipOverlayDrawable(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint(1);
        this.mOuterPaint = paint;
        Paint paint2 = new Paint(1);
        this.mInnerPaint = paint2;
        paint.setStyle(Paint.Style.FILL);
        paint2.setStyle(Paint.Style.FILL);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, C1655R.styleable.TooltipOverlay);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…styleable.TooltipOverlay)");
        int indexCount = obtainStyledAttributes.getIndexCount();
        if (indexCount > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == 1) {
                    int color = obtainStyledAttributes.getColor(index, 0);
                    this.mOuterPaint.setColor(color);
                    this.mInnerPaint.setColor(color);
                } else if (index == 2) {
                    int i4 = (int) (obtainStyledAttributes.getFloat(index, ((float) this.mInnerPaint.getAlpha()) / 255.0f) * ((float) 255));
                    this.mInnerPaint.setAlpha(i4);
                    this.mOuterPaint.setAlpha(i4);
                } else if (index == 3) {
                    this.mDuration = (long) obtainStyledAttributes.getInt(index, 400);
                } else if (index == 4) {
                    this.mRepeatCount = obtainStyledAttributes.getInt(index, 1);
                }
                if (i3 >= indexCount) {
                    break;
                }
                i2 = i3;
            }
        }
        obtainStyledAttributes.recycle();
        int outerAlpha = getOuterAlpha();
        this.mOuterAlpha = outerAlpha;
        int innerAlpha = getInnerAlpha();
        this.mInnerAlpha = innerAlpha;
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "outerAlpha", new int[]{0, outerAlpha});
        Intrinsics.checkNotNullExpressionValue(ofInt, "ofInt(this, \"outerAlpha\", 0, mOuterAlpha)");
        Animator animator = ofInt;
        animator.setDuration((long) (((double) this.mDuration) * 0.3d));
        ObjectAnimator ofInt2 = ObjectAnimator.ofInt(this, "outerAlpha", new int[]{outerAlpha, 0, 0});
        Intrinsics.checkNotNullExpressionValue(ofInt2, "ofInt(this, \"outerAlpha\", mOuterAlpha, 0, 0)");
        Animator animator2 = ofInt2;
        animator2.setStartDelay((long) (((double) this.mDuration) * 0.55d));
        animator2.setDuration((long) (((double) this.mDuration) * 0.44999999999999996d));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "outerRadius", new float[]{0.0f, 1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat, "ofFloat(this, \"outerRadius\", 0f, 1f)");
        ValueAnimator valueAnimator = ofFloat;
        this.mFirstAnimator = valueAnimator;
        ((ObjectAnimator) valueAnimator).setDuration(this.mDuration);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mFirstAnimatorSet = animatorSet;
        animatorSet.playTogether(new Animator[]{animator, valueAnimator, animator2});
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.setDuration(this.mDuration);
        ObjectAnimator ofInt3 = ObjectAnimator.ofInt(this, "innerAlpha", new int[]{0, innerAlpha});
        Intrinsics.checkNotNullExpressionValue(ofInt3, "ofInt(this, \"innerAlpha\", 0, mInnerAlpha)");
        Animator animator3 = ofInt3;
        ((ObjectAnimator) animator3).setDuration((long) (((double) this.mDuration) * 0.3d));
        ObjectAnimator ofInt4 = ObjectAnimator.ofInt(this, "innerAlpha", new int[]{innerAlpha, 0, 0});
        Intrinsics.checkNotNullExpressionValue(ofInt4, "ofInt(this, \"innerAlpha\", mInnerAlpha, 0, 0)");
        Animator animator4 = ofInt4;
        animator4.setStartDelay((long) (((double) this.mDuration) * 0.55d));
        ((ObjectAnimator) animator4).setDuration((long) (((double) this.mDuration) * 0.44999999999999996d));
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "innerRadius", new float[]{0.0f, 1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat2, "ofFloat(this, \"innerRadius\", 0f, 1f)");
        ValueAnimator valueAnimator2 = ofFloat2;
        this.mSecondAnimator = valueAnimator2;
        ((ObjectAnimator) valueAnimator2).setDuration(this.mDuration);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mSecondAnimatorSet = animatorSet2;
        animatorSet2.playTogether(new Animator[]{animator3, valueAnimator2, animator4});
        animatorSet2.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet2.setStartDelay((long) (((double) this.mDuration) * 0.25d));
        animatorSet2.setDuration(this.mDuration);
        animatorSet.addListener(new AnimatorListenerAdapter(this) {
            private boolean cancelled;
            final /* synthetic */ TooltipOverlayDrawable this$0;

            {
                this.this$0 = r1;
            }

            public final boolean getCancelled() {
                return this.cancelled;
            }

            public final void setCancelled(boolean z) {
                this.cancelled = z;
            }

            public void onAnimationCancel(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animation");
                super.onAnimationCancel(animator);
                this.cancelled = true;
            }

            public void onAnimationEnd(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animation");
                if (!this.cancelled && this.this$0.isVisible()) {
                    TooltipOverlayDrawable tooltipOverlayDrawable = this.this$0;
                    tooltipOverlayDrawable.mRepeatIndex = tooltipOverlayDrawable.mRepeatIndex + 1;
                    if (tooltipOverlayDrawable.mRepeatIndex < this.this$0.mRepeatCount) {
                        this.this$0.mFirstAnimatorSet.start();
                    }
                }
            }
        });
        animatorSet2.addListener(new AnimatorListenerAdapter(this) {
            private boolean cancelled;
            final /* synthetic */ TooltipOverlayDrawable this$0;

            {
                this.this$0 = r1;
            }

            public final boolean getCancelled() {
                return this.cancelled;
            }

            public final void setCancelled(boolean z) {
                this.cancelled = z;
            }

            public void onAnimationCancel(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animation");
                super.onAnimationCancel(animator);
                this.cancelled = true;
            }

            public void onAnimationEnd(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animation");
                if (!this.cancelled && this.this$0.isVisible() && this.this$0.mRepeatIndex < this.this$0.mRepeatCount) {
                    this.this$0.mSecondAnimatorSet.setStartDelay(0);
                    this.this$0.mSecondAnimatorSet.start();
                }
            }
        });
    }

    private final void setInnerRadius(float f) {
        this.innerRadius = f;
        invalidateSelf();
    }

    private final int getOuterAlpha() {
        return this.mOuterPaint.getAlpha();
    }

    private final void setOuterAlpha(int i) {
        this.mOuterPaint.setAlpha(i);
        invalidateSelf();
    }

    private final int getInnerAlpha() {
        return this.mInnerPaint.getAlpha();
    }

    private final void setInnerAlpha(int i) {
        this.mInnerPaint.setAlpha(i);
        invalidateSelf();
    }

    private final float getOuterRadius() {
        return this.mOuterRadius;
    }

    private final void setOuterRadius(float f) {
        this.mOuterRadius = f;
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Rect bounds = getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "bounds");
        float width = (float) (bounds.width() / 2);
        float height = (float) (bounds.height() / 2);
        canvas.drawCircle(width, height, this.mOuterRadius, this.mOuterPaint);
        canvas.drawCircle(width, height, this.innerRadius, this.mInnerPaint);
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean z3 = isVisible() != z;
        if (!z) {
            stop();
        } else if (z2 || !this.mStarted) {
            replay();
        }
        return z3;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        super.onBoundsChange(rect);
        float min = (float) (Math.min(rect.width(), rect.height()) / 2);
        this.mOuterRadius = min;
        this.mFirstAnimator.setFloatValues(new float[]{0.0f, min});
        this.mSecondAnimator.setFloatValues(new float[]{0.0f, this.mOuterRadius});
    }

    private final void play() {
        this.mRepeatIndex = 0;
        this.mStarted = true;
        this.mFirstAnimatorSet.start();
        this.mSecondAnimatorSet.setStartDelay((long) (((double) this.mDuration) * 0.25d));
        this.mSecondAnimatorSet.start();
    }

    private final void replay() {
        stop();
        play();
    }

    private final void stop() {
        this.mFirstAnimatorSet.cancel();
        this.mSecondAnimatorSet.cancel();
        this.mRepeatIndex = 0;
        this.mStarted = false;
        setInnerRadius(0.0f);
        setOuterRadius(0.0f);
    }

    @Metadata(mo36737d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\t"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/TooltipOverlayDrawable$Companion;", "", "()V", "ALPHA_MAX", "", "FADEIN_DURATION", "", "FADEOUT_START_DELAY", "SECOND_ANIM_START_DELAY", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: TooltipOverlayDrawable.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
