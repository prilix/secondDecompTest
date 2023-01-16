package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ConfirmWirelessNetworkIndianRacDirections {
    private ConfirmWirelessNetworkIndianRacDirections() {
    }

    public static NavDirections toSwitchOnAirConditionerFrag() {
        return new ActionOnlyNavDirections(R.id.to_switchOnAirConditionerFrag);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
