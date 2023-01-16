package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.presenter;

import androidx.lifecycle.LifecycleOwner;
import com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.OnBoardedIduInfoView;
import com.jch.racWiFi.userOnboarding.network.OnboardingNetworkHelper;
import retrofit2.Response;

public class OnBoardedIduPresenter {
    private OnBoardedIduInfoView onBoardedIduInfoView;

    public OnBoardedIduPresenter(OnBoardedIduInfoView onBoardedIduInfoView2) {
        this.onBoardedIduInfoView = onBoardedIduInfoView2;
    }

    public void getOnBoardedIduInfo(LifecycleOwner lifecycleOwner, String str) {
        OnboardingNetworkHelper.getInstance().requestOnboardedIduInfoToServer(str).observe(lifecycleOwner, new OnBoardedIduPresenter$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$getOnBoardedIduInfo$0$com-jch-racWiFi-iduOnBoarding-IndiaModelOnboardingFlow-presenter-OnBoardedIduPresenter */
    public /* synthetic */ void mo31325x5ad490e0(Response response) {
        this.onBoardedIduInfoView.getOnBoardedIduInfo(response);
    }
}
