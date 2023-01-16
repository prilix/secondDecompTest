package com.jch.racWiFi.userOnboarding.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ManageDevicesViewFragmentV2Directions {
    private ManageDevicesViewFragmentV2Directions() {
    }

    public static NavDirections actionManageDevicesFragmentToDeviceDetailsFragment() {
        return new ActionOnlyNavDirections(R.id.action_manageDevicesFragment_to_deviceDetailsFragment);
    }

    public static NavDirections actionManageDevicesFragmentToQrScanFragment() {
        return new ActionOnlyNavDirections(R.id.action_manageDevicesFragment_to_qrScanFragment);
    }

    public static NavDirections actionManageDevicesFragmentToOnBoardNavGraph() {
        return new ActionOnlyNavDirections(R.id.action_manageDevicesFragment_to_on_board_nav_graph);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
