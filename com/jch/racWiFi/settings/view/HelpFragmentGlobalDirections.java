package com.jch.racWiFi.settings.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class HelpFragmentGlobalDirections {
    private HelpFragmentGlobalDirections() {
    }

    public static NavDirections actionHelpFragmentToIndividualIDUControlFragment() {
        return new ActionOnlyNavDirections(R.id.action_helpFragment_to_individualIDUControlFragment);
    }

    public static NavDirections actionHelpFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_helpFragment_to_homePageFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
