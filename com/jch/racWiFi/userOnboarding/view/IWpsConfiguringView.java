package com.jch.racWiFi.userOnboarding.view;

public interface IWpsConfiguringView extends IOnboardingView {
    void onConfigurationFailed();

    void onConfigurationSuccess();
}
