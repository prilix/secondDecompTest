package com.jch.racWiFi.iduOnBoarding.common;

import androidx.lifecycle.LifecycleOwner;

public class IduOnBoardingPresenter {
    private LifecycleOwner mLifecycleOwner;

    public void requestOnboardedIduInfo() {
    }

    public IduOnBoardingPresenter(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
    }
}
