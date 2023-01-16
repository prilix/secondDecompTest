package com.jch.racWiFi.energyConsumption.view;

import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.C1030Utils;
import com.google.gson.Gson;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.databinding.FragmentConsumedEnergyGraphBinding;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData;
import com.jch.racWiFi.energyConsumption.model.response.RacWiseEnergyUsageResponseMain;
import com.jch.racWiFi.energyConsumption.presenter.EnergyConsumptionPresenter;
import com.jch.racWiFi.energyConsumption.utility.ColoredLabelXAxisRenderer;
import com.jch.racWiFi.energyConsumption.utility.EnergyConsumptionUtility;
import com.jch.racWiFi.energyConsumption.utility.MyMarkerView;
import com.jch.racWiFi.energyConsumption.utility.QueryFilterType;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import okhttp3.ResponseBody;

public class ConsumedEnergyGraphFragment extends GenericFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    Observer<BarData> barDataChangesObserve = new ConsumedEnergyGraphFragment$$ExternalSyntheticLambda1(this);
    private String filterType;
    public FragmentConsumedEnergyGraphBinding mEnergyGraphFragBinding;
    private EnergyConsumptionPresenter mPresenter;
    private EnergyConsumptionViewModel mViewModel;
    private int monthDateIndex = 0;
    private MyMarkerView myMarkerView;
    private int weekDayStart = 0;
    private int weekend = 6;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewModel = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
        if (this.mEnergyGraphFragBinding == null) {
            this.mEnergyGraphFragBinding = (FragmentConsumedEnergyGraphBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_consumed_energy_graph, viewGroup, false);
            this.iDrawerMenuFunctions.getMenuDrawer().setDrawerLockMode(1);
            EnergyConsumptionDataMain responseDataMainInstanse = this.mViewModel.getResponseDataMainInstanse();
            String currencySymbol = responseDataMainInstanse.getCurrencySymbol();
            String currencyCode = responseDataMainInstanse.getCurrencyCode();
            this.mEnergyGraphFragBinding.unitPriceLebelTextView.setText(getString(R.string.energyConsumption_lbl_budgetPerMonth, currencyCode));
            this.mEnergyGraphFragBinding.costRadioBtn.setText(getResources().getString(R.string.energyConsumption_btn_cost, new Object[]{currencySymbol, currencyCode}));
            setClickListenerOnViews();
            EnergyConsumptionPresenter energyConsumptionPresenter = new EnergyConsumptionPresenter(this);
            this.mPresenter = energyConsumptionPresenter;
            energyConsumptionPresenter.setStartDate(EnergyConsumptionUtility.getDynamicDateofWeek(this.weekDayStart));
            this.mPresenter.setEndDate(EnergyConsumptionUtility.getDynamicDateofWeek(this.weekend));
            this.mPresenter.setCurrentWeekMonthYr(true);
            this.mViewModel.getResponseDataMainInstanse().setToFetchData(true);
            this.filterType = QueryFilterType.WEEKLY.name();
            getEnergyConsumptionDatas(QueryFilterType.CURR.name());
            this.mEnergyGraphFragBinding.barchartView.setDoubleTapToZoomEnabled(false);
            this.mEnergyGraphFragBinding.barchartView.setPinchZoom(false);
            this.mEnergyGraphFragBinding.barchartView.fitScreen();
            this.myMarkerView = new MyMarkerView(requireContext(), R.layout.custom_marker, currencySymbol);
            this.mEnergyGraphFragBinding.barchartView.setMarker(this.myMarkerView);
            ViewUtils.setTextViewDrawableColor(this.mEnergyGraphFragBinding.energyRadioBtn, (int) R.color.dark_red);
        }
        return this.mEnergyGraphFragBinding.getRoot();
    }

    private void setClickListenerOnViews() {
        this.mEnergyGraphFragBinding.backButton.setOnClickListener(this);
        this.mEnergyGraphFragBinding.chartLabelLayout.setMode(true, QueryFilterType.COST.name(), QueryFilterType.WEEKLY.name());
        this.mEnergyGraphFragBinding.energyUsageRadioGroup.setOnCheckedChangeListener(this);
        logEvents(R.id.weeklyRadioBtn);
        this.mEnergyGraphFragBinding.energyCostRadioGrp.setOnCheckedChangeListener(this);
        this.mEnergyGraphFragBinding.totalBudgetLayout.setOnClickListener(this);
        this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setOnCheckedChangeListener(this);
        this.mEnergyGraphFragBinding.lastYearBackButtonLayout.setOnClickListener(this);
        this.mEnergyGraphFragBinding.thisYearTextLayout.setOnClickListener(this);
        this.mEnergyGraphFragBinding.thisYearTextLayout.setEnabled(true);
        this.mEnergyGraphFragBinding.nextYearBackButtonLayout.setOnClickListener(this);
        this.mEnergyGraphFragBinding.nextYearBackButtonLayout.setEnabled(true);
    }

    public void onResume() {
        super.onResume();
        EnergyConsumptionDataMain responseDataMainInstanse = this.mViewModel.getResponseDataMainInstanse();
        if (responseDataMainInstanse != null && responseDataMainInstanse.isToUpdateGraphData()) {
            responseDataMainInstanse.setToUpdateGraphData(false);
            String currencySymbol = responseDataMainInstanse.getCurrencySymbol();
            String currencyCode = responseDataMainInstanse.getCurrencyCode();
            this.mEnergyGraphFragBinding.unitPriceLebelTextView.setText(getString(R.string.energyConsumption_lbl_budgetPerMonth, currencyCode));
            this.mEnergyGraphFragBinding.costRadioBtn.setText(getResources().getString(R.string.energyConsumption_btn_cost, new Object[]{currencySymbol, currencyCode}));
            Double[] availableData = this.mPresenter.getAvailableData();
            if (availableData == null || availableData.length == 0) {
                getEnergyConsumptionDatas(QueryFilterType.CURR.name());
            } else {
                this.mPresenter.initiateScreenDatas(responseDataMainInstanse, this.filterType);
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                this.mFragmentToActivityCallback.getNavController().navigateUp();
                return;
            case R.id.lastYearBackButtonLayout:
                if (this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked()) {
                    this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setChecked(false);
                    setWeeklyDatas(0, 6, 1);
                    setMonthsDate(0, 1);
                }
                lastWeekOrMonthOrYrButtonClicked();
                return;
            case R.id.nextYearBackButtonLayout:
                nextWeekMonthOrYrClicked();
                return;
            case R.id.thisYearTextLayout:
                thisWeekMonthOrYearClicked();
                return;
            case R.id.totalBudgetLayout:
                this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_consumedEnergyGraphFragment_to_budgetSetUpFragment);
                return;
            default:
                return;
        }
    }

    private void lastWeekOrMonthOrYrButtonClicked() {
        int totalPrevWeekClickCount = this.mPresenter.getTotalPrevWeekClickCount();
        int totalPrevMonthClickCount = this.mPresenter.getTotalPrevMonthClickCount();
        int i = C18082.f437xeb78465e[QueryFilterType.valueOf(this.mPresenter.getFilter()).ordinal()];
        if (i == 1) {
            setWeeklyDatas(this.weekDayStart - 7, this.weekend - 7, totalPrevWeekClickCount + 1);
        } else if (i != 2) {
            this.mPresenter.setEndDate(EnergyConsumptionUtility.getLastDayOfYear(-1));
        } else {
            setMonthsDate(this.monthDateIndex - 1, totalPrevMonthClickCount + 1);
        }
        Date convertStringIntoDate = EnergyConsumptionUtility.convertStringIntoDate(this.mPresenter.getDataAvailableFrom());
        Date convertStringIntoDate2 = EnergyConsumptionUtility.convertStringIntoDate(this.mPresenter.getEndDate());
        if (convertStringIntoDate == null || convertStringIntoDate2 == null) {
            alertDialog(getString(R.string.energy_consumption), getString(R.string.energyConsuption_lbl_noChartData), false);
        } else if (convertStringIntoDate.compareTo(convertStringIntoDate2) > 0) {
            alertDialog(getString(R.string.energy_consumption), getString(R.string.energyConsuption_lbl_noChartData), false);
            if (this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked()) {
                this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setChecked(false);
            }
        } else {
            this.mPresenter.setPrevTime(true);
            this.mPresenter.setCurrentWeekMonthYr(false);
            getEnergyConsumptionDatas(QueryFilterType.PREV.name());
        }
    }

    /* renamed from: com.jch.racWiFi.energyConsumption.view.ConsumedEnergyGraphFragment$2 */
    static /* synthetic */ class C18082 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$energyConsumption$utility$QueryFilterType */
        static final /* synthetic */ int[] f437xeb78465e;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType[] r0 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f437xeb78465e = r0
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.WEEKLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f437xeb78465e     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.MONTHLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.energyConsumption.view.ConsumedEnergyGraphFragment.C18082.<clinit>():void");
        }
    }

    private void thisWeekMonthOrYearClicked() {
        int i = C18082.f437xeb78465e[QueryFilterType.valueOf(this.mPresenter.getFilter()).ordinal()];
        if (i == 1) {
            setWeeklyDatas(0, 6, 1);
        } else if (i == 2) {
            setMonthsDate(0, 1);
        }
        this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setChecked(false);
        this.mPresenter.setPrevTime(false);
        this.mPresenter.setCurrentWeekMonthYr(true);
        getEnergyConsumptionDatas(QueryFilterType.CURR.name());
    }

    private void nextWeekMonthOrYrClicked() {
        int totalPrevWeekClickCount = this.mPresenter.getTotalPrevWeekClickCount();
        QueryFilterType valueOf = QueryFilterType.valueOf(this.mPresenter.getFilter());
        int totalPrevMonthClickCount = this.mPresenter.getTotalPrevMonthClickCount();
        int i = C18082.f437xeb78465e[valueOf.ordinal()];
        if (i == 1) {
            setWeeklyDatas(this.weekDayStart + 7, this.weekend + 7, totalPrevWeekClickCount - 1);
        } else if (i == 2) {
            setMonthsDate(this.monthDateIndex + 1, totalPrevMonthClickCount - 1);
        }
        this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setChecked(false);
        this.mPresenter.setPrevTime(false);
        this.mPresenter.setCurrentWeekMonthYr(false);
        getEnergyConsumptionDatas(QueryFilterType.CURR.name());
    }

    private void setWeeklyDatas(int i, int i2, int i3) {
        this.weekDayStart = i;
        this.weekend = i2;
        this.mPresenter.setTotalPrevWeekClickCount(i3);
        this.mPresenter.setStartDate(EnergyConsumptionUtility.getDynamicDateofWeek(i));
        this.mPresenter.setEndDate(EnergyConsumptionUtility.getDynamicDateofWeek(i2));
        this.mPresenter.setWeekDayEnd(i2);
    }

    private void setMonthsDate(int i, int i2) {
        this.monthDateIndex = i;
        this.mPresenter.setTotalPrevMonthClickCount(i2);
        this.mPresenter.setStartDate(EnergyConsumptionUtility.getDynamicStartDayOfMonth(i));
        this.mPresenter.setEndDate(EnergyConsumptionUtility.getDynamicEndDayOfMonth(i));
        this.mPresenter.setMonthDateIndex(i);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.onDestroy();
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int id = radioGroup.getId();
        if (id == R.id.energyCostRadioGrp) {
            this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setChecked(false);
            getEnergyConsumptionDatas((this.mPresenter.isPrevTime() ? QueryFilterType.PREV : QueryFilterType.CURR).name());
        } else if (id == R.id.energyUsageRadioGroup) {
            this.mPresenter.setPrevTime(false);
            logEvents(radioGroup.getCheckedRadioButtonId());
            this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setChecked(false);
            getEnergyConsumptionDatas((this.mPresenter.isPrevTime() ? QueryFilterType.PREV : QueryFilterType.CURR).name());
        }
    }

    private void logEvents(int i) {
        if (i == R.id.weeklyRadioBtn) {
            logEvents(Events.USER_TRACKER_WEEKLY_FREQUENCY.name(), 0);
        }
        if (i == R.id.monthlyRadioBtn) {
            logEvents(Events.USER_TRACKER_MONTHLY_FREQUENCY.name(), 0);
        }
        if (i == R.id.yearlyRadioBtn) {
            logEvents(Events.USER_TRACKER_YEARLY_FREQUENCY.name(), 0);
        }
    }

    /* access modifiers changed from: private */
    public void getEnergyConsumptionDatas(String str) {
        String[] strArr;
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            EnergyConsumptionDataMain responseDataMainInstanse = this.mViewModel.getResponseDataMainInstanse();
            ArrayList<EnergyCostIndivisualRacData> indivisualRacListInstanse = this.mViewModel.getIndivisualRacListInstanse();
            if (responseDataMainInstanse.isAllRacSelected()) {
                strArr = new String[indivisualRacListInstanse.size()];
                for (int i = 0; i < indivisualRacListInstanse.size(); i++) {
                    strArr[i] = indivisualRacListInstanse.get(i).getVendorThingId();
                }
            } else {
                strArr = new String[]{indivisualRacListInstanse.get(responseDataMainInstanse.getSelectedItemPosition()).getVendorThingId()};
            }
            String[] strArr2 = strArr;
            if (this.mEnergyGraphFragBinding.weeklyRadioBtn.isChecked()) {
                setWeeklyDatas(this.weekDayStart, this.weekend, this.mPresenter.getTotalPrevWeekClickCount());
                this.filterType = QueryFilterType.WEEKLY.name();
            } else if (this.mEnergyGraphFragBinding.monthlyRadioBtn.isChecked()) {
                setMonthsDate(this.monthDateIndex, this.mPresenter.getTotalPrevMonthClickCount());
                this.filterType = QueryFilterType.MONTHLY.name();
            } else {
                this.filterType = QueryFilterType.YEAR.name();
            }
            this.mViewModel.getEnergyConsumptionData(this.mPresenter.getMode(), FamilyGroupList.getCurrentFamily().familyId, strArr2, this.filterType, str, this.mPresenter.getStartDate(), this.mPresenter.getEndDate()).observeWithCachedTrigger(getViewLifecycleOwner(), new ConsumedEnergyGraphFragment$$ExternalSyntheticLambda2(this, responseDataMainInstanse));
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    /* renamed from: lambda$getEnergyConsumptionDatas$0$com-jch-racWiFi-energyConsumption-view-ConsumedEnergyGraphFragment */
    public /* synthetic */ void mo28948x5e0aef97(EnergyConsumptionDataMain energyConsumptionDataMain, GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        clearChartData();
        if (genericResponse == null) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.unableToReachServer()) {
            showErrorPopUp(getString(R.string.common_alert_unableToReachServer));
        } else {
            ArrayList arrayList = null;
            if (genericResponse.isApiSuccessful()) {
                Gson gson = new Gson();
                ResponseBody body = genericResponse.getResponse().body();
                if (body != null) {
                    arrayList = new ArrayList(Arrays.asList((RacWiseEnergyUsageResponseMain[]) gson.fromJson(Constants.CC.getRawResponse(body), RacWiseEnergyUsageResponseMain[].class)));
                }
                boolean isChecked = this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked();
                if (arrayList != null) {
                    this.mViewModel.setIndivisualIduEnergyUsage(arrayList, this.filterType, isChecked);
                }
                this.mPresenter.initiateScreenDatas(energyConsumptionDataMain, this.filterType);
                this.myMarkerView.setxAxisValues(this.mPresenter.getXAxisValues(), this.mPresenter.getFilter(), EnergyConsumptionUtility.getCurrentMonth(), this.mPresenter.getSelectedMonth(), this.mPresenter.getMode(), isChecked);
                return;
            }
            int code = genericResponse.getResponse().code();
            if (code == 401) {
                showPleaseWaitDialog();
                getCoreActivity().refreshToken(new CallBackListener() {
                    public void onFailure() {
                    }

                    public void onSuccess() {
                        ConsumedEnergyGraphFragment.this.getEnergyConsumptionDatas(QueryFilterType.CURR.name());
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

    private void clearChartData() {
        this.mEnergyGraphFragBinding.barchartView.fitScreen();
        if (this.mEnergyGraphFragBinding.barchartView.getData() != null) {
            ((BarData) this.mEnergyGraphFragBinding.barchartView.getData()).clearValues();
        }
        this.mEnergyGraphFragBinding.barchartView.notifyDataSetChanged();
        this.mEnergyGraphFragBinding.barchartView.clear();
        this.mEnergyGraphFragBinding.barchartView.invalidate();
        this.mEnergyGraphFragBinding.barchartView.setNoDataTextColor(ContextCompat.getColor(getCoreActivity(), R.color.color_dark_red));
        this.mEnergyGraphFragBinding.barchartView.getPaint(7).setTextSize(C1030Utils.convertDpToPixel(20.0f));
        this.mEnergyGraphFragBinding.barchartView.setNoDataText(getResources().getString(R.string.energyConsuption_lbl_noChartData));
    }

    private void initializeChart() {
        XAxis xAxis = this.mEnergyGraphFragBinding.barchartView.getXAxis();
        xAxis.setGranularity(1.0f);
        boolean z = true;
        xAxis.setGranularityEnabled(true);
        if (this.mPresenter.isCompareLastTimeSelected()) {
            xAxis.setAxisMinimum(0.0f);
        } else {
            xAxis.resetAxisMinimum();
        }
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(this.mPresenter.getXAxisValues().length);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(this.mPresenter.getXAxisValues()));
        xAxis.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked());
        xAxis.setTextSize(11.0f);
        this.mEnergyGraphFragBinding.barchartView.setXAxisRenderer(new ColoredLabelXAxisRenderer(this.mEnergyGraphFragBinding.barchartView.getViewPortHandler(), xAxis, this.mEnergyGraphFragBinding.barchartView.getTransformer(YAxis.AxisDependency.LEFT), this.mPresenter.getMonthColors()));
        YAxis axisLeft = this.mEnergyGraphFragBinding.barchartView.getAxisLeft();
        YAxis axisRight = this.mEnergyGraphFragBinding.barchartView.getAxisRight();
        if (this.mEnergyGraphFragBinding.energyCostRadioGrp.getCheckedRadioButtonId() != R.id.energyRadioBtn) {
            z = false;
        }
        axisLeft.setTextColor(ContextCompat.getColor(requireActivity(), R.color.label_text_color));
        axisLeft.setAxisMinimum(0.0f);
        axisRight.setAxisMinimum(0.0f);
        if (!z) {
            double maxCostOrEnergy = (double) ((float) this.mPresenter.getMaxCostOrEnergy());
            float f = (float) (maxCostOrEnergy + (0.2d * maxCostOrEnergy));
            if (f > 0.0f) {
                axisLeft.setAxisMaximum(f);
                axisLeft.setLabelCount(this.mPresenter.getLabelCount());
                axisRight.setAxisMaximum(f);
                axisRight.setLabelCount(this.mPresenter.getLabelCount());
            }
        } else {
            axisLeft.setAxisMaximum((float) (this.mPresenter.getMaxCostOrEnergy() + (this.mPresenter.getMaxCostOrEnergy() * 0.2d)));
            axisRight.setAxisMaximum((float) this.mPresenter.getMaxCostOrEnergy());
            axisLeft.setLabelCount(this.mPresenter.getLabelCount());
            axisRight.setLabelCount(this.mPresenter.getLabelCount());
        }
        this.mEnergyGraphFragBinding.barchartView.getLegend().setEnabled(false);
        axisLeft.setDrawGridLines(false);
        axisRight.setDrawGridLines(false);
        axisRight.setEnabled(false);
        this.mEnergyGraphFragBinding.barchartView.getDescription().setEnabled(false);
        if (z || !this.mPresenter.isBudgetSet()) {
            axisLeft.removeAllLimitLines();
            return;
        }
        LimitLine limitLine = new LimitLine((float) this.mPresenter.getBudget(), "");
        limitLine.setLineWidth(2.0f);
        limitLine.enableDashedLine(10.0f, 10.0f, 0.0f);
        axisLeft.removeAllLimitLines();
        axisLeft.addLimitLine(limitLine);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            lastWeekOrMonthOrYrButtonClicked();
        } else {
            thisWeekMonthOrYearClicked();
        }
    }

    public void onStart() {
        super.onStart();
        this.mPresenter.getBarDataSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), this.barDataChangesObserve);
    }

    public void onStop() {
        super.onStop();
        this.mPresenter.getBarDataSingleLiveEvent().removeObserver(this.barDataChangesObserve);
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-energyConsumption-view-ConsumedEnergyGraphFragment */
    public /* synthetic */ void mo28949xa21acb62(BarData barData) {
        if (barData != null) {
            initializeChart();
            if (this.filterType.equals(QueryFilterType.MONTHLY.name())) {
                this.mEnergyGraphFragBinding.barchartView.setScaleMinima(2.0f, 1.0f);
            } else {
                this.mEnergyGraphFragBinding.barchartView.fitScreen();
            }
            this.mEnergyGraphFragBinding.barchartView.setData(barData);
            this.mEnergyGraphFragBinding.barchartView.animateY(500);
            if (barData.getDataSetCount() > 1) {
                this.mEnergyGraphFragBinding.barchartView.groupBars(0.0f, 0.27f, 0.0f);
            }
            this.mEnergyGraphFragBinding.barchartView.invalidate();
            showBudgetBanner();
            return;
        }
        clearChartData();
    }

    public void showBudgetBanner() {
        double budget = this.mPresenter.getBudget();
        if (!com.jch.racWiFi.Constants.IS_DEMO_MODE && budget != C1030Utils.DOUBLE_EPSILON) {
            if ((this.mPresenter.getMaxCostOrEnergy() * 100.0d) / budget >= 80.0d) {
                hideOrShowTheBudgetView(true);
            }
            this.mEnergyGraphFragBinding.include.imageButtonClear.setOnClickListener(new ConsumedEnergyGraphFragment$$ExternalSyntheticLambda0(this));
            new Handler().postDelayed(new ConsumedEnergyGraphFragment$$ExternalSyntheticLambda3(this), 5000);
        }
    }

    /* renamed from: lambda$showBudgetBanner$2$com-jch-racWiFi-energyConsumption-view-ConsumedEnergyGraphFragment */
    public /* synthetic */ void mo28950x2461ee59(View view) {
        hideOrShowTheBudgetView(false);
    }

    /* renamed from: lambda$showBudgetBanner$3$com-jch-racWiFi-energyConsumption-view-ConsumedEnergyGraphFragment */
    public /* synthetic */ void mo28951xc0cfeab8() {
        hideOrShowTheBudgetView(false);
    }

    private void hideOrShowTheBudgetView(boolean z) {
        if (z) {
            Slide slide = new Slide(48);
            slide.setDuration(600);
            slide.addTarget(this.mEnergyGraphFragBinding.layoutBudgetBanner);
            TransitionManager.beginDelayedTransition((ViewGroup) this.mEnergyGraphFragBinding.getRoot(), slide);
        }
        this.mEnergyGraphFragBinding.layoutBudgetBanner.setVisibility(z ? 0 : 8);
    }
}
