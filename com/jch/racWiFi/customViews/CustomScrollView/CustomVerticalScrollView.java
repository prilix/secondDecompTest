package com.jch.racWiFi.customViews.CustomScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class CustomVerticalScrollView extends ScrollView {
    CustomVerticalScrollView verticalscrollView;

    public void setVerticalscrollView(CustomVerticalScrollView customVerticalScrollView) {
        this.verticalscrollView = customVerticalScrollView;
    }

    public CustomVerticalScrollView(Context context) {
        super(context);
    }

    public CustomVerticalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomVerticalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CustomVerticalScrollView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        this.verticalscrollView.scrollTo(0, i2);
    }
}
