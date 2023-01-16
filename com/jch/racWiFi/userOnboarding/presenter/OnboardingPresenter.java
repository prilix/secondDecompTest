package com.jch.racWiFi.userOnboarding.presenter;

import androidx.lifecycle.LiveData;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import retrofit2.Response;

public interface OnboardingPresenter {
    LiveData<Response<IduDetailsModel>> requestOnboarding(String str, OnBoardingModel onBoardingModel);

    void setStatusCode(Response<IduDetailsModel> response);
}
