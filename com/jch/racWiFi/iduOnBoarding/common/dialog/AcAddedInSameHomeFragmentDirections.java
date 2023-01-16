package com.jch.racWiFi.iduOnBoarding.common.dialog;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class AcAddedInSameHomeFragmentDirections {
    private AcAddedInSameHomeFragmentDirections() {
    }

    public static NavDirections actionAcAddedInSameHomeFragmentToSelectDeviceTypeFragment() {
        return new ActionOnlyNavDirections(R.id.action_acAddedInSameHomeFragment_to_selectDeviceTypeFragment);
    }

    public static NavDirections actionAcAddedInSameHomeFragmentToAddDeviceFragment() {
        return new ActionOnlyNavDirections(R.id.action_acAddedInSameHomeFragment_to_addDeviceFragment);
    }

    public static NavDirections actionAcAddedInSameHomeFragmentToConfirmWirelessNetworkFrag() {
        return new ActionOnlyNavDirections(R.id.action_acAddedInSameHomeFragment_to_confirmWirelessNetworkFrag);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
