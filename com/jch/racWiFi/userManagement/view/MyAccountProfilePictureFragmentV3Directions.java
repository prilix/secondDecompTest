package com.jch.racWiFi.userManagement.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountProfilePictureFragmentV3Directions {
    private MyAccountProfilePictureFragmentV3Directions() {
    }

    public static NavDirections actionMyAccountProfilePictureFragmentToMyAccountFragment2() {
        return new ActionOnlyNavDirections(R.id.action_myAccountProfilePictureFragment_to_myAccountFragment2);
    }

    public static NavDirections actionMyAccountProfilePictureFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_myAccountProfilePictureFragment_to_homePageFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
