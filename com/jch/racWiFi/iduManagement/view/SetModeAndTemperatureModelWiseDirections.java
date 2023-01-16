package com.jch.racWiFi.iduManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SetModeAndTemperatureModelWiseDirections {
    private SetModeAndTemperatureModelWiseDirections() {
    }

    public static NavDirections actionSetModeAndTempratureFragmentV2ToSetTimerFragmentV2() {
        return new ActionOnlyNavDirections(R.id.action_setModeAndTempratureFragmentV2_to_setTimerFragmentV2);
    }

    public static NavDirections actionSetModeAndTempratureFragmentV2ToSetTimerFragmentV22() {
        return new ActionOnlyNavDirections(R.id.action_setModeAndTempratureFragmentV2_to_setTimerFragmentV22);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
