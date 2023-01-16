package com.jch.racWiFi.iduManagement.model;

public class CopyWeeklyTimerModel {

    /* renamed from: id */
    private int f450id;
    private boolean isSelected;
    private String name;
    private String racCloudId;
    private int racTypeId;

    public String getRacCloudId() {
        return this.racCloudId;
    }

    public CopyWeeklyTimerModel() {
    }

    public CopyWeeklyTimerModel(int i, String str, int i2, String str2) {
        this.f450id = i;
        this.name = str;
        this.racTypeId = i2;
        this.racCloudId = str2;
    }

    public int getRacTypeId() {
        return this.racTypeId;
    }

    public void setRacTypeId(int i) {
        this.racTypeId = i;
    }

    public int getId() {
        return this.f450id;
    }

    public void setId(int i) {
        this.f450id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
