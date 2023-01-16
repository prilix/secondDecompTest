package com.jch.racWiFi;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch_hitachi.aircloudglobal.R;

public class MainGraphDirections {
    private MainGraphDirections() {
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_global_homePageFragment);
    }
}
