package com.jch.racWiFi.iduOnBoarding.onBoadingInApMode.views;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ConfirmNetworkApFragmentDirections {
    private ConfirmNetworkApFragmentDirections() {
    }

    public static NavDirections actionApConfirmNetworkFragmentToAddHomeRouter() {
        return new ActionOnlyNavDirections(R.id.action_apConfirmNetworkFragment_to_addHomeRouter);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
