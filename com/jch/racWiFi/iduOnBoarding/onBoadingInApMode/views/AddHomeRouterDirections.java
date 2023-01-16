package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class AddHomeRouterDirections {
    private AddHomeRouterDirections() {
    }

    public static NavDirections actionAddHomeRouterToAddRac() {
        return new ActionOnlyNavDirections(R.id.action_addHomeRouter_to_addRac);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
