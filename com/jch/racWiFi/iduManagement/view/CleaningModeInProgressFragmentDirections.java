package com.jch.racWiFi.iduManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningModeInProgressFragmentDirections {
    private CleaningModeInProgressFragmentDirections() {
    }

    /* renamed from: actionCleaningModeInProgressFragmentToIndividualIDUControlFragment */
    public static NavDirections m229x542aba7b() {
        return new ActionOnlyNavDirections(R.id.action_cleaningModeInProgressFragment_to_individualIDUControlFragment);
    }

    public static NavDirections actionCleaningModeInProgressFragmentToCleaningDoneFragment() {
        return new ActionOnlyNavDirections(R.id.action_cleaningModeInProgressFragment_to_cleaningDoneFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
