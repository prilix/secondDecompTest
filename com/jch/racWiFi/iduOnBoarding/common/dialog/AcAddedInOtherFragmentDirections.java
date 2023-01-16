package com.jch.racWiFi.iduOnBoarding.common.dialog;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class AcAddedInOtherFragmentDirections {
    private AcAddedInOtherFragmentDirections() {
    }

    public static NavDirections actionAcAddedInOtherFragmentToSelectDeviceTypeFragment() {
        return new ActionOnlyNavDirections(R.id.action_acAddedInOtherFragment_to_selectDeviceTypeFragment);
    }

    public static NavDirections actionAcAddedInOtherFragmentToAddDeviceFragment() {
        return new ActionOnlyNavDirections(R.id.action_acAddedInOtherFragment_to_addDeviceFragment);
    }

    public static NavDirections actionAcAddedInOtherFragmentToConfirmWirelessNetworkFrag() {
        return new ActionOnlyNavDirections(R.id.action_acAddedInOtherFragment_to_confirmWirelessNetworkFrag);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
