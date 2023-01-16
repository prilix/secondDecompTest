package com.jch.racWiFi.energyConsumption.presenter;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenu;
import com.accord.common.utils.Logger;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.databinding.FragmentConsumedEnergyGraphBinding;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData;
import com.jch.racWiFi.energyConsumption.model.local.SetBudget;
import com.jch.racWiFi.energyConsumption.utility.EnergyConsumptionUtility;
import com.jch.racWiFi.energyConsumption.utility.EnergyDataConverter;
import com.jch.racWiFi.energyConsumption.utility.QueryFilterType;
import com.jch.racWiFi.energyConsumption.view.ConsumedEnergyGraphFragment;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class EnergyConsumptionPresenter {
    private final SingleLiveEvent<BarData> barDataSingleLiveEvent;
    private double budget;
    boolean centerBtnEnable;
    private int compareWithPrevDataVisibility;
    private boolean currentWeekMonthYr;
    private String dataAvailableFrom;
    private String endDate;
    private final EnergyConsumptionViewModel energyConsumptionViewModel;
    private int leftArrowColor;
    boolean leftBtnEnable;
    private int leftTextColor;
    private final EnergyDataConverter mEnergyDataConverter;
    private final FragmentConsumedEnergyGraphBinding mEnergyGraphFragBinding;
    private String mFilterType;
    private ConsumedEnergyGraphFragment mFragment;
    private double maxCostOrEnergr;
    private int middleTextColor;
    private final List<Integer> monthColors;
    private int monthDateIndex = 0;
    private boolean prevTime;
    private int rightArrowColor;
    boolean rightBtnEnable;
    private int rightTextColor;
    private String selectedMonth;
    private String startDate;
    private int totalPrevMonthClickCount = 1;
    private int totalPrevWeekClickCount = 1;
    private int weekend = 6;
    private String[] xAxisMonthArrData;
    private final String[] xAxisWeekArrData;
    private final String[] xAxisYearArrData;

    public int getLabelCount() {
        return 10;
    }

    public EnergyConsumptionPresenter(ConsumedEnergyGraphFragment consumedEnergyGraphFragment) {
        this.mFragment = consumedEnergyGraphFragment;
        this.mFilterType = QueryFilterType.WEEKLY.name();
        this.monthColors = new ArrayList();
        this.barDataSingleLiveEvent = new SingleLiveEvent<>();
        this.mEnergyDataConverter = new EnergyDataConverter(consumedEnergyGraphFragment.getContext());
        this.xAxisYearArrData = getArrayFromString(consumedEnergyGraphFragment.getString(R.string.energyConsumption_list_year));
        this.xAxisWeekArrData = getArrayFromString(consumedEnergyGraphFragment.getString(R.string.energyConsumption_list_days));
        this.mEnergyGraphFragBinding = consumedEnergyGraphFragment.mEnergyGraphFragBinding;
        this.energyConsumptionViewModel = consumedEnergyGraphFragment.getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
    }

    public void changeBottomLayoutValues(int i) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (i == R.id.monthlyRadioBtn) {
            this.mFilterType = QueryFilterType.MONTHLY.name();
            str = this.mFragment.getString(R.string.common_lbl_lastMonth);
            str5 = this.mFragment.getString(R.string.common_lbl_thisMonth);
            str4 = this.mFragment.getString(R.string.common_lbl_nextMonth);
            str3 = this.mFragment.getString(R.string.common_lbl_days);
            str2 = this.mFragment.getString(R.string.energyConsumption_lbl_compareLastMonth);
        } else if (i != R.id.weeklyRadioBtn) {
            this.mFilterType = QueryFilterType.YEAR.name();
            str = this.mFragment.getString(R.string.common_lbl_lastYear);
            str5 = this.mFragment.getString(R.string.common_lbl_thisYear);
            str4 = this.mFragment.getString(R.string.common_lbl_nextYear);
            str3 = this.mFragment.getString(R.string.common_lbl_months);
            str2 = this.mFragment.getString(R.string.energyConsumption_lbl_compareLastYear);
        } else {
            this.mFilterType = QueryFilterType.WEEKLY.name();
            str = this.mFragment.getString(R.string.common_lbl_lastWeek);
            str5 = this.mFragment.getString(R.string.common_lbl_thisWeek);
            str4 = this.mFragment.getString(R.string.common_lbl_nextWeek);
            str3 = this.mFragment.getString(R.string.common_lbl_day);
            str2 = this.mFragment.getString(R.string.energyConsumption_lbl_compareLastWeek);
        }
        setBottomLayoutValue(str, str5, str4, str3, str2);
    }

    private void changeAvailableDataTime(int i) {
        String str;
        String[] arrayFromString = getArrayFromString(this.mFragment.getString(R.string.energyConsumption_list_monthsInYr));
        String str2 = null;
        if (i == R.id.monthlyRadioBtn) {
            str = EnergyConsumptionUtility.getMonthFromDate(this.startDate, arrayFromString);
            setSelectedMonth(EnergyConsumptionUtility.getMonthFromDate(this.startDate, arrayFromString));
            setMonthlyXAxisValues();
        } else if (i != R.id.weeklyRadioBtn) {
            str = String.valueOf(isPrevTime() ? EnergyConsumptionUtility.getPreviousYear() : EnergyConsumptionUtility.getCurrentYear());
        } else {
            str2 = EnergyConsumptionUtility.getMonthFromDate(this.startDate, arrayFromString);
            str = this.mFragment.getString(R.string.common_lbl_week) + StringUtils.f715LF + EnergyConsumptionUtility.getDayFromDate(this.startDate) + "-" + EnergyConsumptionUtility.getDayFromDate(this.endDate);
        }
        if (str2 == null) {
            this.mEnergyGraphFragBinding.monthAndYearText.setVisibility(8);
        } else {
            this.mEnergyGraphFragBinding.monthAndYearText.setVisibility(0);
            this.mEnergyGraphFragBinding.monthAndYearText.setText(str2);
        }
        this.mEnergyGraphFragBinding.availableDataDurationText.setText(str);
    }

    private String[] getArrayFromString(String str) {
        return str.split(",");
    }

    private String getMonthAndYear(String str) {
        int currentYear = EnergyConsumptionUtility.getCurrentYear();
        return str + StringUtils.f715LF + currentYear;
    }

    private void setMonthlyXAxisValues() {
        int monthLastDate = EnergyConsumptionUtility.getMonthLastDate(this.endDate);
        this.xAxisMonthArrData = new String[monthLastDate];
        for (int i = 1; i <= monthLastDate; i++) {
            this.xAxisMonthArrData[i - 1] = String.valueOf(i);
        }
    }

    public String getSelectedMonth() {
        return this.selectedMonth;
    }

    public void setSelectedMonth(String str) {
        this.selectedMonth = str;
    }

    private void setBottomLayoutValue(String str, String str2, String str3, String str4, String str5) {
        this.mEnergyGraphFragBinding.leftSideTextView.setText(str);
        this.mEnergyGraphFragBinding.middleTextView.setText(str2);
        this.mEnergyGraphFragBinding.rightSideTextView.setText(str3);
        this.mEnergyGraphFragBinding.tvChartXAxisName.setText(str4);
        this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setText(str5);
    }

    public void changeYAxisValue() {
        String str;
        int checkedRadioButtonId = this.mEnergyGraphFragBinding.energyCostRadioGrp.getCheckedRadioButtonId();
        this.mEnergyGraphFragBinding.chartLabelLayout.setMode(this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked(), getMode(), getFilter());
        EnergyConsumptionDataMain responseDataMainInstanse = this.energyConsumptionViewModel.getResponseDataMainInstanse();
        if (checkedRadioButtonId == R.id.energyRadioBtn) {
            str = this.mFragment.getString(R.string.energyConsumption_lbl_energyWithUnit);
            ViewUtils.setTextViewDrawableColor(this.mEnergyGraphFragBinding.energyRadioBtn, (int) R.color.dark_red);
        } else {
            str = this.mFragment.getString(R.string.energyConsumption_lbl_costWithUnit, responseDataMainInstanse.getCurrencySymbol());
            ViewUtils.setTextViewDrawableColor(this.mEnergyGraphFragBinding.energyRadioBtn, (int) R.color.rac_name_oofline_color);
        }
        this.mEnergyGraphFragBinding.tvChartYAxisName.setText(str);
    }

    public void onDestroy() {
        FragmentConsumedEnergyGraphBinding fragmentConsumedEnergyGraphBinding = this.mEnergyGraphFragBinding;
        if (fragmentConsumedEnergyGraphBinding != null) {
            fragmentConsumedEnergyGraphBinding.unbind();
        }
        this.mFragment = null;
    }

    public void initiateScreenDatas(EnergyConsumptionDataMain energyConsumptionDataMain, String str) {
        EnergyCostIndivisualRacData energyCostIndivisualRacData;
        String str2;
        String str3;
        this.mFilterType = str;
        int selectedItemPosition = energyConsumptionDataMain.getSelectedItemPosition();
        if (energyConsumptionDataMain.getSelectedItemPosition() != -1) {
            energyCostIndivisualRacData = energyConsumptionDataMain.getIndivisualRacData().get(selectedItemPosition);
        } else {
            Iterator<EnergyCostIndivisualRacData> it = energyConsumptionDataMain.getIndivisualRacData().iterator();
            EnergyCostIndivisualRacData energyCostIndivisualRacData2 = null;
            while (it.hasNext()) {
                EnergyCostIndivisualRacData next = it.next();
                if (next.getDataAvailableFrom() != 0) {
                    energyCostIndivisualRacData2 = next;
                }
            }
            energyCostIndivisualRacData = energyCostIndivisualRacData2;
        }
        String str4 = "";
        if (energyCostIndivisualRacData != null) {
            str2 = energyCostIndivisualRacData.getLastUpdatedOn() != 0 ? EnergyConsumptionUtility.getDateTimeFromLong(energyCostIndivisualRacData.getLastUpdatedOn()) : str4;
            if (energyCostIndivisualRacData.getDataAvailableFrom() != 0) {
                str4 = EnergyConsumptionUtility.getDateFromLong(energyCostIndivisualRacData.getDataAvailableFrom());
            }
        } else {
            str2 = str4;
        }
        if (str4.isEmpty()) {
            this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setVisibility(4);
        }
        setDataAvailableFrom(str4);
        if (!str2.isEmpty()) {
            this.mEnergyGraphFragBinding.lastUpdatedOnTextView.setText(str2);
        }
        setRacName(energyConsumptionDataMain);
        SetBudget setBudget = energyConsumptionDataMain.getSetBudget();
        if (setBudget != null) {
            this.budget = setBudget.getBudgetAmount();
            if (setBudget.getBudgetAmount() != C1030Utils.DOUBLE_EPSILON) {
                str3 = energyConsumptionDataMain.getCurrencySymbol() + " " + setBudget.getBudgetAmount();
            } else {
                str3 = this.mFragment.getString(R.string.energyConsumption_lbl_notSet);
            }
            this.mEnergyGraphFragBinding.budgetPriceTextView.setText(str3);
        }
        changeYAxisValue();
        int checkedRadioButtonId = this.mEnergyGraphFragBinding.energyUsageRadioGroup.getCheckedRadioButtonId();
        changeBottomLayoutValues(checkedRadioButtonId);
        enableOrDisableBottomLayoutClick();
        changeAvailableDataTime(checkedRadioButtonId);
        createBarData(energyConsumptionDataMain);
    }

    private boolean isLastWeekOrMonthOrYrDataAvaialble() {
        String str;
        int i = C18011.f434xeb78465e[QueryFilterType.valueOf(getFilter()).ordinal()];
        if (i == 1) {
            str = EnergyConsumptionUtility.getDynamicDateofWeek(getWeekDayEnd() - 7);
        } else if (i != 2) {
            str = EnergyConsumptionUtility.getLastDayOfYear(-1);
        } else {
            str = EnergyConsumptionUtility.getDynamicEndDayOfMonth(getMonthDateIndex() - 1);
        }
        Date convertStringIntoDate = EnergyConsumptionUtility.convertStringIntoDate(getDataAvailableFrom());
        Date convertStringIntoDate2 = EnergyConsumptionUtility.convertStringIntoDate(str);
        if (convertStringIntoDate == null || convertStringIntoDate2 == null) {
            return false;
        }
        if (convertStringIntoDate.compareTo(convertStringIntoDate2) <= 0) {
            return true;
        }
        if (this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked()) {
            this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setChecked(false);
        }
        return false;
    }

    /* renamed from: com.jch.racWiFi.energyConsumption.presenter.EnergyConsumptionPresenter$1 */
    static /* synthetic */ class C18011 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$energyConsumption$utility$QueryFilterType */
        static final /* synthetic */ int[] f434xeb78465e;

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
                f434xeb78465e = r0
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.WEEKLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f434xeb78465e     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.MONTHLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.energyConsumption.presenter.EnergyConsumptionPresenter.C18011.<clinit>():void");
        }
    }

    private void createBarData(EnergyConsumptionDataMain energyConsumptionDataMain) {
        Double[] dArr;
        Double[] dArr2;
        EnergyCostIndivisualRacData energyCostIndivisualRacData = energyConsumptionDataMain.getIndivisualRacData().get(energyConsumptionDataMain.getSelectedItemPosition() != -1 ? energyConsumptionDataMain.getSelectedItemPosition() : 0);
        if (energyConsumptionDataMain.isAllRacSelected() && energyConsumptionDataMain.getSelectedItemPosition() == -1) {
            dArr2 = energyConsumptionDataMain.getThisWeekOrMonthOrYearAllRacData();
            dArr = energyConsumptionDataMain.getLastWeekOrMonthOrYearAllRacData();
        } else {
            Double[] thisWeekOrMonthOrYearData = energyCostIndivisualRacData.getThisWeekOrMonthOrYearData();
            Double[] lastWeekOrMonthOrYearData = energyCostIndivisualRacData.getLastWeekOrMonthOrYearData();
            dArr2 = thisWeekOrMonthOrYearData;
            dArr = lastWeekOrMonthOrYearData;
        }
        if (!isAllElementEmpty(dArr2) || !isAllElementEmpty(dArr)) {
            Logger.m45d("Current data = ", Arrays.toString(dArr2) + " prev data = " + Arrays.toString(dArr));
            boolean isChecked = this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked();
            QueryFilterType queryFilterType = this.mEnergyGraphFragBinding.energyCostRadioGrp.getCheckedRadioButtonId() == R.id.costRadioBtn ? QueryFilterType.COST : QueryFilterType.ENERGY;
            this.maxCostOrEnergr = C1030Utils.DOUBLE_EPSILON;
            if (dArr2 != null) {
                for (Double d : dArr2) {
                    if (d != null && d.doubleValue() > this.maxCostOrEnergr) {
                        this.maxCostOrEnergr = d.doubleValue();
                    }
                }
            }
            if (dArr != null && isChecked) {
                for (Double d2 : dArr) {
                    if (d2 != null && d2.doubleValue() > this.maxCostOrEnergr) {
                        this.maxCostOrEnergr = d2.doubleValue();
                    }
                }
            }
            this.barDataSingleLiveEvent.setValue(this.mEnergyDataConverter.getEnergyUsageBarDat(dArr2, dArr, isChecked, queryFilterType));
            return;
        }
        this.barDataSingleLiveEvent.setValue(null);
    }

    private boolean isAllElementEmpty(Double[] dArr) {
        if (dArr == null) {
            return true;
        }
        for (Double d : dArr) {
            if (d != null) {
                return false;
            }
        }
        return true;
    }

    public SingleLiveEvent<BarData> getBarDataSingleLiveEvent() {
        return this.barDataSingleLiveEvent;
    }

    public double getMaxCostOrEnergy() {
        return this.maxCostOrEnergr;
    }

    private void setRacName(EnergyConsumptionDataMain energyConsumptionDataMain) {
        if (energyConsumptionDataMain.isAllRacSelected()) {
            this.mEnergyGraphFragBinding.racNameLayout.setVisibility(0);
            this.mEnergyGraphFragBinding.budgetPriceLayout.setVisibility(0);
            this.mEnergyGraphFragBinding.totalBudgetLayout.setVisibility(0);
            this.mEnergyGraphFragBinding.textViewAcTitle.setVisibility(8);
            this.mEnergyGraphFragBinding.textViewAcName.setVisibility(8);
            this.mEnergyGraphFragBinding.racNameTextView.setText(this.mFragment.getString(R.string.manageUser_lbl_allACs));
            return;
        }
        this.mEnergyGraphFragBinding.racNameLayout.setVisibility(8);
        this.mEnergyGraphFragBinding.budgetPriceLayout.setVisibility(8);
        this.mEnergyGraphFragBinding.totalBudgetLayout.setVisibility(8);
        this.mEnergyGraphFragBinding.textViewAcTitle.setVisibility(0);
        this.mEnergyGraphFragBinding.textViewAcName.setVisibility(0);
        this.mEnergyGraphFragBinding.textViewAcName.setText(energyConsumptionDataMain.getIndivisualRacData().get(energyConsumptionDataMain.getSelectedItemPosition()).getRacName());
    }

    public String[] getXAxisValues() {
        int checkedRadioButtonId = this.mEnergyGraphFragBinding.energyUsageRadioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.monthlyRadioBtn) {
            return this.xAxisMonthArrData;
        }
        if (checkedRadioButtonId != R.id.weeklyRadioBtn) {
            return this.xAxisYearArrData;
        }
        return this.xAxisWeekArrData;
    }

    public String getMode() {
        if (this.mEnergyGraphFragBinding.energyCostRadioGrp.getCheckedRadioButtonId() == R.id.costRadioBtn) {
            return QueryFilterType.COST.name();
        }
        return QueryFilterType.ENERGY.name();
    }

    public String getFilter() {
        int checkedRadioButtonId = this.mEnergyGraphFragBinding.energyUsageRadioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.monthlyRadioBtn) {
            return QueryFilterType.MONTHLY.name();
        }
        if (checkedRadioButtonId != R.id.weeklyRadioBtn) {
            return QueryFilterType.YEAR.name();
        }
        return QueryFilterType.WEEKLY.name();
    }

    public double getBudget() {
        return this.budget;
    }

    public List<Integer> getMonthColors() {
        this.monthColors.clear();
        int length = getXAxisValues().length;
        for (int i = 0; i < length; i++) {
            this.monthColors.add(i, Integer.valueOf(ContextCompat.getColor(this.mFragment.requireActivity(), R.color.label_text_color)));
        }
        if (!isCurrentWeekMonthYr()) {
            return this.monthColors;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        if (this.mFilterType.equals(QueryFilterType.WEEKLY.name())) {
            this.monthColors.add(EnergyConsumptionUtility.getDayIndex(Calendar.getInstance().get(7)), Integer.valueOf(SupportMenu.CATEGORY_MASK));
        } else if (this.mFilterType.equals(QueryFilterType.MONTHLY.name())) {
            this.monthColors.add(instance.get(5) - 1, Integer.valueOf(SupportMenu.CATEGORY_MASK));
        } else {
            this.monthColors.add(instance.get(2), Integer.valueOf(SupportMenu.CATEGORY_MASK));
        }
        return this.monthColors;
    }

    public Double[] getAvailableData() {
        EnergyConsumptionDataMain responseDataMainInstanse = this.energyConsumptionViewModel.getResponseDataMainInstanse();
        boolean z = false;
        EnergyCostIndivisualRacData energyCostIndivisualRacData = this.energyConsumptionViewModel.getIndivisualRacListInstanse().get(responseDataMainInstanse.getSelectedItemPosition() != -1 ? responseDataMainInstanse.getSelectedItemPosition() : 0);
        boolean isChecked = this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked();
        if (responseDataMainInstanse.isAllRacSelected() && responseDataMainInstanse.getSelectedItemPosition() == -1) {
            z = true;
        }
        return isChecked ? z ? responseDataMainInstanse.getLastWeekOrMonthOrYearAllRacData() : energyCostIndivisualRacData.getLastWeekOrMonthOrYearData() : z ? responseDataMainInstanse.getThisWeekOrMonthOrYearAllRacData() : energyCostIndivisualRacData.getThisWeekOrMonthOrYearData();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (getTotalPrevMonthClickCount() < 12) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        if (getTotalPrevWeekClickCount() < 48) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableOrDisableBottomLayoutClick() {
        /*
            r7 = this;
            java.lang.String r0 = r7.getFilter()
            com.jch.racWiFi.energyConsumption.utility.QueryFilterType r0 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.valueOf(r0)
            int[] r1 = com.jch.racWiFi.energyConsumption.presenter.EnergyConsumptionPresenter.C18011.f434xeb78465e
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x0035
            r3 = 2
            if (r0 == r3) goto L_0x0021
            boolean r0 = r7.isPrevTime()
            if (r0 == 0) goto L_0x001e
            goto L_0x001f
        L_0x001e:
            r3 = 1
        L_0x001f:
            r0 = 0
            goto L_0x0048
        L_0x0021:
            int r3 = r7.getTotalPrevMonthClickCount()
            int r0 = r7.getTotalPrevMonthClickCount()
            if (r0 <= r2) goto L_0x001f
            int r0 = r7.getTotalPrevMonthClickCount()
            r4 = 12
            if (r0 >= r4) goto L_0x001f
        L_0x0033:
            r0 = 1
            goto L_0x0048
        L_0x0035:
            int r3 = r7.getTotalPrevWeekClickCount()
            int r0 = r7.getTotalPrevWeekClickCount()
            if (r0 <= r2) goto L_0x001f
            int r0 = r7.getTotalPrevWeekClickCount()
            r4 = 48
            if (r0 >= r4) goto L_0x001f
            goto L_0x0033
        L_0x0048:
            r4 = 4
            r5 = 2131099712(0x7f060040, float:1.7811785E38)
            r6 = 2131100321(0x7f0602a1, float:1.781302E38)
            if (r3 != r2) goto L_0x006e
            r7.setCurrentWeekMonthYr(r2)
            boolean r0 = r7.isLastWeekOrMonthOrYrDataAvaialble()
            if (r0 == 0) goto L_0x005b
            r4 = 0
        L_0x005b:
            r7.compareWithPrevDataVisibility = r4
            r7.leftArrowColor = r5
            r7.leftTextColor = r6
            r7.leftBtnEnable = r2
            r7.middleTextColor = r5
            r7.centerBtnEnable = r1
            r7.rightArrowColor = r6
            r7.rightTextColor = r6
            r7.rightBtnEnable = r1
            goto L_0x009b
        L_0x006e:
            if (r0 == 0) goto L_0x0086
            r7.setCurrentWeekMonthYr(r1)
            r7.compareWithPrevDataVisibility = r4
            r7.leftArrowColor = r5
            r7.leftTextColor = r5
            r7.leftBtnEnable = r2
            r7.middleTextColor = r6
            r7.centerBtnEnable = r2
            r7.rightArrowColor = r5
            r7.rightTextColor = r6
            r7.rightBtnEnable = r2
            goto L_0x009b
        L_0x0086:
            r7.setCurrentWeekMonthYr(r1)
            r7.compareWithPrevDataVisibility = r4
            r7.leftArrowColor = r6
            r7.leftTextColor = r5
            r7.leftBtnEnable = r1
            r7.middleTextColor = r6
            r7.centerBtnEnable = r2
            r7.rightArrowColor = r5
            r7.rightTextColor = r6
            r7.rightBtnEnable = r2
        L_0x009b:
            r7.changeColorOfTextAndImage()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.energyConsumption.presenter.EnergyConsumptionPresenter.enableOrDisableBottomLayoutClick():void");
    }

    private void changeColorOfTextAndImage() {
        if (!this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked()) {
            this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.setVisibility(this.compareWithPrevDataVisibility);
        }
        this.mEnergyGraphFragBinding.leftBackArrow.setColorFilter(this.mFragment.getResources().getColor(this.leftArrowColor));
        this.mEnergyGraphFragBinding.rightArrowIcon.setColorFilter(this.mFragment.getResources().getColor(this.rightArrowColor));
        DrawableCompat.setTint(this.mEnergyGraphFragBinding.leftBackArrow.getDrawable(), ContextCompat.getColor(this.mFragment.requireContext(), R.color.textview_color_vd_light));
        DrawableCompat.setTint(this.mEnergyGraphFragBinding.rightArrowIcon.getDrawable(), ContextCompat.getColor(this.mFragment.requireContext(), R.color.colorRed));
        this.mEnergyGraphFragBinding.lastYearBackButtonLayout.setEnabled(this.leftBtnEnable);
        this.mEnergyGraphFragBinding.leftSideTextView.setTextColor(ContextCompat.getColor(this.mFragment.requireActivity(), this.leftTextColor));
        this.mEnergyGraphFragBinding.thisYearTextLayout.setEnabled(this.centerBtnEnable);
        this.mEnergyGraphFragBinding.middleTextView.setTextColor(ContextCompat.getColor(this.mFragment.requireActivity(), this.middleTextColor));
        this.mEnergyGraphFragBinding.nextYearBackButtonLayout.setEnabled(this.rightBtnEnable);
        this.mEnergyGraphFragBinding.rightSideTextView.setTextColor(ContextCompat.getColor(this.mFragment.requireActivity(), this.rightTextColor));
    }

    public boolean isCompareLastTimeSelected() {
        return this.mEnergyGraphFragBinding.compareWithLastYrCheckBox.isChecked();
    }

    public boolean isPrevTime() {
        return this.prevTime;
    }

    public void setPrevTime(boolean z) {
        this.prevTime = z;
    }

    public boolean isCurrentWeekMonthYr() {
        return this.currentWeekMonthYr;
    }

    public void setCurrentWeekMonthYr(boolean z) {
        this.currentWeekMonthYr = z;
    }

    public boolean isBudgetSet() {
        SetBudget setBudget = this.energyConsumptionViewModel.getResponseDataMainInstanse().getSetBudget();
        if (setBudget == null || setBudget.getBudgetAmount() == C1030Utils.DOUBLE_EPSILON) {
            return false;
        }
        return true;
    }

    public int getTotalPrevWeekClickCount() {
        return this.totalPrevWeekClickCount;
    }

    public void setTotalPrevWeekClickCount(int i) {
        this.totalPrevWeekClickCount = i;
    }

    public int getTotalPrevMonthClickCount() {
        return this.totalPrevMonthClickCount;
    }

    public void setTotalPrevMonthClickCount(int i) {
        this.totalPrevMonthClickCount = i;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public int getWeekDayEnd() {
        return this.weekend;
    }

    public void setWeekDayEnd(int i) {
        this.weekend = i;
    }

    public int getMonthDateIndex() {
        return this.monthDateIndex;
    }

    public void setMonthDateIndex(int i) {
        this.monthDateIndex = i;
    }

    public String getDataAvailableFrom() {
        return this.dataAvailableFrom;
    }

    public void setDataAvailableFrom(String str) {
        this.dataAvailableFrom = str;
    }
}
