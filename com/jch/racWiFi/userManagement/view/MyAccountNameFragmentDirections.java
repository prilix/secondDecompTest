package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountNameFragmentDirections {
    private MyAccountNameFragmentDirections() {
    }

    public static NavDirections actionMyAccountNameFragmentToMyAccountFragment2() {
        return new ActionOnlyNavDirections(R.id.action_myAccountNameFragment_to_myAccountFragment2);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
