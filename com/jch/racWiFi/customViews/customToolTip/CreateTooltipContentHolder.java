package com.jch.racWiFi.customViews.customToolTip;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.View;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.customViews.HintCase.C1687HintCase;
import com.jch.racWiFi.customViews.HintCase.contentholderanimators.FadeInContentHolderAnimator;
import com.jch.racWiFi.customViews.HintCase.contentholderanimators.FadeOutContentHolderAnimator;
import com.jch.racWiFi.customViews.HintCase.customBlock.CustomHintContentHolder;
import com.jch_hitachi.aircloudglobal.R;

public class CreateTooltipContentHolder {
    private int borderId = R.drawable.bubble_border_background;
    private int bordercolor = R.color.black;
    private int containerOffsetXAxis = R.dimen.container_block_x_offset_for_edittext;
    private Context context;
    private String data;
    private Spanned dataSpanned;
    private int hintCasePosition = 3;
    private int hintCaseYOffset = R.dimen.zero_margin;
    /* access modifiers changed from: private */
    public boolean isShowing;
    private C1687HintCase mHintCase;
    private int resId;
    private int setToolTipStyleNew = R.style.tooltip_style_new;
    private int setTooltipStyle = R.style.tooltip;
    private View view;

    public boolean isShowing() {
        return this.isShowing;
    }

    public void setBordercolor(int i) {
        this.bordercolor = i;
    }

    public void setTooltipStyle(int i) {
        this.setTooltipStyle = i;
    }

    public void setContainerOffsetXAxis(int i) {
        this.containerOffsetXAxis = i;
    }

    public void setHitCaseYOffset(int i) {
        this.hintCaseYOffset = i;
    }

    public CreateTooltipContentHolder(Context context2, View view2, Spanned spanned) {
        this.view = view2;
        this.dataSpanned = spanned;
        this.context = context2;
    }

    public CreateTooltipContentHolder(Context context2, View view2, String str) {
        this.view = view2;
        this.data = str;
        this.context = context2;
    }

    public CreateTooltipContentHolder(Context context2, View view2, int i) {
        this.view = view2;
        this.resId = i;
        this.context = context2;
    }

    public void setBorderRes(int i) {
        this.borderId = i;
    }

    public void setHintCasePosition(int i) {
        this.hintCasePosition = i;
    }

    public void build() {
        this.mHintCase = new C1687HintCase(((Activity) this.context).getWindow().getDecorView()).setTarget(this.view, this.hintCaseYOffset).setBackgroundColor(0).setHintBlock(new CustomHintContentHolder.Builder(this.view.getContext()).setContentText((CharSequence) this.data).setBorder(R.dimen.bubble_border, this.bordercolor).setArrowSize(R.dimen.arrow_width, R.dimen.arrow_height).setBackgroundColor(-1).setMargingByResourcesId(R.dimen.hintcase_vertical_margin, R.dimen.hintcase_horizontal_margin, R.dimen.hintcase_vertical_margin, R.dimen.hintcase_horizontal_margin).setContentPaddingByResourcesId(R.dimen.small_margin_horizontal, R.dimen.small_margin_vertical, R.dimen.small_margin_horizontal, R.dimen.small_margin_vertical).setContentStyle(C1655R.style.content_dark).setxTranslationArrowOffset(dpToPx(this.context.getResources(), R.dimen.tooltip_position_Y_offset)).setyTranslationArrowOffset(0.0f).setContainerOffsetXAxis(dpToPx(this.context.getResources(), this.containerOffsetXAxis)).setBorder(this.borderId).setContentStyle(this.setTooltipStyle).build(), new FadeInContentHolderAnimator(), new FadeOutContentHolderAnimator()).setCloseOnTouchView(true).setOnClosedListener(new C1687HintCase.OnClosedListener() {
            public void onClosed() {
                CreateTooltipContentHolder.this.isShowing = false;
            }
        }).setHintCasePosition(this.hintCasePosition);
    }

    public void buildWithNoDimensions() {
        this.mHintCase = new C1687HintCase(((Activity) this.context).getWindow().getDecorView()).setTarget(this.view, (int) R.dimen.tooltip_position_Y_offset).setBackgroundColor(0).setHintBlock(new CustomHintContentHolder.Builder(this.view.getContext()).setContentTextSpanned(this.dataSpanned).setContentText(this.resId, true).setBorder(R.dimen.bubble_border, this.bordercolor).setArrowSize(R.dimen.arrow_width, R.dimen.arrow_height).setBackgroundColor(-1).setMargingByResourcesId(R.dimen.text_size_8dp, R.dimen.text_size_24dp, R.dimen.text_size_8dp, R.dimen.text_size_8dp).setContentPaddingByResourcesId(R.dimen.text_size_8dp, R.dimen.text_size_8dp, R.dimen.text_size_8dp, R.dimen.text_size_8dp).setContentStyle(C1655R.style.content_dark).setxTranslationArrowOffset(dpToPx(this.context.getResources(), R.dimen.tooltip_position_Y_offset)).setyTranslationArrowOffset(0.0f).setContainerOffsetXAxis(dpToPx(this.context.getResources(), this.containerOffsetXAxis)).setBorder(this.borderId).setContentStyle(this.setToolTipStyleNew).build(), new FadeInContentHolderAnimator(), new FadeOutContentHolderAnimator()).setCloseOnTouchView(true).setOnClosedListener(new C1687HintCase.OnClosedListener() {
            public void onClosed() {
                CreateTooltipContentHolder.this.isShowing = false;
            }
        }).setHintCasePosition(this.hintCasePosition);
    }

    public void show() {
        C1687HintCase hintCase = this.mHintCase;
        if (hintCase != null) {
            this.isShowing = true;
            hintCase.show();
            return;
        }
        throw new IllegalStateException("Call build() before showing popup");
    }

    public void dismiss() {
        C1687HintCase hintCase = this.mHintCase;
        if (hintCase != null) {
            this.isShowing = false;
            hintCase.hide();
            return;
        }
        throw new IllegalStateException("Call build() before showing popup");
    }

    private float dpToPx(Resources resources, int i) {
        return TypedValue.applyDimension(1, resources.getDimension(i), resources.getDisplayMetrics());
    }
}
