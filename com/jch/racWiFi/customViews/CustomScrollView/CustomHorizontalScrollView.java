package com.jch.racWiFi.customViews.CustomScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class CustomHorizontalScrollView extends HorizontalScrollView {
    CustomHorizontalScrollView horizontalScrollView;

    public void setHorizontalScrollView(CustomHorizontalScrollView customHorizontalScrollView) {
        this.horizontalScrollView = customHorizontalScrollView;
    }

    public CustomHorizontalScrollView(Context context) {
        super(context);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        this.horizontalScrollView.scrollTo(i, 0);
    }
}
