package com.jch.racWiFi.userManagement.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch_hitachi.aircloudglobal.R;

public class LoginFragmentDirections {
    private LoginFragmentDirections() {
    }

    public static NavDirections actionLoginFragmentToSignUpNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_loginFragment_to_sign_up_nav_graph);
    }

    public static NavDirections actionLoginFragmentToForgotPasswordNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_loginFragment_to_forgot_password_nav_graph);
    }

    public static NavDirections actionLoginFragmentToAccountActivationNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_loginFragment_to_account_activation_nav_graph);
    }
}
