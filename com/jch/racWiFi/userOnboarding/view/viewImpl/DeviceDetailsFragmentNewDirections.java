package com.jch.racWiFi.userOnboarding.view.viewImpl;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.MainGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class DeviceDetailsFragmentNewDirections {
    private DeviceDetailsFragmentNewDirections() {
    }

    /* renamed from: actionDeviceDetailsFragmentToUserPermisissionsDeviceSettingsFragment */
    public static NavDirections m665xb892a9b7() {
        return new ActionOnlyNavDirections(R.id.action_deviceDetailsFragment_to_userPermisissionsDeviceSettingsFragment);
    }

    public static NavDirections actionDeviceDetailsFragmentToManageDevicesFragment() {
        return new ActionOnlyNavDirections(R.id.action_deviceDetailsFragment_to_manageDevicesFragment);
    }

    public static NavDirections actionDeviceDetailsFragmentToHomePageFragment() {
        return new ActionOnlyNavDirections(R.id.action_deviceDetailsFragment_to_homePageFragment);
    }

    public static NavDirections actionDeviceDetailsFragmentToManageUserFragment() {
        return new ActionOnlyNavDirections(R.id.action_deviceDetailsFragment_to_manageUserFragment);
    }

    public static NavDirections actionDeviceDetailsFragmentToManageOwnersByRacIdFragment() {
        return new ActionOnlyNavDirections(R.id.action_deviceDetailsFragment_to_manageOwnersByRacIdFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return MainGraphDirections.actionGlobalHomePageFragment();
    }
}
