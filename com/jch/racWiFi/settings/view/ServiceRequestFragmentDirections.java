package com.jch.racWiFi.settings.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ServiceRequestFragmentDirections {
    private ServiceRequestFragmentDirections() {
    }

    public static NavDirections actionServiceRequestFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_serviceRequestFragment_to_homePageFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
