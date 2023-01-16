package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountManageHomesFragmentDirections {
    private MyAccountManageHomesFragmentDirections() {
    }

    public static NavDirections actionManageHomesFragmentToMyAccountTransferOwnerShipFragment() {
        return new ActionOnlyNavDirections(R.id.action_manageHomesFragment_to_myAccountTransferOwnerShipFragment);
    }

    public static NavDirections actionManageHomesFragmentToMyAccountFragment() {
        return new ActionOnlyNavDirections(R.id.action_manageHomesFragment_to_myAccountFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
