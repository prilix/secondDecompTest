package com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnRacFragmentDirections {
    private EnableWpsOnRacFragmentDirections() {
    }

    public static NavDirections actionEnableWpsOnRacFragmentToEnableWpsOnHomeRouterFragment() {
        return new ActionOnlyNavDirections(R.id.action_enableWpsOnRacFragment_to_enableWpsOnHomeRouterFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
