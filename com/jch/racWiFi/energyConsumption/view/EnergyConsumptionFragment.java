package com.jch.racWiFi.energyConsumption.view;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.FragmentEnergyConsumptionBinding;
import com.jch.racWiFi.energyConsumption.adapter.EnergyConsumptionAcListingAdapter;
import com.jch.racWiFi.energyConsumption.callback.ItemClickListener;
import com.jch.racWiFi.energyConsumption.model.local.CurrencyModel;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData;
import com.jch.racWiFi.energyConsumption.model.local.SetBudget;
import com.jch.racWiFi.energyConsumption.model.request.AllRacEnergyUsageReqBody;
import com.jch.racWiFi.energyConsumption.model.response.AllRacMonthlyData;
import com.jch.racWiFi.energyConsumption.model.response.AllRacTotalCostDataMain;
import com.jch.racWiFi.energyConsumption.model.response.IndivisualRacCostData;
import com.jch.racWiFi.energyConsumption.utility.EnergyConsumptionUtility;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.view.IDUStateAwareFragment;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.tooltip.ClosePolicy;
import com.jch.racWiFi.tooltip.ToolTipGravity;
import com.jch.racWiFi.tooltip.Tooltip;
import com.jch.racWiFi.tooltip.Typefaces;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;

public class EnergyConsumptionFragment extends IDUStateAwareFragment implements ItemClickListener {
    private EnergyConsumptionDataMain energyConsumptionDataMain;
    private EnergyConsumptionViewModel energyConsumptionViewModel;
    private ArrayList<EnergyCostIndivisualRacData> indivisualRacDataList;
    private boolean isAllRacDisabled;
    private FragmentEnergyConsumptionBinding mBinding;
    private EnergyConsumptionAcListingAdapter mEnergyConsumptionAcListingAdapter;

    static /* synthetic */ void lambda$onViewCreated$4(View view) {
    }

