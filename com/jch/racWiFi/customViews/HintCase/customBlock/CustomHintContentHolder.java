package com.jch.racWiFi.customViews.HintCase.customBlock;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jch.racWiFi.customViews.HintCase.C1687HintCase;
import com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView;
import com.jch.racWiFi.customViews.HintCase.hintcontentholders.HintContentHolder;
import com.jch.racWiFi.customViews.HintCase.utils.DimenUtils;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Iterator;

public class CustomHintContentHolder extends HintContentHolder {
    public static final int BACKGROUND_COLOR_TRANSPARENT = 0;
    private static final int DEFAULT_ARROW_SIZE_IN_PX = 50;
    public static final int NO_IMAGE = -1;
    private ArrayList<Integer> alignArrowRules;
    private ArrayList<Integer> alignBlockRules;
    private TriangleShapeView arrow;
    private TriangleShapeView.Direction arrowDirection;
    /* access modifiers changed from: private */
    public int arrowHeight;
    /* access modifiers changed from: private */
    public int arrowWidth;
    /* access modifiers changed from: private */
    public int backgroundColor;
    /* access modifiers changed from: private */
    public int borderColor;
    /* access modifiers changed from: private */
    public int borderSize;
    /* access modifiers changed from: private */
    public int borderid;
    private float containerOffsetXAxis;
    private int contentBottomMargin;
    /* access modifiers changed from: private */
    public int contentBottomPadding;
    private int contentLeftMargin;
    /* access modifiers changed from: private */
    public int contentLeftPadding;
    private LinearLayout contentLinearLayout;
    private int contentRightMargin;
    /* access modifiers changed from: private */
    public int contentRightPadding;
    /* access modifiers changed from: private */
    public CharSequence contentText;
    /* access modifiers changed from: private */
    public CharSequence contentTitle;
    private int contentTopMargin;
    /* access modifiers changed from: private */
    public int contentTopPadding;
    private Context context;
    private int gravity;
    private C1687HintCase hintCase;
    /* access modifiers changed from: private */
    public int imageResourceId;
    /* access modifiers changed from: private */
    public ImageView imageView;
    /* access modifiers changed from: private */
    public int marginBottom;
    /* access modifiers changed from: private */
    public int marginLeft;
    /* access modifiers changed from: private */
    public int marginRight;
    /* access modifiers changed from: private */
    public int marginTop;
    private ViewGroup parent;
    /* access modifiers changed from: private */
    public int shadowSize;
    /* access modifiers changed from: private */
    public Spanned spannedContentText;
    /* access modifiers changed from: private */
    public int strResId = -1;
    /* access modifiers changed from: private */
    public int textStyleId;
    /* access modifiers changed from: private */
    public int titleStyleId;
    /* access modifiers changed from: private */
    public boolean useBorder;
    private float xTranslationArrowOffset;
    private float xTranslationImage;
    private float yTranslationArrowOffset;
    private float yTranslationImage;

    public float getxTranslationArrowOffset() {
        return this.xTranslationArrowOffset;
    }

    public CustomHintContentHolder setxTranslationArrowOffset(float f) {
        this.xTranslationArrowOffset = f;
        return this;
    }

    public float getyTranslationArrowOffset() {
        return this.yTranslationArrowOffset;
    }

    public CustomHintContentHolder setyTranslationArrowOffset(float f) {
        this.yTranslationArrowOffset = f;
        return this;
    }

    public void setContainerOffsetXAxis(float f) {
        this.containerOffsetXAxis = f;
    }

