package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;

public class CustomSwitchCompat extends SwitchCompat {
    private boolean isCheckSilent;

    private void init() {
    }

    public boolean isCheckSilent() {
        return this.isCheckSilent;
    }

    public CustomSwitchCompat(Context context) {
        super(context);
        init();
    }

    public CustomSwitchCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CustomSwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        super.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private void setChecked(boolean z, boolean z2) {
        this.isCheckSilent = !z2;
        super.setChecked(z);
        this.isCheckSilent = false;
    }

    public void setCheckedSilent(boolean z) {
        setChecked(z, false);
    }
}