    static /* synthetic */ boolean lambda$showDialog$7(Dialog dialog, View view) {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EnergyConsumptionViewModel energyConsumptionViewModel2 = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
        this.energyConsumptionViewModel = energyConsumptionViewModel2;
        this.energyConsumptionDataMain = energyConsumptionViewModel2.getResponseDataMainInstanse();
        this.indivisualRacDataList = this.energyConsumptionViewModel.getIndivisualRacListInstanse();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mBinding == null) {
            this.mBinding = (FragmentEnergyConsumptionBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_energy_consumption, viewGroup, false);
            setRecyclerViewAdapter();
            getAllRacTotalConsumedEnergy();
        }
        logEvent(Screens.SCREENS.name(), 9);
        logEvents(Events.ENERGY_COST_FREQUENCY.name(), 0);
        return this.mBinding.getRoot();
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                EnergyConsumptionFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
            }
        });
    }

    public void onResume() {
        super.onResume();
        ViewUtils.setTextViewDrawableColor(this.mBinding.totalEnergryTextView, (int) R.color.rac_name_oofline_color);
        this.iDrawerMenuFunctions.getMenuDrawer().setDrawerLockMode(0);
        updateCurrencyAndBudget();
    }

    /* access modifiers changed from: private */
    public void getAllRacTotalConsumedEnergy() {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            this.mBinding.energyCostRacsContentLayout.setVisibility(8);
            showPleaseWaitDialog();
            AllRacEnergyUsageReqBody allRacEnergyUsageReqBody = new AllRacEnergyUsageReqBody();
            allRacEnergyUsageReqBody.setFrom(EnergyConsumptionUtility.getStartDayOfMonth());
            allRacEnergyUsageReqBody.setTo(EnergyConsumptionUtility.getEndDayOfMonth());
            this.energyConsumptionViewModel.getAllRacTotalEnergyConsumed(FamilyGroupList.getCurrentFamily().familyId, allRacEnergyUsageReqBody).observeWithCachedTrigger(getViewLifecycleOwner(), new EnergyConsumptionFragment$$ExternalSyntheticLambda6(this));
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    /* renamed from: lambda$getAllRacTotalConsumedEnergy$0$com-jch-racWiFi-energyConsumption-view-EnergyConsumptionFragment */
    public /* synthetic */ void mo28958x30cf7d73(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse == null) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.unableToReachServer()) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.isApiSuccessful()) {
            AllRacTotalCostDataMain allRacTotalCostDataMain = (AllRacTotalCostDataMain) genericResponse.getBodyOfType(AllRacTotalCostDataMain.class);
            if (allRacTotalCostDataMain != null) {
                AllRacMonthlyData allRacsMonthlyData = allRacTotalCostDataMain.getAllRacsMonthlyData();
                ArrayList<IndivisualRacCostData> individualRacsData = allRacTotalCostDataMain.getIndividualRacsData();
                if (getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().isEmpty()) {
                    if (FamilyGroupList.getCurrentFamily().role.f480id == 1) {
                        this.mBinding.addRacImageButton.setImageDrawable(ContextCompat.getDrawable(this.mBinding.getRoot().getContext(), R.drawable.ic_enabled_plus_button));
                    } else {
                        this.mBinding.addRacImageButton.setImageDrawable(ContextCompat.getDrawable(this.mBinding.getRoot().getContext(), R.drawable.ic_disabled_plus_button));
                    }
                    this.mBinding.layoutNoRac.setVisibility(0);
                    this.mBinding.addRacImageButton.setVisibility(0);
                    this.mBinding.allRacListRv.setVisibility(8);
                    return;
                }
                this.mBinding.layoutNoRac.setVisibility(8);
                this.mBinding.addRacImageButton.setVisibility(8);
                this.mBinding.allRacListRv.setVisibility(0);
                if (allRacsMonthlyData != null || !individualRacsData.isEmpty()) {
                    createEnergyConsumptionLocalData(allRacTotalCostDataMain);
                } else {
                    this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_nav_energy_consumption_to_ecHomeFragment);
                }
            }
        } else {
            int code = genericResponse.getResponse().code();
            if (code == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        EnergyConsumptionFragment.this.getAllRacTotalConsumedEnergy();
                    }
                }, false);
            } else if (code != 404) {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            } else {
                GenericResponse.GenericErrorBody genericErrorBody = (GenericResponse.GenericErrorBody) genericResponse.getErrorBodyOfType(GenericResponse.GenericErrorBody.class);
                if (genericErrorBody != null) {
                    showErrorPopUp(getString(this.energyConsumptionViewModel.getErrorMessageStringId(genericErrorBody.code)));
                }
            }
        }
    }

    private void setRecyclerViewAdapter() {
        this.mEnergyConsumptionAcListingAdapter = new EnergyConsumptionAcListingAdapter(requireActivity(), this.indivisualRacDataList, this);
        this.mBinding.allRacListRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.mBinding.allRacListRv.setAdapter(this.mEnergyConsumptionAcListingAdapter);
    }

    private void createEnergyConsumptionLocalData(AllRacTotalCostDataMain allRacTotalCostDataMain) {
        RacModelWiseData racModelWiseDataBasedOnRacTypeId;
        RacModelWiseData.EnableOptions enableOptions;
        AllRacMonthlyData allRacsMonthlyData = allRacTotalCostDataMain.getAllRacsMonthlyData();
        if (allRacsMonthlyData == null || !allRacsMonthlyData.isAllRacDisabled()) {
            this.mBinding.viewDisableLayoutAllAc.setVisibility(8);
        } else {
            this.isAllRacDisabled = true;
            this.mBinding.viewDisableLayoutAllAc.setVisibility(0);
            showDialog();
        }
        if (allRacTotalCostDataMain.getIndividualRacsData() == null || allRacTotalCostDataMain.getIndividualRacsData().size() == 0) {
            setViewsVisibility(0, 8);
            return;
        }
        setViewsVisibility(8, 0);
        SetBudget setBudget = new SetBudget();
        this.energyConsumptionDataMain.setTotalEnergyConsumed(allRacsMonthlyData.getEnergyConsumed());
        this.energyConsumptionDataMain.setTotalCost(allRacsMonthlyData.getCost());
        this.energyConsumptionDataMain.setSetBudget(setBudget);
        this.energyConsumptionDataMain.getSetBudget().setBudgetAmount(allRacsMonthlyData.getBudget());
        this.energyConsumptionDataMain.setCurrencyCode(allRacsMonthlyData.getCurrency());
        setCurrencySymbolAndNameFromCode(allRacsMonthlyData.getCurrency());
        double cost = allRacsMonthlyData.getCost();
        double d = C1030Utils.DOUBLE_EPSILON;
        if (cost > C1030Utils.DOUBLE_EPSILON && allRacsMonthlyData.getEnergyConsumed() > C1030Utils.DOUBLE_EPSILON) {
            d = allRacsMonthlyData.getCost() / allRacsMonthlyData.getEnergyConsumed();
        }
        this.energyConsumptionDataMain.setUnitPrice(d);
        this.indivisualRacDataList.clear();
        ArrayList<IndivisualRacCostData> individualRacsData = allRacTotalCostDataMain.getIndividualRacsData();
        if (individualRacsData == null || individualRacsData.size() <= 0) {
            setViewsVisibility(0, 8);
            return;
        }
        Iterator<IndivisualRacCostData> it = individualRacsData.iterator();
        while (it.hasNext()) {
            IndivisualRacCostData next = it.next();
            EnergyCostIndivisualRacData energyCostIndivisualRacData = new EnergyCostIndivisualRacData();
            energyCostIndivisualRacData.setRacName(next.getRacName());
            String vendorThingId = next.getVendorThingId();
            if (vendorThingId == null) {
                vendorThingId = "";
            }
            energyCostIndivisualRacData.setVendorThingId(vendorThingId);
            energyCostIndivisualRacData.setEnergyConsumed(next.getEnergyConsumed());
            energyCostIndivisualRacData.setCost(next.getCost());
            energyCostIndivisualRacData.setCurrenySymbol(this.energyConsumptionDataMain.getCurrencySymbol());
            energyCostIndivisualRacData.setRacDisabled(next.isRacDisabled());
            energyCostIndivisualRacData.setAllRacDisabled(allRacsMonthlyData.isAllRacDisabled());
            this.indivisualRacDataList.add(energyCostIndivisualRacData);
            DetailedIduModel detailedIduModel = getDetailedIduModel(next.getVendorThingId());
            if (!(detailedIduModel == null || (racModelWiseDataBasedOnRacTypeId = this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel.cloudId)) == null || (enableOptions = racModelWiseDataBasedOnRacTypeId.getEnableOptions()) == null)) {
                enableOptions.getEnableOption0().isPowerConsumption();
            }
        }
        if (this.indivisualRacDataList.isEmpty()) {
            setViewsVisibility(0, 8);
            return;
        }
        setViewsVisibility(8, 0);
        this.energyConsumptionDataMain.setIndivisualRacData(this.indivisualRacDataList);
        setAllAcsData();
        this.mEnergyConsumptionAcListingAdapter.notifyDataSetChanged();
    }

    private void setCurrencySymbolAndNameFromCode(String str) {
        if (this.energyConsumptionViewModel.isNullOrEmpty(str)) {
            Currency instance = Currency.getInstance(Resources.getSystem().getConfiguration().getLocales().get(0));
            this.energyConsumptionDataMain.setCurrencySymbol(instance.getSymbol());
            this.energyConsumptionDataMain.setCurrencyName(instance.getDisplayName());
            return;
        }
        ArrayList<CurrencyModel> currrenciesListFromLocale = this.energyConsumptionViewModel.getCurrrenciesListFromLocale();
        if (currrenciesListFromLocale != null && !currrenciesListFromLocale.isEmpty()) {
            Iterator<CurrencyModel> it = currrenciesListFromLocale.iterator();
            while (it.hasNext()) {
                CurrencyModel next = it.next();
                if (str.equals(next.getCode())) {
                    this.energyConsumptionDataMain.setCurrencySymbol(next.getSymbol());
                    this.energyConsumptionDataMain.setCurrencyName(next.getName());
                    return;
                }
            }
        }
    }

    private void setViewsVisibility(int i, int i2) {
        this.mBinding.layoutNoRac.setVisibility(i);
        this.mBinding.addRacImageButton.setVisibility(i);
        this.mBinding.energyCostRacsContentLayout.setVisibility(i2);
    }

    private DetailedIduModel getDetailedIduModel(String str) {
        Iterator it = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().iterator();
        DetailedIduModel detailedIduModel = null;
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel2 = (DetailedIduModel) it.next();
            if (detailedIduModel2.vendorThingId.equals(str)) {
                detailedIduModel = detailedIduModel2;
            }
        }
        return detailedIduModel;
    }

    private void setAllAcsData() {
        String str = this.energyConsumptionDataMain.getTotalEnergyConsumed() + " " + getString(R.string.energy_unit) + " (" + getString(R.string.energyConsumption_lbl_total) + ")";
        String str2 = this.energyConsumptionDataMain.getCurrencySymbol() + " " + this.energyConsumptionDataMain.getTotalCost() + " / " + (this.energyConsumptionDataMain.getSetBudget().getBudgetAmount() != C1030Utils.DOUBLE_EPSILON ? String.valueOf(this.energyConsumptionDataMain.getSetBudget().getBudgetAmount()) : getString(R.string.energyConsumption_lbl_notSet));
        if (this.isAllRacDisabled) {
            this.mBinding.totalCostTextView.setText(this.energyConsumptionDataMain.getCurrencySymbol() + " - -");
            this.mBinding.totalBugdetTextView.setVisibility(4);
            return;
        }
        this.mBinding.totalCostTextView.setText(str2);
        this.mBinding.totalBugdetTextView.setVisibility(0);
        this.mBinding.totalEnergryTextView.setText(str);
    }

    private void updateCurrencyAndBudget() {
        if (this.energyConsumptionDataMain.isToUpdateMainScreenData()) {
            setAllAcsData();
            Iterator<EnergyCostIndivisualRacData> it = this.indivisualRacDataList.iterator();
            while (it.hasNext()) {
                it.next().setCurrenySymbol(this.energyConsumptionDataMain.getCurrencySymbol());
            }
            this.mEnergyConsumptionAcListingAdapter.notifyDataSetChanged();
            this.energyConsumptionDataMain.setToUpdateMainScreenData(false);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.actionBarLayout.backButton.setVisibility(4);
        this.mBinding.actionBarLayout.imageButtonMenu.setVisibility(0);
        this.mBinding.actionBarLayout.imageButtonMenu.setOnClickListener(new EnergyConsumptionFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.selectAcLayout.helpIcon.setOnClickListener(new EnergyConsumptionFragment$$ExternalSyntheticLambda3(this, view));
        this.mBinding.allAcLayout.setOnClickListener(new EnergyConsumptionFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.viewDisableLayoutAllAc.setOnClickListener(EnergyConsumptionFragment$$ExternalSyntheticLambda5.INSTANCE);
        this.mBinding.addRacImageButton.setOnClickListener(new EnergyConsumptionFragment$$ExternalSyntheticLambda4(this, view));
        this.mBinding.selectAcLayout.ecEditImageView.setOnClickListener(new EnergyConsumptionFragment$$ExternalSyntheticLambda2(this));
        if (FamilyGroupList.getCurrentFamily().role.f480id == 1) {
            this.mBinding.selectAcLayout.ecEditImageView.setVisibility(0);
        } else {
            this.mBinding.selectAcLayout.ecEditImageView.setVisibility(8);
        }
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-energyConsumption-view-EnergyConsumptionFragment */
    public /* synthetic */ void mo28959xf9d37a73(View view) {
        this.iDrawerMenuFunctions.openMenuDrawer();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-energyConsumption-view-EnergyConsumptionFragment */
    public /* synthetic */ void mo28960x64030292(View view, View view2) {
        showToolTip(view2, ToolTipGravity.BOTTOM.name(), Constants.CC.getResource(view.getContext()).getString(R.string.energyConsumption_lbl_info), true, R.style.ToolTipLayoutDefaultStyle1);
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-energyConsumption-view-EnergyConsumptionFragment */
    public /* synthetic */ void mo28961xce328ab1(View view) {
        itemClick(-1, true);
    }

    /* renamed from: lambda$onViewCreated$5$com-jch-racWiFi-energyConsumption-view-EnergyConsumptionFragment */
    public /* synthetic */ void mo28962xa2919aef(View view, View view2) {
        if (FamilyGroupList.getCurrentFamily().role.f480id == 1) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_energyConsumption_to_qrScanFragment);
            return;
        }
        showToolTip(view2, ToolTipGravity.TOP.name(), Constants.CC.getResource(view.getContext()).getString(R.string.ec_you_do_not_have), false, R.style.ToolTipLayoutDefaultStyle);
    }

    /* renamed from: lambda$onViewCreated$6$com-jch-racWiFi-energyConsumption-view-EnergyConsumptionFragment */
    public /* synthetic */ void mo28963xcc1230e(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("from", 2);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_energyConsumption_to_ecSelectACsFragment, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBinding.unbind();
    }

    public void itemClick(int i, boolean z) {
        ArrayList<EnergyCostIndivisualRacData> indivisualRacData = this.energyConsumptionDataMain.getIndivisualRacData();
        if (indivisualRacData != null && indivisualRacData.size() > 0) {
            this.energyConsumptionDataMain.setSelectedItemPosition(i);
            this.energyConsumptionDataMain.setAllRacSelected(z);
            this.energyConsumptionViewModel.setResponseLiveData(this.energyConsumptionDataMain);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_nav_energy_consumption_to_consumedEnergyGraphFragment);
        }
    }

    private void showDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(Constants.CC.getResource(this.mBinding.getRoot().getContext()).getString(R.string.energyConsumption_lbl_energyCost));
        singleChoiceDialog.setMessageString(Constants.CC.getResource(this.mBinding.getRoot().getContext()).getString(R.string.energyConsumption_lbl_all_acs_disabled));
        singleChoiceDialog.setPositiveButton(Constants.CC.getResource(this.mBinding.getRoot().getContext()).getString(R.string.common_btn_ok), EnergyConsumptionFragment$$ExternalSyntheticLambda7.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showToolTip(View view, String str, String str2, boolean z, int i) {
        new Tooltip.Builder(view.getContext()).anchor(view, 0, 0, false).styleId(Integer.valueOf(i)).text((CharSequence) str2).typeface(Typefaces.INSTANCE.get(view.getContext(), "fonts/SansPro-Regular.ttc")).maxWidth((int) (((float) view.getContext().getResources().getDisplayMetrics().widthPixels) * 0.7f)).arrow(z).floatingAnimation(Tooltip.Animation.Companion.getSLOW()).closePolicy(getClosePolicy()).showDuration(com.jch.racWiFi.Constants.VALID_WIFI_CONNECTION_CHECK_TIMEOUT).overlay(false).create().show(view, Tooltip.Gravity.valueOf(str), true);
    }

    private ClosePolicy getClosePolicy() {
        return new ClosePolicy.Builder().inside(true).outside(true).consume(true).build();
    }
}
