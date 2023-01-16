package com.jch.racWiFi;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch_hitachi.aircloudglobal.R;

public class OnBoardNavGraphDirections {
    private OnBoardNavGraphDirections() {
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return new ActionOnlyNavDirections(R.id.action_global_deviceDetailsFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_global_homePageFragment);
    }
}
