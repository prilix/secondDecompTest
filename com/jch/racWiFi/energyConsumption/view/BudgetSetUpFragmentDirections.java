package com.jch.racWiFi.energyConsumption.view;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.jch.racWiFi.EnergyConsumptionNavGraphDirections;
import com.jch_hitachi.aircloudglobal.R;

public class BudgetSetUpFragmentDirections {
    private BudgetSetUpFragmentDirections() {
    }

    public static NavDirections actionBudgetSetUpFragmentToSelectCurrencyDialogFragment() {
        return new ActionOnlyNavDirections(R.id.action_budgetSetUpFragment_to_selectCurrencyDialogFragment);
    }

    public static NavDirections actionBudgetSetUpFragmentToSelectBillingStructureFragment() {
        return new ActionOnlyNavDirections(R.id.action_budgetSetUpFragment_to_selectBillingStructureFragment);
    }

    public static NavDirections actionGlobalHomePageFragment() {
        return EnergyConsumptionNavGraphDirections.actionGlobalHomePageFragment();
    }
}
