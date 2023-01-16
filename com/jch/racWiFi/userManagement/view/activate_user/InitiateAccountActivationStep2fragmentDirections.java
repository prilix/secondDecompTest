package com.jch.racWiFi.userManagement.view.activate_user;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.AccountActivationNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class InitiateAccountActivationStep2fragmentDirections {
    private InitiateAccountActivationStep2fragmentDirections() {
    }

    public static NavDirections actionAccountActivationStep2ToAccountActivationSuccess() {
        return new ActionOnlyNavDirections(R.id.action_account_activation_step2_to_account_activation_success);
    }

    public static NavDirections actionGlobalLoginFragment3() {
        return AccountActivationNavGraphDirections.actionGlobalLoginFragment3();
    }
}
