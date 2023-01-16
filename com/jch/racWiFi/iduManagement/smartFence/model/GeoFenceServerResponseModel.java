package com.jch.racWiFi.iduManagement.smartFence.model;

import com.google.gson.annotations.SerializedName;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceData;
import java.util.ArrayList;
import java.util.List;

public class GeoFenceServerResponseModel {
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("familyId")
    private Long familyId;
    @SerializedName("id")

    /* renamed from: id */
    private Integer f469id;
    @SerializedName("lastModifiedAt")
    private String lastModifiedAt;
    @SerializedName("associatedUsers")
    private List<Long> mAssociatedUsers = new ArrayList();
    @SerializedName("geofenceSettings")
    private GeoFenceSettings mGeoFenceSettings = new GeoFenceSettings();
    @SerializedName("racSettings")
    private RacSettings mRacSettings;
    @SerializedName("racIds")
    private List<Long> racIds = new ArrayList();

    public List<Long> getAssociatedRac() {
        return this.racIds;
    }

    public List<Long> getAssociatedUsers() {
        return this.mAssociatedUsers;
    }

    public void setAssociatedUsers(List<Long> list) {
        this.mAssociatedUsers = list;
    }

    public void setId(Integer num) {
        this.f469id = num;
    }

    public Long getFamilyId() {
        return this.familyId;
    }

    public void setFamilyId(Long l) {
        this.familyId = l;
    }

    public GeoFenceSettings getGeoFenceSettings() {
        return this.mGeoFenceSettings;
    }

    public void setGeoFenceSettings(GeoFenceSettings geoFenceSettings) {
        this.mGeoFenceSettings = geoFenceSettings;
    }

    public void setAssociatedRac(List<Long> list) {
        this.racIds = list;
    }

    public RacSettings getRacSettings() {
        return this.mRacSettings;
    }

    public void setRacSettings(RacSettings racSettings) {
        this.mRacSettings = racSettings;
    }

    public GeoFencePair createInstanceFromGeoFenceServerResponseModel() {
        GeoFencePair geoFencePair = new GeoFencePair();
        geoFencePair.setLatLng(getGeoFenceSettings().getLatLng());
        GeoFenceData arrivingGeoFence = geoFencePair.getArrivingGeoFence();
        GeoFenceData leavingGeoFence = geoFencePair.getLeavingGeoFence();
        geoFencePair.familyId = this.familyId;
        arrivingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.ENTER);
        getRacSettings().getArrivingToggleOn().booleanValue();
        arrivingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics((float) getGeoFenceSettings().getArrivingRadius().longValue(), true));
        arrivingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        if (getRacSettings().getRacParametersForArriving() != null) {
            arrivingGeoFence.setModeTempSettings(createModeSettingsForGeoFenceForArriving());
        } else {
            GeoFenceData.ModeTempSettings modeTempSettings = new GeoFenceData.ModeTempSettings();
            modeTempSettings.isSettingsEnabled = false;
            modeTempSettings.sendNullObjectToServer = true;
            arrivingGeoFence.setModeTempSettings(modeTempSettings);
        }
        arrivingGeoFence.setId(this.f469id.intValue());
        arrivingGeoFence.familyID = this.familyId.longValue();
        leavingGeoFence.setFenceTransitionType(GeoFenceData.FenceTransitionType.EXIT);
        getRacSettings().getLeavingToggleOn().booleanValue();
        leavingGeoFence.setGeoFenceDynamics(new GeoFenceData.GeoFenceDynamics((float) getGeoFenceSettings().getLeavingRadius().longValue(), true));
        leavingGeoFence.setGeoFenceUiElements(GeoFenceData.GeoFenceUiElements.DEFAULT);
        if (getRacSettings().getRacParametersForLeaving() != null) {
            leavingGeoFence.setModeTempSettings(createModeSettingsForGeoFenceForLeaving());
        } else {
            GeoFenceData.ModeTempSettings modeTempSettings2 = new GeoFenceData.ModeTempSettings();
            modeTempSettings2.isSettingsEnabled = false;
            modeTempSettings2.sendNullObjectToServer = true;
            leavingGeoFence.setModeTempSettings(modeTempSettings2);
        }
        leavingGeoFence.setId(this.f469id.intValue());
        leavingGeoFence.familyID = this.familyId.longValue();
        geoFencePair.getAssociatedUsers().addAll(getAssociatedUsers());
        geoFencePair.getAssociatedRacs().addAll(getAssociatedRac());
        return geoFencePair;
    }

    private GeoFenceData.ModeTempSettings createModeSettingsForGeoFenceForArriving() {
        RacParameterForArriving racParametersForArriving;
        GeoFenceData.ModeTempSettings modeTempSettings = new GeoFenceData.ModeTempSettings();
        RacSettings racSettings = getRacSettings();
        if (!(racSettings == null || (racParametersForArriving = racSettings.getRacParametersForArriving()) == null)) {
            modeTempSettings.temperature = racParametersForArriving.getTemperature().doubleValue();
            modeTempSettings.powerMode = racParametersForArriving.getPowerMode();
            modeTempSettings.mode = racParametersForArriving.getMode();
            modeTempSettings.fanSpeed = racParametersForArriving.getFanSpeed();
            modeTempSettings.fanSwing = racParametersForArriving.getFanSwing();
            modeTempSettings.humidity = (double) racParametersForArriving.getHumidity();
            modeTempSettings.relativeTemperature = racParametersForArriving.getRelativeTemperature().doubleValue();
            modeTempSettings.sendNullObjectToServer = false;
            modeTempSettings.isSettingsEnabled = racSettings.getArrivingToggleOn().booleanValue();
        }
        return modeTempSettings;
    }

    private GeoFenceData.ModeTempSettings createModeSettingsForGeoFenceForLeaving() {
        RacParameterForLeaving racParametersForLeaving;
        GeoFenceData.ModeTempSettings modeTempSettings = new GeoFenceData.ModeTempSettings();
        RacSettings racSettings = getRacSettings();
        if (!(racSettings == null || (racParametersForLeaving = racSettings.getRacParametersForLeaving()) == null)) {
            modeTempSettings.temperature = racParametersForLeaving.getTemperature().doubleValue();
            modeTempSettings.powerMode = racParametersForLeaving.getPowerMode();
            modeTempSettings.mode = racParametersForLeaving.getMode();
            modeTempSettings.isSettingsEnabled = racSettings.getLeavingToggleOn().booleanValue();
            modeTempSettings.fanSpeed = racParametersForLeaving.getFanSpeed();
            modeTempSettings.fanSwing = racParametersForLeaving.getFanSwing();
            modeTempSettings.humidity = (double) racParametersForLeaving.getHumidity();
            modeTempSettings.relativeTemperature = racParametersForLeaving.getRelativeTemperature().doubleValue();
            modeTempSettings.sendNullObjectToServer = false;
        }
        return modeTempSettings;
    }
}
