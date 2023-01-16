package com.jch.racWiFi.iduOnBoarding.ViewModel;

import androidx.lifecycle.C0534ViewModel;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userOnboarding.network.OnboardingNetworkHelper;
import com.jch_hitachi.aircloudglobal.R;

public class OnBoardingViewModel extends C0534ViewModel {
    private String addedAcRouterSsid;
    private boolean isIduOnline;
    private OnboardingNetworkHelper onboardingNetworkHelper = OnboardingNetworkHelper.getInstance();

    public SingleLiveEvent<GenericResponse> resetIdu(String str, int i) {
        return this.onboardingNetworkHelper.resetIdu(i, str);
    }

    public String getAddedAcRouterSsid() {
        return this.addedAcRouterSsid;
    }

    public void setAddedAcRouterSsid(String str) {
        this.addedAcRouterSsid = str;
    }

    public boolean isIduOnline() {
        return this.isIduOnline;
    }

    public void setIduOnline(boolean z) {
        this.isIduOnline = z;
    }

    public int getErrorMessageStringId(String str) {
        if (str == null || str.isEmpty()) {
            return R.string.errorCode_alert_somethingWentWorng;
        }
        str.hashCode();
        if (!str.equals("PCF009")) {
            return R.string.errorCode_alert_somethingWentWorng;
        }
        return R.string.errorCode_alert_PCF009;
    }
}
