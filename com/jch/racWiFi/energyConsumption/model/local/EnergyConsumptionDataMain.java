package com.jch.racWiFi.energyConsumption.model.local;

import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.EnergyCostSettingsData;
import java.util.ArrayList;

public class EnergyConsumptionDataMain {
    private boolean allRacSelected;
    private String currencyCode = "";
    private String currencyName = "";
    private String currencySymbol = "";
    private EnergyCostSettingsData energyCostSettingsData;
    private ArrayList<EnergyCostIndivisualRacData> indivisualRacData;
    private Double[] lastWeekOrMonthOrYearAllRacData;
    private int selectedItemPosition;
    private SetBudget setBudget;
    private Double[] thisWeekOrMonthOrYearAllRacData;
    private boolean toFetchData;
    private boolean toUpdateGraphData;
    private boolean toUpdateMainScreenData;
    private double totalCost;
    private double totalEnergyConsumed;
    private double unitPrice;

    public double getTotalEnergyConsumed() {
        return this.totalEnergyConsumed;
    }

    public void setTotalEnergyConsumed(double d) {
        this.totalEnergyConsumed = d;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double d) {
        this.totalCost = d;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double d) {
        this.unitPrice = d;
    }

    public SetBudget getSetBudget() {
        return this.setBudget;
    }

    public void setSetBudget(SetBudget setBudget2) {
        this.setBudget = setBudget2;
    }

    public boolean isAllRacSelected() {
        return this.allRacSelected;
    }

    public void setAllRacSelected(boolean z) {
        this.allRacSelected = z;
    }

    public ArrayList<EnergyCostIndivisualRacData> getIndivisualRacData() {
        return this.indivisualRacData;
    }

    public void setIndivisualRacData(ArrayList<EnergyCostIndivisualRacData> arrayList) {
        this.indivisualRacData = arrayList;
    }

    public int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    public void setSelectedItemPosition(int i) {
        this.selectedItemPosition = i;
    }

    public Double[] getThisWeekOrMonthOrYearAllRacData() {
        return this.thisWeekOrMonthOrYearAllRacData;
    }

    public void setThisWeekOrMonthOrYearAllRacData(Double[] dArr) {
        this.thisWeekOrMonthOrYearAllRacData = dArr;
    }

    public Double[] getLastWeekOrMonthOrYearAllRacData() {
        return this.lastWeekOrMonthOrYearAllRacData;
    }

    public void setLastWeekOrMonthOrYearAllRacData(Double[] dArr) {
        this.lastWeekOrMonthOrYearAllRacData = dArr;
    }

    public boolean isToFetchData() {
        return this.toFetchData;
    }

    public void setToFetchData(boolean z) {
        this.toFetchData = z;
    }

    public EnergyCostSettingsData getEnergyCostSettingsData() {
        return this.energyCostSettingsData;
    }

    public void setEnergyCostSettingsData(EnergyCostSettingsData energyCostSettingsData2) {
        this.energyCostSettingsData = energyCostSettingsData2;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public void setCurrencySymbol(String str) {
        this.currencySymbol = str;
    }

    public String getCurrencyName() {
        return this.currencyName;
    }

    public void setCurrencyName(String str) {
        this.currencyName = str;
    }

    public boolean isToUpdateGraphData() {
        return this.toUpdateGraphData;
    }

    public void setToUpdateGraphData(boolean z) {
        this.toUpdateGraphData = z;
    }

    public boolean isToUpdateMainScreenData() {
        return this.toUpdateMainScreenData;
    }

    public void setToUpdateMainScreenData(boolean z) {
        this.toUpdateMainScreenData = z;
    }

    public String toString() {
        return "EnergyConsumptionDataMain{totalEnergyConsumed='" + this.totalEnergyConsumed + '\'' + ", totalCost=" + this.totalCost + ", unitPrice=" + this.unitPrice + ", allRacSelected=" + this.allRacSelected + ", setBudget=" + this.setBudget + ", indivisualRacData=" + this.indivisualRacData + ", selectedItemPosition=" + this.selectedItemPosition + '}';
    }
}
