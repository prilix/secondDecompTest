package com.jch.racWiFi.userManagement.view.forgot_password;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.ForgotPasswordNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep2FragmentDirections {
    private ForgotPasswordStep2FragmentDirections() {
    }

    public static NavDirections actionForgotPasswordStep2ToForgotPasswordStep3() {
        return new ActionOnlyNavDirections(R.id.action_forgot_password_step2_to_forgot_password_step3);
    }

    public static NavDirections actionGlobalLoginFragment2() {
        return ForgotPasswordNavGraphDirections.actionGlobalLoginFragment2();
    }
}
