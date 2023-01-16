package com.jch.racWiFi.iduManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class WeeklyTimerDevicesFragmentV2Directions {
    private WeeklyTimerDevicesFragmentV2Directions() {
    }

    public static NavDirections actionWeeklyTimerDevicesFragmentToWeeklyTimerFragmentV3() {
        return new ActionOnlyNavDirections(R.id.action_weeklyTimerDevicesFragment_to_weeklyTimerFragmentV3);
    }

    /* renamed from: actionWeeklyTimerDevicesFragmentV2ToWeeklyTimerCopyTimerScheduleFragment */
    public static NavDirections m259x37ab1d29() {
        return new ActionOnlyNavDirections(R.id.action_weeklyTimerDevicesFragmentV2_to_weeklyTimerCopyTimerScheduleFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
