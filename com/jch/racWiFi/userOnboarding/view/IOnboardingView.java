package com.jch.racWiFi.userOnboarding.view;

import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;

public interface IOnboardingView {
    void onAlreadyOnboarded();

    void onBoardingErrorCode(int i);

    void onNetworkError();

    void onOnboardingSuccessful(IduDetailsModel iduDetailsModel);

    void onPerformingOnboarding();

    void onboardingFailed();
}
