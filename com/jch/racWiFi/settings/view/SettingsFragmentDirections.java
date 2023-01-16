package com.jch.racWiFi.settings.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SettingsFragmentDirections {
    private SettingsFragmentDirections() {
    }

    public static NavDirections actionSettingsFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_homePageFragment);
    }

    public static NavDirections actionSettingsFragmentToTemperaturePreferenceFragment() {
        return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_temperaturePreferenceFragment);
    }

    public static NavDirections actionSettingsFragmentToUserPreferencesFragment() {
        return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_userPreferencesFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
