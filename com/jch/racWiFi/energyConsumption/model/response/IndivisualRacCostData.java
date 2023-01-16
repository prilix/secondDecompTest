package com.jch.racWiFi.energyConsumption.model.response;

public class IndivisualRacCostData {
    private double cost;
    private double energyConsumed;
    private boolean isEnergyConsumptionSupported;
    private boolean isRacDisabled;
    private boolean isRacMultiSplitModel;
    private int racId;
    private String racName = "";
    private String vendorThingId = "";

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public String getRacName() {
        return this.racName;
    }

    public void setRacName(String str) {
        this.racName = str;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(int i) {
        this.cost = (double) i;
    }

    public double getEnergyConsumed() {
        return this.energyConsumed;
    }

    public void setEnergyConsumed(int i) {
        this.energyConsumed = (double) i;
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
}
