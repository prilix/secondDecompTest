package com.jch.racWiFi.energyConsumption.fragment;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.EnergyConsumptionNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ECSelectACFragmentDirections {
    private ECSelectACFragmentDirections() {
    }

    public static NavDirections actionEcSelectACsFragmentToEnergyConsumptionFragment() {
        return new ActionOnlyNavDirections(R.id.action_EcSelectACsFragment_to_EnergyConsumptionFragment);
    }

    public static NavDirections actionEcSelectACsFragmentToECHomeFragment() {
        return new ActionOnlyNavDirections(R.id.action_EcSelectACsFragment_to_ECHomeFragment);
    }

    public static NavDirections actionEcSelectACsFragmentToQrScanFragment() {
        return new ActionOnlyNavDirections(R.id.action_EcSelectACsFragment_to_qrScanFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return EnergyConsumptionNavGraphDirections.actionGlobalHomePageFragment();
    }
}
