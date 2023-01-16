package com.jch.racWiFi.energyConsumption.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.EnergyConsumptionNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class EnergyConsumptionFragmentDirections {
    private EnergyConsumptionFragmentDirections() {
    }

    public static NavDirections actionNavEnergyConsumptionToConsumedEnergyGraphFragment() {
        return new ActionOnlyNavDirections(R.id.action_nav_energy_consumption_to_consumedEnergyGraphFragment);
    }

    public static NavDirections actionNavEnergyConsumptionToEcHomeFragment() {
        return new ActionOnlyNavDirections(R.id.action_nav_energy_consumption_to_ecHomeFragment);
    }

    public static NavDirections actionEnergyConsumptionToQrScanFragment() {
        return new ActionOnlyNavDirections(R.id.action_energyConsumption_to_qrScanFragment);
    }

    public static NavDirections actionEnergyConsumptionToEcSelectACsFragment() {
        return new ActionOnlyNavDirections(R.id.action_energyConsumption_to_ecSelectACsFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return EnergyConsumptionNavGraphDirections.actionGlobalHomePageFragment();
    }
}