    public View getView(Context context2, C1687HintCase hintCase2, ViewGroup viewGroup) {
        this.hintCase = hintCase2;
        this.parent = viewGroup;
        this.context = context2;
        calculateDataToPutTheArroW(hintCase2);
        setArrow(context2);
        FrameLayout.LayoutParams parentLayoutParams = getParentLayoutParams(-1, -1, this.gravity, this.marginLeft, this.marginTop, this.marginRight, this.marginBottom);
        RelativeLayout relativeLayout = new RelativeLayout(context2);
        relativeLayout.setLayoutParams(parentLayoutParams);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        Iterator<Integer> it = this.alignBlockRules.iterator();
        while (it.hasNext()) {
            layoutParams.addRule(it.next().intValue());
        }
        layoutParams.topMargin = this.contentTopMargin;
        layoutParams.bottomMargin = this.contentBottomMargin;
        layoutParams.rightMargin = this.contentRightMargin;
        layoutParams.leftMargin = this.contentLeftMargin;
        LinearLayout linearLayout = new LinearLayout(context2);
        this.contentLinearLayout = linearLayout;
        linearLayout.setBackgroundResource(this.borderid);
        LayerDrawable layerDrawable = (LayerDrawable) this.contentLinearLayout.getBackground().getCurrent();
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.getDrawable(layerDrawable.getNumberOfLayers() - 1);
        gradientDrawable.setColor(this.backgroundColor);
        if (this.useBorder) {
            gradientDrawable.setStroke(this.borderSize, this.borderColor);
        }
        this.contentLinearLayout.setLayoutParams(layoutParams);
        this.contentLinearLayout.setGravity(17);
        int i = this.borderSize + this.shadowSize;
        this.contentLinearLayout.setPadding(this.contentLeftPadding + i, this.contentTopPadding + i, this.contentRightPadding + i, i + this.contentBottomPadding);
        this.contentLinearLayout.setOrientation(1);
        this.contentLinearLayout.setTranslationX(this.containerOffsetXAxis);
        if (this.contentTitle != null) {
            this.contentLinearLayout.addView(getTextViewTitle(context2));
        }
        if (existImage()) {
            this.contentLinearLayout.addView(getImage(context2, this.imageView, this.imageResourceId));
        }
        if (this.strResId != -1) {
            this.contentLinearLayout.addView(getTextViewDescriptionRes(context2));
        } else {
            if (this.contentText != null) {
                this.contentLinearLayout.addView(getTextViewDescription(context2));
            }
            if (this.spannedContentText != null) {
                this.contentLinearLayout.addView(getTextViewDescription(context2));
            }
        }
        relativeLayout.addView(this.contentLinearLayout);
        relativeLayout.addView(this.arrow);
        return relativeLayout;
    }

    public void onLayout() {
        calculateArrowTranslation();
        this.arrow.setTranslationX(this.xTranslationImage);
        this.arrow.setTranslationY(this.yTranslationImage);
        if ((this.hintCase.getBlockInfoPosition() == 2 || this.hintCase.getBlockInfoPosition() == 0) && this.arrow.getBottom() >= this.contentLinearLayout.getBottom()) {
            this.contentLinearLayout.setTranslationY(((this.arrow.getY() + ((float) (this.arrow.getHeight() / 2))) - this.contentLinearLayout.getY()) - ((float) (this.contentLinearLayout.getHeight() / 2)));
        }
    }

