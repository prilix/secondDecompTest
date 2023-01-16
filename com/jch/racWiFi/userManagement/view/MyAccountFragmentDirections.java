package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountFragmentDirections {
    private MyAccountFragmentDirections() {
    }

    public static NavDirections actionMyAccountFragment2ToMyAccountProfilePictureFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_myAccountProfilePictureFragment);
    }

    public static NavDirections actionMyAccountFragment2ToMyAccountNameFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_myAccountNameFragment);
    }

    public static NavDirections actionMyAccountFragment2ToMyAccountAddressFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_myAccountAddressFragment);
    }

    public static NavDirections actionMyAccountFragment2ToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_homePageFragment);
    }

    public static NavDirections actionMyAccountFragment2ToChangePasswordFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_changePasswordFragment);
    }

    public static NavDirections actionMyAccountFragment2ToMyAccountManageHomesFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_myAccountManageHomesFragment);
    }

    public static NavDirections actionMyAccountFragment2ToDeleteAccountTransferOwnerShipFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_deleteAccountTransferOwnerShipFragment);
    }

    public static NavDirections actionMyAccountFragment2ToAccountDisabledSuccessFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_AccountDisabledSuccessFragment);
    }

    public static NavDirections actionMyAccountFragment2ToAccountDeletedSuccessFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountFragment2_to_AccountDeletedSuccessFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
