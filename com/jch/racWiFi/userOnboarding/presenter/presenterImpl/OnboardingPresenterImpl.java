package com.jch.racWiFi.userOnboarding.presenter.presenterImpl;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.network.OnboardingNetworkHelper;
import com.jch.racWiFi.userOnboarding.presenter.OnboardingPresenter;
import com.jch.racWiFi.userOnboarding.view.IOnboardingView;
import retrofit2.Response;

public class OnboardingPresenterImpl implements OnboardingPresenter {
    IOnboardingView iOnboardingView;

    public OnboardingPresenterImpl(IOnboardingView iOnboardingView2) {
        this.iOnboardingView = iOnboardingView2;
    }

    public SingleLiveEvent<Response<IduDetailsModel>> requestOnboarding(String str, OnBoardingModel onBoardingModel) {
        return OnboardingNetworkHelper.getInstance().requestOnBoardingToServer(onBoardingModel);
    }

    public void setStatusCode(Response<IduDetailsModel> response) {
        if (response == null) {
            this.iOnboardingView.onNetworkError();
        } else if (response.isSuccessful()) {
            this.iOnboardingView.onOnboardingSuccessful(response.body());
        } else {
            this.iOnboardingView.onBoardingErrorCode(response.code());
        }
    }
}
