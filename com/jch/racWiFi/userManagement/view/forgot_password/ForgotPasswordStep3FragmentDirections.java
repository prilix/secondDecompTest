package com.jch.racWiFi.userManagement.view.forgot_password;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.ForgotPasswordNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ForgotPasswordStep3FragmentDirections {
    private ForgotPasswordStep3FragmentDirections() {
    }

    public static NavDirections actionForgotPasswordStep3ToForgotPasswordStep4() {
        return new ActionOnlyNavDirections(R.id.action_forgot_password_step3_to_forgot_password_step4);
    }

    public static NavDirections actionGlobalLoginFragment2() {
        return ForgotPasswordNavGraphDirections.actionGlobalLoginFragment2();
    }
}
