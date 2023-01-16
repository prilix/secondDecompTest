package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountTransferOwnerShipFragmentDirections {
    private MyAccountTransferOwnerShipFragmentDirections() {
    }

    /* renamed from: actionMyAccountChangeOwnerShipFragmentToMyAccountManageHomesFragment */
    public static NavDirections m557x6ff1f47d() {
        return new ActionOnlyNavDirections(R.id.action_myAccountChangeOwnerShipFragment_to_myAccountManageHomesFragment);
    }

    public static NavDirections actionMyAccountChangeOwnerShipFragmentToHomepage() {
        return new ActionOnlyNavDirections(R.id.action_myAccountChangeOwnerShipFragment_to_homepage);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
