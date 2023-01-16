package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class QrScanFragmentV2Directions {
    private QrScanFragmentV2Directions() {
    }

    public static NavDirections actionQrScanFragmentToQrCodeScanned() {
        return new ActionOnlyNavDirections(R.id.action_qrScanFragment_to_qrCodeScanned);
    }

    public static NavDirections actionQrScanFragmentToUnableToScanQrSsidFragment() {
        return new ActionOnlyNavDirections(R.id.action_qrScanFragment_to_unableToScanQrSsidFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
