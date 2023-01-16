package com.jch.racWiFi.energyConsumption.model.request;

public class EnergyConsumptionRequestBody {
    private EnergyConsumptionRequestQuery query;
    private String[] racs;

    public String[] getRacs() {
        return this.racs;
    }

    public void setRacs(String[] strArr) {
        this.racs = strArr;
    }

    public EnergyConsumptionRequestQuery getQuery() {
        return this.query;
    }

    public void setQuery(EnergyConsumptionRequestQuery energyConsumptionRequestQuery) {
        this.query = energyConsumptionRequestQuery;
    }
}
