package com.jch.racWiFi.customViews.HintCase.hintcontentholders;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jch.racWiFi.customViews.HintCase.C1687HintCase;

public class SimpleHintContentHolder extends HintContentHolder {
    public static final int NO_IMAGE = -1;
    /* access modifiers changed from: private */
    public CharSequence contentText;
    /* access modifiers changed from: private */
    public CharSequence contentTitle;
    /* access modifiers changed from: private */
    public int gravity;
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
    /* access modifiers changed from: private */
    public int textStyleId;
    /* access modifiers changed from: private */
    public int titleStyleId;

    public View getView(Context context, C1687HintCase hintCase, ViewGroup viewGroup) {
        FrameLayout.LayoutParams parentLayoutParams = getParentLayoutParams(-2, -2, this.gravity, this.marginLeft, this.marginTop, this.marginRight, this.marginBottom);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(parentLayoutParams);
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        if (this.contentTitle != null) {
            linearLayout.addView(getTextViewTitle(context));
        }
        if (existImage()) {
            linearLayout.addView(getImage(context, this.imageView, this.imageResourceId));
        }
        if (this.contentText != null) {
            linearLayout.addView(getTextViewDescription(context));
        }
        return linearLayout;
    }

    private ImageView getImage(Context context, ImageView imageView2, int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        layoutParams.setMargins(0, 20, 0, 50);
        if (imageView2 == null) {
            imageView2 = new ImageView(context);
        }
        if (i != -1) {
            imageView2.setImageResource(i);
        }
        imageView2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView2.setAdjustViewBounds(true);
        imageView2.setLayoutParams(layoutParams);
        return imageView2;
    }

    private View getTextViewTitle(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 20, 0, 20);
        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(1);
        SpannableString spannableString = new SpannableString(this.contentTitle);
        spannableString.setSpan(new TextAppearanceSpan(context, this.titleStyleId), 0, spannableString.length(), 0);
        textView.setText(spannableString);
        return textView;
    }

    private View getTextViewDescription(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(1);
        SpannableString spannableString = new SpannableString(this.contentText);
        spannableString.setSpan(new TextAppearanceSpan(context, this.textStyleId), 0, spannableString.length(), 0);
        textView.setText(spannableString);
        return textView;
    }

    private boolean existImage() {
        return (this.imageView == null && this.imageResourceId == -1) ? false : true;
    }

    public static class Builder {
        private SimpleHintContentHolder blockInfo;
        private Context context;

        public Builder(Context context2) {
            this.context = context2;
            SimpleHintContentHolder simpleHintContentHolder = new SimpleHintContentHolder();
            this.blockInfo = simpleHintContentHolder;
            simpleHintContentHolder.imageResourceId = -1;
            this.blockInfo.gravity = 17;
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

        public Builder setContentText(int i) {
            this.blockInfo.contentText = this.context.getString(i);
            return this;
        }

        public Builder setContentStyle(int i) {
            this.blockInfo.textStyleId = i;
            return this;
        }

        public Builder setGravity(int i) {
            this.blockInfo.gravity = i;
            return this;
        }

        public Builder setMargin(int i, int i2, int i3, int i4) {
            this.blockInfo.marginLeft = i;
            this.blockInfo.marginTop = i2;
            this.blockInfo.marginRight = i3;
            this.blockInfo.marginBottom = i4;
            return this;
        }

        public Builder setMarginByResourcesId(int i, int i2, int i3, int i4) {
            this.blockInfo.marginLeft = this.context.getResources().getDimensionPixelSize(i);
            this.blockInfo.marginTop = this.context.getResources().getDimensionPixelSize(i2);
            this.blockInfo.marginRight = this.context.getResources().getDimensionPixelSize(i3);
            this.blockInfo.marginBottom = this.context.getResources().getDimensionPixelSize(i4);
            return this;
        }

        public SimpleHintContentHolder build() {
            return this.blockInfo;
        }
    }
}
