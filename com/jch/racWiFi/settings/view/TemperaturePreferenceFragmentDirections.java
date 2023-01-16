package com.jch.racWiFi.settings.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class TemperaturePreferenceFragmentDirections {
    private TemperaturePreferenceFragmentDirections() {
    }

    public static NavDirections actionTemperaturePreferenceFragmentToSettingsFragment() {
        return new ActionOnlyNavDirections(R.id.action_temperaturePreferenceFragment_to_settingsFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
