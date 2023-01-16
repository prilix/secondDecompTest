package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J(\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fXD¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fXD¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, mo36738d2 = {"Lcom/jch/racWiFi/customViews/customWidgets/CircleProgressBar;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "backgroundPaint", "Landroid/graphics/Paint;", "backgroundWidth", "", "centerX", "centerY", "oval", "Landroid/graphics/RectF;", "value", "progress", "getProgress", "()F", "setProgress", "(F)V", "progressPaint", "progressWidth", "radius", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "h", "oldw", "oldh", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: CircleProgressBar.kt */
public final class CircleProgressBar extends View {
    private final Paint backgroundPaint;
    private final float backgroundWidth;
    private float centerX;
    private float centerY;
    private final RectF oval;
    private float progress;
    private final Paint progressPaint;
    private final float progressWidth;
    private float radius;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CircleProgressBar(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public void _$_clearFindViewByIdCache() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CircleProgressBar(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CircleProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.backgroundWidth = 10.0f;
        this.progressWidth = 10.0f;
        Paint paint = new Paint();
        paint.setColor(-3355444);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10.0f);
        paint.setAntiAlias(true);
        Unit unit = Unit.INSTANCE;
        this.backgroundPaint = paint;
        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor("#D2103E"));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(10.0f);
        paint2.setAntiAlias(true);
        Unit unit2 = Unit.INSTANCE;
        this.progressPaint = paint2;
        this.oval = new RectF();
    }

    public final float getProgress() {
        return this.progress;
    }

    public final void setProgress(float f) {
        this.progress = f;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        float f = (float) 2;
        float f2 = ((float) i) / f;
        this.centerX = f2;
        float f3 = ((float) i2) / f;
        this.centerY = f3;
        float f4 = f2 - this.progressWidth;
        this.radius = f4;
        this.oval.set(f2 - f4, f3 - f4, f2 + f4, f3 + f4);
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            canvas.drawCircle(this.centerX, this.centerY, this.radius, this.backgroundPaint);
        }
        if (canvas != null) {
            canvas.drawArc(this.oval, 270.0f, this.progress * 360.0f, false, this.progressPaint);
        }
    }
}
