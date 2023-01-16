package com.jch.racWiFi.energyConsumption.fragment;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.EnergyConsumptionNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ECHomeFragmentDirections {
    private ECHomeFragmentDirections() {
    }

    public static NavDirections actionEcHomeFragmentToEcSelectACsFragment() {
        return new ActionOnlyNavDirections(R.id.action_ecHomeFragment_to_ecSelectACsFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return EnergyConsumptionNavGraphDirections.actionGlobalHomePageFragment();
    }
}
