package com.jch.racWiFi.userManagement.view.SignUpFlow;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.SignUpNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class CreateAccountStep3FragmentDirections {
    private CreateAccountStep3FragmentDirections() {
    }

    public static NavDirections actionCreateAccountOtpVerificationSignUp3ToEnterAddressSignUp4() {
        return new ActionOnlyNavDirections(R.id.action_createAccountOtpVerificationSignUp3_to_enterAddressSignUp4);
    }

    /* renamed from: actionCreateAccountOtpVerificationSignUp3ToAccountCreationSuccessSignUp5 */
    public static NavDirections m570x2493eddb() {
        return new ActionOnlyNavDirections(R.id.action_createAccountOtpVerificationSignUp3_to_accountCreationSuccessSignUp5);
    }

    public static NavDirections actionGlobalLoginFragment() {
        return SignUpNavGraphDirections.actionGlobalLoginFragment();
    }
}
