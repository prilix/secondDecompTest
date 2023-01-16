package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SwitchOnAirConditonerDirections {
    private SwitchOnAirConditonerDirections() {
    }

    public static NavDirections actionSwitchOnAirConditionerFragToAddRac() {
        return new ActionOnlyNavDirections(R.id.action_switchOnAirConditionerFrag_to_addRac);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
