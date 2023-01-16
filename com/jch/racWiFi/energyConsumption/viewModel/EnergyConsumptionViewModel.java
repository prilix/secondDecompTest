package com.jch.racWiFi.energyConsumption.viewModel;

import androidx.lifecycle.C0534ViewModel;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.StatusCode;
import com.jch.racWiFi.energyConsumption.model.local.CurrencyModel;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionBudgetAndPrice;
import com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain;
import com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData;
import com.jch.racWiFi.energyConsumption.model.request.AllRacEnergyUsageReqBody;
import com.jch.racWiFi.energyConsumption.model.request.ECSettings;
import com.jch.racWiFi.energyConsumption.model.request.EnergyConsumptionRequestBody;
import com.jch.racWiFi.energyConsumption.model.request.EnergyConsumptionRequestQuery;
import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.EnergyCostSettingsData;
import com.jch.racWiFi.energyConsumption.networkCall.EnergyConsumptionNetworkCall;
import com.jch.racWiFi.energyConsumption.utility.EnergyConsumptionUtility;
import com.jch.racWiFi.energyConsumption.utility.QueryFilterType;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class EnergyConsumptionViewModel extends C0534ViewModel {
    private final String TAG = getClass().getSimpleName();
    private final SingleLiveEvent<Integer> billingTypeLiveData = new SingleLiveEvent<>();
    private final ArrayList<CurrencyModel> currencyList = new ArrayList<>();
    private final SingleLiveEvent<CurrencyModel> currencyLiveData = new SingleLiveEvent<>();
    private final EnergyConsumptionDataMain energyConsumptionDataMain = new EnergyConsumptionDataMain();
    private final EnergyConsumptionNetworkCall energyConsumptionNetworkCall = new EnergyConsumptionNetworkCall();
    private final ArrayList<EnergyCostIndivisualRacData> indivisualRacDataList = new ArrayList<>();
    private final SingleLiveEvent<HashMap<Long, Boolean>> mRacIdsMapSingleLiveEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<ArrayList<Long>> mRacIdsSingleLiveEvent = new SingleLiveEvent<>();
    private final int monthsMaxDays = 32;
    private final SingleLiveEvent<EnergyConsumptionDataMain> responseLiveData = new SingleLiveEvent<>();
    private final int weekMaxDay = 8;
    private final int yearMaxMonths = 13;

    public void setResponseLiveData(EnergyConsumptionDataMain energyConsumptionDataMain2) {
        this.responseLiveData.postValue(energyConsumptionDataMain2);
    }

    public SingleLiveEvent<EnergyConsumptionDataMain> getResponseLiveData() {
        return this.responseLiveData;
    }

    public void setCurrencyModelLiveData(CurrencyModel currencyModel) {
        this.currencyLiveData.postValue(currencyModel);
    }

    public SingleLiveEvent<CurrencyModel> getCurrencyModelLiveData() {
        return this.currencyLiveData;
    }

    public void setRacIds(ArrayList<Long> arrayList) {
        this.mRacIdsSingleLiveEvent.postValue(arrayList);
    }

    public void setRacIdsMap(HashMap<Long, Boolean> hashMap) {
        this.mRacIdsMapSingleLiveEvent.postValue(hashMap);
    }

    public SingleLiveEvent<ArrayList<Long>> getRacIds() {
        return this.mRacIdsSingleLiveEvent;
    }

    public SingleLiveEvent<HashMap<Long, Boolean>> getRacIdsMap() {
        return this.mRacIdsMapSingleLiveEvent;
    }

    public void setBillingTypeLiveData(int i) {
        this.billingTypeLiveData.postValue(Integer.valueOf(i));
    }

    public SingleLiveEvent<Integer> getBillingTypeLiveData() {
        return this.billingTypeLiveData;
    }

    public EnergyConsumptionDataMain getResponseDataMainInstanse() {
        return this.energyConsumptionDataMain;
    }

    public ArrayList<EnergyCostIndivisualRacData> getIndivisualRacListInstanse() {
        return this.indivisualRacDataList;
    }

    public SingleLiveEvent<GenericResponse> getAllRacTotalEnergyConsumed(int i, AllRacEnergyUsageReqBody allRacEnergyUsageReqBody) {
        return this.energyConsumptionNetworkCall.getAllRacTotalEnergyConsumed(i, allRacEnergyUsageReqBody);
    }

    public SingleLiveEvent<GenericResponse> getAllRacData(int i) {
        return this.energyConsumptionNetworkCall.getAllRacData(i);
    }

    public SingleLiveEvent<GenericResponse> setEnergyConsumptionBudgetAndPrice(EnergyConsumptionBudgetAndPrice energyConsumptionBudgetAndPrice) {
        return this.energyConsumptionNetworkCall.setBudgetAndPriceData(energyConsumptionBudgetAndPrice);
    }

    public SingleLiveEvent<GenericResponse> getEnergyConsumptionData(String str, int i, String[] strArr, String str2, String str3, String str4, String str5) {
        String str6;
        EnergyConsumptionRequestQuery energyConsumptionRequestQuery;
        EnergyConsumptionRequestBody energyConsumptionRequestBody = new EnergyConsumptionRequestBody();
        energyConsumptionRequestBody.setRacs(strArr);
        String name = QueryFilterType.CURR.name();
        int i2 = C18111.f438xeb78465e[QueryFilterType.valueOf(str2).ordinal()];
        if (i2 == 1 || i2 == 2) {
            EnergyConsumptionRequestQuery queryFilter = getQueryFilter(QueryFilterType.DATE.name(), str4, str5);
            str6 = name;
            energyConsumptionRequestQuery = queryFilter;
        } else if (str3.equals(QueryFilterType.CURR.name())) {
            energyConsumptionRequestQuery = getQueryFilter(QueryFilterType.YEAR.name(), EnergyConsumptionUtility.getStartDayOfMonth(), EnergyConsumptionUtility.getEndDayOfMonth());
            str6 = QueryFilterType.CURR.name();
        } else {
            energyConsumptionRequestQuery = getQueryFilter(QueryFilterType.YEAR.name(), EnergyConsumptionUtility.getStartDayOfMonth(), EnergyConsumptionUtility.getEndDayOfMonth());
            str6 = QueryFilterType.PREV.name();
        }
        energyConsumptionRequestQuery.setTime(str3);
        energyConsumptionRequestQuery.setType(str2);
        energyConsumptionRequestQuery.setYear(str6);
        energyConsumptionRequestQuery.setRequestType(str);
        energyConsumptionRequestBody.setQuery(energyConsumptionRequestQuery);
        return this.energyConsumptionNetworkCall.getEnergyConsumptionData(i, energyConsumptionRequestBody);
    }

    /* renamed from: com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel$1 */
    static /* synthetic */ class C18111 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$energyConsumption$utility$QueryFilterType */
        static final /* synthetic */ int[] f438xeb78465e;

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
                f438xeb78465e = r0
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.WEEKLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f438xeb78465e     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.MONTHLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel.C18111.<clinit>():void");
        }
    }

    private EnergyConsumptionRequestQuery getQueryFilter(String str, String str2, String str3) {
        EnergyConsumptionRequestQuery energyConsumptionRequestQuery = new EnergyConsumptionRequestQuery();
        energyConsumptionRequestQuery.setFilterBy(str);
        energyConsumptionRequestQuery.setStart(str2);
        energyConsumptionRequestQuery.setEnd(str3);
        return energyConsumptionRequestQuery;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00dd A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setIndivisualIduEnergyUsage(java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.response.RacWiseEnergyUsageResponseMain> r10, java.lang.String r11, boolean r12) {
        /*
            r9 = this;
            r9.resetPrevDatas()
            r0 = 0
            r1 = 0
        L_0x0005:
            int r2 = r10.size()
            if (r1 >= r2) goto L_0x00e6
            java.lang.Object r2 = r10.get(r1)
            com.jch.racWiFi.energyConsumption.model.response.RacWiseEnergyUsageResponseMain r2 = (com.jch.racWiFi.energyConsumption.model.response.RacWiseEnergyUsageResponseMain) r2
            com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain r3 = r9.energyConsumptionDataMain
            boolean r3 = r3.isAllRacSelected()
            r4 = 0
            if (r3 == 0) goto L_0x0030
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r3 = r9.indivisualRacDataList
            int r3 = r3.size()
            int r5 = r10.size()
            if (r3 != r5) goto L_0x0030
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r3 = r9.indivisualRacDataList
            java.lang.Object r3 = r3.get(r1)
            com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData r3 = (com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData) r3
            r5 = r1
            goto L_0x0066
        L_0x0030:
            r3 = 0
        L_0x0031:
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r5 = r9.indivisualRacDataList
            int r5 = r5.size()
            if (r3 >= r5) goto L_0x0064
            java.lang.Object r5 = r10.get(r1)
            com.jch.racWiFi.energyConsumption.model.response.RacWiseEnergyUsageResponseMain r5 = (com.jch.racWiFi.energyConsumption.model.response.RacWiseEnergyUsageResponseMain) r5
            java.lang.String r5 = r5.getVendorThingId()
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r6 = r9.indivisualRacDataList
            java.lang.Object r6 = r6.get(r3)
            com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData r6 = (com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData) r6
            java.lang.String r6 = r6.getVendorThingId()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0061
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r5 = r9.indivisualRacDataList
            java.lang.Object r5 = r5.get(r3)
            com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData r5 = (com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData) r5
            r8 = r5
            r5 = r3
            r3 = r8
            goto L_0x0066
        L_0x0061:
            int r3 = r3 + 1
            goto L_0x0031
        L_0x0064:
            r3 = r4
            r5 = 0
        L_0x0066:
            if (r3 != 0) goto L_0x0070
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r3 = r9.indivisualRacDataList
            java.lang.Object r3 = r3.get(r0)
            com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData r3 = (com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData) r3
        L_0x0070:
            java.lang.String r6 = r2.getVendorThingId()
            r3.setVendorThingId(r6)
            long r6 = r2.getLastUpdatedOn()
            r3.setLastUpdatedOn(r6)
            long r6 = r2.getDataAvailableFrom()
            r3.setDataAvailableFrom(r6)
            java.util.HashMap r2 = r2.getData()
            if (r2 == 0) goto L_0x00d4
            boolean r6 = r2.isEmpty()
            if (r6 != 0) goto L_0x00d4
            int[] r6 = com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel.C18111.f438xeb78465e
            com.jch.racWiFi.energyConsumption.utility.QueryFilterType r7 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.valueOf(r11)
            int r7 = r7.ordinal()
            r6 = r6[r7]
            r7 = 1
            if (r6 == r7) goto L_0x00bb
            r7 = 2
            if (r6 == r7) goto L_0x00af
            if (r12 == 0) goto L_0x00aa
            java.lang.Double[] r2 = r9.getYearlyEnergyData(r2)
            goto L_0x00c9
        L_0x00aa:
            java.lang.Double[] r2 = r9.getYearlyEnergyData(r2)
            goto L_0x00c6
        L_0x00af:
            if (r12 == 0) goto L_0x00b6
            java.lang.Double[] r2 = r9.getMonthlyEnergyUsageData(r2)
            goto L_0x00c9
        L_0x00b6:
            java.lang.Double[] r2 = r9.getMonthlyEnergyUsageData(r2)
            goto L_0x00c6
        L_0x00bb:
            if (r12 == 0) goto L_0x00c2
            java.lang.Double[] r2 = r9.getWeeklyEnergyUsageData(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.Double[] r2 = r9.getWeeklyEnergyUsageData(r2)
        L_0x00c6:
            r8 = r4
            r4 = r2
            r2 = r8
        L_0x00c9:
            if (r4 == 0) goto L_0x00ce
            r3.setThisWeekOrMonthOrYearData(r4)
        L_0x00ce:
            if (r2 == 0) goto L_0x00dd
            r3.setLastWeekOrMonthOrYearData(r2)
            goto L_0x00dd
        L_0x00d4:
            if (r12 == 0) goto L_0x00da
            r3.setLastWeekOrMonthOrYearData(r4)
            goto L_0x00dd
        L_0x00da:
            r3.setThisWeekOrMonthOrYearData(r4)
        L_0x00dd:
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r2 = r9.indivisualRacDataList
            r2.set(r5, r3)
            int r1 = r1 + 1
            goto L_0x0005
        L_0x00e6:
            com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain r10 = r9.energyConsumptionDataMain
            boolean r10 = r10.isAllRacSelected()
            if (r10 == 0) goto L_0x016c
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r10 = r9.indivisualRacDataList
            int r10 = r10.size()
            if (r10 <= 0) goto L_0x016c
            int r10 = r9.getArraySize(r11)
            java.lang.Double[] r10 = new java.lang.Double[r10]
            int r11 = r9.getArraySize(r11)
            java.lang.Double[] r11 = new java.lang.Double[r11]
            java.lang.String r12 = r9.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "indivisual rac data list "
            r1.append(r2)
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r2 = r9.indivisualRacDataList
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.accord.common.utils.Logger.m45d(r12, r1)
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r12 = r9.indivisualRacDataList
            java.util.Iterator r12 = r12.iterator()
        L_0x0120:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0162
            java.lang.Object r1 = r12.next()
            com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData r1 = (com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData) r1
            java.lang.Double[] r2 = r1.getThisWeekOrMonthOrYearData()
            java.lang.Double[] r1 = r1.getLastWeekOrMonthOrYearData()
            if (r2 == 0) goto L_0x014b
            r3 = 0
        L_0x0137:
            int r4 = r2.length
            if (r3 >= r4) goto L_0x014b
            r4 = r2[r3]
            r5 = r10[r3]
            double r4 = r9.getAddedValue(r4, r5)
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            r10[r3] = r4
            int r3 = r3 + 1
            goto L_0x0137
        L_0x014b:
            if (r1 == 0) goto L_0x0120
            r2 = 0
        L_0x014e:
            int r3 = r1.length
            if (r2 >= r3) goto L_0x0120
            r3 = r1[r2]
            r4 = r11[r2]
            double r3 = r9.getAddedValue(r3, r4)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            r11[r2] = r3
            int r2 = r2 + 1
            goto L_0x014e
        L_0x0162:
            com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain r12 = r9.energyConsumptionDataMain
            r12.setThisWeekOrMonthOrYearAllRacData(r10)
            com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain r10 = r9.energyConsumptionDataMain
            r10.setLastWeekOrMonthOrYearAllRacData(r11)
        L_0x016c:
            com.jch.racWiFi.energyConsumption.model.local.EnergyConsumptionDataMain r10 = r9.energyConsumptionDataMain
            java.util.ArrayList<com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData> r11 = r9.indivisualRacDataList
            r10.setIndivisualRacData(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel.setIndivisualIduEnergyUsage(java.util.ArrayList, java.lang.String, boolean):void");
    }

    private int getArraySize(String str) {
        if (isNullOrEmpty(str)) {
            return 0;
        }
        int i = C18111.f438xeb78465e[QueryFilterType.valueOf(str).ordinal()];
        if (i != 1) {
            return i != 2 ? 13 : 32;
        }
        return 8;
    }

    private double getAddedValue(Double d, Double d2) {
        double d3 = C1030Utils.DOUBLE_EPSILON;
        double doubleValue = d != null ? d.doubleValue() : 0.0d;
        if (d2 != null) {
            d3 = d2.doubleValue();
        }
        return d3 + doubleValue;
    }

    private Double[] getYearlyEnergyData(HashMap<String, Double> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        Double[] dArr = new Double[13];
        for (String next : hashMap.keySet()) {
            dArr[EnergyConsumptionUtility.getMonthIndexFromGivenMonth(next)] = Double.valueOf(hashMap.get(next).doubleValue());
        }
        return dArr;
    }

    private Double[] getWeeklyEnergyUsageData(HashMap<String, Double> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        Double[] dArr = new Double[8];
        for (String next : hashMap.keySet()) {
            dArr[EnergyConsumptionUtility.getWeekIndexFromDate(next)] = hashMap.get(next);
        }
        return dArr;
    }

    private Double[] getMonthlyEnergyUsageData(HashMap<String, Double> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        Double[] dArr = new Double[32];
        for (String next : hashMap.keySet()) {
            dArr[Integer.parseInt(next.split("-")[2]) - 1] = Double.valueOf(hashMap.get(next).doubleValue());
        }
        return dArr;
    }

    public SingleLiveEvent<GenericResponse> getEnergyCostSettingsData(int i) {
        return this.energyConsumptionNetworkCall.getEnergyConsumptionSettingsData(i);
    }

    public SingleLiveEvent<GenericResponse> saveEnergyCostSettingsData(EnergyCostSettingsData energyCostSettingsData) {
        return this.energyConsumptionNetworkCall.saveEnergyConsumptionSettingsData(energyCostSettingsData);
    }

    public SingleLiveEvent<GenericResponse> saveEcSettings(ECSettings eCSettings) {
        return this.energyConsumptionNetworkCall.saveEcSettings(eCSettings);
    }

    public int getErrorMessageStringId(String str) {
        if (str == null || str.isEmpty()) {
            return R.string.errorCode_alert_somethingWentWorng;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1995143803:
                if (str.equals("NFE002")) {
                    c = 0;
                    break;
                }
                break;
            case -1995143796:
                if (str.equals("NFE009")) {
                    c = 1;
                    break;
                }
                break;
            case -1995143734:
                if (str.equals(StatusCode.BUDGET_SETTINGS_NOT_FOUND)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.string.errorCode_alert_NFE002;
            case 1:
                return R.string.errorCode_alert_NFE009;
            case 2:
                return R.string.errorCode_alert_NFE029;
            default:
                return R.string.errorCode_alert_somethingWentWorng;
        }
    }

    public ArrayList<CurrencyModel> getAllCurrencyList() {
        return this.currencyList;
    }

    public ArrayList<CurrencyModel> getCurrrenciesListFromLocale() {
        if (getAllCurrencyList().isEmpty()) {
            Locale[] availableLocales = Locale.getAvailableLocales();
            getAllCurrencyList().clear();
            HashSet hashSet = new HashSet();
            for (Locale instance : availableLocales) {
                try {
                    Currency instance2 = Currency.getInstance(instance);
                    hashSet.add(new CurrencyModel(instance2.getCurrencyCode(), instance2.getDisplayName(), instance2.getSymbol()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.currencyList.addAll(hashSet);
            Collections.sort(getAllCurrencyList(), new CurrencyModel());
        }
        return getAllCurrencyList();
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public void resetPrevDatas() {
        EnergyConsumptionDataMain energyConsumptionDataMain2 = this.energyConsumptionDataMain;
        if (energyConsumptionDataMain2 != null) {
            energyConsumptionDataMain2.setThisWeekOrMonthOrYearAllRacData((Double[]) null);
            this.energyConsumptionDataMain.setLastWeekOrMonthOrYearAllRacData((Double[]) null);
            ArrayList<EnergyCostIndivisualRacData> arrayList = this.indivisualRacDataList;
            if (arrayList != null && arrayList.size() > 0) {
                for (int i = 0; i < this.indivisualRacDataList.size(); i++) {
                    this.indivisualRacDataList.get(i).setLastWeekOrMonthOrYearData((Double[]) null);
                    this.indivisualRacDataList.get(i).setLastWeekOrMonthOrYearData((Double[]) null);
                }
            }
        }
    }
}
