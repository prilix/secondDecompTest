package com.jch.racWiFi.userManagement.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SplashFragmentDirections {
    private SplashFragmentDirections() {
    }

    public static NavDirections actionSplashFragmentToLoginFragment() {
        return new ActionOnlyNavDirections(R.id.action_splashFragment_to_loginFragment);
    }

    public static NavDirections actionSplashFragmentToSignUpNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_splashFragment_to_sign_up_nav_graph);
    }
}
