package com.jch.racWiFi.userOnboarding.view;

public interface IConfiguringView extends IOnboardingView {
    void onConfigurationFailed();

    void onConfigurationSuccess();

    void onWifiParingSuccessful(String str, String str2, String str3, String str4);
}
