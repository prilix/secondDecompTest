package com.jch.racWiFi.iduOnBoarding.directOnboarding;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class DirectOnboardFragmentDirections {
    private DirectOnboardFragmentDirections() {
    }

    public static NavDirections actionDirectOnboardingFragmentToQrScanFragment() {
        return new ActionOnlyNavDirections(R.id.action_directOnboardingFragment_to_qrScanFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