    private void setArrow(Context context2) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.arrowWidth, this.arrowHeight);
        Iterator<Integer> it = this.alignArrowRules.iterator();
        while (it.hasNext()) {
            layoutParams.addRule(it.next().intValue());
        }
        TriangleShapeView triangleShapeView = new TriangleShapeView(context2);
        this.arrow = triangleShapeView;
        triangleShapeView.setBackgroundColor(this.backgroundColor);
        if (this.useBorder) {
            this.arrow.setBorder(this.borderSize, this.borderColor);
        }
        this.arrow.setDirection(this.arrowDirection);
        this.arrow.setShadowSize(this.shadowSize);
        this.arrow.setLayoutParams(layoutParams);
    }

    private void calculateArrowTranslation() {
        int blockInfoPosition = this.hintCase.getBlockInfoPosition();
        if (blockInfoPosition == 0) {
            this.xTranslationImage = 0.0f;
            this.yTranslationImage = (this.hintCase.getShape().getCenterY() - ((float) (this.parent.getHeight() / 2))) - ((float) DimenUtils.getStatusBarHeight(this.hintCase.getView().getContext()));
            this.xTranslationImage += getxTranslationArrowOffset();
            this.yTranslationImage += getyTranslationArrowOffset();
        } else if (blockInfoPosition == 1) {
            float centerX = this.hintCase.getShape().getCenterX() - ((float) (this.parent.getWidth() / 2));
            this.xTranslationImage = centerX;
            this.yTranslationImage = 0.0f;
            this.xTranslationImage = centerX + getxTranslationArrowOffset();
            this.yTranslationImage += getyTranslationArrowOffset();
        } else if (blockInfoPosition == 2) {
            this.xTranslationImage = 0.0f;
            this.yTranslationImage = (this.hintCase.getShape().getCenterY() - ((float) (this.parent.getHeight() / 2))) - ((float) DimenUtils.getStatusBarHeight(this.hintCase.getView().getContext()));
            this.xTranslationImage += getxTranslationArrowOffset();
            this.yTranslationImage += getyTranslationArrowOffset();
        } else if (blockInfoPosition != 3) {
            this.xTranslationImage = 0.0f;
            this.yTranslationImage = 0.0f;
            this.xTranslationImage = 0.0f + getxTranslationArrowOffset();
            this.yTranslationImage += getyTranslationArrowOffset();
        } else {
            float centerX2 = this.hintCase.getShape().getCenterX() - ((float) (this.parent.getWidth() / 2));
            this.xTranslationImage = centerX2;
            this.yTranslationImage = 0.0f;
            this.xTranslationImage = centerX2 + getxTranslationArrowOffset();
            this.yTranslationImage += getyTranslationArrowOffset();
        }
    }

    private void calculateDataToPutTheArroW(C1687HintCase hintCase2) {
        int blockInfoPosition = hintCase2.getBlockInfoPosition();
        this.alignArrowRules = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.alignBlockRules = arrayList;
        if (blockInfoPosition == 0) {
            arrayList.add(11);
            this.alignArrowRules.add(11);
            this.alignArrowRules.add(15);
            this.gravity = 5;
            this.contentRightMargin = this.arrowHeight;
            this.contentLeftMargin = 0;
            this.contentTopMargin = 0;
            this.contentBottomMargin = 0;
            this.arrowDirection = TriangleShapeView.Direction.RIGHT;
            this.marginRight = 0;
        } else if (blockInfoPosition == 1) {
            arrayList.add(12);
            this.alignArrowRules.add(14);
            this.alignArrowRules.add(12);
            this.gravity = 80;
            this.contentRightMargin = 0;
            this.contentLeftMargin = 0;
            this.contentTopMargin = 0;
            this.contentBottomMargin = (this.arrowHeight - this.borderSize) - this.shadowSize;
            this.arrowDirection = TriangleShapeView.Direction.DOWN;
            this.marginBottom = 0;
        } else if (blockInfoPosition == 2) {
            arrayList.add(9);
            this.alignArrowRules.add(15);
            this.alignArrowRules.add(9);
            this.gravity = 3;
            this.contentRightMargin = 0;
            this.contentLeftMargin = this.arrowHeight;
            this.contentTopMargin = 0;
            this.contentBottomMargin = 0;
            this.arrowDirection = TriangleShapeView.Direction.LEFT;
            this.marginLeft = 0;
        } else if (blockInfoPosition != 3) {
            arrayList.add(14);
            this.alignArrowRules.add(13);
            this.gravity = 17;
            this.contentRightMargin = 0;
            this.contentLeftMargin = 0;
            this.contentTopMargin = 0;
            this.contentBottomMargin = 0;
            this.xTranslationImage = 0.0f;
            this.yTranslationImage = 0.0f;
        } else {
            arrayList.add(14);
            this.alignArrowRules.add(14);
            this.alignArrowRules.add(10);
            this.gravity = 48;
            this.contentRightMargin = 0;
            this.contentLeftMargin = 0;
            this.contentTopMargin = (this.arrowHeight - this.borderSize) - this.shadowSize;
            this.contentBottomMargin = 0;
            this.arrowDirection = TriangleShapeView.Direction.UP;
            this.marginTop = 0;
        }
    }

    private ImageView getImage(Context context2, ImageView imageView2, int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        layoutParams.setMargins(0, 20, 0, 50);
        if (imageView2 == null) {
            imageView2 = new ImageView(context2);
        }
        if (i != -1) {
            imageView2.setImageResource(i);
        }
        imageView2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView2.setAdjustViewBounds(true);
        imageView2.setLayoutParams(layoutParams);
        return imageView2;
    }

    private View getTextViewTitle(Context context2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, 20);
        TextView textView = new TextView(context2);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(1);
        SpannableString spannableString = new SpannableString(this.contentTitle);
        spannableString.setSpan(new TextAppearanceSpan(context2, this.titleStyleId), 0, spannableString.length(), 0);
        textView.setText(spannableString);
        return textView;
    }

    private View getTextViewDescription(Context context2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(context2);
        textView.setLayoutParams(layoutParams);
        if (this.contentText != null) {
            textView.setGravity(1);
            SpannableString spannableString = new SpannableString(this.contentText);
            spannableString.setSpan(new TextAppearanceSpan(context2, this.textStyleId), 0, spannableString.length(), 0);
            textView.setText(spannableString);
        } else {
            Spanned spanned = this.spannedContentText;
            if (spanned != null) {
                textView.setText(spanned);
            }
        }
        return textView;
    }

    private View getTextViewDescriptionRes(Context context2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(context2);
        textView.setLayoutParams(layoutParams);
        textView.setText(this.strResId);
        return textView;
    }

    private boolean existImage() {
        return (this.imageView == null && this.imageResourceId == -1) ? false : true;
    }

    public static class Builder {
        private CustomHintContentHolder blockInfo;
        private Context context;

        public Builder setxTranslationArrowOffset(float f) {
            this.blockInfo.setxTranslationArrowOffset(f);
            return this;
        }

        public Builder setyTranslationArrowOffset(float f) {
            this.blockInfo.setyTranslationArrowOffset(f);
            return this;
        }

        public Builder setContainerOffsetXAxis(float f) {
            this.blockInfo.setContainerOffsetXAxis(f);
            return this;
        }

        public Builder setBorder(int i) {
            this.blockInfo.borderid = i;
            return this;
        }

        public Builder(Context context2) {
            this.context = context2;
            CustomHintContentHolder customHintContentHolder = new CustomHintContentHolder();
            this.blockInfo = customHintContentHolder;
            customHintContentHolder.imageResourceId = -1;
            this.blockInfo.arrowWidth = 50;
            this.blockInfo.arrowHeight = 50;
            this.blockInfo.useBorder = false;
            this.blockInfo.shadowSize = context2.getResources().getDimensionPixelSize(R.dimen.shadow);
        }

        public Builder setBorder(int i, int i2) {
            this.blockInfo.useBorder = true;
            this.blockInfo.borderSize = this.context.getResources().getDimensionPixelSize(i);
            this.blockInfo.borderColor = this.context.getResources().getColor(i2);
            return this;
        }

        public Builder setBackgroundColorFromResource(int i) {
            this.blockInfo.backgroundColor = this.context.getResources().getColor(i);
            return this;
        }

        public Builder setBackgroundColor(int i) {
            this.blockInfo.backgroundColor = i;
            return this;
        }

        public Builder setImageDrawableId(int i) {
            this.blockInfo.imageResourceId = i;
            return this;
        }

        public Builder setImageView(ImageView imageView) {
            this.blockInfo.imageView = imageView;
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.blockInfo.contentTitle = charSequence;
            return this;
        }

        public Builder setContentTitle(int i) {
            this.blockInfo.contentTitle = this.context.getString(i);
            return this;
        }

        public Builder setTitleStyle(int i) {
            this.blockInfo.titleStyleId = i;
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.blockInfo.contentText = charSequence;
            return this;
        }

        public Builder setContentTextSpanned(Spanned spanned) {
            this.blockInfo.spannedContentText = spanned;
            return this;
        }

        public Builder setContentText(int i, boolean z) {
            this.blockInfo.strResId = i;
            return this;
        }

        public Builder setContentText(int i) {
            this.blockInfo.contentText = this.context.getString(i);
            return this;
        }

        public Builder setContentStyle(int i) {
            this.blockInfo.textStyleId = i;
            return this;
        }

        public Builder setMargin(int i, int i2, int i3, int i4) {
            this.blockInfo.marginLeft = i;
            this.blockInfo.marginTop = i2;
            this.blockInfo.marginRight = i3;
            this.blockInfo.marginBottom = i4;
            return this;
        }

        public Builder setMargingByResourcesId(int i, int i2, int i3, int i4) {
            this.blockInfo.marginLeft = this.context.getResources().getDimensionPixelSize(i);
            this.blockInfo.marginTop = this.context.getResources().getDimensionPixelSize(i2);
            this.blockInfo.marginRight = this.context.getResources().getDimensionPixelSize(i3);
            this.blockInfo.marginBottom = this.context.getResources().getDimensionPixelSize(i4);
            return this;
        }

        public Builder setContentPadding(int i, int i2, int i3, int i4) {
            this.blockInfo.contentLeftPadding = i;
            this.blockInfo.contentTopPadding = i2;
            this.blockInfo.contentRightPadding = i3;
            this.blockInfo.contentBottomPadding = i4;
            return this;
        }

        public Builder setContentPaddingByResourcesId(int i, int i2, int i3, int i4) {
            this.blockInfo.contentLeftPadding = this.context.getResources().getDimensionPixelSize(i);
            this.blockInfo.contentTopPadding = this.context.getResources().getDimensionPixelSize(i2);
            this.blockInfo.contentRightPadding = this.context.getResources().getDimensionPixelSize(i3);
            this.blockInfo.contentBottomPadding = this.context.getResources().getDimensionPixelSize(i4);
            return this;
        }

        public Builder setArrowSize(int i, int i2) {
            this.blockInfo.arrowWidth = this.context.getResources().getDimensionPixelSize(i);
            this.blockInfo.arrowHeight = this.context.getResources().getDimensionPixelSize(i2);
            return this;
        }

        public CustomHintContentHolder build() {
            return this.blockInfo;
        }
    }
}
