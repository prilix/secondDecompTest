package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view;

import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models.OnboardingInfoResponseBody;
import retrofit2.Response;

public interface OnBoardedIduInfoView {
    void getOnBoardedIduInfo(Response<OnboardingInfoResponseBody> response);
}
