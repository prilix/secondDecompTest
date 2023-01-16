package com.jch.racWiFi.userManagement.view.forgot_password;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.ForgotPasswordNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep1FragmentDirections {
    private ForgotPasswordStep1FragmentDirections() {
    }

    public static NavDirections actionForgotPasswordStep1ToForgotPasswordStep2() {
        return new ActionOnlyNavDirections(R.id.action_forgot_password_step1_to_forgot_password_step2);
    }

    public static NavDirections actionGlobalLoginFragment2() {
        return ForgotPasswordNavGraphDirections.actionGlobalLoginFragment2();
    }
}
