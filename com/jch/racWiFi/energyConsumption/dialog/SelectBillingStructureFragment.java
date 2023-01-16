package com.jch.racWiFi.energyConsumption.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jch.racWiFi.GenericDialogFragment;
import com.jch.racWiFi.databinding.DialogEnergyCostBillingStructureBinding;
import com.jch.racWiFi.energyConsumption.adapter.SelectBillingRvAdapter;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch_hitachi.aircloudglobal.R;

public class SelectBillingStructureFragment extends GenericDialogFragment implements View.OnClickListener {
    private DialogEnergyCostBillingStructureBinding mBinding;
    private EnergyConsumptionViewModel mViewModel;
    private SelectBillingRvAdapter selectBillingRvAdapter;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.selectBillingRvAdapter = new SelectBillingRvAdapter(getResources().getStringArray(R.array.billing_structure_array));
        this.mViewModel = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DialogEnergyCostBillingStructureBinding dialogEnergyCostBillingStructureBinding = (DialogEnergyCostBillingStructureBinding) DataBindingUtil.inflate(layoutInflater, R.layout.dialog_energy_cost_billing_structure, viewGroup, false);
        this.mBinding = dialogEnergyCostBillingStructureBinding;
        return dialogEnergyCostBillingStructureBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.buttonCancel.setOnClickListener(this);
        this.mBinding.buttonSave.setOnClickListener(this);
        setRecyclerViewListAdapter();
    }

    private void setRecyclerViewListAdapter() {
        this.mBinding.recyclerViewBillingStructure.setHasFixedSize(true);
        this.mBinding.recyclerViewBillingStructure.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.mBinding.recyclerViewBillingStructure.setAdapter(this.selectBillingRvAdapter);
        this.selectBillingRvAdapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_cancel) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id == R.id.button_save) {
            if (this.selectBillingRvAdapter.getCheckedPosition() != -1) {
                this.mViewModel.setBillingTypeLiveData(this.selectBillingRvAdapter.getCheckedPosition());
                this.mFragmentToActivityCallback.getNavController().navigateUp();
                return;
            }
            showErrorPopUp(getString(R.string.energyConsumption_alert_selectBilling));
        }
    }
}
