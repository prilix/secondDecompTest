package com.jch.racWiFi.iduManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SetTimerFragmentV2Directions {
    private SetTimerFragmentV2Directions() {
    }

    public static NavDirections actionSetTimerFragmentV2ToIndividualIDUControlFragment() {
        return new ActionOnlyNavDirections(R.id.action_setTimerFragmentV2_to_individualIDUControlFragment);
    }

    public static NavDirections actionSetTimerFragmentV2ToSetModeAndTempratureFragmentV2() {
        return new ActionOnlyNavDirections(R.id.action_setTimerFragmentV2_to_setModeAndTempratureFragmentV2);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
