package com.jch.racWiFi.tooltip;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.jch.racWiFi.C1655R;
import com.jch_hitachi.aircloudglobal.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u0002R\u000e\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/TooltipOverlay;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleResId", "(Landroid/content/Context;II)V", "layoutMargins", "init", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: TooltipOverlay.kt */
public final class TooltipOverlay extends AppCompatImageView {
    private int layoutMargins;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TooltipOverlay(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TooltipOverlay(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public void _$_clearFindViewByIdCache() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TooltipOverlay(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? R.style.ToolTipOverlayDefaultStyle : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TooltipOverlay(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context, R.style.ToolTipLayoutDefaultStyle);
    }

    private final void init(Context context, int i) {
        setImageDrawable(new TooltipOverlayDrawable(context, i));
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, C1655R.styleable.TooltipOverlay);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl…styleable.TooltipOverlay)");
        this.layoutMargins = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TooltipOverlay(Context context, int i, int i2) {
        super(context, (AttributeSet) null, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context, i2);
    }
}
