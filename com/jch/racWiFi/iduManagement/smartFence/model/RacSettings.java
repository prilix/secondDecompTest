package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.gson.annotations.SerializedName;

public class RacSettings {
    @SerializedName("arrivingToggleOn")
    private Boolean mArrivingToggleOn;
    @SerializedName("leavingToggleOn")
    private Boolean mLeavingToggleOn;
    @SerializedName("racParametersForArriving")
    private RacParameterForArriving racParametersForArriving;
    @SerializedName("racParametersForLeaving")
    private RacParameterForLeaving racParametersForLeaving;

    public Boolean getArrivingToggleOn() {
        return this.mArrivingToggleOn;
    }

    public void setArrivingToggleOn(Boolean bool) {
        this.mArrivingToggleOn = bool;
    }

    public Boolean getLeavingToggleOn() {
        return this.mLeavingToggleOn;
    }

    public void setLeavingToggleOn(Boolean bool) {
        this.mLeavingToggleOn = bool;
    }

    public RacParameterForArriving getRacParametersForArriving() {
        return this.racParametersForArriving;
    }

    public void setRacParametersForArriving(RacParameterForArriving racParameterForArriving) {
        this.racParametersForArriving = racParameterForArriving;
    }

    public RacParameterForLeaving getRacParametersForLeaving() {
        return this.racParametersForLeaving;
    }

    public void setRacParametersForLeaving(RacParameterForLeaving racParameterForLeaving) {
        this.racParametersForLeaving = racParameterForLeaving;
    }
}
