package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ChangePasswordFragmentDirections {
    private ChangePasswordFragmentDirections() {
    }

    public static NavDirections actionChangePasswordFragmentToMyAccountFragment2() {
        return new ActionOnlyNavDirections(R.id.action_changePasswordFragment_to_myAccountFragment2);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
