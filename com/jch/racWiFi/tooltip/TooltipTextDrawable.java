package com.jch.racWiFi.tooltip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.util.ObjectsCompat;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.tooltip.Tooltip;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 72\u00020\u0001:\u00017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0010H\u0002JP\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\bH\u0002J\u0010\u0010'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\nH\u0016J\b\u0010+\u001a\u00020\nH\u0016J\u0010\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020.H\u0017J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u0010H\u0014J\u0010\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020\nH\u0016J \u00103\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0012\u00104\u001a\u00020\u001c2\b\u00105\u001a\u0004\u0018\u000106H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000¨\u00068"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/TooltipTextDrawable;", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "builder", "Lcom/jch/racWiFi/tooltip/Tooltip$Builder;", "(Landroid/content/Context;Lcom/jch/racWiFi/tooltip/Tooltip$Builder;)V", "arrowRatio", "", "arrowWeight", "", "bgPaint", "Landroid/graphics/Paint;", "gravity", "Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;", "outlineRect", "Landroid/graphics/Rect;", "padding", "path", "Landroid/graphics/Path;", "point", "Landroid/graphics/PointF;", "radius", "rectF", "Landroid/graphics/RectF;", "stPaint", "tmpPoint", "calculatePath", "", "outBounds", "calculatePathWithGravity", "left", "top", "right", "bottom", "maxY", "maxX", "minY", "minX", "draw", "canvas", "Landroid/graphics/Canvas;", "getAlpha", "getOpacity", "getOutline", "outline", "Landroid/graphics/Outline;", "onBoundsChange", "bounds", "setAlpha", "alpha", "setAnchor", "setColorFilter", "cf", "Landroid/graphics/ColorFilter;", "Companion", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: TooltipTextDrawable.kt */
public final class TooltipTextDrawable extends Drawable {
    public static final float ARROW_RATIO_DEFAULT = 1.4f;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final float arrowRatio;
    private int arrowWeight;
    private final Paint bgPaint;
    private Tooltip.Gravity gravity;
    private final Rect outlineRect = new Rect();
    private int padding;
    private final Path path;
    private PointF point;
    private final float radius;
    private final RectF rectF;
    private final Paint stPaint;
    private final PointF tmpPoint = new PointF();

