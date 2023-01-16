package com.jch.racWiFi.iduManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class HolidayModeFragmentDirections {
    private HolidayModeFragmentDirections() {
    }

    public static NavDirections actionHolidayModeFragmentToSetTemperatureHolidayModeFragment() {
        return new ActionOnlyNavDirections(R.id.action_holidayModeFragment_To_setTemperatureHolidayModeFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
