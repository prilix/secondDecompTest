package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.presenter;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.p010di.util.TokenUtil;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.model.OnBoardingModel;
import com.jch.racWiFi.userOnboarding.presenter.presenterImpl.OnboardingPresenterImpl;
import com.jch.racWiFi.userOnboarding.view.IOnboardingView;
import retrofit2.Response;

public class SoftApPresenterImpl extends OnboardingPresenterImpl implements Observer<Response<IduDetailsModel>> {
    private Fragment fragment;
    /* access modifiers changed from: private */
    public LifecycleOwner lifecycleOwner;

    public SoftApPresenterImpl(LifecycleOwner lifecycleOwner2, IOnboardingView iOnboardingView) {
        super(iOnboardingView);
        this.fragment = (Fragment) iOnboardingView;
        this.lifecycleOwner = lifecycleOwner2;
    }

    public void onBoardingApi(OnBoardingModel onBoardingModel) {
        final SingleLiveEvent requestOnboarding = requestOnboarding(TokenUtil.getInstance().obtain().getBearerToken(), onBoardingModel);
        Fragment fragment2 = this.fragment;
        if (fragment2 != null && fragment2.getActivity() != null) {
            this.fragment.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    requestOnboarding.observe(SoftApPresenterImpl.this.lifecycleOwner, SoftApPresenterImpl.this);
                }
            });
        }
    }

    public void onChanged(Response<IduDetailsModel> response) {
        setStatusCode(response);
    }
}
