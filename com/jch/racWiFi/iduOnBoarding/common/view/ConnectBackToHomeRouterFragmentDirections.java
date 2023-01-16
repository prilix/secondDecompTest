package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ConnectBackToHomeRouterFragmentDirections {
    private ConnectBackToHomeRouterFragmentDirections() {
    }

    /* renamed from: actionConnectBackToHomeRouterFragmentToConfiguringDeviceCheckMdns */
    public static NavDirections m425x8d388934() {
        return new ActionOnlyNavDirections(R.id.action_connectBackToHomeRouterFragment_to_configuringDeviceCheckMdns);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
