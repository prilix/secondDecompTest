package com.jch.racWiFi.iduOnBoarding.IndiaModelOnboardingFlow.models;

import java.util.ArrayList;

public class OnboardingInfoResponseBody {
    private ArrayList<HomeDetails> homes;

    /* renamed from: id */
    private Long f476id;
    private String name;
    private boolean online;
    private String region = "";
    private String routerSSID = "";
    private String thingId;
    private String vendorThingId = "";

    public String getVendorThingId() {
        return this.vendorThingId;
    }

    public void setVendorThingId(String str) {
        this.vendorThingId = str;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public String getRouterSSID() {
        return this.routerSSID;
    }

    public void setRouterSSID(String str) {
        this.routerSSID = str;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setOnline(boolean z) {
        this.online = z;
    }

    public Long getId() {
        return this.f476id;
    }

    public void setId(Long l) {
        this.f476id = l;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getThingId() {
        return this.thingId;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public ArrayList<HomeDetails> getHomes() {
        return this.homes;
    }

    public void setHomes(ArrayList<HomeDetails> arrayList) {
        this.homes = arrayList;
    }
}
