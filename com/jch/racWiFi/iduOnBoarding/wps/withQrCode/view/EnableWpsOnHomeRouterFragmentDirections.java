package com.jch.racWiFi.iduOnBoarding.wps.withQrCode.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class EnableWpsOnHomeRouterFragmentDirections {
    private EnableWpsOnHomeRouterFragmentDirections() {
    }

    public static NavDirections actionEnableWpsOnHomeRouterFragmentToConfiguringDeviceCheckMdns() {
        return new ActionOnlyNavDirections(R.id.action_enableWpsOnHomeRouterFragment_to_configuringDeviceCheckMdns);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
