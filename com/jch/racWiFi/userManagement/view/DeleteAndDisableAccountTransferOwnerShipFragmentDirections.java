package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class DeleteAndDisableAccountTransferOwnerShipFragmentDirections {
    private DeleteAndDisableAccountTransferOwnerShipFragmentDirections() {
    }

    public static NavDirections actionDeleteAccountChangeOwnerShipFragmentToMyAccountFragment2() {
        return new ActionOnlyNavDirections(R.id.action_deleteAccountChangeOwnerShipFragment_to_myAccountFragment2);
    }

    /* renamed from: actionDeleteAccountChangeOwnershipToAccountDisabledSuccessFragment */
    public static NavDirections m528xed71ee78() {
        return new ActionOnlyNavDirections(R.id.action_deleteAccountChangeOwnership_to_AccountDisabledSuccessFragment);
    }

    /* renamed from: actionDeleteAccountChangeOwnershipToAccountDeletedSuccessFragment */
    public static NavDirections m527xa905f8d9() {
        return new ActionOnlyNavDirections(R.id.action_deleteAccountChangeOwnership_to_AccountDeletedSuccessFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
