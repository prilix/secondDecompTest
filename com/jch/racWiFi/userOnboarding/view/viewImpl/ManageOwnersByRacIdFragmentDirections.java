package com.jch.racWiFi.userOnboarding.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ManageOwnersByRacIdFragmentDirections {
    private ManageOwnersByRacIdFragmentDirections() {
    }

    public static NavDirections actionManageOwnersByRacIdFragmentToDeviceDetailsFragment() {
        return new ActionOnlyNavDirections(R.id.action_ManageOwnersByRacIdFragment_to_deviceDetailsFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
