package com.jch.racWiFi.energyConsumption.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.EnergyConsumptionNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class ConsumedEnergyGraphFragmentDirections {
    private ConsumedEnergyGraphFragmentDirections() {
    }

    public static NavDirections actionConsumedEnergyGraphFragmentToBudgetSetUpFragment() {
        return new ActionOnlyNavDirections(R.id.action_consumedEnergyGraphFragment_to_budgetSetUpFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return EnergyConsumptionNavGraphDirections.actionGlobalHomePageFragment();
    }
}
