package com.jch.racWiFi.energyConsumption.model.response;

import java.util.ArrayList;

public class AllRacTotalCostDataMain {
    private AllRacMonthlyData allRacsData;
    private ArrayList<IndivisualRacCostData> individualRacsData;

    public AllRacMonthlyData getAllRacsMonthlyData() {
        return this.allRacsData;
    }

    public void setAllRacsMonthlyData(AllRacMonthlyData allRacMonthlyData) {
        this.allRacsData = allRacMonthlyData;
    }

    public ArrayList<IndivisualRacCostData> getIndividualRacsData() {
        return this.individualRacsData;
    }

    public void setIndividualRacsData(ArrayList<IndivisualRacCostData> arrayList) {
        this.individualRacsData = arrayList;
    }
}
