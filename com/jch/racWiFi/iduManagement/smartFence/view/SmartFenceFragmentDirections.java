package com.jch.racWiFi.iduManagement.smartFence.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.SmartFenceNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SmartFenceFragmentDirections {
    private SmartFenceFragmentDirections() {
    }

    public static NavDirections actionSmartFenceFragmentToSmartFenceSelectAcFragmentt() {
        return new ActionOnlyNavDirections(R.id.action_smartFenceFragment_to_SmartFenceSelectAcFragmentt);
    }

    public static NavDirections actionSmartFenceFragmentToSmartFenceSelectAcFragment() {
        return new ActionOnlyNavDirections(R.id.action_smartFenceFragment_to_SmartFenceSelectAcFragment);
    }

    public static NavDirections actionSmartFenceFragmentToSmartFenceGeoFenceSettings() {
        return new ActionOnlyNavDirections(R.id.action_smartFenceFragment_to_SmartFenceGeoFenceSettings);
    }

    public static NavDirections actionSmartFenceFragmentToOnBoarding() {
        return new ActionOnlyNavDirections(R.id.action_SmartFenceFragment_to_OnBoarding);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return SmartFenceNavGraphDirections.actionGlobalHomePageFragment();
    }
}
