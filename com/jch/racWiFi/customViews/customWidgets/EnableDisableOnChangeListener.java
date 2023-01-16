package com.jch.racWiFi.customViews.customWidgets;

import com.suke.widget.SwitchButton;

public interface EnableDisableOnChangeListener extends SwitchButton.OnCheckedChangeListener {
    void suspendListener();
}
