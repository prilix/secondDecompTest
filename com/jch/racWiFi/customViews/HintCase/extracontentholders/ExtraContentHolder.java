package com.jch.racWiFi.customViews.HintCase.extracontentholders;

import android.widget.RelativeLayout;
import com.jch.racWiFi.customViews.HintCase.ContentHolder;

public abstract class ExtraContentHolder extends ContentHolder {
    public RelativeLayout.LayoutParams getParentLayoutParams(int i, int i2, int... iArr) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i2);
        for (int addRule : iArr) {
            layoutParams.addRule(addRule);
        }
        return layoutParams;
    }
}
