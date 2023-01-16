package com.jch.racWiFi;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SignUpNavGraphDirections {
    private SignUpNavGraphDirections() {
    }

    public static NavDirections actionGlobalLoginFragment() {
        return new ActionOnlyNavDirections(R.id.action_global_loginFragment);
    }
}
