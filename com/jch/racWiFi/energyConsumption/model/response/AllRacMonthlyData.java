package com.jch.racWiFi.energyConsumption.model.response;

public class AllRacMonthlyData {
    private double budget;
    private double cost;
    private String currency = "";
    private double energyConsumed;
    private boolean isAllRacDisabled;

    public double getCost() {
        return this.cost;
    }

    public void setCost(double d) {
        this.cost = d;
    }

    public double getEnergyConsumed() {
        return this.energyConsumed;
    }

    public void setEnergyConsumed(double d) {
        this.energyConsumed = d;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double d) {
        this.budget = d;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public boolean isAllRacDisabled() {
        return this.isAllRacDisabled;
    }

    public void setAllRacDisabled(boolean z) {
        this.isAllRacDisabled = z;
    }
}
