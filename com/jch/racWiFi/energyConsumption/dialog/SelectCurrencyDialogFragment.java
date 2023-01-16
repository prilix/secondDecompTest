package com.jch.racWiFi.energyConsumption.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jch.racWiFi.GenericDialogFragment;
import com.jch.racWiFi.databinding.DialogEnergyCostCurrencyBinding;
import com.jch.racWiFi.databinding.SearchCurrencyBinding;
import com.jch.racWiFi.energyConsumption.adapter.SelectCurrencyRvAdapter;
import com.jch.racWiFi.energyConsumption.model.local.CurrencyModel;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Objects;

public class SelectCurrencyDialogFragment extends GenericDialogFragment implements View.OnClickListener, TextWatcher {
    private ArrayList<CurrencyModel> currencyList;
    private DialogEnergyCostCurrencyBinding mBinding;
    private SearchCurrencyBinding mCurrencyBinding;
    private EnergyConsumptionViewModel mViewModel;
    private SelectCurrencyRvAdapter selectCurrencyRvAdapter;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.currencyList = new ArrayList<>();
        this.selectCurrencyRvAdapter = new SelectCurrencyRvAdapter(this.currencyList);
        this.mViewModel = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DialogEnergyCostCurrencyBinding dialogEnergyCostCurrencyBinding = (DialogEnergyCostCurrencyBinding) DataBindingUtil.inflate(layoutInflater, R.layout.dialog_energy_cost_currency, viewGroup, false);
        this.mBinding = dialogEnergyCostCurrencyBinding;
        return dialogEnergyCostCurrencyBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mCurrencyBinding = this.mBinding.llinearlayoutIncluded;
        this.mBinding.buttonCancel.setOnClickListener(this);
        this.mBinding.buttonSave.setOnClickListener(this);
        this.mCurrencyBinding.clear.setOnClickListener(this);
        this.mCurrencyBinding.editTextSearchCurrency.addTextChangedListener(this);
        this.currencyList.clear();
        this.currencyList.addAll(this.mViewModel.getCurrrenciesListFromLocale());
        setRecyclerViewListAdapter();
    }

    private void setRecyclerViewListAdapter() {
        this.mBinding.recyclerViewCurrency.setHasFixedSize(true);
        this.mBinding.recyclerViewCurrency.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.mBinding.recyclerViewCurrency.setAdapter(this.selectCurrencyRvAdapter);
        this.selectCurrencyRvAdapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.buttonCancel) {
            boolean z = false;
            if (id == R.id.buttonSave) {
                if (this.selectCurrencyRvAdapter.getCheckedPosition() != -1) {
                    z = true;
                }
                if (z) {
                    this.mViewModel.setCurrencyModelLiveData(this.selectCurrencyRvAdapter.currencyListFiltered.get(this.selectCurrencyRvAdapter.getCheckedPosition()));
                    this.mFragmentToActivityCallback.getNavController().navigateUp();
                    return;
                }
                showErrorPopUp(getString(R.string.energyConsumption_alert_selectCurrency));
            } else if (id == R.id.clear) {
                Editable text = this.mCurrencyBinding.editTextSearchCurrency.getText();
                Objects.requireNonNull(text);
                Editable editable = text;
                text.clear();
                enableOrDisableClearIcon(0.4f, false);
            }
        } else {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.selectCurrencyRvAdapter.setCheckedPosition(-1);
        this.selectCurrencyRvAdapter.getFilter().filter(charSequence.toString());
        if (charSequence.length() > 0) {
            enableOrDisableClearIcon(1.0f, true);
        } else {
            enableOrDisableClearIcon(0.4f, false);
        }
    }

    private void enableOrDisableClearIcon(float f, boolean z) {
        this.mCurrencyBinding.clear.setAlpha(f);
        this.mCurrencyBinding.clear.setEnabled(z);
    }
}
