package com.jch.racWiFi.energyConsumption.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.GenericDialogFragment;
import com.jch.racWiFi.databinding.DilogEnergyBudgetBinding;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionBudgetAndPrice;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.energyConsumption.model.local.SetBudget;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch_hitachi.aircloudglobal.R;

public class SelectBudgetDialogFragment extends GenericDialogFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemClickListener, TextWatcher {
    private ArrayAdapter<Integer> arrayAdapter;
    private double budgetAmount;
    private Integer[] budgetAmountArray;
    /* access modifiers changed from: private */
    public EnergyConsumptionDataMain energyConsumptionDataMain;
    /* access modifiers changed from: private */
    public EnergyConsumptionViewModel energyConsumptionViewModel;
    /* access modifiers changed from: private */
    public ListPopupWindow listPopupWindow;
    private DilogEnergyBudgetBinding mBinding;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.listPopupWindow = new ListPopupWindow(requireActivity());
        this.budgetAmountArray = getBudgetAmountArray();
        this.arrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.list_pop_up_windowlayout, this.budgetAmountArray);
        EnergyConsumptionViewModel energyConsumptionViewModel2 = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
        this.energyConsumptionViewModel = energyConsumptionViewModel2;
        this.energyConsumptionDataMain = energyConsumptionViewModel2.getResponseDataMainInstanse();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DilogEnergyBudgetBinding dilogEnergyBudgetBinding = (DilogEnergyBudgetBinding) DataBindingUtil.inflate(layoutInflater, R.layout.dilog_energy_budget, viewGroup, false);
        this.mBinding = dilogEnergyBudgetBinding;
        return dilogEnergyBudgetBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.buttonNegative.setOnClickListener(this);
        this.mBinding.buttonPositive.setOnClickListener(this);
        enableOrDisableBtnClick(this.mBinding.switchMainHome.isChecked());
        this.mBinding.switchMainHome.setOnCheckedChangeListener(this);
        this.mBinding.llinearlayoutIncluded.clear.setOnClickListener(this);
        this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.setDropDownWidth(500);
        this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.addTextChangedListener(this);
        this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.setAdapter(new ArrayAdapter(requireActivity(), R.layout.list_pop_up_windowlayout, this.budgetAmountArray));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        observeSelectedBudgetAmount();
    }

    private void observeSelectedBudgetAmount() {
        this.energyConsumptionViewModel.getResponseLiveData().observe(getViewLifecycleOwner(), new SelectBudgetDialogFragment$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$observeSelectedBudgetAmount$0$com-jch-racWiFi-energyConsumption-dialog-SelectBudgetDialogFragment */
    public /* synthetic */ void mo28616x5f2da575(EnergyConsumptionDataMain energyConsumptionDataMain2) {
        SetBudget setBudget = energyConsumptionDataMain2.getSetBudget();
        if (setBudget != null) {
            double budgetAmount2 = setBudget.getBudgetAmount();
            this.budgetAmount = budgetAmount2;
            if (budgetAmount2 != C1030Utils.DOUBLE_EPSILON) {
                this.mBinding.switchMainHome.setChecked(true);
                this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.setText(String.valueOf(this.budgetAmount));
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_negative) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id != R.id.button_positive) {
            if (id == R.id.clear) {
                Editable text = this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.getText();
                if (text == null || text.toString().isEmpty()) {
                    listPopUpWindow();
                } else {
                    this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.setText("");
                }
            }
        } else if (this.mBinding.switchMainHome.isChecked()) {
            Editable text2 = this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.getText();
            if (text2 == null || text2.toString().isEmpty()) {
                showErrorPopUp(getString(R.string.common_alert_selectOrEnterBudget));
                return;
            }
            double parseInt = (double) Integer.parseInt(text2.toString());
            this.budgetAmount = parseInt;
            sendSelectedBudgetToServer(parseInt);
        } else {
            this.budgetAmount = C1030Utils.DOUBLE_EPSILON;
            sendSelectedBudgetToServer(C1030Utils.DOUBLE_EPSILON);
        }
    }

    private void listPopUpWindow() {
        this.listPopupWindow.setAdapter(this.arrayAdapter);
        this.listPopupWindow.setAnchorView(this.mBinding.llinearlayoutIncluded.autoCompleteTextView1);
        this.listPopupWindow.setWidth(500);
        this.listPopupWindow.setHeight(-2);
        this.listPopupWindow.setModal(true);
        this.listPopupWindow.setOnItemClickListener(this);
        this.listPopupWindow.show();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        enableOrDisableBtnClick(z);
    }

    private void enableOrDisableBtnClick(boolean z) {
        this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.setEnabled(z);
        this.mBinding.llinearlayoutIncluded.clear.setEnabled(z);
        if (z && this.budgetAmount != C1030Utils.DOUBLE_EPSILON) {
            this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.setText(String.valueOf(this.budgetAmount));
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mBinding.llinearlayoutIncluded.autoCompleteTextView1.setText(String.valueOf(this.budgetAmountArray[i]));
        this.listPopupWindow.dismiss();
    }

    private Integer[] getBudgetAmountArray() {
        Integer[] numArr = new Integer[10];
        this.budgetAmountArray = numArr;
        numArr[0] = 1000;
        for (int i = 1; i < 10; i++) {
            Integer[] numArr2 = this.budgetAmountArray;
            numArr2[i] = Integer.valueOf(numArr2[i - 1].intValue() + 1000);
        }
        return this.budgetAmountArray;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence == null || charSequence.toString().isEmpty()) {
            this.mBinding.llinearlayoutIncluded.clear.setImageDrawable(R.drawable.ic_arrow_down_red);
        } else {
            this.mBinding.llinearlayoutIncluded.clear.setImageDrawable(R.drawable.ic_popup_window_close_svg);
        }
    }

    private void sendSelectedBudgetToServer(final double d) {
        EnergyConsumptionBudgetAndPrice energyConsumptionBudgetAndPrice = new EnergyConsumptionBudgetAndPrice();
        energyConsumptionBudgetAndPrice.setPrice(this.energyConsumptionDataMain.getUnitPrice());
        energyConsumptionBudgetAndPrice.setBudget(d);
        showPleaseWaitDialog();
        this.energyConsumptionViewModel.setEnergyConsumptionBudgetAndPrice(energyConsumptionBudgetAndPrice).observeWithCachedTrigger(getViewLifecycleOwner(), new Observer<GenericResponse>() {
            public void onChanged(GenericResponse genericResponse) {
                SelectBudgetDialogFragment.this.dismissPleaseWaitDialog();
                if (genericResponse == null) {
                    SelectBudgetDialogFragment selectBudgetDialogFragment = SelectBudgetDialogFragment.this;
                    selectBudgetDialogFragment.showErrorPopUp(selectBudgetDialogFragment.getString(R.string.common_alert_unableToReachServer));
                } else if (genericResponse.unableToReachServer()) {
                    SelectBudgetDialogFragment selectBudgetDialogFragment2 = SelectBudgetDialogFragment.this;
                    selectBudgetDialogFragment2.showErrorPopUp(selectBudgetDialogFragment2.getString(R.string.common_alert_unableToReachServer));
                } else if (genericResponse.isApiSuccessful()) {
                    SetBudget setBudget = SelectBudgetDialogFragment.this.energyConsumptionViewModel.getResponseDataMainInstanse().getSetBudget();
                    setBudget.setBudgetAmount(d);
                    SelectBudgetDialogFragment.this.energyConsumptionDataMain.setSetBudget(setBudget);
                    SelectBudgetDialogFragment.this.energyConsumptionDataMain.setToFetchData(false);
                    SelectBudgetDialogFragment.this.energyConsumptionViewModel.setResponseLiveData(SelectBudgetDialogFragment.this.energyConsumptionDataMain);
                    SelectBudgetDialogFragment.this.listPopupWindow.dismiss();
                    SelectBudgetDialogFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
                } else {
                    SelectBudgetDialogFragment selectBudgetDialogFragment3 = SelectBudgetDialogFragment.this;
                    selectBudgetDialogFragment3.showErrorPopUp(selectBudgetDialogFragment3.getString(R.string.common_alert_unableToReachServer));
                }
            }
        });
    }
}
