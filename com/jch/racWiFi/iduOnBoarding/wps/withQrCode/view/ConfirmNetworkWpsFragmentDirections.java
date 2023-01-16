package com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ConfirmNetworkWpsFragmentDirections {
    private ConfirmNetworkWpsFragmentDirections() {
    }

    public static NavDirections actionWpsConfirmNetworkFragmentToEnableWpsOnRacFragment() {
        return new ActionOnlyNavDirections(R.id.action_wpsConfirmNetworkFragment_to_enableWpsOnRacFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
