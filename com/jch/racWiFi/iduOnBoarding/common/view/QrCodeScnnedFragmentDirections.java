package com.jch.racWiFi.iduOnBoarding.common.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.OnBoardNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class QrCodeScnnedFragmentDirections {
    private QrCodeScnnedFragmentDirections() {
    }

    public static NavDirections actionQrCodeScannedToAddDeviceFragment() {
        return new ActionOnlyNavDirections(R.id.action_qrCodeScanned_to_addDeviceFragment);
    }

    public static NavDirections toConfirmWirelessNetworkIndiaFrag() {
        return new ActionOnlyNavDirections(R.id.to_confirmWirelessNetworkIndiaFrag);
    }

    public static NavDirections actionQrCodeScannedToAcAddedInOtherFragment() {
        return new ActionOnlyNavDirections(R.id.action_qrCodeScanned_to_acAddedInOtherFragment);
    }

    public static NavDirections actionQrCodeScannedToAcAddedInSameHomeFragment() {
        return new ActionOnlyNavDirections(R.id.action_qrCodeScanned_to_acAddedInSameHomeFragment);
    }

    public static NavDirections actionGlobalDeviceDetailsFragment() {
        return OnBoardNavGraphDirections.actionGlobalDeviceDetailsFragment();
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return OnBoardNavGraphDirections.actionGlobalHomePageFragment();
    }
}
