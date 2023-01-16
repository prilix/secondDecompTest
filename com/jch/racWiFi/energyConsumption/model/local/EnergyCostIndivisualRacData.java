package com.jch.racWiFi.energyConsumption.model.local;

import java.util.Arrays;

public class EnergyCostIndivisualRacData {
    private double cost;
    private String currenySymbol = "";
    private long dataAvailableFrom;
    private double energyConsumed;
    private boolean isAllRacDisabled;
    private boolean isEnergyConsumptionSupported;
    private boolean isRacDisabled;
    private boolean isRacMultiSplitModel;
    private long lastUpdatedOn;
    private Double[] lastWeekOrMonthOrYearData;
    private int racId;
    private String racName = "";
    private Double[] thisWeekOrMonthOrYearData;
    private String vendorThingId = "";

    public String getRacName() {
        return this.racName;
    }

    public void setRacName(String str) {
        this.racName = str;
    }

    public double getEnergyConsumed() {
        return this.energyConsumed;
    }

    public void setEnergyConsumed(double d) {
        this.energyConsumed = d;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double d) {
        this.cost = d;
    }

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public long getDataAvailableFrom() {
        return this.dataAvailableFrom;
    }

    public void setDataAvailableFrom(long j) {
        this.dataAvailableFrom = j;
    }

    public long getLastUpdatedOn() {
        return this.lastUpdatedOn;
    }

    public void setLastUpdatedOn(long j) {
        this.lastUpdatedOn = j;
    }

    public Double[] getThisWeekOrMonthOrYearData() {
        return this.thisWeekOrMonthOrYearData;
    }

    public void setThisWeekOrMonthOrYearData(Double[] dArr) {
        this.thisWeekOrMonthOrYearData = dArr;
    }

    public Double[] getLastWeekOrMonthOrYearData() {
        return this.lastWeekOrMonthOrYearData;
    }

    public void setLastWeekOrMonthOrYearData(Double[] dArr) {
        this.lastWeekOrMonthOrYearData = dArr;
    }

    public String getCurrenySymbol() {
        return this.currenySymbol;
    }

    public void setCurrenySymbol(String str) {
        this.currenySymbol = str;
    }

    public int getRacId() {
        return this.racId;
    }

    public void setRacId(int i) {
        this.racId = i;
    }

    public boolean isEnergyConsumptionSupported() {
        return this.isEnergyConsumptionSupported;
    }

    public void setEnergyConsumptionSupported(boolean z) {
        this.isEnergyConsumptionSupported = z;
    }

    public boolean isRacMultiSplitModel() {
        return this.isRacMultiSplitModel;
    }

    public void setRacMultiSplitModel(boolean z) {
        this.isRacMultiSplitModel = z;
    }

    public boolean isRacDisabled() {
        return this.isRacDisabled;
    }

    public void setRacDisabled(boolean z) {
        this.isRacDisabled = z;
    }

    public boolean isAllRacDisabled() {
        return this.isAllRacDisabled;
    }

    public void setAllRacDisabled(boolean z) {
        this.isAllRacDisabled = z;
    }

    public String toString() {
        return "EnergyConsumptionIndivisualRacListData{racName='" + this.racName + '\'' + ", vendorThingId='" + this.vendorThingId + '\'' + ", energyConsumed=" + this.energyConsumed + ", cost=" + this.cost + ", currenySymbol='" + this.currenySymbol + '\'' + ", lastUpdatedOn=" + this.lastUpdatedOn + ", dataAvailableFrom=" + this.dataAvailableFrom + ", thisWeekOrMonthOrYearData=" + Arrays.toString(this.thisWeekOrMonthOrYearData) + ", lastWeekOrMonthOrYearData=" + Arrays.toString(this.lastWeekOrMonthOrYearData) + ", racId=" + this.racId + ", isEnergyConsumptionSupported=" + this.isEnergyConsumptionSupported + ", isRacMultiSplitModel=" + this.isRacMultiSplitModel + ", isRacDisabled=" + this.isRacDisabled + '}';
    }
}
