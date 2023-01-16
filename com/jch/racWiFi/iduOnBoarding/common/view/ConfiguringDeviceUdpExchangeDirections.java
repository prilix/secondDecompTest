package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ConfiguringDeviceUdpExchangeDirections {
    private ConfiguringDeviceUdpExchangeDirections() {
    }

    public static NavDirections actionConfiguringDeviceUdpExchangeToConfiguringDeviceCheckMdns() {
        return new ActionOnlyNavDirections(R.id.action_configuringDeviceUdpExchange_to_configuringDeviceCheckMdns);
    }

    public static NavDirections actionConfiguringDeviceUdpExchangeToConfiguringFailedUdpExchange() {
        return new ActionOnlyNavDirections(R.id.action_configuringDeviceUdpExchange_to_configuringFailedUdpExchange);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
