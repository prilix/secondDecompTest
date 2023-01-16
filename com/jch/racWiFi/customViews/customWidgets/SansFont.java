package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class SansFont extends TextView {
    public SansFont(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public SansFont(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SansFont(Context context) {
        super(context);
        init();
    }

    public void init() {
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/SansPro-Regular.ttf"), 0);
    }
}
