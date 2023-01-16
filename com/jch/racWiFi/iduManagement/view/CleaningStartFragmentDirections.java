package com.jch.racWiFi.iduManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningStartFragmentDirections {
    private CleaningStartFragmentDirections() {
    }

    public static NavDirections actionCleaningStartFragmentToCleaningModeInProgressFragment() {
        return new ActionOnlyNavDirections(R.id.action_cleaningStartFragment_to_cleaningModeInProgressFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
