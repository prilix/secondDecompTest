package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ConfiguringDeviceCheckMdnsDirections {
    private ConfiguringDeviceCheckMdnsDirections() {
    }

    public static NavDirections actionConfiguringPageFragmentToSuccessFragment() {
        return new ActionOnlyNavDirections(R.id.action_configuringPageFragment_to_successFragment);
    }

    /* renamed from: actionConfiguringDeviceCheckMdnsToConnectBackToHomeRouterFragment */
    public static NavDirections m421xb7b8dea8() {
        return new ActionOnlyNavDirections(R.id.action_configuringDeviceCheckMdns_to_connectBackToHomeRouterFragment);
    }

    public static NavDirections actionConfiguringDeviceCheckMdnsToConfiguringFailedUdpExchange() {
        return new ActionOnlyNavDirections(R.id.action_configuringDeviceCheckMdns_to_configuringFailedUdpExchange);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
