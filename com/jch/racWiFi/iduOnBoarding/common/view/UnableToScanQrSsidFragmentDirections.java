package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class UnableToScanQrSsidFragmentDirections {
    private UnableToScanQrSsidFragmentDirections() {
    }

    public static NavDirections actionUnableToScanQrSsidFragmentToSelectDeviceTypeFragment() {
        return new ActionOnlyNavDirections(R.id.action_unableToScanQrSsidFragment_to_selectDeviceTypeFragment);
    }

    public static NavDirections actionUnableToScanQrSsidFragmentToAcAddedInSameHomeFragment() {
        return new ActionOnlyNavDirections(R.id.action_unableToScanQrSsidFragment_to_acAddedInSameHomeFragment);
    }

    public static NavDirections actionUnableToScanQrSsidFragmentToAcAddedInOtherFragment() {
        return new ActionOnlyNavDirections(R.id.action_unableToScanQrSsidFragment_to_acAddedInOtherFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
