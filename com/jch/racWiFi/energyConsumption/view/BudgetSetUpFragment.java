package com.jch.racWiFi.energyConsumption.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Utils.DecimalDigitsInputFilter;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.FragementEnergyConsuptionBudgetSetupBinding;
import com.jch.racWiFi.databinding.LayoutEnergyBudgetBinding;
import com.jch.racWiFi.databinding.LayoutPeakHoursAndMonthBinding;
import com.jch.racWiFi.databinding.LayoutProgressiveUnitBillBinding;
import com.jch.racWiFi.databinding.LayoutSimpleFlatRateBinding;
import com.jch.racWiFi.energyConsumption.adapter.ProgressiveBillUnitRvAdapter;
import com.jch.racWiFi.energyConsumption.adapter.SelectTimeOrSeasonBillingRvAdapter;
import com.jch.racWiFi.energyConsumption.callback.EditTextValueChangeListener;
import com.jch.racWiFi.energyConsumption.callback.ItemClickListener;
import com.jch.racWiFi.energyConsumption.model.local.CurrencyModel;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.energyConsumption.model.local.SetBudget;
import com.jch.racWiFi.energyConsumption.model.local.TimeOrMonthModel;
import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.EnergyCostSettingsData;
import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.ProgressiveUnitModel;
import com.jch.racWiFi.energyConsumption.utility.BillingStructureType;
import com.jch.racWiFi.energyConsumption.utility.SeasonBasedType;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import java.util.ArrayList;
import java.util.Objects;