    public int getOpacity() {
        return -3;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public TooltipTextDrawable(Context context, Tooltip.Builder builder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes((AttributeSet) null, C1655R.styleable.TooltipLayout, builder.getDefStyleAttr$app_release(), builder.getDefStyleRes$app_release());
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…der.defStyleRes\n        )");
        this.radius = (float) obtainStyledAttributes.getDimensionPixelSize(3, 4);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 2);
        int color = obtainStyledAttributes.getColor(2, 0);
        int color2 = obtainStyledAttributes.getColor(7, 0);
        this.arrowRatio = obtainStyledAttributes.getFloat(1, 1.4f);
        obtainStyledAttributes.recycle();
        this.rectF = new RectF();
        if (color != 0) {
            Paint paint = new Paint(1);
            this.bgPaint = paint;
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
        } else {
            this.bgPaint = null;
        }
        if (color2 != 0) {
            Paint paint2 = new Paint(1);
            this.stPaint = paint2;
            paint2.setColor(color2);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth((float) dimensionPixelSize);
        } else {
            this.stPaint = null;
        }
        this.path = new Path();
    }

    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Paint paint = this.bgPaint;
        if (paint != null) {
            canvas.drawPath(this.path, paint);
        }
        Paint paint2 = this.stPaint;
        if (paint2 != null) {
            canvas.drawPath(this.path, paint2);
        }
    }

    public final void setAnchor(Tooltip.Gravity gravity2, int i, PointF pointF) {
        Unit unit;
        Intrinsics.checkNotNullParameter(gravity2, "gravity");
        if (gravity2 != this.gravity || i != this.padding || !ObjectsCompat.equals(this.point, pointF)) {
            this.gravity = gravity2;
            this.padding = i;
            this.arrowWeight = (int) (((float) i) / this.arrowRatio);
            if (pointF == null) {
                unit = null;
            } else {
                this.point = new PointF(pointF.x, pointF.y);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                TooltipTextDrawable tooltipTextDrawable = this;
                this.point = null;
            }
            if (!getBounds().isEmpty()) {
                Rect bounds = getBounds();
                Intrinsics.checkNotNullExpressionValue(bounds, "bounds");
                calculatePath(bounds);
                invalidateSelf();
            }
        }
    }

    private final void calculatePath(Rect rect) {
        int i = rect.left + this.padding;
        int i2 = rect.top + this.padding;
        int i3 = rect.right - this.padding;
        int i4 = rect.bottom - this.padding;
        float f = (float) i4;
        float f2 = this.radius;
        float f3 = f - f2;
        float f4 = (float) i3;
        float f5 = f4 - f2;
        float f6 = (float) i2;
        float f7 = f6 + f2;
        float f8 = (float) i;
        float f9 = f2 + f8;
        if (this.point == null || this.gravity == null) {
            this.rectF.set(f8, f6, f4, f);
            Path path2 = this.path;
            RectF rectF2 = this.rectF;
            float f10 = this.radius;
            path2.addRoundRect(rectF2, f10, f10, Path.Direction.CW);
            return;
        }
        calculatePathWithGravity(rect, i, i2, i3, i4, f3, f5, f7, f9);
    }

    private final void calculatePathWithGravity(Rect rect, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4) {
        Rect rect2 = rect;
        if (this.gravity == Tooltip.Gravity.LEFT || this.gravity == Tooltip.Gravity.RIGHT) {
            float f5 = f - f3;
            if (f5 < ((float) (this.arrowWeight * 2))) {
                this.arrowWeight = (int) ((float) Math.floor((double) (f5 / ((float) 2))));
            }
        } else if (this.gravity == Tooltip.Gravity.BOTTOM || this.gravity == Tooltip.Gravity.TOP) {
            float f6 = f2 - f4;
            if (f6 < ((float) (this.arrowWeight * 2))) {
                this.arrowWeight = (int) ((float) Math.floor((double) (f6 / ((float) 2))));
            }
        }
        Companion companion = Companion;
        PointF pointF = this.tmpPoint;
        PointF pointF2 = this.point;
        Intrinsics.checkNotNull(pointF2);
        boolean access$isDrawPoint = companion.isDrawPoint(i, i2, i3, i4, f, f2, f3, f4, pointF, pointF2, this.gravity, this.arrowWeight);
        int i5 = i;
        companion.clampPoint(i5, i2, i3, i4, this.tmpPoint);
        this.path.reset();
        float f7 = (float) i5;
        float f8 = (float) i2;
        this.path.moveTo(this.radius + f7, f8);
        if (access$isDrawPoint && this.gravity == Tooltip.Gravity.BOTTOM) {
            this.path.lineTo((this.tmpPoint.x + f7) - ((float) this.arrowWeight), f8);
            this.path.lineTo(this.tmpPoint.x + f7, (float) rect2.top);
            this.path.lineTo(this.tmpPoint.x + f7 + ((float) this.arrowWeight), f8);
        }
        float f9 = (float) i3;
        this.path.lineTo(f9 - this.radius, f8);
        this.path.quadTo(f9, f8, f9, this.radius + f8);
        if (access$isDrawPoint && this.gravity == Tooltip.Gravity.LEFT) {
            this.path.lineTo(f9, (this.tmpPoint.y + f8) - ((float) this.arrowWeight));
            this.path.lineTo((float) rect2.right, this.tmpPoint.y + f8);
            this.path.lineTo(f9, this.tmpPoint.y + f8 + ((float) this.arrowWeight));
        }
        float f10 = (float) i4;
        this.path.lineTo(f9, f10 - this.radius);
        this.path.quadTo(f9, f10, f9 - this.radius, f10);
        if (access$isDrawPoint && this.gravity == Tooltip.Gravity.TOP) {
            this.path.lineTo(this.tmpPoint.x + f7 + ((float) this.arrowWeight), f10);
            this.path.lineTo(this.tmpPoint.x + f7, (float) rect2.bottom);
            this.path.lineTo((this.tmpPoint.x + f7) - ((float) this.arrowWeight), f10);
        }
        this.path.lineTo(this.radius + f7, f10);
        this.path.quadTo(f7, f10, f7, f10 - this.radius);
        if (access$isDrawPoint && this.gravity == Tooltip.Gravity.RIGHT) {
            this.path.lineTo(f7, this.tmpPoint.y + f8 + ((float) this.arrowWeight));
            this.path.lineTo((float) rect2.left, this.tmpPoint.y + f8);
            this.path.lineTo(f7, (this.tmpPoint.y + f8) - ((float) this.arrowWeight));
        }
        this.path.lineTo(f7, this.radius + f8);
        this.path.quadTo(f7, f8, this.radius + f7, f8);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        super.onBoundsChange(rect);
        calculatePath(rect);
    }

    public int getAlpha() {
        Paint paint = this.bgPaint;
        Integer valueOf = paint == null ? null : Integer.valueOf(paint.getAlpha());
        if (valueOf != null) {
            return valueOf.intValue();
        }
        TooltipTextDrawable tooltipTextDrawable = this;
        return 0;
    }

    public void setAlpha(int i) {
        Paint paint = this.bgPaint;
        if (paint != null) {
            paint.setAlpha(i);
        }
        Paint paint2 = this.stPaint;
        if (paint2 != null) {
            paint2.setAlpha(i);
        }
    }

    public void getOutline(Outline outline) {
        Intrinsics.checkNotNullParameter(outline, "outline");
        copyBounds(this.outlineRect);
        Rect rect = this.outlineRect;
        int i = this.padding;
        rect.inset(i, i);
        outline.setRoundRect(this.outlineRect, this.radius);
        if (getAlpha() < 255) {
            outline.setAlpha(0.0f);
        }
    }

    @Metadata(mo36737d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002Jj\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/TooltipTextDrawable$Companion;", "", "()V", "ARROW_RATIO_DEFAULT", "", "clampPoint", "", "left", "", "top", "right", "bottom", "tmpPoint", "Landroid/graphics/PointF;", "isDrawPoint", "", "maxY", "maxX", "minY", "minX", "point", "gravity", "Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;", "arrowWeight", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: TooltipTextDrawable.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean isDrawPoint(int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, PointF pointF, PointF pointF2, Tooltip.Gravity gravity, int i5) {
            pointF.set(pointF2.x, pointF2.y);
            if (gravity == Tooltip.Gravity.RIGHT || gravity == Tooltip.Gravity.LEFT) {
                int i6 = (int) pointF.y;
                if (i2 <= i6 && i6 <= i4) {
                    float f5 = (float) i2;
                    float f6 = (float) i5;
                    if (pointF.y + f5 + f6 > f) {
                        pointF.y = (f - f6) - f5;
                        return true;
                    } else if ((pointF.y + f5) - f6 >= f3) {
                        return true;
                    } else {
                        pointF.y = (f3 + f6) - f5;
                        return true;
                    }
                }
            } else {
                int i7 = (int) pointF.x;
                if (i <= i7 && i7 <= i3) {
                    int i8 = (int) pointF.x;
                    if (i <= i8 && i8 <= i3) {
                        float f7 = (float) i;
                        float f8 = (float) i5;
                        if (pointF.x + f7 + f8 > f2) {
                            pointF.x = (f2 - f8) - f7;
                            return true;
                        } else if ((pointF.x + f7) - f8 >= f4) {
                            return true;
                        } else {
                            pointF.x = (f4 + f8) - f7;
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public final void clampPoint(int i, int i2, int i3, int i4, PointF pointF) {
            float f = (float) i2;
            if (pointF.y < f) {
                pointF.y = f;
            } else {
                float f2 = (float) i4;
                if (pointF.y > f2) {
                    pointF.y = f2;
                }
            }
            float f3 = (float) i;
            if (pointF.x < f3) {
                pointF.x = f3;
            }
            float f4 = (float) i3;
            if (pointF.x > f4) {
                pointF.x = f4;
            }
        }
    }
}
