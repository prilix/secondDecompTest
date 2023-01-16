package com.jch.racWiFi.iduManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningDoneFragmentDirections {
    private CleaningDoneFragmentDirections() {
    }

    public static NavDirections actionCleaningDoneFragmentToIndividualIDUControlFragment() {
        return new ActionOnlyNavDirections(R.id.action_cleaningDoneFragment_to_individualIDUControlFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
