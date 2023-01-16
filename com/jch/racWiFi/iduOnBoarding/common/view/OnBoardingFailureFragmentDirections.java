package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class OnBoardingFailureFragmentDirections {
    private OnBoardingFailureFragmentDirections() {
    }

    public static NavDirections actionConfiguringFailedUdpExchangeToConfiguringDeviceCheckMdns() {
        return new ActionOnlyNavDirections(R.id.action_configuringFailedUdpExchange_to_configuringDeviceCheckMdns);
    }

    public static NavDirections actionConfiguringFailedUdpExchangeToQrScanFragment() {
        return new ActionOnlyNavDirections(R.id.action_configuringFailedUdpExchange_to_qrScanFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
