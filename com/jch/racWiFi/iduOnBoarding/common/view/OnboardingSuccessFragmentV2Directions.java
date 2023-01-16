package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;

public class OnboardingSuccessFragmentV2Directions {
    private OnboardingSuccessFragmentV2Directions() {
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
