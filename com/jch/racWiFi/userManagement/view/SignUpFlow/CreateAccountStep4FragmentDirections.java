package com.jch.racWiFi.userManagement.view.SignUpFlow;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.SignUpNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class CreateAccountStep4FragmentDirections {
    private CreateAccountStep4FragmentDirections() {
    }

    public static NavDirections actionEnterAddressSignUp4ToAccountCreationSuccessSignUp5() {
        return new ActionOnlyNavDirections(R.id.action_enterAddressSignUp4_to_accountCreationSuccessSignUp5);
    }

    public static NavDirections actionGlobalLoginFragment() {
        return SignUpNavGraphDirections.actionGlobalLoginFragment();
    }
}
