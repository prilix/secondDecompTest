package com.jch.racWiFi.fcm.model;

import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("id")

    /* renamed from: id */
    private int f445id;
    @SerializedName("level")
    private int level;
    @SerializedName("name")
    private String name;

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getId() {
        return this.f445id;
    }

    public void setId(int i) {
        this.f445id = i;
    }
}
