package com.jch.racWiFi.energyConsumption.model.local;

public class TimeOrMonthModel {
    private boolean isSelected;
    private String timeOrSeasonName;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String getTimeOrSeasonName() {
        return this.timeOrSeasonName;
    }

    public void setTimeOrSeasonName(String str) {
        this.timeOrSeasonName = str;
    }
}
