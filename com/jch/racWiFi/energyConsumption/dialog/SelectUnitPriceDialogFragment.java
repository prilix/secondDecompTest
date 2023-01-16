package com.jch.racWiFi.energyConsumption.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import com.jch.racWiFi.GenericDialogFragment;
import com.jch.racWiFi.databinding.DilogEnergyConsuptionCostBinding;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionBudgetAndPrice;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch_hitachi.aircloudglobal.R;

public class SelectUnitPriceDialogFragment extends GenericDialogFragment implements View.OnClickListener {
    EnergyConsumptionDataMain energyConsumptionDataMain;
    /* access modifiers changed from: private */
    public EnergyConsumptionViewModel energyConsumptionViewModel;
    /* access modifiers changed from: private */
    public int lastSelectedUnitPrice;
    private DilogEnergyConsuptionCostBinding mBinding;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EnergyConsumptionViewModel energyConsumptionViewModel2 = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
        this.energyConsumptionViewModel = energyConsumptionViewModel2;
        this.energyConsumptionDataMain = energyConsumptionViewModel2.getResponseDataMainInstanse();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DilogEnergyConsuptionCostBinding dilogEnergyConsuptionCostBinding = (DilogEnergyConsuptionCostBinding) DataBindingUtil.inflate(layoutInflater, R.layout.dilog_energy_consuption_cost, viewGroup, false);
        this.mBinding = dilogEnergyConsuptionCostBinding;
        return dilogEnergyConsuptionCostBinding.getRoot();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setUnitPriceNumberPicker();
        this.mBinding.buttonNegative.setOnClickListener(this);
        this.mBinding.buttonPositive.setOnClickListener(this);
        this.energyConsumptionViewModel.getResponseLiveData().observe(getViewLifecycleOwner(), new Observer<EnergyConsumptionDataMain>() {
            public void onChanged(EnergyConsumptionDataMain energyConsumptionDataMain) {
            }
        });
    }

    private void setUnitPriceNumberPicker() {
        this.mBinding.numberPickerUnitPrice.setMinValue(1);
        this.mBinding.numberPickerUnitPrice.setMaxValue(100);
        this.mBinding.numberPickerUnitPrice.setWrapSelectorWheel(false);
        this.mBinding.numberPickerUnitPrice.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                SelectUnitPriceDialogFragment.this.lastSelectedUnitPrice = i2;
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_negative) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id == R.id.button_positive) {
            sendSelectedUnitPriceToServer();
        }
    }

    private void sendSelectedUnitPriceToServer() {
        EnergyConsumptionBudgetAndPrice energyConsumptionBudgetAndPrice = new EnergyConsumptionBudgetAndPrice();
        energyConsumptionBudgetAndPrice.setPrice((double) this.lastSelectedUnitPrice);
        energyConsumptionBudgetAndPrice.setBudget(this.energyConsumptionDataMain.getSetBudget().getBudgetAmount());
        showPleaseWaitDialog();
        this.energyConsumptionViewModel.setEnergyConsumptionBudgetAndPrice(energyConsumptionBudgetAndPrice).observeWithCachedTrigger(getViewLifecycleOwner(), new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                SelectUnitPriceDialogFragment.this.dismissPleaseWaitDialog();
                if (genericResponse == null) {
                    SelectUnitPriceDialogFragment selectUnitPriceDialogFragment = SelectUnitPriceDialogFragment.this;
                    selectUnitPriceDialogFragment.showErrorPopUp(selectUnitPriceDialogFragment.getString(R.string.common_alert_unableToReachServer));
                } else if (genericResponse.unableToReachServer()) {
                    SelectUnitPriceDialogFragment selectUnitPriceDialogFragment2 = SelectUnitPriceDialogFragment.this;
                    selectUnitPriceDialogFragment2.showErrorPopUp(selectUnitPriceDialogFragment2.getString(R.string.common_alert_unableToReachServer));
                } else if (genericResponse.isApiSuccessful()) {
                    SelectUnitPriceDialogFragment.this.energyConsumptionDataMain.setToFetchData(false);
                    SelectUnitPriceDialogFragment.this.energyConsumptionDataMain.setUnitPrice((double) SelectUnitPriceDialogFragment.this.lastSelectedUnitPrice);
                    SelectUnitPriceDialogFragment.this.energyConsumptionViewModel.setResponseLiveData(SelectUnitPriceDialogFragment.this.energyConsumptionDataMain);
                    SelectUnitPriceDialogFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
                } else {
                    SelectUnitPriceDialogFragment selectUnitPriceDialogFragment3 = SelectUnitPriceDialogFragment.this;
                    selectUnitPriceDialogFragment3.showErrorPopUp(selectUnitPriceDialogFragment3.getString(R.string.common_alert_unableToReachServer));
                }
            }
        });
    }
}