public class BudgetSetUpFragment extends GenericFragment implements View.OnClickListener, SwitchButton.OnCheckedChangeListener, ItemClickListener, EditTextValueChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String[] billingStructureArray;
    Observer<Integer> billingStructureTypeObserver = new BudgetSetUpFragment$$ExternalSyntheticLambda3(this);
    private EnergyCostSettingsData budgetSetUpReqBody;
    Observer<CurrencyModel> currencyModelObserver = new BudgetSetUpFragment$$ExternalSyntheticLambda0(this);
    private EnergyConsumptionDataMain energyConsumptionDataMain;
    private LayoutPeakHoursAndMonthBinding hoursAndMonthBinding;
    private FragementEnergyConsuptionBudgetSetupBinding mBinding;
    /* access modifiers changed from: private */
    public LayoutEnergyBudgetBinding mEnergyBudgetBinding;
    private InputFilter[] mFilters;
    private EnergyConsumptionViewModel mViewModel;
    private ProgressiveBillUnitRvAdapter progressiveBillUnitRvAdapter;
    private ArrayList<ProgressiveUnitModel> progressiveUnitBillList;
    private LayoutProgressiveUnitBillBinding progressiveUnitBinding;
    private ArrayList<TimeOrMonthModel> seasonList;
    private SelectTimeOrSeasonBillingRvAdapter selectTimeOrSeasonBillingRvAdapter;
    private LayoutSimpleFlatRateBinding simpleFlatRateBinding;
    private ArrayList<TimeOrMonthModel> timeHoursList;
    private ArrayList<TimeOrMonthModel> timeOrSeasonListMain;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EnergyConsumptionViewModel energyConsumptionViewModel = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
        this.mViewModel = energyConsumptionViewModel;
        this.energyConsumptionDataMain = energyConsumptionViewModel.getResponseDataMainInstanse();
        this.billingStructureArray = getResources().getStringArray(R.array.billing_structure_array);
        this.timeOrSeasonListMain = new ArrayList<>();
        this.timeHoursList = new ArrayList<>();
        this.seasonList = new ArrayList<>();
        this.selectTimeOrSeasonBillingRvAdapter = new SelectTimeOrSeasonBillingRvAdapter(this.timeOrSeasonListMain, this);
        this.mFilters = new InputFilter[]{new DecimalDigitsInputFilter(6, 2)};
        this.progressiveUnitBillList = new ArrayList<>();
        this.progressiveBillUnitRvAdapter = new ProgressiveBillUnitRvAdapter(this.progressiveUnitBillList, this.mFilters, this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragementEnergyConsuptionBudgetSetupBinding fragementEnergyConsuptionBudgetSetupBinding = (FragementEnergyConsuptionBudgetSetupBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragement_energy_consuption_budget_setup, viewGroup, false);
        this.mBinding = fragementEnergyConsuptionBudgetSetupBinding;
        return fragementEnergyConsuptionBudgetSetupBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        String currencyCode = this.energyConsumptionDataMain.getCurrencyCode();
        String currencyName = this.energyConsumptionDataMain.getCurrencyName();
        if (!this.mViewModel.isNullOrEmpty(currencyCode)) {
            this.mBinding.textViewCurrencyCode.setText(currencyCode);
        }
        if (!this.mViewModel.isNullOrEmpty(currencyName)) {
            this.mBinding.textViewCurrencyName.setText(currencyName);
        }
        LayoutSimpleFlatRateBinding layoutSimpleFlatRateBinding = this.mBinding.layoutIncludeUnitPrice;
        this.simpleFlatRateBinding = layoutSimpleFlatRateBinding;
        layoutSimpleFlatRateBinding.unitPriceFlatRateEditText.setFilters(this.mFilters);
        this.simpleFlatRateBinding.unitPriceFlatRateEditText.addTextChangedListener(getTextWatcher(false));
        LayoutPeakHoursAndMonthBinding layoutPeakHoursAndMonthBinding = this.mBinding.layoutIncludeSelectPeakHoursMonths;
        this.hoursAndMonthBinding = layoutPeakHoursAndMonthBinding;
        layoutPeakHoursAndMonthBinding.editTextPeakHrUnitPrice.setFilters(this.mFilters);
        this.hoursAndMonthBinding.editTextOffPeakHrUnitPrice.setFilters(this.mFilters);
        this.hoursAndMonthBinding.editTextPeakHrUnitPrice.addTextChangedListener(getTextWatcher(false));
        this.hoursAndMonthBinding.editTextOffPeakHrUnitPrice.addTextChangedListener(getTextWatcher(false));
        this.mBinding.backButton.setOnClickListener(this);
        this.mBinding.textViewSave.setOnClickListener(this);
        this.mBinding.layoutSelectCurrency.setOnClickListener(this);
        this.mBinding.layoutSelectBillingStructure.setOnClickListener(this);
        LayoutProgressiveUnitBillBinding layoutProgressiveUnitBillBinding = this.mBinding.layoutIncludeProgressiveUnitBill;
        this.progressiveUnitBinding = layoutProgressiveUnitBillBinding;
        layoutProgressiveUnitBillBinding.imageButtonAdd.setOnClickListener(this);
        this.progressiveUnitBinding.imageButtonMinus.setOnClickListener(this);
        this.progressiveUnitBinding.editTextFixedCharges.setFilters(this.mFilters);
        this.progressiveUnitBinding.editTextFixedCharges.addTextChangedListener(getTextWatcher(false));
        LayoutEnergyBudgetBinding layoutEnergyBudgetBinding = this.mBinding.layoutIncludeEnergyBudget;
        this.mEnergyBudgetBinding = layoutEnergyBudgetBinding;
        layoutEnergyBudgetBinding.switchEnergyBudget.setOnCheckedChangeListener(this);
        this.mEnergyBudgetBinding.clearBudgetIcon.setOnClickListener(this);
        this.mEnergyBudgetBinding.enterBudgetEditText.addTextChangedListener(getTextWatcher(true));
        setTimeOrSeasonRecyclerViewAdapter();
        setProgressiveUnitRecyclerViewAdapter();
        getEnergyConsumptionSettingsData();
    }

    private void setTimeOrSeasonRecyclerViewAdapter() {
        this.hoursAndMonthBinding.recylerViewPeakHoursAndMonth.setNestedScrollingEnabled(false);
        this.hoursAndMonthBinding.recylerViewPeakHoursAndMonth.setHasFixedSize(true);
        this.hoursAndMonthBinding.recylerViewPeakHoursAndMonth.setLayoutManager(new GridLayoutManager(requireActivity(), 4));
        this.hoursAndMonthBinding.recylerViewPeakHoursAndMonth.setAdapter(this.selectTimeOrSeasonBillingRvAdapter);
        maketimeOrSeasonList(getString(R.string.energyConsumption_list_hours).split(","), false);
        maketimeOrSeasonList(getResources().getStringArray(R.array.months_array), true);
    }

    private void setProgressiveUnitRecyclerViewAdapter() {
        this.progressiveUnitBinding.recyclerViewProgressiveUnit.setHasFixedSize(true);
        this.progressiveUnitBinding.recyclerViewProgressiveUnit.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.progressiveUnitBinding.recyclerViewProgressiveUnit.setAdapter(this.progressiveBillUnitRvAdapter);
    }

    private void maketimeOrSeasonList(String[] strArr, boolean z) {
        for (String timeOrSeasonName : strArr) {
            TimeOrMonthModel timeOrMonthModel = new TimeOrMonthModel();
            timeOrMonthModel.setTimeOrSeasonName(timeOrSeasonName);
            if (z) {
                this.seasonList.add(timeOrMonthModel);
            } else {
                this.timeHoursList.add(timeOrMonthModel);
            }
        }
    }

    /* access modifiers changed from: private */
    public void getEnergyConsumptionSettingsData() {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            this.mViewModel.getEnergyCostSettingsData(FamilyGroupList.getCurrentFamily().familyId).observeWithCachedTrigger(getViewLifecycleOwner(), new BudgetSetUpFragment$$ExternalSyntheticLambda1(this));
        } else {
            showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
        }
        if (this.budgetSetUpReqBody == null) {
            this.budgetSetUpReqBody = new EnergyCostSettingsData();
        }
    }

    /* renamed from: lambda$getEnergyConsumptionSettingsData$0$com-jch-racWiFi-energyConsumption-view-BudgetSetUpFragment */
    public /* synthetic */ void mo28939x44368ff9(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse == null) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.unableToReachServer()) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.isApiSuccessful()) {
            getEnergySettingsResponseHandler((EnergyCostSettingsData) genericResponse.getBodyOfType(EnergyCostSettingsData.class));
        } else {
            int code = genericResponse.getResponse().code();
            if (code == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        BudgetSetUpFragment.this.getEnergyConsumptionSettingsData();
                    }
                }, false);
            } else if (code != 404) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else {
                GenericResponse.GenericErrorBody genericErrorBody = (GenericResponse.GenericErrorBody) genericResponse.getErrorBodyOfType(GenericResponse.GenericErrorBody.class);
                if (genericErrorBody != null) {
                    showErrorPopUp(getString(this.mViewModel.getErrorMessageStringId(genericErrorBody.code)));
                }
            }
        }
    }

    private void getEnergySettingsResponseHandler(EnergyCostSettingsData energyCostSettingsData) {
        if (energyCostSettingsData != null) {
            this.energyConsumptionDataMain.setEnergyCostSettingsData(energyCostSettingsData);
            try {
                EnergyCostSettingsData energyCostSettingsData2 = (EnergyCostSettingsData) energyCostSettingsData.clone();
                this.budgetSetUpReqBody = energyCostSettingsData2;
                energyCostSettingsData2.setCurrencySymbol(this.energyConsumptionDataMain.getCurrencySymbol());
                this.budgetSetUpReqBody.setCurrencyName(this.energyConsumptionDataMain.getCurrencyName());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            String currency = energyCostSettingsData.getCurrency();
            if (!isNullOrEmpty(currency)) {
                this.mBinding.textViewCurrencyCode.setText(currency);
            }
            int i = C18064.f436xf4d3da6e[BillingStructureType.valueOf(energyCostSettingsData.getBillingStructureType()).ordinal()];
            float f = 1.0f;
            if (i == 1) {
                this.timeOrSeasonListMain.clear();
                this.timeOrSeasonListMain.addAll(this.timeHoursList);
                this.mBinding.textViewSelectedRate.setText(this.billingStructureArray[1]);
                showLastSelectedTimeOrSeason(false, energyCostSettingsData.getPeakUnitPrice(), energyCostSettingsData.getNonPeakUnitPrice(), energyCostSettingsData.getPeakHours());
                this.selectTimeOrSeasonBillingRvAdapter.notifyDataSetChanged();
                this.hoursAndMonthBinding.textViewTapToSelection.setText(R.string.energyConsumption_lbl_tapToSelectPeakHour);
                setUnitPriceLayout(0, 8, 8);
            } else if (i == 2) {
                this.timeOrSeasonListMain.clear();
                this.timeOrSeasonListMain.addAll(this.seasonList);
                this.mBinding.textViewSelectedRate.setText(this.billingStructureArray[2]);
                showLastSelectedTimeOrSeason(true, energyCostSettingsData.getPeakUnitPrice(), energyCostSettingsData.getNonPeakUnitPrice(), energyCostSettingsData.getPeakMonths());
                this.selectTimeOrSeasonBillingRvAdapter.notifyDataSetChanged();
                this.hoursAndMonthBinding.textViewTapToSelection.setText(R.string.energyConsumption_lbl_tapToSelectPeakMonths);
                setUnitPriceLayout(0, 8, 8);
            } else if (i != 3) {
                this.mBinding.textViewSelectedRate.setText(this.billingStructureArray[0]);
                setUnitPriceLayout(8, 8, 0);
                this.simpleFlatRateBinding.unitPriceFlatRateEditText.setText(String.valueOf(energyCostSettingsData.getFlateUnitPrice()));
            } else {
                this.mBinding.textViewSelectedRate.setText(this.billingStructureArray[3]);
                setUnitPriceLayout(8, 0, 8);
                this.progressiveUnitBinding.editTextFixedCharges.setText(String.valueOf(energyCostSettingsData.getFixedCharges()));
                this.progressiveUnitBillList.clear();
                this.progressiveUnitBillList.addAll(energyCostSettingsData.getPeakOrUnitRange());
                this.progressiveBillUnitRvAdapter.notifyDataSetChanged();
                if (this.progressiveUnitBillList.size() > 1) {
                    enableOrDisableToAddOrRemoveIcon(true, 1.0f, true, 1.0f);
                }
            }
            setCurrencyCode();
            this.mEnergyBudgetBinding.enterBudgetEditText.setText(String.valueOf(energyCostSettingsData.getBudget()));
            this.mEnergyBudgetBinding.switchEnergyBudget.setChecked(energyCostSettingsData.isMonthlyBudget());
            this.mEnergyBudgetBinding.enterBudgetEditText.setEnabled(energyCostSettingsData.isMonthlyBudget());
            this.mEnergyBudgetBinding.clearBudgetIcon.setEnabled(energyCostSettingsData.isMonthlyBudget());
            ImageButton imageButton = this.mEnergyBudgetBinding.clearBudgetIcon;
            if (!energyCostSettingsData.isMonthlyBudget()) {
                f = 0.4f;
            }
            imageButton.setAlpha(f);
            return;
        }
        this.budgetSetUpReqBody.setBillingStructureType(BillingStructureType.FLAT_RATE.name());
        showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
    }

    /* renamed from: com.jch.racWiFi.energyConsumption.view.BudgetSetUpFragment$4 */
    static /* synthetic */ class C18064 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$energyConsumption$utility$BillingStructureType */
        static final /* synthetic */ int[] f436xf4d3da6e;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.jch.racWiFi.energyConsumption.utility.BillingStructureType[] r0 = com.jch.racWiFi.energyConsumption.utility.BillingStructureType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f436xf4d3da6e = r0
                com.jch.racWiFi.energyConsumption.utility.BillingStructureType r1 = com.jch.racWiFi.energyConsumption.utility.BillingStructureType.TIME_BASED_PEAK_RATES     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f436xf4d3da6e     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.energyConsumption.utility.BillingStructureType r1 = com.jch.racWiFi.energyConsumption.utility.BillingStructureType.SEASON_BASED_PEAK_RATES     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f436xf4d3da6e     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.energyConsumption.utility.BillingStructureType r1 = com.jch.racWiFi.energyConsumption.utility.BillingStructureType.PROGRESSIVE_ENERGY_RATES     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f436xf4d3da6e     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.energyConsumption.utility.BillingStructureType r1 = com.jch.racWiFi.energyConsumption.utility.BillingStructureType.FLAT_RATE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.energyConsumption.view.BudgetSetUpFragment.C18064.<clinit>():void");
        }
    }

    private void showLastSelectedTimeOrSeason(boolean z, double d, double d2, ArrayList<String> arrayList) {
        this.hoursAndMonthBinding.editTextPeakHrUnitPrice.setText(String.valueOf(d));
        this.hoursAndMonthBinding.editTextOffPeakHrUnitPrice.setText(String.valueOf(d2));
        if (arrayList != null && arrayList.size() != 0) {
            for (int i = 0; i < this.timeOrSeasonListMain.size(); i++) {
                if (z) {
                    if (!arrayList.contains(SeasonBasedType.values()[i].name())) {
                    }
                } else if (!arrayList.contains(this.timeHoursList.get(i).getTimeOrSeasonName())) {
                }
                this.timeOrSeasonListMain.get(i).setSelected(true);
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                this.mFragmentToActivityCallback.getNavController().navigateUp();
                return;
            case R.id.clearBudgetIcon:
                this.mEnergyBudgetBinding.enterBudgetEditText.getText().clear();
                return;
            case R.id.image_button_add:
                addProgressiveBillUnit();
                enableOrDisableSaveButton();
                return;
            case R.id.image_button_minus:
                if (this.progressiveUnitBillList.size() > 1) {
                    ArrayList<ProgressiveUnitModel> arrayList = this.progressiveUnitBillList;
                    arrayList.remove(arrayList.size() - 1);
                    this.progressiveBillUnitRvAdapter.notifyDataSetChanged();
                    enableOrDisableToAddOrRemoveIcon(true, 1.0f, true, 1.0f);
                }
                if (this.progressiveUnitBillList.size() == 1) {
                    enableOrDisableToAddOrRemoveIcon(true, 1.0f, false, 0.4f);
                }
                enableOrDisableSaveButton();
                return;
            case R.id.layout_select_billing_structure:
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_budgetSetUpFragment_to_selectBillingStructureFragment);
                return;
            case R.id.layout_select_currency:
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_budgetSetUpFragment_to_selectCurrencyDialogFragment);
                return;
            case R.id.text_view_save:
                saveChanges();
                return;
            default:
                return;
        }
    }

    private void saveChanges() {
        if (isNullOrEmpty(this.budgetSetUpReqBody.getCurrency())) {
            showErrorPopUp(getString(R.string.energyConsumption_alert_selectCurrency));
            return;
        }
        int i = C18064.f436xf4d3da6e[BillingStructureType.valueOf(this.budgetSetUpReqBody.getBillingStructureType()).ordinal()];
        if (i == 1) {
            createHrOrMonthsReqBody(false);
            if (this.budgetSetUpReqBody.getPeakHours() == null || this.budgetSetUpReqBody.getPeakHours().isEmpty()) {
                return;
            }
        } else if (i == 2) {
            createHrOrMonthsReqBody(true);
            if (this.budgetSetUpReqBody.getPeakMonths() == null || this.budgetSetUpReqBody.getPeakMonths().isEmpty()) {
                return;
            }
        } else if (i == 3) {
            Editable text = this.progressiveUnitBinding.editTextFixedCharges.getText();
            if (text == null || isNullOrEmpty(text.toString())) {
                showErrorPopUp(getString(R.string.energyConsumption_alert_enterFixedCharges));
                return;
            }
            this.budgetSetUpReqBody.setFixedCharges(Double.parseDouble(text.toString()));
            ArrayList<ProgressiveUnitModel> arrayList = this.progressiveUnitBillList;
            if (arrayList != null && arrayList.size() > 0) {
                int size = this.progressiveUnitBillList.size() - 1;
                View childAt = this.progressiveUnitBinding.recyclerViewProgressiveUnit.getChildAt(size);
                CharSequence text2 = ((TextView) childAt.findViewById(R.id.text_view_from_unit_price)).getText();
                Objects.requireNonNull(text2);
                CharSequence charSequence = text2;
                String charSequence2 = text2.toString();
                Editable text3 = ((EditText) childAt.findViewById(R.id.edit_text_to_unit)).getText();
                Objects.requireNonNull(text3);
                Editable editable = text3;
                String obj = text3.toString();
                Editable text4 = ((EditText) childAt.findViewById(R.id.edit_text_unit_price)).getText();
                Objects.requireNonNull(text4);
                Editable editable2 = text4;
                String obj2 = text4.toString();
                if (isNullOrEmpty(obj) || isNullOrEmpty(obj2)) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_enterUnitPrice));
                    return;
                }
                double parseDouble = Double.parseDouble(obj);
                if (obj.isEmpty() || parseDouble == C1030Utils.DOUBLE_EPSILON) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_toUnit));
                    return;
                }
                int i2 = (Double.parseDouble(charSequence2) > parseDouble ? 1 : (Double.parseDouble(charSequence2) == parseDouble ? 0 : -1));
                if (i2 == 0) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_fromToUnitSame));
                    return;
                } else if (i2 > 0) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_fromUnitMoreThanToUnit));
                    return;
                } else if (obj2.isEmpty() || Double.parseDouble(obj2) == C1030Utils.DOUBLE_EPSILON) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_enterUnitPrice));
                    return;
                } else {
                    this.progressiveUnitBillList.get(size).setTo((double) ((int) Double.parseDouble(obj)));
                    this.progressiveUnitBillList.get(size).setUnitPrice(Double.parseDouble(obj2));
                    this.budgetSetUpReqBody.setPeakOrUnitRange(this.progressiveUnitBillList);
                }
            }
        } else if (i == 4) {
            Editable text5 = this.simpleFlatRateBinding.unitPriceFlatRateEditText.getText();
            if (isNullOrEmpty(text5.toString())) {
                showErrorPopUp(getString(R.string.energyConsumption_alert_enterUnitPrice));
                return;
            }
            this.budgetSetUpReqBody.setFlateUnitPrice(Double.parseDouble(text5.toString()));
        }
        boolean isChecked = this.mEnergyBudgetBinding.switchEnergyBudget.isChecked();
        String obj3 = this.mEnergyBudgetBinding.enterBudgetEditText.getText().toString();
        if (isChecked) {
            if (isNullOrEmpty(obj3)) {
                showErrorPopUp(getString(R.string.energyConsumption_alert_enterBudget));
                return;
            } else {
                this.budgetSetUpReqBody.setBudget(Double.parseDouble(obj3));
            }
        }
        saveEnergyConsumptionSettingsDataOnServer();
    }

    /* access modifiers changed from: private */
    public void saveEnergyConsumptionSettingsDataOnServer() {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            this.budgetSetUpReqBody.setFamilyId(FamilyGroupList.getCurrentFamily().familyId);
            this.mViewModel.saveEnergyCostSettingsData(this.budgetSetUpReqBody).observeWithCachedTrigger(getViewLifecycleOwner(), new BudgetSetUpFragment$$ExternalSyntheticLambda2(this));
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    /* renamed from: lambda$saveEnergyConsumptionSettingsDataOnServer$1$com-jch-racWiFi-energyConsumption-view-BudgetSetUpFragment */
    public /* synthetic */ void mo28942xeef87e77(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse == null) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.unableToReachServer()) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.isApiSuccessful()) {
            this.energyConsumptionDataMain.setEnergyCostSettingsData(this.budgetSetUpReqBody);
            this.energyConsumptionDataMain.setToUpdateGraphData(true);
            this.energyConsumptionDataMain.setToUpdateMainScreenData(true);
            this.energyConsumptionDataMain.setCurrencyName(this.budgetSetUpReqBody.getCurrencyName());
            this.energyConsumptionDataMain.setCurrencySymbol(this.budgetSetUpReqBody.getCurrencySymbol());
            this.energyConsumptionDataMain.setCurrencyCode(this.budgetSetUpReqBody.getCurrency());
            SetBudget setBudget = this.mViewModel.getResponseDataMainInstanse().getSetBudget();
            setBudget.setBudgetAmount(this.budgetSetUpReqBody.isMonthlyBudget() ? this.budgetSetUpReqBody.getBudget() : C1030Utils.DOUBLE_EPSILON);
            this.energyConsumptionDataMain.setSetBudget(setBudget);
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else {
            int code = genericResponse.getResponse().code();
            if (code == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        BudgetSetUpFragment.this.saveEnergyConsumptionSettingsDataOnServer();
                    }
                }, false);
            } else if (code != 404) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else {
                GenericResponse.GenericErrorBody genericErrorBody = (GenericResponse.GenericErrorBody) genericResponse.getErrorBodyOfType(GenericResponse.GenericErrorBody.class);
                if (genericErrorBody != null) {
                    showErrorPopUp(getString(this.mViewModel.getErrorMessageStringId(genericErrorBody.code)));
                }
            }
        }
    }

    private ArrayList<String> getSelectedPeakHourOrMonth(boolean z) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < this.timeOrSeasonListMain.size(); i++) {
            TimeOrMonthModel timeOrMonthModel = this.timeOrSeasonListMain.get(i);
            if (timeOrMonthModel.isSelected()) {
                arrayList.add(z ? SeasonBasedType.values()[i].name() : timeOrMonthModel.getTimeOrSeasonName());
            }
        }
        return arrayList;
    }

    private void createHrOrMonthsReqBody(boolean z) {
        if (this.budgetSetUpReqBody.getPeakHours() != null && !this.budgetSetUpReqBody.getPeakHours().isEmpty()) {
            this.budgetSetUpReqBody.getPeakHours().clear();
        }
        if (this.budgetSetUpReqBody.getPeakMonths() != null && !this.budgetSetUpReqBody.getPeakMonths().isEmpty()) {
            this.budgetSetUpReqBody.getPeakMonths().clear();
        }
        ArrayList<String> selectedPeakHourOrMonth = getSelectedPeakHourOrMonth(z);
        if (selectedPeakHourOrMonth.isEmpty()) {
            showErrorPopUp(getString(z ? R.string.energyConsumption_lbl_tapToSelectPeakMonths : R.string.energyConsumption_lbl_tapToSelectPeakHour));
            return;
        }
        Editable text = this.hoursAndMonthBinding.editTextPeakHrUnitPrice.getText();
        Editable text2 = this.hoursAndMonthBinding.editTextOffPeakHrUnitPrice.getText();
        if (isNullOrEmpty(text.toString())) {
            showErrorPopUp(getString(R.string.energyConsumption_alert_enterPeakHourUnitPrice));
        } else if (isNullOrEmpty(text2.toString())) {
            showErrorPopUp(getString(R.string.energyConsumption_alert_enterOffPeakHourUnitPrice));
        } else {
            this.budgetSetUpReqBody.setPeakUnitPrice(Double.parseDouble(text.toString()));
            this.budgetSetUpReqBody.setNonPeakUnitPrice(Double.parseDouble(text2.toString()));
            if (z) {
                this.budgetSetUpReqBody.setPeakMonths(selectedPeakHourOrMonth);
            } else {
                this.budgetSetUpReqBody.setPeakHours(selectedPeakHourOrMonth);
            }
        }
    }

    private void addProgressiveBillUnit() {
        int size = this.progressiveUnitBillList.size();
        if (size <= 10) {
            ProgressiveUnitModel progressiveUnitModel = new ProgressiveUnitModel();
            String currency = this.budgetSetUpReqBody.getCurrency();
            if (currency != null && !currency.isEmpty()) {
                progressiveUnitModel.setCurrencyCode(currency);
            }
            if (size > 0) {
                int i = size - 1;
                View childAt = this.progressiveUnitBinding.recyclerViewProgressiveUnit.getChildAt(i);
                CharSequence text = ((TextView) childAt.findViewById(R.id.text_view_from_unit_price)).getText();
                Objects.requireNonNull(text);
                CharSequence charSequence = text;
                double parseDouble = Double.parseDouble(text.toString());
                Editable text2 = ((EditText) childAt.findViewById(R.id.edit_text_to_unit)).getText();
                Objects.requireNonNull(text2);
                Editable editable = text2;
                String obj = text2.toString();
                Editable text3 = ((EditText) childAt.findViewById(R.id.edit_text_unit_price)).getText();
                Objects.requireNonNull(text3);
                Editable editable2 = text3;
                String obj2 = text3.toString();
                double parseDouble2 = Double.parseDouble(obj);
                if (obj.isEmpty() || parseDouble2 == C1030Utils.DOUBLE_EPSILON) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_toUnit));
                    return;
                }
                int i2 = (parseDouble > parseDouble2 ? 1 : (parseDouble == parseDouble2 ? 0 : -1));
                if (i2 == 0) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_fromToUnitSame));
                    return;
                } else if (i2 > 0) {
                    showErrorPopUp(getString(R.string.energyConsumption_alert_fromUnitMoreThanToUnit));
                    return;
                } else {
                    this.progressiveUnitBillList.get(i).setFrom(parseDouble);
                    this.progressiveUnitBillList.get(i).setTo(parseDouble2);
                    if (obj2.isEmpty() || Double.parseDouble(obj2) == C1030Utils.DOUBLE_EPSILON) {
                        showErrorPopUp(getString(R.string.energyConsumption_alert_enterUnitPrice));
                        return;
                    } else {
                        this.progressiveUnitBillList.get(i).setUnitPrice(Double.parseDouble(obj2));
                        progressiveUnitModel.setFrom(this.progressiveUnitBillList.get(i).getTo() + 1.0d);
                    }
                }
            }
            this.progressiveUnitBillList.add(progressiveUnitModel);
            this.progressiveBillUnitRvAdapter.notifyDataSetChanged();
            enableOrDisableToAddOrRemoveIcon(true, 1.0f, true, 1.0f);
        }
        if (this.progressiveUnitBillList.size() == 10) {
            enableOrDisableToAddOrRemoveIcon(false, 0.4f, true, 1.0f);
        }
    }

    private void enableOrDisableToAddOrRemoveIcon(boolean z, float f, boolean z2, float f2) {
        this.progressiveUnitBinding.imageButtonAdd.setAlpha(f);
        this.progressiveUnitBinding.imageButtonAdd.setEnabled(z);
        this.progressiveUnitBinding.imageButtonMinus.setAlpha(f2);
        this.progressiveUnitBinding.imageButtonMinus.setEnabled(z2);
    }

    public void onStart() {
        super.onStart();
        this.mViewModel.getCurrencyModelLiveData().observeSingleEvent(getViewLifecycleOwner(), this.currencyModelObserver);
        this.mViewModel.getBillingTypeLiveData().observeSingleEvent(getViewLifecycleOwner(), this.billingStructureTypeObserver);
    }

    public void onStop() {
        super.onStop();
        this.mViewModel.getCurrencyModelLiveData().removeObserver(this.currencyModelObserver);
        this.mViewModel.getBillingTypeLiveData().removeObserver(this.billingStructureTypeObserver);
    }

    /* renamed from: lambda$new$2$com-jch-racWiFi-energyConsumption-view-BudgetSetUpFragment */
    public /* synthetic */ void mo28940xcac2147b(CurrencyModel currencyModel) {
        if (currencyModel != null) {
            String code = currencyModel.getCode();
            String name = currencyModel.getName();
            String symbol = currencyModel.getSymbol();
            this.budgetSetUpReqBody.setCurrency(code);
            this.budgetSetUpReqBody.setCurrencyName(name);
            this.budgetSetUpReqBody.setCurrencySymbol(symbol);
            setCurrencyCode();
            if (!isNullOrEmpty(code)) {
                this.mBinding.textViewCurrencyCode.setText(code);
            }
            if (!isNullOrEmpty(name)) {
                this.mBinding.textViewCurrencyName.setText(name);
            }
            enableOrDisableSaveButton();
        }
    }

    /* renamed from: lambda$new$3$com-jch-racWiFi-energyConsumption-view-BudgetSetUpFragment */
    public /* synthetic */ void mo28941xd0c5dfda(Integer num) {
        this.mBinding.textViewSelectedRate.setText(this.billingStructureArray[num.intValue()]);
        BillingStructureType billingStructureType = BillingStructureType.values()[num.intValue()];
        if (billingStructureType != null) {
            this.budgetSetUpReqBody.setBillingStructureType(billingStructureType.name());
            int i = C18064.f436xf4d3da6e[billingStructureType.ordinal()];
            if (i == 1) {
                this.timeOrSeasonListMain.clear();
                this.timeOrSeasonListMain.addAll(this.timeHoursList);
                this.selectTimeOrSeasonBillingRvAdapter.notifyDataSetChanged();
                this.hoursAndMonthBinding.textViewPeakHours.setText(getString(R.string.energyConsumption_lbl_peakHours));
                this.hoursAndMonthBinding.textViewOffPeakHours.setText(getString(R.string.energyConsumption_lbl_offPeakHours));
                this.hoursAndMonthBinding.textViewTapToSelection.setText(R.string.energyConsumption_lbl_tapToSelectPeakHour);
                setUnitPriceLayout(0, 8, 8);
            } else if (i == 2) {
                this.timeOrSeasonListMain.clear();
                this.timeOrSeasonListMain.addAll(this.seasonList);
                this.selectTimeOrSeasonBillingRvAdapter.notifyDataSetChanged();
                this.hoursAndMonthBinding.textViewPeakHours.setText(getString(R.string.energyConsumption_lbl_peakMonths));
                this.hoursAndMonthBinding.textViewOffPeakHours.setText(getString(R.string.energyConsumption_lbl_offPeakMonths));
                this.hoursAndMonthBinding.textViewTapToSelection.setText(R.string.energyConsumption_lbl_tapToSelectPeakMonths);
                setUnitPriceLayout(0, 8, 8);
            } else if (i != 3) {
                setUnitPriceLayout(8, 8, 0);
            } else {
                if (this.progressiveUnitBillList.size() == 0) {
                    addProgressiveBillUnit();
                }
                setUnitPriceLayout(8, 0, 8);
            }
        }
        enableOrDisableSaveButton();
    }

    private void setCurrencyCode() {
        String currency = this.budgetSetUpReqBody.getCurrency();
        if (currency != null && !currency.isEmpty()) {
            this.mEnergyBudgetBinding.textViewCurrencyCode.setText(currency);
            this.progressiveUnitBinding.textViewCurrencyCode.setText(currency);
            this.hoursAndMonthBinding.textViewUnitPriceCurrencyKwh.setText(getString(R.string.energyConsumption_lbl_unitPriceCurrencyKwh, currency));
            if (this.progressiveUnitBillList.size() > 0) {
                for (int i = 0; i < this.progressiveUnitBillList.size(); i++) {
                    this.progressiveUnitBillList.get(i).setCurrencyCode(currency);
                    this.progressiveBillUnitRvAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void setUnitPriceLayout(int i, int i2, int i3) {
        this.mBinding.layoutHoursAndMonthUnit.setVisibility(i);
        this.mBinding.layoutProgressiveUnit.setVisibility(i2);
        this.mBinding.layoutSimpleFlatUnit.setVisibility(i3);
    }

    public void onCheckedChanged(SwitchButton switchButton, boolean z) {
        this.budgetSetUpReqBody.setMonthlyBudget(z);
        this.mEnergyBudgetBinding.enterBudgetEditText.setEnabled(z);
        this.mEnergyBudgetBinding.clearBudgetIcon.setEnabled(z);
        this.mEnergyBudgetBinding.clearBudgetIcon.setAlpha(z ? 1.0f : 0.4f);
        enableOrDisableSaveButton();
    }

    private TextWatcher getTextWatcher(final boolean z) {
        return new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (z) {
                    BudgetSetUpFragment.this.mEnergyBudgetBinding.clearBudgetIcon.setEnabled(editable.length() > 0);
                    BudgetSetUpFragment.this.mEnergyBudgetBinding.clearBudgetIcon.setAlpha(editable.length() > 0 ? 1.0f : 0.4f);
                }
                BudgetSetUpFragment.this.enableOrDisableSaveButton();
            }
        };
    }

    /* access modifiers changed from: private */
    public void enableOrDisableSaveButton() {
        double d;
        double d2;
        EnergyCostSettingsData energyCostSettingsData = this.mViewModel.getResponseDataMainInstanse().getEnergyCostSettingsData();
        if (energyCostSettingsData == null) {
            return;
        }
        if (!energyCostSettingsData.getCurrency().equals(this.budgetSetUpReqBody.getCurrency())) {
            changeSaveTextColor(true, R.color.cost_bar_selected_color);
            return;
        }
        BillingStructureType valueOf = BillingStructureType.valueOf(this.budgetSetUpReqBody.getBillingStructureType());
        if (valueOf != BillingStructureType.valueOf(energyCostSettingsData.getBillingStructureType())) {
            changeSaveTextColor(true, R.color.cost_bar_selected_color);
            return;
        }
        int i = C18064.f436xf4d3da6e[valueOf.ordinal()];
        String str = null;
        double d3 = C1030Utils.DOUBLE_EPSILON;
        if (i == 1 || i == 2) {
            String obj = this.hoursAndMonthBinding.editTextPeakHrUnitPrice.getText() != null ? this.hoursAndMonthBinding.editTextPeakHrUnitPrice.getText().toString() : null;
            if ((isNullOrEmpty(obj) ? 0.0d : Double.parseDouble(obj)) != energyCostSettingsData.getPeakUnitPrice()) {
                changeSaveTextColor(true, R.color.cost_bar_selected_color);
                return;
            }
            if (this.hoursAndMonthBinding.editTextOffPeakHrUnitPrice.getText() != null) {
                str = this.hoursAndMonthBinding.editTextOffPeakHrUnitPrice.getText().toString();
            }
            if ((isNullOrEmpty(str) ? 0.0d : Double.parseDouble(str)) != energyCostSettingsData.getNonPeakUnitPrice()) {
                changeSaveTextColor(true, R.color.cost_bar_selected_color);
                return;
            }
        } else if (i == 3) {
            if (this.progressiveUnitBinding.editTextFixedCharges.getText() != null) {
                str = this.progressiveUnitBinding.editTextFixedCharges.getText().toString();
            }
            if ((isNullOrEmpty(str) ? 0.0d : Double.parseDouble(str)) != energyCostSettingsData.getFixedCharges()) {
                changeSaveTextColor(true, R.color.cost_bar_selected_color);
                return;
            }
            int size = energyCostSettingsData.getPeakOrUnitRange().size();
            int size2 = this.progressiveUnitBillList.size();
            int childCount = this.progressiveUnitBinding.recyclerViewProgressiveUnit.getChildCount();
            if (size != size2) {
                changeSaveTextColor(true, R.color.cost_bar_selected_color);
                return;
            }
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.progressiveUnitBinding.recyclerViewProgressiveUnit.getChildAt(i2);
                Editable text = ((EditText) childAt.findViewById(R.id.edit_text_to_unit)).getText();
                Objects.requireNonNull(text);
                Editable editable = text;
                String obj2 = text.toString();
                Editable text2 = ((EditText) childAt.findViewById(R.id.edit_text_unit_price)).getText();
                Objects.requireNonNull(text2);
                Editable editable2 = text2;
                String obj3 = text2.toString();
                double parseDouble = isNullOrEmpty(obj2) ? 0.0d : Double.parseDouble(obj2);
                double parseDouble2 = isNullOrEmpty(obj3) ? 0.0d : Double.parseDouble(obj3);
                if (energyCostSettingsData.getPeakOrUnitRange().size() > i2) {
                    ProgressiveUnitModel progressiveUnitModel = energyCostSettingsData.getPeakOrUnitRange().get(i2);
                    d2 = progressiveUnitModel.getTo();
                    d = progressiveUnitModel.getUnitPrice();
                } else {
                    d2 = 0.0d;
                    d = 0.0d;
                }
                if (parseDouble == d2 && parseDouble2 == d) {
                    i2++;
                } else {
                    changeSaveTextColor(true, R.color.cost_bar_selected_color);
                    return;
                }
            }
        } else if (i == 4) {
            String obj4 = this.simpleFlatRateBinding.unitPriceFlatRateEditText.getText().toString();
            if ((isNullOrEmpty(obj4) ? 0.0d : Double.parseDouble(obj4)) != energyCostSettingsData.getFlateUnitPrice()) {
                changeSaveTextColor(true, R.color.cost_bar_selected_color);
                return;
            }
        }
        if (Boolean.compare(this.budgetSetUpReqBody.isMonthlyBudget(), energyCostSettingsData.isMonthlyBudget()) != 0) {
            changeSaveTextColor(true, R.color.cost_bar_selected_color);
            return;
        }
        double budget = this.budgetSetUpReqBody.getBudget();
        String obj5 = this.mEnergyBudgetBinding.enterBudgetEditText.getText().toString();
        try {
            if (!isNullOrEmpty(obj5)) {
                d3 = Double.parseDouble(obj5);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (budget != d3) {
            changeSaveTextColor(true, R.color.cost_bar_selected_color);
        } else {
            changeSaveTextColor(false, R.color.color_848484);
        }
    }

    public void itemClick(int i, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<String> arrayList2;
        EnergyCostSettingsData energyCostSettingsData = this.mViewModel.getResponseDataMainInstanse().getEnergyCostSettingsData();
        BillingStructureType valueOf = BillingStructureType.valueOf(this.budgetSetUpReqBody.getBillingStructureType());
        if (valueOf != BillingStructureType.valueOf(energyCostSettingsData.getBillingStructureType())) {
            this.mBinding.textViewSave.setEnabled(true);
            this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(requireContext(), R.color.cost_bar_selected_color));
            changeSaveTextColor(true, R.color.cost_bar_selected_color);
            return;
        }
        if (BillingStructureType.SEASON_BASED_PEAK_RATES == valueOf) {
            arrayList2 = energyCostSettingsData.getPeakMonths();
            arrayList = getSelectedPeakHourOrMonth(true);
        } else {
            arrayList2 = energyCostSettingsData.getPeakHours();
            arrayList = getSelectedPeakHourOrMonth(false);
        }
        boolean z2 = !arrayList2.equals(arrayList);
        if (z2) {
            changeSaveTextColor(z2, R.color.cost_bar_selected_color);
        } else {
            enableOrDisableSaveButton();
        }
    }

    private void changeSaveTextColor(boolean z, int i) {
        this.mBinding.textViewSave.setEnabled(z);
        this.mBinding.textViewSave.setColorFilter(ContextCompat.getColor(requireContext(), i));
    }

    public void valueChange() {
        enableOrDisableSaveButton();
    }
}
