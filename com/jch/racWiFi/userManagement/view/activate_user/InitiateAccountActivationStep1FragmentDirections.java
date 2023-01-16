package com.jch.racWiFi.userManagement.view.activate_user;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.AccountActivationNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class InitiateAccountActivationStep1FragmentDirections {
    private InitiateAccountActivationStep1FragmentDirections() {
    }

    public static NavDirections actionAccountActivationStep1ToAccountActivationStep2() {
        return new ActionOnlyNavDirections(R.id.action_account_activation_step1_to_account_activation_step2);
    }

    public static NavDirections actionGlobalLoginFragment3() {
        return AccountActivationNavGraphDirections.actionGlobalLoginFragment3();
    }
}
