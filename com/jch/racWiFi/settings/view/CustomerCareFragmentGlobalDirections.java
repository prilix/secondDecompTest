package com.jch.racWiFi.settings.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class CustomerCareFragmentGlobalDirections {
    private CustomerCareFragmentGlobalDirections() {
    }

    public static NavDirections actionCustomerCareFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_customerCareFragment_to_homePageFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
