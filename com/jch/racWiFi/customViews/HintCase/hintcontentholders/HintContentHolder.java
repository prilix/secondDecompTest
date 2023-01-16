package com.jch.racWiFi.customViews.HintCase.hintcontentholders;

import android.widget.FrameLayout;
import com.jch.racWiFi.customViews.HintCase.ContentHolder;

public abstract class HintContentHolder extends ContentHolder {
    public FrameLayout.LayoutParams getParentLayoutParams(int i, int i2, int i3) {
        return new FrameLayout.LayoutParams(i, i2, i3);
    }

    public FrameLayout.LayoutParams getParentLayoutParams(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2, i3);
        layoutParams.setMargins(i4, i5, i6, i7);
        return layoutParams;
    }
}
