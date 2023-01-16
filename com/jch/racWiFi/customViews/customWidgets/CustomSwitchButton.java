package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.util.AttributeSet;
import com.suke.widget.SwitchButton;

public class CustomSwitchButton extends SwitchButton {
    private SwitchButton.OnCheckedChangeListener onCheckedChangeListener;

    public CustomSwitchButton(Context context) {
        super(context);
    }

    public CustomSwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomSwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CustomSwitchButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setCheckedSilent(boolean z) {
        super.setChecked(z);
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
    }
}
