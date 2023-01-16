package com.jch.racWiFi.fcm.model;

import androidx.databinding.ViewDataBinding;
import com.jch.racWiFi.fcm.util.Type;

public class Banner {
    private boolean freezeUi;
    private Type type;
    private ViewDataBinding viewDataBinding;

    public Type getType() {
        return this.type;
    }

    public void setType(Type type2) {
        this.type = type2;
    }

    public ViewDataBinding getViewDataBinding() {
        return this.viewDataBinding;
    }

    public void setViewDataBinding(ViewDataBinding viewDataBinding2) {
        this.viewDataBinding = viewDataBinding2;
    }

    public boolean isFreezeUi() {
        return this.freezeUi;
    }

    public void setFreezeUi(boolean z) {
        this.freezeUi = z;
    }
}
