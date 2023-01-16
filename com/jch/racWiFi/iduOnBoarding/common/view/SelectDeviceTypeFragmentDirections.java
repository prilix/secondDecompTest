package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class SelectDeviceTypeFragmentDirections {
    private SelectDeviceTypeFragmentDirections() {
    }

    public static NavDirections actionSelectDeviceTypeFragmentToAddDeviceFragment() {
        return new ActionOnlyNavDirections(R.id.action_selectDeviceTypeFragment_to_addDeviceFragment);
    }

    public static NavDirections actionSelectDeviceTypeFragmentToConfirmWirelessNetworkIndiaFrag() {
        return new ActionOnlyNavDirections(R.id.action_selectDeviceTypeFragment_to_confirmWirelessNetworkIndiaFrag);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
