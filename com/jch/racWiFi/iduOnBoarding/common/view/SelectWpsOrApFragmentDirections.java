package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SelectWpsOrApFragmentDirections {
    private SelectWpsOrApFragmentDirections() {
    }

    public static NavDirections toConfirmWirelessNetworkIndiaFrag() {
        return new ActionOnlyNavDirections(R.id.to_confirmWirelessNetworkIndiaFrag);
    }

    public static NavDirections toConfirmNetworkWpsFragment() {
        return new ActionOnlyNavDirections(R.id.to_confirmNetworkWpsFragment);
    }

    public static NavDirections toConfirmNetworkApFragment() {
        return new ActionOnlyNavDirections(R.id.to_confirmNetworkApFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
