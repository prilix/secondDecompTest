package com.jch.racWiFi.energyConsumption.model.request.budgetSetUp;

import com.jch.racWiFi.energyConsumption.utility.BillingStructureType;
import java.util.ArrayList;

public class EnergyCostSettingsData implements Cloneable {
    private String billingStructureType = BillingStructureType.FLAT_RATE.name();
    private double budget;
    private String currency;
    private String currencyName = "";
    private String currencySymbol = "";
    private int familyId;
    private double fixedCharges;
    private double flateUnitPrice;
    private boolean monthlyBudget;
    private double nonPeakUnitPrice;
    private ArrayList<String> peakHours;
    private ArrayList<String> peakMonths;
    private ArrayList<ProgressiveUnitModel> peakOrUnitRange;
    private double peakUnitPrice;

    public int getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(int i) {
        this.familyId = i;
    }

    public double getFlateUnitPrice() {
        return this.flateUnitPrice;
    }

    public void setFlateUnitPrice(double d) {
        this.flateUnitPrice = d;
    }

    public double getPeakUnitPrice() {
        return this.peakUnitPrice;
    }

    public void setPeakUnitPrice(double d) {
        this.peakUnitPrice = d;
    }

    public double getNonPeakUnitPrice() {
        return this.nonPeakUnitPrice;
    }

    public void setNonPeakUnitPrice(double d) {
        this.nonPeakUnitPrice = d;
    }

    public double getFixedCharges() {
        return this.fixedCharges;
    }

    public void setFixedCharges(double d) {
        this.fixedCharges = d;
    }

    public boolean isMonthlyBudget() {
        return this.monthlyBudget;
    }

    public void setMonthlyBudget(boolean z) {
        this.monthlyBudget = z;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double d) {
        this.budget = d;
    }

    public String getBillingStructureType() {
        return this.billingStructureType;
    }

    public void setBillingStructureType(String str) {
        this.billingStructureType = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public ArrayList<String> getPeakHours() {
        return this.peakHours;
    }

    public void setPeakHours(ArrayList<String> arrayList) {
        this.peakHours = arrayList;
    }

    public ArrayList<String> getPeakMonths() {
        return this.peakMonths;
    }

    public void setPeakMonths(ArrayList<String> arrayList) {
        this.peakMonths = arrayList;
    }

    public ArrayList<ProgressiveUnitModel> getPeakOrUnitRange() {
        return this.peakOrUnitRange;
    }

    public void setPeakOrUnitRange(ArrayList<ProgressiveUnitModel> arrayList) {
        this.peakOrUnitRange = arrayList;
    }

    public String getCurrencyName() {
        return this.currencyName;
    }

    public void setCurrencyName(String str) {
        this.currencyName = str;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public void setCurrencySymbol(String str) {
        this.currencySymbol = str;
    }

    public String toString() {
        return "BudgetSetUpReqBody{familyId=" + this.familyId + ", flateUnitPrice=" + this.flateUnitPrice + ", peakUnitPrice=" + this.peakUnitPrice + ", nonPeakUnitPrice=" + this.nonPeakUnitPrice + ", fixedCharges=" + this.fixedCharges + ", monthlyBudget=" + this.monthlyBudget + ", budget=" + this.budget + ", billingStructureType='" + this.billingStructureType + '\'' + ", currency='" + this.currency + '\'' + ", peakHours=" + this.peakHours + ", peakMonths=" + this.peakMonths + ", peakOrUnitRange=" + this.peakOrUnitRange + '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
